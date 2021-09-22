package InterfaceGraphique;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
//gestion des images
import java.io.*;
import javax.imageio.ImageIO;

/**
  *<h1> class Cellule </h1>
  *<p> La classe Cellule permet de créé les cases du jeu que nous devrons déplacer.
  *</p>
  * @author: Paul, Marguerite
*/
public class Cellule extends JButton {

  /**
  *<p>  type int position sur l'axe x </p>
  */
  protected int posx;

  /**
  *<p>  type int position sur l'axe y </p>
  */
  protected int posy;

  /**
  *<p>  type int défini la longeur de la pièce </p>
  */
  protected int longeur;

  /**
  *<p>  type int défini la hauteur de la pièce </p>
  */
  protected int hauteur;

  /**
  *<p>  type boolean indique si la pièce est la dernière du plateau </p>
  */
  protected boolean dernier;

  /**
  *<p>  type int correspond au numéro de la case </p>
  */
  protected int numero;

  /**
  *<p>  type Color couleur rouge redéfini </p>
  */
  protected Color red = new Color(75,0,2);

  /**
  *<p>  type Color couleur blanche redéfini </p>
  */
  protected Color blanc = new Color(255,255,255);

  /**
  *<p> type Font police d'écriture pour les cellules non déplaçable </p>
  */
  protected Font normal;

  /**
  *<p> type Font police d'écriture pour les cellules jouables </p>
  */
  protected Font jouable ;
  /**
    *<p>
      *posx : position sur l'axe x de la cellule </p> <p>
      * posy : position sur l'axe y de la cellule </p> <p>
      * hauteur : hauteur de la pièce </p> <p>
      * longeur : longeur de la pièce </p> <p>
      * dernier: boolean précisant si la pièce est la dernière du plateau </p> <p>
      * int numero: identifiant de la pièce  </p> <p>
      * </p>
      * <p> En plus de ces paramètres, le constructeurs génère une image de fond pour toute les cellules
      * (à l'execption de la dernière) et affiche son numéro.
      *</p>
  */
  public Cellule(int posx, int posy,int longeur,int hauteur, boolean dernier,int numero){
    super();
    this.posx = posx;
    this.posy = posy;
    this.hauteur = hauteur;
    this.longeur = longeur;
    this.dernier = dernier;
    this.numero = numero;

    this.jouable =  new Font("Arial", Font.ITALIC + Font.BOLD, 18);
    this.normal = new Font("Arial", Font.ITALIC , 14);
    this.setFont(this.normal);

    this.setBounds(posx,posy,longeur,hauteur);
    this.setForeground(blanc);
    try{
      BufferedImage test = ImageIO.read(new File("test.jpg"));
      if(!dernier){
        BufferedImage  sub = test.getSubimage(this.posx,this.posy,this.longeur,this.hauteur);
        ImageIcon ico = new ImageIcon(sub);
        this.setIcon(ico);
        this.setIconTextGap( - ico.getIconWidth()/2-20);//centre le text dans la case par rapport à l'image(le -20 est là pour laisser la place pour afficher le numéro)
        this.setText(""+this.numero);

      }
      else{
        this.setBackground(red);

        this.setText("0");
      }
    }catch(IOException e) {
      e.printStackTrace();
    }
  }

  /**
  *<p> méthode ne retournant rien et ne prenant pas de paramètre, elle
  * permet de modifier l'affichage de la cellule (changement de police et
  * de couleur) </p>
  */
  public void caseJouable(){
    Color vert = new Color(0,255,0);
    this.setForeground(vert);
    this.setFont(this.jouable);

  }

  /**
  *<p> méthode ne retournant rien et ne prenant aucun paramètre, elle
  * réinitialise l'affichage de la cellule en lui remettant sa couleur
  * et police d'origine </p>
  */
  public void sortieCaseJouable(){
    this.setForeground(blanc);
    this.setFont(this.normal);
  }

  /**
  *<p> méthode sans argument retournant la position sur l'axe x
  @return int: position de la pièce sur l'axe x  </p>
  */
  public int getPosx(){
    return this.posx;
  }

  /**
  *<p> méthode sans argument retournant la position sur l'axe y
  @return int: position de la pièce sur l'axe y  </p>
  */
  public int getPosy(){
    return this.posy;
  }



  public String toString(){
    return "je suis la cellule numero : "+this.numero;
  }
 }
