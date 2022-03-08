#!/usr/bin/bash

cd ~/.tmp/
rm -rf givefuck
git clone --depth 1 https://github.com/mnemotechnician/givefuck
cd givefuck

bash ./gradlew --console verbose publish

artifactPath=./app/build/libs/givefuck

#install our application into the first applicable path

pathes=$( echo $PATH | tr ":" "\n" )
path=${pathes[0]}/givefuck

echo "copying the executable into $path"
cp $artifactPath $path
chmod +x $path

echo "installed successfully, removing the temp directory"
rm -rf ~/.tmp/givefuck
