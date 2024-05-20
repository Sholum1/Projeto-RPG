package Item;

import Personagem.Personagem;

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
    }
}
