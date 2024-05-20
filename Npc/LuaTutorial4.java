package Npc;

/**
 * LuaTutorial2
 */
public class LuaTutorial4 extends LuaTutorial3 {

	// Mostra como usar a mochila e os itens
    @Override
    public void dialogo() {
		System.out.println("Lua: Você está com vida baixa por causa da" +
				"batalha");
		System.out.println("Lua: Tome uma Alga, você se sentirá cansado" +
				"mas recuperará vida");
		System.out.println("Lua: Todo item causa um efeito positivo e "
				+"negativo, cuidado ao utilizá-los");
		System.out.println("Lua: Para conferir seus itens pressione [n]" +
				"e, para utilizar um item pressione [u]");
    }
}
