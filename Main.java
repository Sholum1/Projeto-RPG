import java.util.ArrayList;
import java.util.Scanner;

import Criatura.*;
import Item.*;
import Npc.*;
import Tabuleiro.*;
import Personagem.*;

/** Main: Inicia o jogo
 *
 */
public class Main {
    // Método para calcular a vida e mostrar na tela
    public static void calculaVida (TemVida elem) {
	if (elem instanceof Criatura) {
	    System.out.print(((Criatura)elem).getNome());
	} else if (elem instanceof Personagem) {
	    System.out.print("Giu");
	}
	System.out.println(" está com " + elem.getVida() + " de vida");
    }

    // Método para mostrar quando uma opção é inválida
    public static void err () {
	System.err.println("Opção inválida, tente novamente");
    }

    public static void main(String[] args) {
	boolean respInvalida = true;
	Scanner scan = new Scanner(System.in);
	System.out.println("\t\t   Afundando");
	System.out.println("Bem vindo ao nosso jogo!");
	while (respInvalida) {
	    System.out.println("Deseja iniciar o tutorial? [S/n]");
	    String opcao = scan.nextLine();
	    // Inicia o jogo
	    if (opcao.equalsIgnoreCase("Sim") || opcao.equalsIgnoreCase("S")) {
		ArrayList<Npc> npcs = new ArrayList<Npc>();
		ArrayList<Criatura> criaturas = new ArrayList<Criatura>();
		ArrayList<Item> itens = new ArrayList<Item>();
		Tabuleiro tabuleiro = new Tabuleiro(8);
		Personagem giu = new Personagem();
		tabuleiro.adicionarElemento(giu, 4, 4);
		LuaTutorial lua = new LuaTutorial();
		tabuleiro.adicionarElemento(lua, 3, 4);
		npcs.add(lua);
		boolean viva = true;
		int dialogoLua = 1;
		while (viva) {
		    tabuleiro.mostraMapa();
		    boolean pertoNpc = false, pertoItem = false;
		    Npc npc = null;
		    for (Npc npcIterado : npcs) {
			pertoNpc = tabuleiro.interagir(giu, npcIterado);
			if (pertoNpc) {
			    npc = npcIterado;
			    break;
			}
		    }
		    Item item = null;
		    for (Item itemIterado : itens) {
			pertoItem = tabuleiro.interagir(giu, itemIterado);
			if (pertoItem) {
			    item = itemIterado;
			    break;
			}
		    }
		    opcao = scan.nextLine();
		    if (opcao.equals("m")) {
			giu.verMochila();
			opcao = scan.nextLine();
		    }
		    if (opcao.equals("u")) {
			boolean opcaoInvalida = true;
			while(opcaoInvalida) {
			    int pos = -1;
			    if (scan.hasNextInt()) pos = scan.nextInt();
			    else opcao = scan.nextLine();
			    if (pos <= giu.getMochila().size() && pos > 0) {
				giu.consumirItem(giu.getMochila().get(pos - 1));
				opcaoInvalida = false;
				opcao = scan.nextLine();
				break;
			    } else if (opcao.equals("n")) {
				opcaoInvalida = false;
				break;
			    } else {
				err();
			    }
			}
		    }
		    if (item != null && opcao.equals(("p"))) {
			giu.adicionarItem(item);
			tabuleiro.removerElemento(item);
			itens.remove(item);
		    }
		    tabuleiro.moverPersonagem(opcao, giu);
		    if (opcao.equals("l")) {
			opcao = scan.nextLine();
			Criatura criatura = tabuleiro.luaAtacar(opcao, giu);
			if(criatura != null)
			    calculaVida(criatura);
			giu.getLua().mudaFase(29);
		    }
		    for (Criatura criatura : criaturas) {
			if (tabuleiro.interagir(giu, criatura)) {
			    if(opcao.equals("j")) {
				tabuleiro.atacar(giu, criatura);
				calculaVida(criatura);
			    }
			    tabuleiro.atacar(criatura, giu);
			    calculaVida(giu);
			    viva = giu.perdeu();
			    if (!viva) {
				System.out.println("Você morreu");
				break;
			    }
			} else {
			    tabuleiro.moverCriatura(criatura, giu);
			}
			if (criatura != null) {
			    if (tabuleiro.faleceu(criatura)) {
				System.out.println(criatura.getNome() +
						   " morreu");
				if(tabuleiro.drop(criatura)) {
				    System.out.println("E soltou um item");
				    itens.add(criatura.getDrop());
				}
				criaturas.remove(criatura);
				break;
			    }
			}
		    }
		    if (npc != null) {
			if (npc.equals(lua) && opcao.equals("i")) {
			    lua.dialogo(dialogoLua++);
			    if (dialogoLua == 3) {
				Alga alga = new Alga();
				Criatura baiacu = new Criatura(alga, 2, 9, "Baiacu");
				tabuleiro.removerElemento(lua);
				npcs.remove(lua);
				tabuleiro.adicionarElemento(baiacu, 7, 7);
				criaturas.add(baiacu);
			    } else if (dialogoLua == 4) {
				Criatura polvo = new Criatura(20, 18, "polvo");
				tabuleiro.mover(0, 4, giu);
				tabuleiro.removerElemento(lua);
				npcs.remove(lua);
				tabuleiro.adicionarElemento(polvo, 7, 4);
				criaturas.add(polvo);
			    } else if (dialogoLua == 5) {
				tabuleiro.removerElemento(lua);
				npcs.remove(lua);
			    } else if (dialogoLua > 5) {
				viva = false;
				respInvalida = false;
				break;
			    }
			}
		    }
		    if (tabuleiro.passou(giu)) {
			int[] posGiu = tabuleiro.achePosicao(giu);
			tabuleiro.adicionarElemento(lua, posGiu[0] != 7
						    ? posGiu[0] + 1
						    : posGiu[0] - 1, posGiu[1]);
			npcs.add(lua);
		    }
		}
	    } else if (opcao.equalsIgnoreCase("Não")
		       || opcao.equalsIgnoreCase("N")
		       || opcao.equalsIgnoreCase("Nao")) {
		respInvalida = false;
		break;
	    } else {
		err();
	    }
	}
	scan.close();
    }
}
