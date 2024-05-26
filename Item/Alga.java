package Item;

import Personagem.Personagem;


/**

 * A classe Alga herda da classe abstrata Item, e o faz porque confere valores
 * específicos de vida e energia à personagem principal ao ter um objeto nela
 * instanciado consumido.
 * 
 * Para tal, sobrescreve o método "consumir".
 */
public class Alga extends Item {

    // Construtor
    public Alga() {
        super("Alga");
    }

    // Dá ao Personagem os efeitos do item
    @Override
    public void consumir(Personagem personagem) {
        personagem.setVida(personagem.getVida() + 3);
        personagem.setEnergia(personagem.getEnergia() - 3);
	System.out.println("Giu está com " + personagem.getVida() + " de vida e "
			   + personagem.getEnergia() + " de energia.");
    }
}
