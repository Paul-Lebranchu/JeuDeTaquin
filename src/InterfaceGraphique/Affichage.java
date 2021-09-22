package InterfaceGraphique;


import java.awt.*;
import javax.swing.*;

import Taquin.*;
import Util.*;

/**

  *<h1> Class Affichage </h1>
  *<p>
  *Cette classe gère la fenetre et son contenu
  *elle n'a pas de méthode, juste un constructeur
  *</p>
  * @author: Paul, Marguerite
*/
public class Affichage extends JFrame {

  /**
  *<p> type PlateauT paramètre pour l'objet Fenetre défini dans le cosntructeur </p>
  */
  protected PlateauT plat;

  /**
  *<p> type JPanel le JPanel qui contiendra la fenetre à afficher </p>
  */
  protected JPanel pan = new JPanel();

  /**
  *<p> plat: instance de PlateauT qui jouera le role d'attribut pour l'instance de Fenetre </p> <p>
  *setTitle: titre de la fenetre </p> <p>
  *setSize : taille de la fenetre </p> <p>
  *fen : génère un objet de type Fenetre </p> <p>
  *setLayout: gère l'agencement du JPanel où l'on mettra l"instance de Fenetre </p> <p>
  *setContentPane(pan): affiche un plateau de caseJeu </p> <p>
  *setVisible: rend le tout visible </p> <p>
  *setDefaultCloseOperation: permet de quitter le prog en cliquant sur la croix rouge </p>
  */
  public Affichage(PlateauT plat){
		this.setTitle("taquin");
		this.setSize(1000, 1000);
    Fenetre fen = (new Fenetre(plat));
    this.pan.setLayout(new GridLayout(1,1));
    this.pan.add(fen);
    this.setContentPane(pan);
		this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
