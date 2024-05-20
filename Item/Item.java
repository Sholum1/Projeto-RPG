package Item;

import Tabuleiro.Elemento;
import Personagem.Personagem;

public abstract class Item implements Elemento {
    private final String nome;
    private int quantidade;

    // Construtor
    public Item(String nome) {
	this.nome = nome;
	this.quantidade = 0;
    }

    // Getters e Setters (nome é final, então só tem get)
    public String getNome() {
	return nome;
    }

    public int getQuantidade() {
	return quantidade;
    }
    public void setQuantidade(int quantidade) {
	this.quantidade = quantidade;
    }

    // Faz a quantidade aumentar ou diminuir
    public void operaQuantidade(char sinal) {
	if (sinal == '+') {
	    this.quantidade++;
	} else if (sinal == '-') {
	    this.quantidade--;
	}
    }

    // Dá os efeitos do item
    public abstract void consumir(Personagem personagem);
}
