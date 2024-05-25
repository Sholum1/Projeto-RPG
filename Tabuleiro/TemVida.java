package Tabuleiro;

/**
 * TemVida: Interface de marcação para as classes que possuem vida
 * e podem dar dano
 */
public interface TemVida extends Elemento {
    int getDano();
    void setVida(int vida);
    int getVida();
}
