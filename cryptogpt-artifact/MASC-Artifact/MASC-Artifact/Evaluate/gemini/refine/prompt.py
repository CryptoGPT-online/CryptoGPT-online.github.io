
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


prompts = {
           "prompt_refine": prompt_refine}

