package Npc;

/**
 * LuaTutorial2
 */
public class LuaTutorial2 extends LuaTutorial1 {

	// Apresenta os diálogos referentes ao combate
    @Override
    public void dialogo() {
		System.out.println("Lua: Muito bem!");
		System.out.println("Lua: Cuidado! Um baiacu está vindo em sua direção");
		System.out.println("Lua: Venha, pegue a espada de São Jorge");
		System.out.println("Lua: Toda vez que se aproximar de uma criatura,"
				+"pressione [a] para atacar");
    }
}
