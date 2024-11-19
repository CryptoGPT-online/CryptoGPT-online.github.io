import pathlib
import textwrap
import os
import google.generativeai as genai
import glob
from prompt import prompts
import time
from google.generativeai.types import HarmCategory, HarmBlockThreshold
# Used to securely store your API key

from IPython.display import display
from IPython.display import Markdown


class Config:
    def __init__(self, temperature=0, model="", query_times=1, prompt="prompt_refine"):
        self.temperature = temperature
        self.model = model
        self.query_times = query_times
        self.prompt = prompt


def to_markdown(text):
    text = text.replace('•', '  *')
    return Markdown(textwrap.indent(text, '> ', predicate=lambda _: True))


config = Config(1, "gemini-1.5-pro")

GOOGLE_API_KEY = ""

genai.configure(api_key=GOOGLE_API_KEY, transport="rest")

model = genai.GenerativeModel(config.model)


basedir = "/Users/your_user_name/Desktop/paper/cryptogpt/codex/cryptoapi-bench-master/src/main/java/org/cryptoapi/result/gemini-1.5-pro-temp1-prompt_insecure_classify"

for subdir in os.listdir(basedir):
    if subdir not in ["correct", "basic", "fp", "interprocedural", "pathsensitive", "fieldsensitive"][1:]:
        continue
    thisdir = os.path.join(basedir, subdir)
    print(thisdir)
    paths = glob.glob(thisdir + "/**/*.java", recursive=True)
    for path in paths:
        print(path)
        if os.path.isfile(basedir.replace("result","refine") + "/" + path.split("/")[-4] + "/" + path.split("/")[-1][:-3] + "son"):
            continue
        text = open(path).readlines()
        messages = prompts[config.prompt].copy()
        jsons = glob.glob("/".join(path.split("/")[:-1]) + "/*.json", recursive=True)
        text.append("---misuses list begin:---\n")
        for j in jsons:
            text.extend(open(j, "r").readlines())
        text.append("\n---misuses lists end---")
        print("".join(text[:]))
        messages.append({"role": "user", "parts": ["".join(text[:])]})
        # a = input("Are you sure?")
        # if a != "yes":
        #     exit(0)
        hyper = 0
        if hyper == 0:
            if os.path.isfile("/".join(path.split("/")[:-1])+"/refine.json"):
                continue
            for i in range(1):
                try:
                    response = model.generate_content(messages, safety_settings={
                        HarmCategory.HARM_CATEGORY_HATE_SPEECH: HarmBlockThreshold.BLOCK_NONE,
                        HarmCategory.HARM_CATEGORY_HARASSMENT: HarmBlockThreshold.BLOCK_NONE,
                        HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT: HarmBlockThreshold.BLOCK_NONE,
                        HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT: HarmBlockThreshold.BLOCK_NONE,
                    })
                    with open("/".join(path.split("/")[:-1]) + "/refine.json", "w+") as f:
                        f.write(response.text)
                except Exception as e:
                    print(e)
                    continue
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
                        response = model.generate_content(messages, safety_settings={
                            HarmCategory.HARM_CATEGORY_HATE_SPEECH: HarmBlockThreshold.BLOCK_NONE,
                            HarmCategory.HARM_CATEGORY_HARASSMENT: HarmBlockThreshold.BLOCK_NONE,
                            HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT: HarmBlockThreshold.BLOCK_NONE,
                            HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT: HarmBlockThreshold.BLOCK_NONE,
                        })
                        with open(resultdir + "/" + str(i) + ".json", "w+") as f:
                            f.write(response.text)
                    except Exception as e:
                        print(e)
                time.sleep(2)
