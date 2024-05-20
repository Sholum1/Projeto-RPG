package Npc;

public class LuaTutorial3 extends LuaTutorial2 {

	// Explica o funcionamento da Lua
    @Override
    public void dialogo() {
		System.out.println("Lua: Caso uma criatura esteja distante eu posso" +
				"emitir um raio de luz, que no impacto causará dano");
		System.out.println("Lua: Entretanto o poder do raio e o tempo" +
				"de recarga variam com a fase que eu estou");
		System.out.println("Lua: Minha fase é alterada a cada 29 turnos" +
				"fique sempre de olho nesta informação");
		System.out.println("Lua: Para me fazer refletir luz pressione a [l]");
		System.out.println("Lua: O raio de Luz percorrerá um trajeto e," +
				"caso encontre um inimigo, causará um dano");
    }
}
