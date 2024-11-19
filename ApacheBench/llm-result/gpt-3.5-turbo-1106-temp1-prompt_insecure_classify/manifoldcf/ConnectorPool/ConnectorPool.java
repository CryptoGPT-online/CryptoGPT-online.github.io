
package org.apache.manifoldcf.core.connectorpool;

import org.apache.manifoldcf.core.interfaces.*;
import org.apache.manifoldcf.core.system.ManifoldCF;

import java.util.*;
import java.io.*;
import java.lang.reflect.*;


public abstract class ConnectorPool<T extends IConnector>
{
  public static final String _rcsid = "@(#)$Id$";

                              
  
  protected final static String targetCalcLockPrefix = "_POOLTARGET_";
  
  
  protected final String serviceTypePrefix;

  
  protected final Map<String,Pool> poolHash = new HashMap<String,Pool>();

  
  protected final static Random randomNumberGenerator = new Random();
  
  protected ConnectorPool(String serviceTypePrefix)
  {
    this.serviceTypePrefix = serviceTypePrefix;
  }


  protected abstract boolean isInstalled(IThreadContext tc, String className)
    throws ManifoldCFException;
  

  protected abstract boolean isConnectionNameValid(IThreadContext tc, String connectionName)
    throws ManifoldCFException;
  

  protected T createConnectorInstance(IThreadContext threadContext, String className)
    throws ManifoldCFException
  {
    if (!isInstalled(threadContext,className))
      return null;

    try
    {
      Class theClass = ManifoldCF.findClass(className);
      Class[] argumentClasses = new Class[0];
            Constructor c = theClass.getConstructor(argumentClasses);
      Object[] arguments = new Object[0];
      Object o = c.newInstance(arguments);
      try
      {
        return (T)o;
      }
      catch (ClassCastException e)
      {
        throw new ManifoldCFException("Class '"+className+"' does not implement IConnector.");
      }
    }
    catch (InvocationTargetException e)
    {
      Throwable z = e.getTargetException();
      if (z instanceof Error)
        throw (Error)z;
      else if (z instanceof RuntimeException)
        throw (RuntimeException)z;
      else if (z instanceof ManifoldCFException)
        throw (ManifoldCFException)z;
      else
        throw new RuntimeException("Unknown exception type: "+z.getClass().getName()+": "+z.getMessage(),z);
    }
    catch (ClassNotFoundException e)
    {
            return null;
          }
    catch (NoSuchMethodException e)
    {
      throw new ManifoldCFException("No appropriate constructor for IConnector implementation '"+
        className+"'.  Need xxx(ConfigParams).",
        e);
    }
    catch (SecurityException e)
    {
      throw new ManifoldCFException("Protected constructor for IConnector implementation '"+className+"'",
        e);
    }
    catch (IllegalAccessException e)
    {
      throw new ManifoldCFException("Unavailable constructor for IConnector implementation '"+className+"'",
        e);
    }
    catch (IllegalArgumentException e)
    {
      throw new ManifoldCFException("Shouldn't happen!!!",e);
    }
    catch (InstantiationException e)
    {
      throw new ManifoldCFException("InstantiationException for IConnector implementation '"+className+"'",
        e);
    }
    catch (ExceptionInInitializerError e)
    {
      throw new ManifoldCFException("ExceptionInInitializerError for IConnector implementation '"+className+"'",
        e);
    }

  }


