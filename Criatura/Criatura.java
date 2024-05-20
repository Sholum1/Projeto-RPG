package Criatura;

import Item.*;
import Personagem.*;
import Tabuleiro.Elemento;

public class Criatura implements Elemento {
    private Item drop;
    private int dano, vida;
    private final String nome;

    // Construtor
    public Criatura(int dano, int vida, String nome) {
	this.dano = dano;
	this.vida = vida;
	this.nome = nome;
    }
    // Caso seja uma criatura com drop
    public Criatura(Item item, int dano, int vida, String nome) {
	this(dano, vida, nome);
	this.drop = item;
    }

    // Inicio dos Getters e Setters
    public String getNome() {
	return nome;
    }

    public int getDano() {
	return dano;
    }
    public void setDano(int dano) {
	this.dano = dano;
    }

    public Item getDrop() {
	return drop;
    }
    public void setDrop(Item drop) {
	this.drop = drop;
    }

    public int getVida() {
	return vida;
    }
    public void setVida(int vida) {
	this.vida = vida;
    }
    // Fim dos Getters e Setters

    // Ataca o jogador
    public void atacar(Personagem jogador) {
	int novaVida = (jogador.getVida() - getDano());
	jogador.setVida((novaVida < 0) ? 0 : novaVida);
    }

    // Confere se a criatura solta algum item
    public boolean confereDrop() {
	return (getDrop() != null);
    }
    // Confere se a criatura morreu
    public boolean confereMorte() {
	return (getVida() == 0);
    }
}
