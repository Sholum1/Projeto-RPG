package Item;

import Personagem.Personagem;


/**

 * A classe Pocao herda da classe abstrata Item, e o faz porque confere valores
 * específicos de medo e energia à personagem principal ao ter um objeto nela
 * instanciado consumido.
 * 
 * Para tal, sobrescreve o método "consumir".
 */
public class Pocao extends Item {

    // Construtor
    public Pocao() {
        super("Poção");
    }

    // Dá ao Personagem os efeitos do item
    @Override
    public void consumir(Personagem personagem) {
        personagem.setEnergia(personagem.getEnergia() + 2);
        personagem.setMedo(personagem.getMedo() + 5);
	System.out.println("Giu está com " + personagem.getEnergia() +
			   " de energia e " + personagem.getMedo() +
			   " de medo.");
    }
}
