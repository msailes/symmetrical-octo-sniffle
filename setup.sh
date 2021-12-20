#!/usr/bin/env bash

set -e

apt-get update -y
apt-get install -y python3-pip default-jdk maven nodejs npm
npm i -g aws-cdk
pip3 install awscli

cd ~
git clone https://github.com/msailes/symmetrical-octo-sniffle.git
cd ~/symmetrical-octo-sniffle

cd ~/symmetrical-octo-sniffle/software/java/HelloWorldFunction
mvn package

cd ~/symmetrical-octo-sniffle/infrastucture
cdk bootstrap
cdk deploy
