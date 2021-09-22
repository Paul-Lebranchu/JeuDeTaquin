package InterfaceGraphique;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.applet.Applet;
//gestion des images
import java.io.*;
import javax.imageio.ImageIO;

import java.util.*;

import Taquin.*;
import Util.*;

/**
  *<h1> class Plateau </h1>
  *<p>
  *La classe pateau sert à créé le plateau de jeu contenant les cellule(case du jeu) à déplacer. </p> <p>
  *Cette classe joue un role important dans la vue et sert également de controlleur.
  *</p>

  * @author: Paul, Marguerite
*/
public class Plateau extends JPanel{
  /**
  *<p> type int, correspond au nombre de case par ligne </p>
  */
  protected int x;

  /**
  *<p> type int, correspond au nombre de case par colonne </p>
  */
  protected int y;

  /**
  *<p> type Cellule[][], contient les cases du jeu</p>
  */
  protected Cellule[][] plat;

  /**
  *<p> type PlateauT joue le role du modèle</p>
  */
  protected PlateauT modele;

  /**
  *<p> type Cellule contient la dernière cellule du plateau de jeu</p>
  */
  protected Cellule lastCellule;

  /**
  *<p> type int compte le nombre de coup joué par l'utilisateur </p>
  */
  protected int cpt;

  /**
  *<p> type JLabel affiche le nombre de coup joué</p>
  */
  protected JLabel coupJouer;

  /**
  *<p> type JTextArea c'est ici que l'on doit rentrait le nombre de ligne que l'on souhaite dans notre
  nouveau plateau de Jeu</p>
  */
  protected JTextArea ligne;

  /**
  *<p> type JTextArea c'est ici que l'on doit rentrait le nombre de colonne que l'on souhaite dans notre
  nouveau plateau de Jeu</p>
  */
  protected JTextArea colonne;

  /**
  *<p> type JLabel affiche un message félicitant le joueur à la fin d'une partie</p>
  */
  protected JLabel bravo;

  /**
  *<p> type JButton bouton permettant de créé une nouvelle partie</p>
  */
  protected JButton nouvelPartie;

  /**
  *<p> type JPanel l'endroit où l'on devra ajouter les boutons/Cellules etc</p>
  */
  protected JPanel fen;
  /**
    *<p> PlateauT modele : joue le role dans modèle dans le jeu </p> <p>
    *JPanel fen : endroit où l'on affichera le contenu créé dans cette classe </p> <p>
    *Le constructeur gère également la position des différent bouton et appelle les fonctions de défini plus bas
    *</p>
  */
  public Plateau(PlateauT modele, JPanel fen){
      this.x = modele.gety();
      this.y = modele.getx();
      this.plat = new Cellule[x][y];
      this.modele =modele;
      this.fen = fen;
      this.cpt = 0;
      //création des zones de JTextArea et des JLabel et positionnement
      this.ligne = new JTextArea();
      this.ligne.setBounds(40,80,200,20);
      this.fen.add(ligne);

      this.colonne = new JTextArea();
      this.colonne.setBounds(40,120,200,20);
      this.fen.add(colonne);

      this.coupJouer = new JLabel ("vous avez joué : "+ this.cpt + " coups");
      this.coupJouer.setBounds(760,840,200,20);
      this.fen.add(coupJouer);

      this.bravo = new JLabel("partie en cours");
      this.bravo.setBounds(40,160,200,20);
      this.fen.add(bravo);

      // création du bouton nouvel partie
      this.nouvelPartie =new JButton("nouvelle partie");
      nouvelPartie.setBounds(40,40,200,20);
      fen.add(nouvelPartie);

      //ajout des event au bouton/cellules
      this.eventBouton();

      this.lastCellule = this.plat[x-1][y-1];

      //mélanger
      this.melanger();

      //commande de jeu à la souris et au clavier
      this.deplacement();
  }

