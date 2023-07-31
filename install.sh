#!/usr/bin/bash

mkdir -p ~/.tmp/
cd ~/.tmp/
rm -rf givefuck
git clone --depth 1 https://github.com/mnemotechnician/givefuck
cd givefuck

bash ./gradlew --console verbose publish

artifactPath=./app/build/libs/givefuck

path=~/.local/bin/
mkdir -p $path

echo "copying the executable into $path."
sudo cp $artifactPath $path
chmod +x $path/givefuck

echo "installed successfully, removing the temp directory"
rm -r ~/.tmp/givefuck

echo "Make sure ~/.local/bin is in your \$PATH. If not, add it there or specify the full path to run this program."
