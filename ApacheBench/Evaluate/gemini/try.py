import pathlib
import textwrap
import os
import google.generativeai as genai
import glob
from prompt import prompts
import time

# Used to securely store your API key

from IPython.display import display
from IPython.display import Markdown
from google.generativeai.types import HarmCategory, HarmBlockThreshold


class Config:
    def __init__(self, temperature=0, model="", query_times=5, prompt="prompt_insecure_classify"):
        self.temperature = temperature
        self.model = model
        self.query_times = query_times
        self.prompt = prompt


def to_markdown(text):
    text = text.replace('•', '  *')
    return Markdown(textwrap.indent(text, '> ', predicate=lambda _: True))


config = Config(1, "models/gemini-1.5-pro")

GOOGLE_API_KEY = ""

genai.configure(api_key=GOOGLE_API_KEY, transport="rest")

model = genai.GenerativeModel(config.model)

basedir = "/Users/your_user_name/Downloads/apache"

for subdir in os.listdir(basedir):
    thisdir = os.path.join(basedir, subdir + "/single")
    print(thisdir)
    paths = glob.glob(thisdir + "/*.java")
    for path in paths:
        print(path)
        # if "Corrected" in path:
        #     continue
        text = open(path).readlines()
        print("".join(text[:]))
        messages = prompts[config.prompt].copy()
        messages.append({"role": "user", "parts": ["".join(text[:])]})

        # a = input("Are you sure?")
        # if a != "yes":
        #     exit(0)

        hyper = config.temperature
        if hyper == 0:
            resultdir = "/Users/your_user_name/Desktop/paper/cryptogpt/codex/ApacheBench/result/{}-temp{}-{}/".format \
                            (config.model, hyper, config.prompt) + subdir + "/" + path.split("single/")[1][:-5]
            if not os.path.isdir(resultdir):
                os.system("mkdir -p " + resultdir)
                os.system("cp " + path + " " + resultdir)
            else:
                if len(os.listdir(resultdir)) >= 2:
                    continue
            for i in range(1):
                response = model.generate_content(messages)
                with open(resultdir + "/" + str(i) + ".json", "w+") as f:
                    f.write(response.text)
        else:
            resultdir = "/Users/your_user_name/Desktop/paper/cryptogpt/codex/ApacheBench/result/{}-temp{}-{}/".format \
                            (config.model, hyper, config.prompt) + subdir + "/" + path.split("single/")[1][:-5]
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
