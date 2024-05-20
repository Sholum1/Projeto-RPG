package Npc;

import Tabuleiro.Elemento;

public abstract class Npc implements Elemento {

   protected final String nome;

   protected Npc(String nome) {
       this.nome = nome;
    }

    // Só possui um get, já que é final
    public String getNome(){
        return nome;
    }

    // Apresenta os diálogos do NPC
    protected abstract void dialogo();
}
