package Util;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.util.List;

/** 	<h1> class AbstractModelEcoutable </h1>
 *
 *		<p>La classe implémente ModelEcoutable et définit les méthodes ajout/retraitEcouteur</p>
 *    @author Paul, Raphaëlle
 */

public abstract class AbstractModelEcoutable implements ModelEcoutable {

	/**
		*<p> List<EcouteurModel> : contient tout les EcouteurModel du modèle</p>
	*/
	public List<EcouteurModel> ecouteurs;

	/**
		*<p> methode ajoutant un EcouteurModel à la liste
		*@param EcouteurModel ec : l'EcouteurModel que l'on souhaite ajouter</p>
	*/
	@Override
	public void ajoutEcouteur(EcouteurModel ec) {
		ecouteurs.add(ec);
	}

	/**
		*<p> methode retirant un EcouteurModel à la liste
		*@param EcouteurModel ec : l'EcouteurModel que l'on souhaite retirer</p>
	*/
	@Override
	public void retraitEcouteur(EcouteurModel ec) {
		ecouteurs.remove(ec);
	}

	protected abstract void mouvPiece();
}
