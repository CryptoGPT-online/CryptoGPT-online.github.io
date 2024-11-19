# The artifacts of CryptoGPT

## Data Structure

### Cryptoapi-bench-master - the scripts and results in CryptoAPIBench
- src - the original and refined test cases in CryptoAPIBench
- Evaluate - the scripts for evaluation
    - try*.py - The script that invokes LLMs for analysis.
    - refine*.py - The script that invokes LLMs for code&analysis validation.
    - prompt.py - The prompts used for evaluation
- llm-result - LLM-generated analysis reports for cryptographic misuses.
- sat-result - sat-generated analysis reports for cryptographic misuses.

### MASC-Artifact - the scripts and results in MASC benchmark
- MASC-Artifact - the original and refined test cases in CryptoAPIBench
- Evaluate - the scripts for evaluation, this directory is similar as that in Cryptoapi-bench-master
- llm-result - LLM-generated analysis reports for cryptographic misuses. 
- sat-result - sat-generated analysis reports for cryptographic misuses.

### ApacheBench - the scripts and results in ApacheCrypto benchmark
    
- apache - the file-level test cases in every Apache projects
- Evaluate - the scripts for evaluation, this directory is similar as that in Cryptoapi-bench-master
- result - LLM-generated analysis reports for cryptographic misuses.