  public T[] grabMultiple(IThreadContext threadContext, Class<T> clazz,
    String[] orderingKeys, String[] connectionNames,
    String[] classNames, ConfigParams[] configInfos, int[] maxPoolSizes)
    throws ManifoldCFException
  {
    T[] rval = (T[])Array.newInstance(clazz,classNames.length);
    Map<String,Integer> orderMap = new HashMap<String,Integer>();
    for (int i = 0; i < orderingKeys.length; i++)
    {
      if (orderMap.get(orderingKeys[i]) != null)
        throw new ManifoldCFException("Found duplicate order key");
      orderMap.put(orderingKeys[i],new Integer(i));
    }
    java.util.Arrays.sort(orderingKeys);
    for (int i = 0; i < orderingKeys.length; i++)
    {
      String orderingKey = orderingKeys[i];
      int index = orderMap.get(orderingKey).intValue();
      String connectionName = connectionNames[index];
      String className = classNames[index];
      ConfigParams cp = configInfos[index];
      int maxPoolSize = maxPoolSizes[index];
      try
      {
        T connector = grab(threadContext,connectionName,className,cp,maxPoolSize);
        rval[index] = connector;
      }
      catch (Throwable e)
      {
        while (i > 0)
        {
          i--;
          orderingKey = orderingKeys[i];
          index = orderMap.get(orderingKey).intValue();
          try
          {
            release(threadContext,connectionName,rval[index]);
          }
          catch (ManifoldCFException e2)
          {
          }
        }
        if (e instanceof ManifoldCFException)
          throw (ManifoldCFException)e;
        else if (e instanceof RuntimeException)
          throw (RuntimeException)e;
        else if (e instanceof Error)
          throw (Error)e;
        else
          throw new RuntimeException("Unexpected exception type: "+e.getClass().getName()+": "+e.getMessage(),e);
      }
    }
    return rval;
  }

  public T grab(IThreadContext threadContext, String connectionName,
    String className, ConfigParams configInfo, int maxPoolSize)
    throws ManifoldCFException
  {
        
            while (true)
    {
      Pool p;
      synchronized (poolHash)
      {
        p = poolHash.get(connectionName);
        if (p == null)
        {
          p = new Pool(threadContext, maxPoolSize, connectionName);
          poolHash.put(connectionName,p);
                              p.pollAll(threadContext);
        }
        else
        {
          p.updateMaximumPoolSize(threadContext, maxPoolSize);
        }
      }

      T rval = p.getConnector(threadContext,className,configInfo);
      if (rval != null)
        return rval;
    }

  }

  public void releaseMultiple(IThreadContext threadContext, String[] connectionNames, T[] connectors)
    throws ManifoldCFException
  {
    ManifoldCFException currentException = null;
    for (int i = 0; i < connectors.length; i++)
    {
      String connectionName = connectionNames[i];
      T c = connectors[i];
      try
      {
        release(threadContext,connectionName,c);
      }
      catch (ManifoldCFException e)
      {
        if (currentException == null)
          currentException = e;
      }
    }
    if (currentException != null)
      throw currentException;
  }


  public void release(IThreadContext threadContext, String connectionName, T connector)
    throws ManifoldCFException
  {
        if (connector == null)
      return;

        Pool p;
    synchronized (poolHash)
    {
      p = poolHash.get(connectionName);
    }

    if (p != null)
      p.releaseConnector(threadContext, connector);
    else
    {
            connector.setThreadContext(threadContext);
      try
      {
        connector.disconnect();
      }
      finally
      {
        connector.clearThreadContext();
      }
    }
  }


  public void pollAllConnectors(IThreadContext threadContext)
    throws ManifoldCFException
  {
    
        synchronized (poolHash)
    {
      Iterator<String> iter = poolHash.keySet().iterator();
      while (iter.hasNext())
      {
        String connectionName = iter.next();
        Pool p = poolHash.get(connectionName);
        if (isConnectionNameValid(threadContext,connectionName))
          p.pollAll(threadContext);
        else
        {
          p.releaseAll(threadContext);
          iter.remove();
        }
      }
    }

  }

  public void flushUnusedConnectors(IThreadContext threadContext)
    throws ManifoldCFException
  {
        synchronized (poolHash)
    {
      Iterator<Pool> iter = poolHash.values().iterator();
      while (iter.hasNext())
      {
        Pool p = iter.next();
        p.flushUnused(threadContext);
      }
    }
  }

  public void closeAllConnectors(IThreadContext threadContext)
    throws ManifoldCFException
  {
        synchronized (poolHash)
    {
      Iterator<Pool> iter = poolHash.values().iterator();
      while (iter.hasNext())
      {
        Pool p = iter.next();
        p.releaseAll(threadContext);
        iter.remove();
      }
    }
  }

    
  protected String buildServiceTypeName(String connectionName)
  {
    return serviceTypePrefix + connectionName;
  }
  
  protected String buildTargetCalcLockName(String connectionName)
  {
    return targetCalcLockPrefix + serviceTypePrefix + connectionName;
  }