  /**
  *<p> Méthode sans argument et ne retournant rien, cette méthode permet de gérer
  *les déplacement au clavier et à la souris et gère également la boucle de jeu.
  *La méthode ajoute des évenement aux cellules jouable tant que le jeu n'est pas terminé
  *, une fois fini, le jeu affiche un message de Félicitations et indique au joueur en combien de coups
  *il a gagné</p>
  */
  public void deplacement(){

    // déplacement à la souris
    for (int i = 0 ;i < this.y ; i++){
      for (int j =0 ; j < this.x ; j++){
        Cellule c = this.plat[j][i];

          c.addMouseListener(new MouseAdapter() {
            //quand la souris entre dans la case, j'affiche un message et modifie l'apparence de la case
          	@Override
            public void mouseEntered(MouseEvent e) {
              for (int k = 0; k < modele.mouvementPossible().size(); k++){
                if (c.numero == modele.mouvementPossible().get(k).getNum() && !modele.cEstFini()){
                  System.out.println("hey je peux bouger, je suis la cellule numéro "+c.numero);
                  c.caseJouable();
                }
              }
            }
            //quand la souris sors, je rend son apparence de base à la cellule
            @Override
            public void mouseExited(MouseEvent e){
              for (int k = 0; k < modele.mouvementPossible().size(); k++){
                if (c.numero == modele.mouvementPossible().get(k).getNum()){
                  c.sortieCaseJouable();
                }
              }
            }
            //quand je clique sur la case, elle bouge (pas opérationnel)
            @Override
            public void mousePressed(MouseEvent e){
              System.out.println(modele.mouvementPossible());
              //parcours des mouvement possibles et affectations d'évènement aux cellules qui ont le même numéro que
              // Cases de l'ArrayList renvoyés par mouvementPossible, cette ajout d'évènement ne sera fait que si le modèle
              // n'est pas fini
              for (int k = 0; k < modele.mouvementPossible().size(); k++){
                if (c.numero == modele.mouvementPossible().get(k).getNum() && !modele.cEstFini()){
                  //stock la position de la cellule cible
                  int cPosx = c.getPosx();
                  int cPosy = c.getPosy();

                  //change la position de la cellule cible avec la cellule vide
                  c.posx = lastCellule.posx;
                  c.posy = lastCellule.posy;
                  c.setBounds(c.posx,c.posy,c.longeur,c.hauteur);

                  //la cellule vide prend la place de la cellule cible
                  lastCellule.posx = cPosx;
                  lastCellule.posy = cPosy;
                  lastCellule.setBounds(lastCellule.posx,lastCellule.posy,lastCellule.longeur,lastCellule.hauteur);

                  //met à jour le modele
                  int coup = c.numero;
                  modele.coupJoueur2(coup);
                  modele.affichage();
                  System.out.println(modele.mouvementPossible());

                  //met à jour le compteur
                  cpt += 1;
                  coupJouer.setText("vous avez joué : "+ cpt + " coups");
                  System.out.println(modele.cEstFini());

                  //si la partie est fini, affiché le message de victoire
                  if(modele.cEstFini()){
                    bravo.setText("Félicitations, vous avez gagné en "+cpt+" coups! Pour rejouer, appuyer sur nouvelle partie. \n Pour quitter, appuyer sur la croix rouge.");
                    fen.add(bravo);
                    bravo.setBounds(50,640,1000,500);
                  }
                  break;
                }
              }
            }
          });
        }
      }
      // déplacement au clavier
      this.lastCellule.addKeyListener(new KeyListener(){
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e){

          if((e.getKeyCode()== 38) && !modele.cEstFini() && lastCellule.posy > 250){
            System.out.println("je vais en haut");
            for (int i = 0 ;i < x ; i++){
              Boolean shouldBreak = false;
              for (int j =0 ; j < y ; j++){
                Cellule c = plat[i][j];
                /*comparaison des positions des cellules avec la position de la dernière celluke après déplacement
                 si la position est identique(avec un risque d'erreur de -1 ou +1 à cause de l'arrondie pour la valeur
                 hauteur/largeur pour la corrdonnées qui sera modifié) on échange la position des deux cellules*/
                if(c.posx == lastCellule.posx && c.posy <= lastCellule.posy-lastCellule.hauteur+1 && c.posy >= lastCellule.posy -lastCellule.hauteur -1){

                  int interimX = c.getPosx();
                  int interimY = c.getPosy();

                  c.posx = lastCellule.posx;
                  c.posy = lastCellule.posy;
                  c.setBounds(c.posx,c.posy,c.longeur,c.hauteur);

                  lastCellule.posx = interimX;
                  lastCellule.posy = interimY;
                  lastCellule.setBounds(lastCellule.posx,lastCellule.posy,lastCellule.longeur,lastCellule.hauteur);

                  int coup = c.numero;
                  modele.coupJoueur2(coup);
                  modele.affichage();
                  shouldBreak =true;
                  break;
                }
              }
              if (shouldBreak == true){
                break;
              }
            }

            cpt += 1;
          }
          if((e.getKeyCode()== 40 && !modele.cEstFini() && lastCellule.posy + lastCellule.hauteur + y < 750 )){
            System.out.println("je vais en bas" );
            for (int i = 0 ;i < x ; i++){
              Boolean shouldBreak = false;
              for (int j =0 ; j < y ; j++){
                Cellule c = plat[i][j];
                if(c.posx == lastCellule.posx && c.posy <= lastCellule.posy+lastCellule.hauteur+1 && c.posy >= lastCellule.posy+lastCellule.hauteur-1 ){
                  int interimX = c.getPosx();
                  int interimY = c.getPosy();

                  c.posx = lastCellule.posx;
                  c.posy = lastCellule.posy;
                  c.setBounds(c.posx,c.posy,c.longeur,c.hauteur);

                  lastCellule.posx = interimX;
                  lastCellule.posy = interimY;
                  lastCellule.setBounds(lastCellule.posx,lastCellule.posy,lastCellule.longeur,lastCellule.hauteur);

                  int coup = c.numero;
                  modele.coupJoueur2(coup);
                  modele.affichage();
                  shouldBreak =true;
                  break;
                }
              }
              if (shouldBreak == true){
                break;
              }
            }

            cpt += 1;
          }
          if((e.getKeyCode()== 37) && !modele.cEstFini() && lastCellule.posx > 250){
            System.out.println("je vais à gauche");
            for (int i = 0 ;i < x ; i++){
              Boolean shouldBreak = false;
              for (int j =0 ; j < y ; j++){
                Cellule c = plat[i][j];
                if(c.posx <= lastCellule.posx-lastCellule.longeur+1 && c.posx >= lastCellule.posx-lastCellule.longeur-1 && c.posy == lastCellule.posy){
                  int interimX = c.getPosx();
                  int interimY = c.getPosy();

                  c.posx = lastCellule.posx;
                  c.posy = lastCellule.posy;
                  c.setBounds(c.posx,c.posy,c.longeur,c.hauteur);

                  lastCellule.posx = interimX;
                  lastCellule.posy = interimY;
                  lastCellule.setBounds(lastCellule.posx,lastCellule.posy,lastCellule.longeur,lastCellule.hauteur);

                  int coup = c.numero;
                  modele.coupJoueur2(coup);
                  modele.affichage();
                  shouldBreak =true;
                  break;
                }
              }
              if (shouldBreak == true){
                break;
              }
            }
            cpt += 1;

          }
          if((e.getKeyCode()== 39) && !modele.cEstFini() && lastCellule.posx +lastCellule.longeur + x < 750){
            System.out.println("je vais à droite");
            for (int i = 0 ;i < x ; i++){
              Boolean shouldBreak = false;
              for (int j =0 ; j < y ; j++){
                Cellule c = plat[i][j];
                if(c.posx <= lastCellule.posx+lastCellule.longeur+1 && c.posx >= lastCellule.posx+lastCellule.longeur-1 && c.posy == lastCellule.posy){
                  int interimX = c.getPosx();
                  int interimY = c.getPosy();

                  c.posx = lastCellule.posx;
                  c.posy = lastCellule.posy;
                  c.setBounds(c.posx,c.posy,c.longeur,c.hauteur);

                  lastCellule.posx = interimX;
                  lastCellule.posy = interimY;
                  lastCellule.setBounds(lastCellule.posx,lastCellule.posy,lastCellule.longeur,lastCellule.hauteur);

                  int coup = c.numero;
                  modele.coupJoueur2(coup);
                  modele.affichage();
                  shouldBreak =true;
                  break;
                }
              }
              if (shouldBreak == true){
                break;
              }
            }

            cpt += 1;
          }

          coupJouer.setText("vous avez joué : "+ cpt + " coups");

          if(modele.cEstFini()){
            bravo.setText("Félicitations, vous avez gagné en "+cpt+" coups! Pour rejouer, appuyer sur nouvelle partie. \n Pour quitter, appuyer sur la croix rouge.");
            bravo.setBounds(50,640,1000,500);
          }
        }

        @Override
        public void keyReleased(KeyEvent e){}

      });
      //si le modèle est terminé des sa création (plus le plateau est petit, plus la probabilité est grande): afficher le message suivant
      if(this.modele.cEstFini()){
        bravo.setText("Pas de chance et la partie a été gagné des sa création!Sinon, appuyer sur nouvelle partie. \n Pour quitter, appuyer sur la croix rouge.");
        bravo.setBounds(25,640,1000,500);
      }
    }

  /**
    *<p> Méthode sans argument et ne retournant rien, cette méthode ajoute un évenement au bouton nouvelle nouvelPartie
    * et créé l'ensemble des cases de jeu en leur affectant un evenement qui indique leur numéro </p>
  */
  public void eventBouton(){

    this.nouvelPartie.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
      //retire tout ce qu'il y avait dans le JPanel qui contenait le précédent Plateau
      fen.removeAll();
      //récupère le contenu de JTextArea et les convertit en int -> doit gérer erreur si utilisateur rentre pas un nombre

      int nbLigne = Integer.parseInt(ligne.getText());
      int nbColonne = Integer.parseInt(colonne.getText());
      //créé un nouveau plateau de jeu
      PlateauT nouveau = new PlateauT(nbLigne,nbColonne);
      Plateau nouvPlat = new Plateau(nouveau,fen);

      }
    });

    //création des boutons de jeu
    for (int i = 0 ;i < this.x ; i++){
      for (int j =0 ; j < this.y ; j++){
        if(i != this.x-1 || j != this.y-1){
          int num = this.x*j + i +1;
          Cellule c = new Cellule(250+i*500/this.x,250+j*500/this.y,500/this.x,500/this.y,false,num);
          fen.add(c);
          c.addMouseListener(new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
            System.out.println(""+c);
            }
          });
          this.plat[i][j] = c;
        }
        else{
          Cellule c = new Cellule(250+i*500/this.x,250+j*500/this.y,500/this.x,500/this.y,true,0);
          fen.add(c);
          c.addMouseListener(new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
            System.out.println("je suis la dernière cellule,"+c);
            }
          });

          this.plat[i][j] = c;
        }
      }
    }

  /**
  *<p>Méthode mélangeant le plateau de jeu en simulant un certains nombre de coup pour
  *s'assurer que la partie ne soit pas impossible à terminer </p>
  */

  }
  public void melanger(){

    //boucle pour mélanger le plateau avant de lancer le jeu
    //l =nombre de coup à mélanger
    for(int l = 0; l < Math.pow((this.x*this.y),2); l++){
			ArrayList<Cases> liste = modele.mouvementPossible();

      Cases coup = modele.melanger2(liste);
      /*simule des coups joué en utilisant la même méthode que pour le déplacement à la souris:
      * parcours la liste des mouvement possibles et joue un coup en choissisant un des numéros de
      * cette liste.
      */
      for (int i = 0 ;i < this.y ; i++){
        for (int j =0 ; j < this.x ; j++){
          Cellule c = this.plat[j][i];
            if (c.numero == coup.getNum()){

              int cPosx = c.getPosx();
              int cPosy = c.getPosy();

              c.posx = this.lastCellule.posx;
              c.posy = this.lastCellule.posy;
              c.setBounds(c.posx,c.posy,c.longeur,c.hauteur);

              this.lastCellule.posx = cPosx;
              this.lastCellule.posy = cPosy;
              this.lastCellule.setBounds(this.lastCellule.posx,this.lastCellule.posy,this.lastCellule.longeur,this.lastCellule.hauteur);

          }
        }
      }
      // mise à jour du modèle
      this.modele.bouger(coup);
		}
  }

}
