
prompt_insecure_practice = [{"role": "user", "parts": ["You are an expert Java software security auditor, please check "
                                              "whether there is any crypto API misuses (or insecure use of crypto API) "
                                              "in below code. Don't talk about best practice, only focus on security issues."
                                              "Reply in a bare JSON format as below:"
                                              "[{\"misuse\": \"misuse type(e.g. Broken Crypto Algorithm)\", \"vulnerable_code\": \"misuse code snippets\",\"description\": \"the description of why there is a misuse\","
                                              " \"recommendation\": \"suggestions for fixing misuse\"},{\"misuse\":...}]."
                                              "Your consideration should include but not limit to misuses "
                                              "related to CWE-256, 295, 297, 321, 326, 327, 338, 547, 650, 757, 759 and 760. "]
                    }, {"role": "model", "parts": ["Got it. Please provide the code."]}]

prompt_classify_practice = [
    {"role": "user", "parts": ["You are an expert Java software security auditor, please check "
                               "whether there is any crypto API misuses (or insecure use of crypto API) "
                               "in below code. Don't talk about best practice, only focus on security issues."
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



prompts = {
           "prompt_insecure_practice": prompt_insecure_practice,
           "prompt_classify_practice": prompt_classify_practice}
