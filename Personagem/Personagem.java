package Personagem;

import java.util.ArrayList;
import Item.Item;
import Tabuleiro.*;


/**
 
 * A classe Personagem representa Giu, a protagonista da história, a qual será
 * controlada pelo usuário durante o jogo.
 * 
 * Para que possa guardar os itens que encontra no mapa e drops de criaturas,
 * leva consigo uma mochila, implementada aqui como um ArrayList de itens da
 * classe Item.
 * 
 * Para acessar a mochila, há os métodos "adicionarItem", "removerItem",
 * "consumirItem" e "verMochila".
 * 
 * Há também o método "perdeu", que verificará os atributos de Giu e retornará
 * se o usuário perdeu o jogo ou não (ou seja, se Giu morreu ou está viva).
 */
public class Personagem implements TemVida {
    private final Lua lua;
    private int vida, energia, medo, felicidade;
    private final int dano;
    private ArrayList<Item> mochila;
    
    // Construtor
    public Personagem() {
        this.vida = 20;
        this.energia = 10;
        this.medo = 5;
        this.felicidade = 5;
        this.dano = 3;
        this.mochila = new ArrayList<Item>();
	this.lua = new Lua(Lua.Fase.NOVA);
    }

    // Getters e Setters
    public Lua getLua() {
	return lua;
    }

    public int getDano() {
	return dano;
    }

    public int getEnergia() {
	return energia;
    }
    public void setEnergia(int energia) {
	this.energia = energia;
    }
    public int getFelicidade() {
	return felicidade;
    }
    public void setFelicidade(int felicidade) {
	this.felicidade = felicidade;
    }

    public int getMedo() {
	return medo;
    }
    public void setMedo(int medo) {
	this.medo = medo;
    }
    public int getVida() {
	return vida;
    }
    public void setVida(int vida) {
	this.vida = vida;
    }

    public ArrayList<Item> getMochila() {
	return mochila;
    }


    public void setMochila(ArrayList<Item> mochila) {
	this.mochila = mochila;
    }

    // Confere se Giu morreu
    public boolean perdeu() {
	return !(getVida() <= 0 || getEnergia() <= 0 || getFelicidade() <= 0
		 || getMedo() >= 20);
    }

    // Parte da Mochila

    // Confere se o item já existe na mochila e o adiciona ou aumenta seu número
    public void adicionarItem(Item item) {
	if (getMochila().isEmpty() || !getMochila().contains(item))
	    getMochila().add(item);
        for (Item i : getMochila()) {
            if (item == i) {
		i.operaQuantidade('+');
		System.out.println("Giu possui: " + item.getQuantidade()
				   + " " + item.getNome() + "s");
		break;
	    }
	}
    }

    // Remove o item da mochila
    private void removerItem(Item item) {
	mochila.remove(item);
    }

    // Usa o item, reduzindo a quantidade da mochila e aplicando seus efeitos
    public void consumirItem(Item item) {
	for (Item i : getMochila()) {
	    if(item == i) {
		i.consumir(this);
		i.operaQuantidade('-');
		if (i.getQuantidade() == 0) {
		    removerItem(item);
		}
		break;
	    }
	}
    }

    // Mostra os elementos da mochila e suas quantidades
    public void verMochila() {
        System.out.print("Dentro da mochila há: ");
	for (Item i : getMochila()) {
	    System.out.print(i.getNome() + " [" + i.getQuantidade() + "]");
	    if (i != getMochila().getLast()) {
		System.out.print(", ");
	    }
	}
	System.out.println();
    }

}
