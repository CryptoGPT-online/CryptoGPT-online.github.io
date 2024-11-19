import os
import glob
import openai
import tiktoken
import time
from prompt import prompts
from config import Config

# Used to securely store your API key


gpt_series = {'gpt-3.5': "gpt-3.5-turbo-1106", 'gpt-4': "gpt-4", 'gpt-4-turbo': "gpt-4-1106-preview", 'gpt-4-32k': "gpt-4-32k", "gpt-4o": "gpt-4o"}

config = Config(1, gpt_series['gpt-4o'], 5, "prompt_insecure_refine")

def num_tokens_from_messages(messages, model="gpt-3.5-turbo"):
    """Returns the number of tokens used by a list of messages."""
    try:
        encoding = tiktoken.encoding_for_model(model)
    except KeyError:
        print("Warning: model not found. Using cl100k_base encoding.")
        encoding = tiktoken.get_encoding("cl100k_base")
    if model == "gpt-3.5-turbo":
        print("Warning: gpt-3.5-turbo may change over time. Returning num tokens assuming gpt-3.5-turbo-0301.")
        return num_tokens_from_messages(messages, model="gpt-3.5-turbo-0301")
    elif model == "gpt-4" or model == "gpt-4-1106-preview":
        print("Warning: gpt-4 may change over time. Returning num tokens assuming gpt-4-0314.")
        return num_tokens_from_messages(messages, model="gpt-4-0613")
    elif model == "gpt-3.5-turbo-0301":
        tokens_per_message = 4  # every message follows <|start|>{role/name}\n{content}<|end|>\n
        tokens_per_name = -1  # if there's a name, the role is omitted
    elif model == "gpt-4-0613":
        tokens_per_message = 3
        tokens_per_name = 1
    else:
        raise NotImplementedError(
            f"""num_tokens_from_messages() is not implemented for model {model}. See https://github.com/openai/openai-python/blob/main/chatml.md for information on how messages are converted to tokens.""")
    num_tokens = 0
    for message in messages:
        num_tokens += tokens_per_message
        for key, value in message.items():
            num_tokens += len(encoding.encode(value))
            if key == "name":
                num_tokens += tokens_per_name
    num_tokens += 3  # every reply is primed with <|start|>assistant<|message|>
    return num_tokens

MODEL = config.model

openai.api_key = ""


basedir = "/Users/your_user_name/Desktop/paper/cryptogpt/codex/MASC-Artifact/MASC-Artifact/Evaluate/result/gpt-4o-temp1-prompt_insecure_practice"

for subdir in os.listdir(basedir):
    thisdir = os.path.join(basedir, subdir)
    print(thisdir)
    paths = glob.glob(thisdir + "/**/*.java", recursive=True)
    for path in paths:
        print(path)
        if os.path.isfile("/".join(path.replace("result","refine").split("/")[:-2])+".json"):
            continue
        if os.path.isfile("/".join(path.split("/")[:-1]) + "/refine.json"):
            continue
        text = open(path).readlines()
        messages = prompts[config.prompt].copy()

        jsons = glob.glob("/".join(path.split("/")[:-1]) + "/*.json", recursive=True)
        text.append("---misuse lists begin---")
        for j in jsons:
            text.extend(open(j, "r").readlines())
        text.append("---misuse lists end---")
        print("".join(text[:]))
        messages.append({"role": "user", "content": "".join(text[:])})
        # a = input("Are you sure?")
        # if a != "yes":
        #     exit(0)

        hyper = 1
        if hyper == 1:
            for i in range(1):
                response=[]
                while not response:
                    try:
                        response = openai.ChatCompletion.create(
                            model=MODEL,
                            messages=messages,
                            temperature=hyper,
                        )
                    except openai.error.OpenAIError as e:
                        print(e)
                        time.sleep(5)
                with open("/".join(path.split("/")[:-1]) + "/refine.json", "w+") as f:
                    f.write(response['choices'][0]['message']['content'])
        else:
            resultdir = "/Users/your_user_name/Desktop/paper/cryptogpt/codex/cryptoapi-bench-master/src/main/java/org/cryptoapi/result/{}-temp{}-{}/".format \
                            (config.model, hyper, config.prompt) + path.split("normalize/")[1][:-5]
            if not os.path.isdir(resultdir):
                os.system("mkdir -p " + resultdir)
                os.system("cp " + path + " " + resultdir)
            else:
                if len(os.listdir(resultdir)) >= config.query_times + 1:
                    continue
            for i in range(config.query_times):
                if os.path.isfile(resultdir + "/" + str(i) + ".json"):
                    continue
                response = []
                while not response:
                    try:
                        response = model.generate_content(messages)
                        with open(resultdir + "/" + str(i) + ".json", "w+") as f:
                            f.write(response.text)
                    except Exception as e:
                        print(e)
                time.sleep(2)
