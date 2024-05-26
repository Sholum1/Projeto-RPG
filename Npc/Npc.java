package Npc;

import Tabuleiro.Elemento;


/**

 * A classe Npc será utilizada para implementar e instanciar os personagens que
 * não sejam jogáveis, ou seja, os seres que surgirem ao longo da história e
 * cujos diálogos contribuirão para sua construção.
 * 
 * Por esse motivo a classe foi implementada como abstrata e conta com o método
 * "dialogo".
 * 
 * Também implementa a interface "Elemento", tendo em vista que os NPCs
 * constituem elementos no mapa (tabuleiro).
 */
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
    protected abstract void dialogo(int opcao);
}