  protected class Pool
  {
    
    protected boolean isAlive = true;
    
    protected int globalMax;
    
    protected final String serviceTypeName;
    
    protected final String serviceName;
    
    protected final String targetCalcLockName;
    
    protected final List<T> stack = new ArrayList<T>();
    
    protected int numFree = 0;
    
    protected int localMax = 0;
    
    protected int localInUse = 0;

    public Pool(IThreadContext threadContext, int maxCount, String connectionName)
      throws ManifoldCFException
    {
      this.globalMax = maxCount;
      this.targetCalcLockName = buildTargetCalcLockName(connectionName);
      this.serviceTypeName = buildServiceTypeName(connectionName);
            ILockManager lockManager = LockManagerFactory.make(threadContext);
      this.serviceName = lockManager.registerServiceBeginServiceActivity(serviceTypeName, null, null);
    }


    public synchronized void updateMaximumPoolSize(IThreadContext threadContext, int maxPoolSize)
      throws ManifoldCFException
    {
            globalMax = maxPoolSize;
          }


    public synchronized T getConnector(IThreadContext threadContext, String className, ConfigParams configParams)
      throws ManifoldCFException
    {
                  while (isAlive && numFree <= 0)
      {
        try
        {
          wait();
        }
        catch (InterruptedException e)
        {
          throw new ManifoldCFException("Interrupted: "+e.getMessage(),e,ManifoldCFException.INTERRUPTED);
        }
      }
      if (!isAlive)
        return null;
      
                  while (true)
      {
        if (stack.size() == 0)
        {
          T newrc = createConnectorInstance(threadContext,className);
          if (newrc == null)
            return null;
          newrc.connect(configParams);
          stack.add(newrc);
        }
        
                T rc = stack.remove(stack.size()-1);
                                        rc.setThreadContext(threadContext);
                if (!(rc.getClass().getName().equals(className) && rc.getConfiguration().equals(configParams)))
        {
                    try
          {
            rc.disconnect();
          }
          finally
          {
            rc.clearThreadContext();
          }
          continue;
        }
                numFree--;
        return rc;
      }
    }


    public synchronized void releaseConnector(IThreadContext threadContext, T connector)
      throws ManifoldCFException
    {
      if (connector == null)
        return;

            connector.clearThreadContext();
            stack.add(connector);
      numFree++;
                                                      while (stack.size() > 0 && stack.size() > numFree)
      {
                                int j;
        for (j = 0; j < stack.size(); j++)
        {
          if (!stack.get(j).isConnected())
            break;
        }
        T rc;
        if (j == stack.size())
          rc = stack.remove(stack.size()-1);
        else
          rc = stack.remove(j);
        rc.setThreadContext(threadContext);
        try
        {
          rc.disconnect();
        }
        finally
        {
          rc.clearThreadContext();
        }
      }

      notifyAll();
    }

    /** Notify all free connectors.
    */
    public synchronized void pollAll(IThreadContext threadContext)
      throws ManifoldCFException
    {
                                                                                                
      ILockManager lockManager = LockManagerFactory.make(threadContext);
      lockManager.enterWriteLock(targetCalcLockName);
      try
      {
                SumClass sumClass = new SumClass(serviceName);
        lockManager.scanServiceData(serviceTypeName, sumClass);
                
        int numServices = sumClass.getNumServices();
        if (numServices == 0)
          return;
        int globalTarget = sumClass.getGlobalTarget();
        int globalInUse = sumClass.getGlobalInUse();
        int maximumTarget = globalMax - globalTarget;
        if (maximumTarget > globalMax - globalInUse)
          maximumTarget = globalMax - globalInUse;
        if (maximumTarget < 0)
          maximumTarget = 0;
        
                int fairTarget = globalMax / numServices;
        int remainder = globalMax % numServices;
                if (randomNumberGenerator.nextInt(numServices) < remainder)
          fairTarget++;
        
                int localInUse = localMax - numFree;              for (T rc : stack)
        {
                    rc.setThreadContext(threadContext);
          try
          {
            rc.poll();
            if (rc.isConnected())
              localInUse++;                 }
          finally
          {
            rc.clearThreadContext();
          }
        }
        int optimalTarget = localMax;
        if (localMax > localInUse)
          optimalTarget--;
        else
        {
                    int increment = globalMax >> 2;
          if (increment == 0)
            increment = 1;
          optimalTarget += increment;
        }
        
        
                int target = maximumTarget;
        if (target > fairTarget)
          target = fairTarget;
        if (target > optimalTarget)
          target = optimalTarget;
        
                                        
        lockManager.updateServiceData(serviceTypeName, serviceName, pack(target, localInUse));
        
                if (target == localMax)
          return;
                        localInUse = localMax - numFree;
        localMax = target;
                        numFree = localMax - localInUse;
        notifyAll();
      }
      finally
      {
        lockManager.leaveWriteLock(targetCalcLockName);
      }
      
            while (stack.size() > 0 && stack.size() > numFree)
      {
                                int j;
        for (j = 0; j < stack.size(); j++)
        {
          if (!stack.get(j).isConnected())
            break;
        }
        T rc;
        if (j == stack.size())
          rc = stack.remove(stack.size()-1);
        else
          rc = stack.remove(j);
        rc.setThreadContext(threadContext);
        try
        {
          rc.disconnect();
        }
        finally
        {
          rc.clearThreadContext();
        }
      }

    }

