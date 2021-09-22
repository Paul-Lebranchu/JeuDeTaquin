package Taquin;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
import Taquin.*;


public class Exe{

	public static void jeu(){
		Scanner recup = new Scanner(System.in);
		System.out.println("Entrez la taille de votre tableau (x)");
		int coordX = recup.nextInt();
		System.out.println("Entrez la taille de votre tableau (y)");
		int coordY = recup.nextInt();
		PlateauT plateau = new PlateauT(coordX, coordY);
		plateau.affichage();
		System.out.println(" ");
		for(int i = 0; i < Math.pow(3, (coordX * coordY)); i++){
			ArrayList<Cases> liste = plateau.mouvementPossible();
			plateau.melanger(liste);
		}
		plateau.affichage();
		while(plateau.cEstFini() != true){
			plateau.coupJoueur();
			plateau.affichage();
		}
		System.out.println("Voulez vous rejouer? 0 = oui 1 = non");
		int rep = recup.nextInt();
		if(rep == 0){
			jeu();
		}
	}

	public static void main(String[] args){
		jeu();
	}

}
