package Tabuleiro;

import java.util.ArrayList;

import Personagem.*;
import Criatura.*;
import Npc.*;
import Item.*;


/**

 * Tabuleiro: Simula o mapa do jogo e faz as ações do tabuleiro.
 * 
 * O mapa é implementado como uma matriz quadrada de tamanho fixo já no
 * construtor.
 * 
 * Apresenta métodos de adição e remoção de elementos, bem como o "mostrarMapa"
 * para imprimir o tabuleiro na tela.
 * 
 * Quanto à movimentação, tem os métodos "mover", "moverCriatura" e
 * "moverPersonagem", além do "achePosicao" para encontrar um elemento.
 * 
 * Caso encontre algo possível de se interagir, realiza-se a conferência através
 * do método "interagir".
 * 
 * Para os ataques, têm os métodos "atacar", que se relaciona ao personagem,
 * além de "faleceu" para conferir se Giu ou uma criatura estão vivos e "drop",
 * que é acionado quando uma criatura morre.
 * 
 * Os métodos de ataque de Lua estão temporariamente implementados nessa classe,
 * porém não pertencerão a ela na versão final do jogo.
 */
public class Tabuleiro {
    private ArrayList<ArrayList<Elemento>> mapa = new ArrayList<>();
    private final int tamanho;

    // Construtor
    public Tabuleiro(int tamanho) {
	this.tamanho = tamanho;
	for (int i = 0; i < tamanho; i++) {
	    mapa.add(new ArrayList<Elemento>());
	    for (int j = 0; j < tamanho; j++) {
		mapa.get(i).add(null);
	    }
	}
    }

    //  Getters e Setters (tamanho é final, então só possui get)
    public ArrayList<ArrayList<Elemento>> getMapa() {
	return mapa;
    }
    public void setMapa(ArrayList<ArrayList<Elemento>> mapa) {
	this.mapa = mapa;
    }

    public int getTamanho() {
	return tamanho;
    }

    // Adiciona um elemento novo no mapa, na posição selecionada
    public void adicionarElemento(Elemento elem, int x, int y) {
	if (x < getTamanho() && y < getTamanho()) {
	    mapa.get(x).set(y, elem);
	}
    }

    // Remove o elemento da posição selecionada
    public Elemento removerElemento(int x, int y) {
	Elemento elem = null;
	if (x < getTamanho() && y < getTamanho()) {
	    elem = mapa.get(x).get(y);
	    mapa.get(x).set(y, null);
	}
	return elem;
    }

    public boolean removerElemento(Elemento elem) {
	int[] pos = achePosicao(elem);
	return removerElemento(pos[0], pos[1]).equals(elem);
    }

    // Imprime o estado atual do mapa
    public void mostraMapa() {
	for (int i = 0; i < getTamanho(); i++) {
	    System.out.print("\t\t");
	    for (int j = 0; j < getTamanho(); j++) {
		Elemento pos = mapa.get(i).get(j);
		if (pos == null) {
		    System.out.print("~ ");
		} else if (pos instanceof Personagem) {
		    System.out.print("G ");
		} else if (pos instanceof Criatura) {
		    Criatura criatura = (Criatura)pos;
		    char primeiraLetra = Character.toLowerCase
			(criatura.getNome().charAt(0));
		    System.out.print(primeiraLetra+" ");
		} else if (pos instanceof Item) {
		    System.out.print("I ");
		} else if (pos instanceof Npc) {
		    Npc npc = (Npc)pos;
		    char primeiraLetra = npc.getNome().charAt(0);
		    System.out.print(primeiraLetra+" ");
		}
	    }
	    System.out.println();
	}
    }

    // Acha a posição de um dado elemento
    public int[] achePosicao(Elemento elem) {
	int[] pos = null;
	for (int i = 0; i < getTamanho(); i++) {
	    for (int j = 0; j < getTamanho(); j++) {
		if (mapa.get(i).get(j) != null &&
		    mapa.get(i).get(j).equals(elem)) {
		    pos = new int[]{i ,j};
		    break;
		}
	    }
	}
	return pos;
    }