    /** Flush unused connectors.
    */
    public synchronized void flushUnused(IThreadContext threadContext)
      throws ManifoldCFException
    {
      while (stack.size() > 0)
      {
                T rc = stack.remove(stack.size()-1);
        rc.setThreadContext(threadContext);
        try
        {
          rc.disconnect();
        }
        finally
        {
          rc.clearThreadContext();
        }
      }
    }

    /** Release all free connectors.
    */
    public synchronized void releaseAll(IThreadContext threadContext)
      throws ManifoldCFException
    {
      flushUnused(threadContext);
      
            if (isAlive)
      {
        isAlive = false;
        notifyAll();
        ILockManager lockManager = LockManagerFactory.make(threadContext);
        lockManager.endServiceActivity(serviceTypeName, serviceName);
      }
    }

  }

  protected static class SumClass implements IServiceDataAcceptor
  {
    protected final String serviceName;
    protected int numServices = 0;
    protected int globalTargetTally = 0;
    protected int globalInUseTally = 0;
    
    public SumClass(String serviceName)
    {
      this.serviceName = serviceName;
    }
    
    @Override
    public boolean acceptServiceData(String serviceName, byte[] serviceData)
      throws ManifoldCFException
    {
      numServices++;

      if (!serviceName.equals(this.serviceName))
      {
        globalTargetTally += unpackTarget(serviceData);
        globalInUseTally += unpackInUse(serviceData);
      }
      return false;
    }

    public int getNumServices()
    {
      return numServices;
    }
    
    public int getGlobalTarget()
    {
      return globalTargetTally;
    }
    
    public int getGlobalInUse()
    {
      return globalInUseTally;
    }
    
  }
  
  protected static int unpackTarget(byte[] data)
  {
    if (data == null || data.length != 8)
      return 0;
    return (((int)data[0]) & 0xff) +
      ((((int)data[1]) << 8) & 0xff00) +
      ((((int)data[2]) << 16) & 0xff0000) +
      ((((int)data[3]) << 24) & 0xff000000);
  }

  protected static int unpackInUse(byte[] data)
  {
    if (data == null || data.length != 8)
      return 0;
    return (((int)data[4]) & 0xff) +
      ((((int)data[5]) << 8) & 0xff00) +
      ((((int)data[6]) << 16) & 0xff0000) +
      ((((int)data[7]) << 24) & 0xff000000);
  }

  protected static byte[] pack(int target, int inUse)
  {
    byte[] rval = new byte[8];
    rval[0] = (byte)(target & 0xff);
    rval[1] = (byte)((target >> 8) & 0xff);
    rval[2] = (byte)((target >> 16) & 0xff);
    rval[3] = (byte)((target >> 24) & 0xff);
    rval[4] = (byte)(inUse & 0xff);
    rval[5] = (byte)((inUse >> 8) & 0xff);
    rval[6] = (byte)((inUse >> 16) & 0xff);
    rval[7] = (byte)((inUse >> 24) & 0xff);
    return rval;
  }
  
}
