prompt_insecure = [{"role": "user", "parts": ["You are an expert Java software security auditor, please check "
                                              "whether there is any crypto API misuses (or insecure use of crypto API) "
                                              "in below code."
                                              "Reply in a bare JSON format as below:"
                                              "[{\"misuse\": \"misuse type(e.g. Broken Crypto Algorithm)\", \"vulnerable_code\": \"misuse code snippets\",\"description\": \"the description of why there is a misuse\","
                                              " \"recommendation\": \"suggestions for fixing misuse\"},{\"misuse\":...}]."
                                              "Your consideration should include but not limit to misuses "
                                              "related to CWE-256, 295, 297, 321, 326, 327, 338, 547, 650, 757, 759 and 760. "]
                    }, {"role": "model", "parts": ["Got it. Please provide the code."]}]

prompt_insecure_classify = [
    {"role": "user", "parts": ["You are an expert Java software security auditor, please check "
                               "whether there is any crypto API misuses (or insecure use of crypto API) "
                               "in below code."
                               "If you identify ""any misuses, you should classify them to below categories:\n"
                               "CWE-327: Use of a Broken or Risky Cryptographic Algorithm.\n"
                               "CWE-295: Improper Certificate Validation. \n"
                               "CWE-330: Use of Insufficiently Random Values.\n"
                               "CWE-326: Inadequate Encryption Strength. (e.g. Low key/salt length or iteration number, static password/salt...)\n"
                               "CWE-798: Use of Hardcoded Credentials.\n"
                               "CWE-757: Selection of Less-Secure Algorithm During Negotiation.\n"
                               "Reply in a bare JSON format as below:"
                               "[{\"misuse\": \"misuse type (e.g. CWE-327: Use of a Broken or Risky Cryptographic Algorithm.)\", \"vulnerable_code\": \"misuse code snippets\",\"description\": \"the description of why there is a misuse\","
                               " \"recommendation\": \"suggestions for fixing misuse\"},{\"misuse\":...}]"]
     # "Your consideration should include misuses related to CWE-256, 295, 297, 321, 327, 338, 547, 650, 326, 547, 759, 757 and 760. "
     }, {"role": "model", "parts": ["Got it. Please provide the code."]}]

brute_insecure = [
    {"role": "user", "parts": ["You are an expert Java software security auditor, "
                               "please check whether there are any crypto API misuses "
                               "(or insecure use of crypto API) in below classes."
                               "Reply in a bare JSON format as below:{{\"misuse\": \"misuse type\", "
                               "\"vulnerable_method\": \"the class name and method name of vulnerable code\","
                               "\"vulnerable_code\": \"the vulnerable code snippets\", "
                               "\"description\": \"the description of misuse\","
                               " \"recommendation\": \"suggestions for fixing misuse\"},{\"misuse\":...}}"
                               ". Consideration includes but not limited to CWE-256, 295, 297, 321, 327, 338, 547, 650, 326, 547, 759, 757 and 760. "]
     # "Your consideration should include misuses related to CWE-256, 295, 297, 321, 327, 338, 547, 650, 326, 547, 759, 757 and 760. "
     }, {"role": "model", "parts": ["Got it. I will reply in JSON format. Please provide the code of classes."]}]

brute_classify = [
    {"role": "user", "parts": ["You are an expert Java software security auditor, please check "
                               "whether there are any crypto API misuses (or insecure use of crypto API) "
                               "in below classes."
                               "If you identify any misuses, you should classify them to below categories:\n"
                               "CWE-327: Use of a Broken or Risky Cryptographic Algorithm.\n"
                               "CWE-295: Improper Certificate Validation. \n"
                               "CWE-330: Use of Insufficiently Random Values.\n"
                               "CWE-326: Inadequate Encryption Strength. (e.g. Low key/salt length or iteration number, static password/salt...)\n"
                               "CWE-798: Use of Hardcoded Credentials.\n"
                               "CWE-757: Selection of Less-Secure Algorithm During Negotiation.\n"
                               "Reply in a bare JSON format as below:"
                               "[{\"misuse\": \"misuse type (e.g. CWE-327: Use of a Broken or Risky Cryptographic Algorithm.)\","
                               "\"vulnerable_method\": \"the class name and method name of vulnerable code\","
                               "\"vulnerable_code\": \"the vulnerable code snippets\", "
                               "\"description\": \"the description of misuse\","
                               " \"recommendation\": \"suggestions for fixing misuse\"},{\"misuse\":...}]"]
     # "Your consideration should include misuses related to CWE-256, 295, 297, 321, 327, 338, 547, 650, 326, 547, 759, 757 and 760. "
     }, {"role": "model", "parts": ["Got it. I will reply in JSON format. Please provide the code of classes."]}]

