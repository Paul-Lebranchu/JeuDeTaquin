Bauchez Marguerite 21803320
Cocquerez Olivier 21803229
Lebranchu Paul 21403460
Lemaire Raphaëlle 21802756

Jeu de taquin

Vous pouvez lancer le programme à l'aide des commandes suivantes en ouvrant un terminal dans ce dossier:

- java -jar dist/exeTerm.jar pour lancer le jeu en mode console.
- java -jar dist/exeGraph.jar pour lancer la version graphique du jeu.



Le script exeTermi.sh compile l'ensemble des fichiers et execute le code pour que nous puissions jouer dans le terminal. Pour l'executer, rentrer la commande ./exeTermi.sh 

Le script exeGraph.sh compile l'ensemble des fichier et execute le code pour que l'on puisse jouer au jeu en version graphique. Pour l'executer, rentrer la commande ./exeGrah.sh

(pour les commandes suivante exe.sh correspond au nom du fichier que vous souhaiter lancer, il faut donc remplacer exe.sh soit par exeGraph.sh soit par exeTermi.sh)

Si vous avez des problèmes de droit, faite chmod -x exe.sh sous windows, chmod 774 exe.sh sous linux et réexecuter la commande ./exe.sh

Si la commande ne s'execute toujours pas, il est possible que vous ayez un problème de droit avec le script compile.sh; pour régler ceci rentrer la série de commande suivante:

cd script
chmod -x compile.sh (windows) ou chmod 774 compile.sh (linux)
cd ..
./exe.sh

Vous pouvez également lancer le programme en cliquant sur le nom du script et en choissant l'option "lancé depuis un terminal"


