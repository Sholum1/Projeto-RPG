package Npc;


/**
 
 * LuaTutorial é uma classe criada para que a personagem Lua dê ao usuário um
 * tutorial das mecânicas gerais do jogo.
 * 
 * Como utiliza basicamente diálogos para tal, herda de Npc e sobrescreve o
 * método "dialogo".
 */
public class LuaTutorial extends Npc {

    // Construtor
    public LuaTutorial() {
	super("Lua");
    }

    // Apresenta os diálogos iniciais
    @Override
    public void dialogo(int dialogo) {
	switch (dialogo) {
	case 1:
	    System.out.println("Lua: Vamos aprender a nos mover neste vasto "
			       + "Oceano.");
	    System.out.println("Lua: Pressione [w] para subir, [s] para descer,"
			       + " [a] para ir para a esquerda e [d]"
			       + " para a direita.");
	    System.out.println("Lua: Volte aqui para conversar comigo quando se"
			       + " sentir pronto.");
	    break;
	case 2:
	    System.out.println("Lua: Muito bem!");
	    System.out.println("Lua: Cuidado! Um baiacu está vindo em sua"
			       + " direção.");
	    System.out.println("Lua: Venha, pegue a espada de São Jorge.");
	    System.out.println("Lua: Toda vez que se aproximar de uma criatura,"
			       +" pressione [j] para atacar.");
	    break;
	case 3:
	    System.out.println("Lua: Caso uma criatura esteja distante eu posso"
			       + " emitir um raio de luz, que no impacto causará"
			       + " dano.");
	    System.out.println("Lua: Entretanto o poder do raio e o tempo" +
			       " de recarga variam com a fase na qual eu estou.");
	    System.out.println("Lua: Minha fase é alterada a cada 29 turnos." +
			       " Fique sempre de olho nesta informação!");
		System.out.println("Lua: Para me fazer refletir luz pressione"
			       + " [l], seguido de [Enter] e, enfim, dê a direção" +
				   " [w, a, s, d].");
	    System.out.println("Lua: O raio de luz percorrerá um trajeto e," +
			       " caso encontre um inimigo, causará dano.");
	    System.out.println("Lua: Aqui o meu ataque não terá tempo de recarga"
			       + " e, a cada ataque, mudarei de fase.");
		System.out.println("Lua: Mas lembre-se: a cada fase meu brilho muda,"
				   + " portanto, a distância que meus raios alcançam, também");
	    break;
	case 4 :
	    System.out.println("Lua: Você está com vida baixa por causa da" +
			       " batalha.");
	    System.out.println("Lua: Em sua baralha contra o baiacu você pegou" +
			       "uma alga, você pode comê-la.");
	    System.out.println("Lua: Você se sentirá cansado mas recuperará vida");
	    System.out.println("Lua: Todo item causa um efeito positivo e "
			       + " um negativo, cuidado ao utilizá-los!");
	    System.out.println("Lua: Para conferir seus itens pressione [m].");
	    System.out.println("Lua: Para utilizar um item pressione [u]" +
			       " seguido de sua posição na mochila." +
			       " (começando de 1)");
	    System.out.println("Lua: Por fim, para desistir de utilizar um item"
			       + " pressione [n].");
	    break;
	case 5:
	    System.out.println("Lua: Fim do tutorial, obrigado por jogar!");
	    break;
	default:
	    break;
	}
    }
}
