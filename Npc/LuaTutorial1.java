package Npc;

/**
 * LuaTutorial1
 */
public class LuaTutorial1 extends Npc {

	// Construtor
    public LuaTutorial1() {
	    super("Lua");
    }

	// Apresenta os diálogos iniciais
    @Override
    public void dialogo() {
		System.out.println("Lua: Vamos aprender a nos mover neste vasto"
				+ "Oceano");
		System.out.println("Lua: Pressione [w] para subir, [s] para descer,"
				+"[a] para ir para a esquerda e [d] para a direita");
		System.out.println("Lua: Volte aqui para conversar comigo quando se"
				+ "sentir pronto");
    }
}
