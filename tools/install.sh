#!/usr/bin/bash

cd ~/.tmp/
git clone --depth 1 https://github.com/mnemotechnician/givefuck
cd givefuck

bash ./gradlew publish

artifactPath=./app/build/libs/givefuck

#install our application into the first applicable path

pathes=$( echo $PATH | tr ":" "\n" )
path=${pathes[0]}/givefuck

echo copying executable into $path
cp $artifactPath $path
chmod +x $path

echo "installed successfully, removing the temp directory"
rm -r ~/.tmp/givefuck
