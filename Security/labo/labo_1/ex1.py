try:
  logs = open("access.log").read()
except FileNotFoundError:
  print("File not found")

for line in logs.split("\n"):
    print("ip:", line.split(" ")[0])