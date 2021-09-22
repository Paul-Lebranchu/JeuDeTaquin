package Taquin;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

import Util.*;

public class PlateauT /*ajout test evnt*/extends AbstractModelEcoutable{

	protected int x;
	protected int y;
	protected Cases[][] board;

/**<p>
 * 		Le Constructeur:
 * 			@author Olivier
 * 			@param x
 			@param y
 * 			On definit la taille du tableau avec les coordonnée entière x et y.
 * 			On créer des Cases avec leurs valeurs respectives.
 			@see Cases
 * </p>*/
	public PlateauT(int x, int y){
		this.ecouteurs = new ArrayList<>();//ajout test Event

		this.x = x;
		this.y = y;
		this.board = new Cases[y][x];
		int compteur = 1;
		for( int i = 0; i < x; i++){
			for( int j = 0; j < y; j++){
				board[j][i] = new Cases(j, i);
				board[j][i].setNum(compteur);
				compteur++;
			}
		}
		board[y-1][x-1] = new Cases(y-1, x-1);
		board[y-1][x-1].setNum(0);
	}

/** *<p>
 * 		La Méthode mouvementPossible:
 * 			@author Olivier
 * 			@return Arraylist<Cases>
 * 			Cette méthode renvois une liste des mouvement faisable selon l'emplacement de la case vide et la limite du tableau.
 *			On ajoute donc à la liste les cases adjacentes à la case vide (numero = 0). On évite ainsi les coups impossibles (hors cadre/mouvement irréalisables)
 *</p>*/
	public ArrayList<Cases> mouvementPossible(){
		ArrayList<Cases> liste = new ArrayList<Cases>();
		for(int i = 0; i < this.x; i++){
			for(int j = 0; j < this.y; j++){
				if(this.board[j][i].numero == 0){
					if(j+1 < this.y){//la case est dans le plateau
						Cases coup = this.board[j+1][i];
						liste.add(coup);
					}
					if(j-1 >= 0){
						Cases coup = this.board[j-1][i];
						liste.add(coup);
					}
					if(i+1 < this.x){
						Cases coup = this.board[j][i+1];
						liste.add(coup);
					}
					if(i-1 >= 0){
						Cases coup = this.board[j][i-1];
						liste.add(coup);
					}
				}
			}
		}
		return liste;
	}

/** <p>
 * 		La Méthode bouger:
 * 			@author Olivier
 * 			@param Cases
 *			Permet de faire bouger la case rentrée en paramètre.
 *</p>*/
	public void bouger(Cases coup){
		for(int i = 0; i < this.x; i++){
			for(int j = 0; j < this.y; j++){
				if(this.board[j][i].numero == 0){
					this.board[j][i].setNum(coup.numero);
					coup.setNum(0);
				}
			}
		}
	}
	
/** <p>
 * 		La Méthode melanger/melanger2:
* 			@author Raphaëlle
 * 			@param
 * 			@return
 * 			Cette méthode fait glisser les pieces une a une pour les mélanger afin que le jeu ai une solution à coup sur.
 * 			On y utilise la méthode mouvementPossible où l'on sélection aléatoirement un mouvement que l'on renvois dans méthode bouger XXX fois.
 *			@see mouvementPossible
 *			@see bouger
 </p>*/
	public void melanger(ArrayList<Cases> listeCoup){
		int taille = listeCoup.size();
		Random rand = new Random();
		int coup = rand.nextInt(taille);
		bouger(listeCoup.get(coup));
	}

	//utiliser pour la vue
	public Cases melanger2(ArrayList<Cases> listeCoup){
		int taille = listeCoup.size();
		Random rand = new Random();
		int coup = rand.nextInt(taille);
		return listeCoup.get(coup);
	}

	public void coupJoueur(){
		Scanner recup = new Scanner(System.in);
		ArrayList<Cases> listejouables = mouvementPossible();
		System.out.println(listejouables);
		System.out.println("entrer votre choix ~~ la liste commence à 0 ~~");
		int coup = recup.nextInt();
		bouger(listejouables.get(coup));
	}

	//utiliser pour la vue
  public void coupJoueur2(int coup){
		ArrayList<Cases> listejouables = mouvementPossible();
		int index;
		for(int i = 0; i < listejouables.size(); i++){
			if (coup == listejouables.get(i).numero){
				index = i;
				bouger(listejouables.get(index));
			}
		}
		this.mouvPiece();
	}

/**<p>
 * 		La Méthode affichage:
 * 			@author Olivier
 * 			Cette méthode renvois un tableau dans le terminal ce qui permet de visualiser les changements sans fenetre graphique.
 *</p>*/
	public void affichage(){
		for( int i = 0; i < this.x; i++){
			for( int j = 0; j < this.y; j++){
				System.out.print("|" + this.board[j][i]);
			}
			System.out.println("|");
		}
	}

 /**<p>
 * 		La Méthode cEstFini:
 * 			@author Raphaëlle
 * 			@return True, False
 * 			On verifie que les cases sont en ordre croissant de droite à gauche. Si tel est le cas, on renvois True sinon on renvois False.
 			De plus, la case vide doit être la à la dernière clase selon l'ordre précedamment cité.
 </p>
 */
	public Boolean cEstFini(){
		int compt = 1;
		for(int i = 0; i < this.x; i++){
			for(int j = 0; j < this.y; j++){
				if((i+1)*(j+1)!=(this.x*this.y)){
					if(this.board[j][i].numero != compt){
				 		return false;
					}
				}

				compt++;
			}
		}
		return true;
	}

    public int getx(){
        return this.x;
    }

    public int gety(){
        return this.y;
    }

    public Cases[][] getBoard(){
        return this.board;
    }

	//ajout test Event:
	@Override
	public void mouvPiece(){
		for(EcouteurModel ecout : this.ecouteurs){
			ecout.modelMiseAJour(this);
		}
	}
}
