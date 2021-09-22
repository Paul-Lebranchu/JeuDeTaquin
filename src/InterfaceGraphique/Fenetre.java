package InterfaceGraphique;

/**

  *<h1> Class Cellules </h1>

  *<p>
  *La classe Fenetre est le JPanle qui contiendra tout les éléments à afficher
  *</p>
  * @author: Paul, Marguerite
*/
import java.awt.*;
import javax.swing.*;

//gestion des images
import java.io.*;
import javax.imageio.ImageIO;

import java.awt.event.*;
import java.applet.Applet;

import Taquin.*;
import Util.*;

public class Fenetre extends JPanel implements EcouteurModel{

  /**
  *<p> type Color gère la couleur de fond de la bordure du plateau </p>
  */
  protected Color bordurePlateau;

  /**
  *<p> type Color gère la couleur du contour du plateau </p>
  */
  protected Color contourPlateau;

  /**
  *<p> type Color gère la couleur de fond du jeu </p>
  */
  protected Color background;

  /**
  *<p> type PlateauT joue le role du modèle </p>
  */
  protected PlateauT modele;

  /**
    *<p> PlateauT modele : instance de PlateauT qui joue le role du modèle (dans le MVC)  </p>
    <p> Le constructeur gère également la mise en place des écouteurs sur le modèle  </p>
  */
  public Fenetre(PlateauT modele){
      super();
      this.modele = modele;
      this.setLayout(null);
      modele.ajoutEcouteur(this);


      this.bordurePlateau = new Color(146,41,41);
      this.contourPlateau = new Color(0,0,0);
      this.background = new Color(194,175,177);

      Plateau p =new Plateau(modele,this);
      this.repaint();

  }

  /**
    *<p>
    * méthode ne retournant rien, gère l'affichage de tout les éléments de la Fenetre.
    *Dans un premier temps, elle définit la couleur du fond d'écran, puis elle créé
    *un cadre pour le plateau du jeu.
    *Enfin, la méthode ajoute un objet de type Plateau qui contiendra les cellules du jeu
    *et les affichera.
    *@param g: objet graphique où l'on dessinera le contenu de la méthode.
    *</p>
  */

  @Override
  public void paintComponent(Graphics g){

    super.paintComponent(g);
    //fond d'écran
    g.setColor(this.background);
    g.fillRect(0,0,this.getWidth(),this.getHeight());

    //bord du plateau de jeu et contour
    g.setColor(this.bordurePlateau);
    g.fillRoundRect(220,220,560,560,100,100);

    g.setColor(this.contourPlateau);
    g.drawRoundRect(220,220,560,560,100,100);

    g.drawRect(250,250,500,500);

  }

  /**
    *</p> méthode hérité de la classe EcouteurModel <p>
  */
  @Override
  public void modelMiseAJour(Object source){

  }
}
