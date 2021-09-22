#!/bin/sh
cd script
./compile.sh

cd ..
cd src

echo "test jeu version graphique du package interface graphique"
echo ""

java -cp "build" InterfaceGraphique/Main
