#!/bin/sh
cd script
./compile.sh

cd ..
cd src

echo "test jeu version terminal du package jeu de taquin brut"
echo ""

java -cp "build" Taquin/Exe
