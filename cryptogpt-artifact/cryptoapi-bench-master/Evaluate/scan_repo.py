import os
import glob
import openai
import tiktoken
import time
from prompt import prompts
from config import Config

gpt_series = {'gpt-3.5': "gpt-3.5-turbo", 'gpt-4': "gpt-4", 'gpt-4-turbo': "gpt-4-1106-preview", 'gpt-4-32k': "gpt-4-32k"}

config = Config(1, gpt_series['gpt-4-turbo'], 5, "prompt_insecure_classify_python")

# config = Config(0.7, gpt_series['gpt-4-turbo'], 5, "prompt_insecure")

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
    elif model == "gpt-4" or model == "gpt-4-1106-preview" or model == "gpt-4-32k":
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

basedir = "scan_files"

while 1:
    text = open("temp.py", encoding ='utf-8').readlines()
    messages = prompts[config.prompt].copy()
    messages.append({"role": "user", "content": "".join(text[:])})
    print(messages)
    print(f"{num_tokens_from_messages(messages, MODEL)}")
    response = []
    while not response:
        try:
            response = openai.ChatCompletion.create(
                model=MODEL,
                messages=messages,
                temperature=1,
            )
        # except:
        #     time.sleep(5)
        except openai.error.OpenAIError as e:
            time.sleep(3)
    print(response['choices'][0]['message']['content'])
    break