brute_practice = [
    {"role": "user", "parts": ["You are an expert Java software security auditor, please check "
                               "whether there are any crypto API misuses (or insecure use of crypto API) "
                               "in below classes."
                               "If you identify any misuses, you should classify them to below categories:\n"
                               "CWE-327: Use of a Broken or Risky Cryptographic Algorithm.\n"
                               "CWE-295: Improper Certificate Validation. \n"
                               "CWE-330: Use of Insufficiently Random Values.\n"
                               "CWE-326: Inadequate Encryption Strength. (e.g. Low key/salt length or iteration number, static password/salt...)\n"
                               "CWE-798: Use of Hardcoded Credentials.\n"
                               "CWE-757: Selection of Less-Secure Algorithm During Negotiation.\n"
                               "Reply in a bare JSON format as below:"
                               "[{\"misuse\": \"misuse type (e.g. CWE-327: Use of a Broken or Risky Cryptographic Algorithm.)\","
                               "\"vulnerable_method\": \"the class name and method name of vulnerable code\","
                               "\"vulnerable_code\": \"the vulnerable code snippets\", "
                               "\"description\": \"the description of misuse\","
                               " \"recommendation\": \"suggestions for fixing misuse\"},{\"misuse\":...}]."
                               "Don't talk about best practice."]
     # "Your consideration should include misuses related to CWE-256, 295, 297, 321, 327, 338, 547, 650, 326, 547, 759, 757 and 760. "
     }, {"role": "model", "parts": ["Got it. I will reply in JSON format. Please provide the code of classes."]}]

prompt_refine = [
    {"role": "user",
     "parts": ["You are an expert Java software security auditor. "
               "You will be provided with Java code with several lists "
               "describing potential cryptographic API misuses in the code. "
                "Please review the code with misuse reports carefully, perform contextual analysis, "
               "to determine if any reported misuses are not applicable(i.e. false positives) and explain the reason. "
               "Based on your analysis, provide an optimized list of "
               "cryptographic API misuses in the following format: "
               "[{\"misuse category\": \"the category of misuse\", "
               "\"vulnerable_method\": \"the class name and method name of vulnerable code\", "
               "\"vulnerable_code\": \"the vulnerable code snippets\", "
               "\"description\": \"the description of misuse\", "
               "\"recommendation\": \"suggestions for fixing misuse\"}, {...}]."]
     },
    {"role": "model", "parts": [
        "Understood. Please provide the code and the initial lists of misuses. "
        "I will identify any false positives in the lists you provide and "
        "explain my judgement. Based on the initial lists, I will then remove the false positive reports to"
        " give you an optimized list of misuses in the specified JSON format, "
        "taking the context into account. "
        "I will arrange the output format as ### False Positive Misuse ###\n, ### Optimized Misuse JSON###\n"]
     }
]

prompt_refine_noclass = [
    {"role": "user",
     "parts": ["You are an expert Java software security auditor. "
               "You will be provided with Java code that contains several lists "
               "describing potential cryptographic API misuses. "
               "Please review the code with misuse reports carefully, considering the context, "
               "to determine if any reported misuses are not applicable(i.e. false positives) and explain the reason. "
               "Based on your analysis, provide an optimized list of "
               "cryptographic API misuses in the following JSON format: "
               "[{\"misuse category\": \"the category of misuse\", "
               "\"vulnerable_method\": \"the class name and method name of vulnerable code\", "
               "\"vulnerable_code\": \"the vulnerable code snippets\", "
               "\"description\": \"the description of misuse\", "
               "\"recommendation\": \"suggestions for fixing misuse\"}, {...}]."]
     },
    {"role": "model", "parts": [
        "Understood. Please provide the code and the initial lists of misuses. "
        "I will identify any false positives in the lists you provide and "
        "explain my judgement. Based on the initial lists, I will then remove the false positive reports to"
        " give you an optimized list of misuses in the specified JSON format, "
        "taking the context into account. "
        "I will arrange the output format as ### False Positive Misuse ###\n, ### Optimized Misuse JSON###\n"]
     }
]

prompts = {"prompt_insecure": prompt_insecure,
           "prompt_insecure_classify": prompt_insecure_classify,
           "prompt_refine_noclass":prompt_refine_noclass,

           "brute_insecure": brute_insecure,
           "brute_classify": brute_classify,
           "prompt_refine": prompt_refine}

