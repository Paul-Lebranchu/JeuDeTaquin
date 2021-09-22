package Util;

/**
	*<h1> Interface ModelEcoutable </h1>
	*<p> Interface obligeant ces classes filles à avoir les méthodes ajoutEcouteur et retraitEcouteur</p>
*/
public interface ModelEcoutable {

	public void ajoutEcouteur(EcouteurModel ec);
	public void retraitEcouteur(EcouteurModel ec );

}
