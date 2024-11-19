import os
import glob
import openai
import tiktoken
import time
from prompt import prompts
from config import Config


gpt_series = {'gpt-3.5': "gpt-3.5-turbo", 'gpt-4': "gpt-4", 'gpt-4-turbo': "gpt-4-1106-preview", 'gpt-4-32k': "gpt-4-32k", "gpt-4-newest":"gpt-4-turbo", "gpt-4o-mini": "gpt-4o-mini","gpt-4o": "gpt-4o"}

config = Config(1, gpt_series['gpt-4o'], 5, "prompt_insecure_practice")


# config = Config(0.7, gpt_series['gpt-4-turbo'], 5, "prompt_insecure")

def num_tokens_from_messages(messages, model="gpt-3.5-turbo"):
    """Returns the number of tokens used by a list of messages."""
    try:
        encoding = tiktoken.encoding_for_model(model)
    except KeyError:
        print("Warning: model not found. Using cl100k_base encoding.")
        encoding = tiktoken.get_encoding("cl100k_base")
    if model == "gpt-3.5-turbo-1106":
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
        tokens_per_message = 3
        tokens_per_name = 1
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

basedir = "/Users/your_user_name/Desktop/paper/cryptogpt/codex/MASC-Artifact/MASC-Artifact/minimal_flaws/normalize"

for subdir in os.listdir(basedir):
    thisdir = os.path.join(basedir,subdir)
    print(thisdir)
    paths = glob.glob(thisdir+"/**/*.java", recursive=True)
    for path in paths:
        print(path)
        # if "Corrected" in path:
        #     continue
        text = open(path).readlines()
        print("".join(text[:]))
        messages = prompts[config.prompt].copy()
        messages.append({"role": "user", "content": "".join(text[:])})
        # for m in messages:
        #     print(m["content"])
        # exit(0)
        print(f"{num_tokens_from_messages(messages, MODEL)}")

        # a = input("Are you sure?")
        # if a != "yes":
        #     exit(0)

        hyper = config.temperature
        if hyper == 0:
            resultdir = "/Users/your_user_name/Desktop/paper/cryptogpt/codex/MASC-Artifact/MASC-Artifact/Evaluate/result/{}-temp{}-{}/".format\
                            (config.model, hyper, config.prompt) + path.split("normalize/")[1][:-5]
            if not os.path.isdir(resultdir):
                os.system("mkdir -p "+resultdir)
                os.system("cp "+path+" "+resultdir)
            else:
                if len(os.listdir(resultdir)) >= 2:
                    continue
            for i in range(1):
                response = openai.ChatCompletion.create(
                    model=MODEL,
                    messages=messages,
                    temperature=hyper,
                )
                with open(resultdir+"/"+str(i)+".json","w+") as f:
                    f.write(response['choices'][0]['message']['content'])
        else:
            resultdir = "/Users/your_user_name/Desktop/paper/cryptogpt/codex/MASC-Artifact/MASC-Artifact/Evaluate/result/{}-temp{}-{}/".format \
                            (config.model, hyper, config.prompt) + path.split("normalize/")[1][:-5]
            if not os.path.isdir(resultdir):
                os.system("mkdir -p " + resultdir)
                os.system("cp " + path + " " + resultdir)
            else:
                if len(os.listdir(resultdir)) >= config.query_times+1:
                    continue
            for i in range(config.query_times):
                if os.path.isfile(resultdir + "/" + str(i) + ".json"):
                    continue
                response = []
                while not response:
                    try:
                        response = openai.ChatCompletion.create(
                            model=MODEL,
                            messages=messages,
                            temperature=hyper,
                        )
                    except openai.error.OpenAIError as e:
                        time.sleep(5)

                with open(resultdir + "/" + str(i) + ".json", "w+") as f:
                    f.write(response['choices'][0]['message']['content'])
                time.sleep(2)