    // Vê a possibilidade de interagir com algo
    public boolean interagir(Personagem personagem, Elemento elem) {
	int[] posPersonagem = achePosicao(personagem),
	    posElem = achePosicao(elem),
	    delta = new int[]{0, 1, -1};
	for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 3; j++) {
		if (((posPersonagem[0] + delta[i]) == posElem[0]) &&
		    (posPersonagem[1] + delta[j] == posElem[1])) {
		    if (elem instanceof Npc) {
			Npc npc = (Npc)elem;
			System.out.println("Pressione [i] para interagir com "
					   + npc.getNome());
		    } else if (elem instanceof Item) {
			Item item = (Item)elem;
			System.out.println("Pressione [p] para colocar " +
					   item.getNome() + " na mochila");
		    }
		    return true;
		}
	    }
	}
	return false;
    }

    // Move um elemento
    public boolean mover(int xObjetivo, int yObjetivo,
			  Elemento elem) {
	int[] posElem = achePosicao(elem);
	int xOriginal = posElem[0], yOriginal = posElem[1];
	if ((xObjetivo < getTamanho() && xObjetivo >= 0)
	    && (yObjetivo < getTamanho() && yObjetivo >= 0)) {
	    Elemento objetivo = mapa.get(xObjetivo).get(yObjetivo);
	    if (objetivo == null) {
		mapa.get(xObjetivo).set(yObjetivo, elem);
		mapa.get(xOriginal).set(yOriginal, null);
		return true;
	    }
	}
	return false;
    }

    // Move uma criatura, ela sempre segue o jogador
    public void moverCriatura (Criatura criatura, Personagem personagem) {
	int[] posPersonagem = achePosicao(personagem),
	    posCriatura = achePosicao(criatura);
	int xDiferença = posCriatura[0] - posPersonagem[0],
	    yDiferença = posCriatura[1] - posPersonagem[1];
	if (!interagir(personagem, criatura)) {
	    if (Math.abs(xDiferença) > Math.abs(yDiferença)) {
		mover(posCriatura[0] - (int)Math.signum(xDiferença),
		      posCriatura[1], criatura);
	    } else {
		mover(posCriatura[0], posCriatura[1] -
		      (int)Math.signum(yDiferença), criatura);
	    }
	}

    }

    // Move a Giu
    public void moverPersonagem(String opcao, Personagem personagem) {
	int[] pos = achePosicao(personagem);
	switch (opcao) {
	case "w":
	    mover(pos[0] - 1, pos[1], personagem);
	    break;
	case "s":
	    mover(pos[0] + 1, pos[1], personagem);
	    break;
	case "a":
	    mover(pos[0], pos[1] - 1, personagem);
	    break;
	case "d":
	    mover(pos[0], pos[1] + 1, personagem);
	    break;
	default:
	    break;
	}
    }

    // Confere se uma criatura ou o personagem estão mortos
    public boolean faleceu(TemVida elem) {
	return elem.getVida() == 0;
    }

    // Transforma a criatura em seu item
    public boolean drop(Criatura criatura) {
	int[] pos = achePosicao(criatura);
	Item drop = criatura.getDrop();
	mapa.get(pos[0]).set(pos[1], drop);
	return drop != null;
    }

    // Metódo que faz o Personagem atacar ou apanhar de uma Criatura
    public void atacar(TemVida atacando, TemVida apanhando) {
	int diff = apanhando.getVida() - atacando.getDano();
	apanhando.setVida(diff < 0 ? 0 : diff);
    }

    public boolean passou(Personagem personagem) {
	for (int i = 0; i < getTamanho(); i++) {
	    for (int j = 0; j < getTamanho(); j++) {
		if (mapa.get(i).get(j) != null &&
		    !mapa.get(i).get(j).equals(personagem)) {
		    return false;
		}
	    }
	}
	return true;
    }

	/*

	 * Função única para o tutorial, no jogo mesmo devemos usar o
	 * Lua.refletir()
	 */
    private boolean luaAtaque(Elemento elem, Lua lua) {
	if (elem instanceof Criatura) {
	    Criatura criatura = (Criatura)elem;
	    int diff = criatura.getVida() - lua.getDanoLua();
	    criatura.setVida(diff < 0 ? 0 : diff);
	    return true;
	}
	return false;
    }

    // Ataca com a Lua, calculando a distância máxima e caso
    // encontre uma criatura no caminho, atinge-a
    public Criatura luaAtacar(String opcao, Personagem personagem) {
	int[] pos = achePosicao(personagem);
	int xSinal = 0, ySinal = 0;
	Lua lua = personagem.getLua();
	switch (opcao) {
	case "w":
	    xSinal = -1;
	    break;
	case "s":
	    xSinal = 1;
	    break;
	case "a":
	    ySinal = -1;
	    break;
	case "d":
	    ySinal = 1;
	    break;
	default:
	    break;
	}
	if (xSinal != 0) {
	    for (int i = pos[0];
		 i < xSinal*(lua.getDistanciaLua() + pos[0]);
		 i += xSinal) {
		if (i == getTamanho()) break;
		Elemento elem = mapa.get(i).get(pos[1]);
		if(luaAtaque(elem, lua))
		    return (Criatura)elem;
	    }
	} else if (ySinal != 0) {
	    for (int j = pos[1];
		 j < ySinal*(lua.getDistanciaLua() + pos[1]);
		 j += ySinal) {
		if (j == getTamanho()) break;
		Elemento elem = mapa.get(pos[0]).get(j);
		if(luaAtaque(elem, lua))
		    return (Criatura)elem;
	    }
	}
	return null;
    }
}
