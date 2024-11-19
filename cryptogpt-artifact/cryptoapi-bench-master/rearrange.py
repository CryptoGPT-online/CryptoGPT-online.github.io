import os
import re

# basedir = "/Users/your_user_name/Desktop/paper/cryptogpt/codex/cryptoapi-bench-master/src/main/java/org/cryptoapi/bench"
# newdir = "/Users/your_user_name/Desktop/paper/cryptogpt/codex/cryptoapi-bench-master/src/main/java/org/cryptoapi/rearrange"
#
# for category in os.listdir(basedir):
#     subdir = os.path.join(basedir, category)
#     if os.path.isfile(subdir):
#         continue
#     for tc in os.listdir(subdir):
#         if "ABMC" in tc:
#             os.system("mkdir -p "+os.path.join(newdir, "multi/"+category))
#             os.system("cp "+os.path.join(subdir, tc)+" "+os.path.join(newdir, "multi/"+category))


newdir = "/Users/your_user_name/Desktop/paper/cryptogpt/codex/cryptoapi-bench-master/src/main/java/org/cryptoapi/rearrange"


newdir2 = "/Users/your_user_name/Desktop/paper/cryptogpt/codex/cryptoapi-bench-master/src/main/java/org/cryptoapi/normalize"

for subdir in os.listdir(newdir):
    if subdir != "multi":
        continue
    subpath = os.path.join(newdir, subdir)
    for cate in os.listdir(subpath):
        catedir = os.path.join(subpath, cate)
        newcatedir = catedir.replace("rearrange", "normalize")
        os.system("mkdir -p "+newcatedir)
        for file in os.listdir(catedir):
            new = []
            filepath = os.path.join(catedir, file)
            print(filepath)
            text = open(filepath, "r").readlines()
            if "ABMC" in filepath:
                for line in text:
                    if line.startswith("package") or line.startswith("import"):
                        continue
                    if line.startswith("public class "):
                        new.append(line[13:line.index("ABMC")])
                    # elif line.startswith("class "):
                    #     new.append(line[6:-3])
            text = open(filepath, "r").read()
            for i in new:
                text = re.sub(i, i[0] + i[-1], text, flags=re.IGNORECASE)
            with open(os.path.join(newcatedir, file), "w") as f:
                f.write(text.split("\n")[0] + '\n' + text[text.index("public"):])
