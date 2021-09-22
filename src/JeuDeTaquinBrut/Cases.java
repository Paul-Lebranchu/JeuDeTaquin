package Taquin;

/** <h1>La Classe Cases:</h1>
 * 		<p>
 		@author Raphaëlle</p>
 */
public class Cases{

	protected int numero;
	protected int coordonneeX;
	protected int coordonneeY;

 /** 		<h2>Le Constructeur:</h2>
 * 			<p>
 			@param numero, coordonneeX, coordonneeY
 * 			On definit une valeur entière pour la case permettant de savoir quelle case est bougée.
 * 			On définit aussi l'emplacement de la cases de part X et Y tout deux des entiers.
 * 			</p>	*/
	public Cases(int coordonneeX, int coordonneeY){
		this.coordonneeX = coordonneeX;
		this.coordonneeY = coordonneeY;
		this.numero = 0;
	}

 /** 		<h2>La Méthode getX:</h2>
 * 			<p>
 			@return coordonneX
 * 			Permet de réutiliser l'emplacement selon l'absisse du palet.</p>	*/
	public int getX(){
		return this.coordonneeX;
	}

 /** 		<h2>La Méthode getY:</h2>
 * 			<p>
 			@see getX()	</p>
 */
	public int getY(){
		return this.coordonneeY;
	}

 /** 		<h2>La Méthode getNum:</h2>
 * 			<p>
 			@return coordonneX
 * 			Permet de récuperer le numero du palet.</p>	*/
	public int getNum(){
		return this.numero;
	}

/**		<h2>La Méthode setX:</h2>
 * 			<p>
 			@param x
 * 			Permet de donnée une nouvelle coordonnée entière selon l'absisse du palet.</p>
 */
	public void setX(int x){
		this.coordonneeX = x;
	}

 /** 	<h2>La Méthode setY:</h2>
 * 			<p>
 			@see setX()</p>
 */	
	public void setY(int y){
		this.coordonneeY = y;
	}
	
/**		<h2>La Méthode setNum:</h2>
 * 			<p>
 			@param numero
 * 			Permet de donnée une nouvelle valeur au palet.</p>
 */
	public void setNum(int numero){
		this.numero = numero;
	}

 /** 	<h2>La Méthode toString():</h2>
 * 			<p>
 			@return this.numero
 * 			permet de renvoyer la valeur de la case en format charactère</p>
 */
	@Override
	public String toString(){
		return "" + this.numero;
	}
}
