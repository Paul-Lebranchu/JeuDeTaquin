package InterfaceGraphique;

import Taquin.*;
import InterfaceGraphique.*;
/**

  *<h1> class Main</h1>
  *<p>
  * Cette classe est un executable, elle a pour role de lancer le jeu en mode graphique en crééant une instance de PlateauT et une instance
  * d'affichage </p>
  * @author: Paul, Marguerite
*/
public class Main{
  public static void main(String[] args) {


    PlateauT plateauTerminal = new PlateauT(3,4);
    Affichage fen = new Affichage(plateauTerminal);

  }

}
