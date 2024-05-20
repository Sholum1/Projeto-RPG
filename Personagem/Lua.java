package Personagem;

import Criatura.*;

/**
 * Lua
 */
public class Lua {
    // A ideia é que conforme a fase a Lua possa refletir mais luz,
    // aumentando assim seu ataque, mas também o intervalo de tempo entre
    // cada ataque
    public enum Fase {
        NOVA(1, 3, 2), // A Lua nova praticamente não reflete luz na terra
        CRESCENTE(4, 7, 4), // A Lua crescente reflete um pouco mais de luz
        CHEIA(8, 12, 16), // A Lua nova é a que mais reflete luz
        MINGUANTE(6, 10, 8); // A Lua minguante começa a refazer o ciclo

        private final int dano, tempoAtaque, distanciaAtaque;

	// Construtor da Fase
        private Fase(int dano, int tempoAtaque, int distanciaAtaque) {
            this.dano = dano;
            this.tempoAtaque = tempoAtaque;
            this.distanciaAtaque = distanciaAtaque;
        }

	// Por serem final só possuem os Getters
	public int getDano() {
	    return dano;
	}

	public int getDistanciaAtaque() {
	    return distanciaAtaque;
	}

	public int getTempoAtaque() {
	    return tempoAtaque;
	}
    }

    private Fase fase;
    private int tempoCiclo = 29;

    // Construtor da Lua
    public Lua(Fase fase) {
	this.fase = fase;
    }

    public Fase getFase() {
	return fase;
    }
    public void setFase(Fase fase) {
	this.fase = fase;
    }

    // Para evitar de ficar escrevendo a mesma coisa varias vezes
    public int getDanoLua() {
	return fase.getDano();
    }
    public int getDistanciaLua() {
	return fase.getDistanciaAtaque();
    }
    public int getTempoLua() {
	return fase.getTempoAtaque();
    }

    // A cada 29 ciclos do loop a fase troca (baseado nos 29 dias
    // para cada troca de fase)
    public void mudaFase() {
	if (tempoCiclo-- == 0) {
	    if (getFase() == Fase.NOVA) {
		setFase(Fase.CRESCENTE);
	    } else if (getFase() == Fase.CRESCENTE) {
		setFase(Fase.CHEIA);
	    } else if (getFase() == Fase.CHEIA) {
		setFase(Fase.MINGUANTE);
	    } else if (getFase() == Fase.MINGUANTE) {
		setFase(Fase.NOVA);
	    }
	    this.tempoCiclo = 29;
	}
    }

    // Ação de ataque da Lua, seu dano depende da Fase
    public void refletir(Criatura criatura, int tempo) {
	if (tempo == getTempoLua()) {
	    int novaVida = (criatura.getVida() - getDanoLua());
	    criatura.setVida((novaVida < 0) ? 0 : novaVida);
	} else {
	    System.out.println("A Lua tenta refletir luz, mas está fraca");
	}
    }
}
