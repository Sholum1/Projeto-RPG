package Item;

import Personagem.Personagem;

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
    }
}
