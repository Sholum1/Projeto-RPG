package Item;

import Personagem.Personagem;

public class Raiz extends Item {

    // Construtor
    public Raiz() {
	    super("Raiz");
    }

    // Dá ao Personagem os efeitos do item
    @Override
    public void consumir(Personagem personagem) {
        personagem.setEnergia(personagem.getEnergia() + 3);
        personagem.setFelicidade(personagem.getFelicidade() - 2);
    }

}
