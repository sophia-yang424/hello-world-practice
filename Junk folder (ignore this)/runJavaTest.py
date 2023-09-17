import subprocess

process = subprocess.Popen(["java", "Hello.java"])
process.wait()

print(process.stdout)