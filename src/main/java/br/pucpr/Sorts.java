package br.pucpr;
import java.util.LinkedList;
public class BubbleSort {

    private int tamanho;
    private LinkedList<Jogo>[] jogos;
    private final double fatorDeCarga = 0.75;
    private boolean rehashing = false;

    public BubbleSort(int tamanho){
        this.tamanho = tamanho;
        this.jogos = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++){
            jogos[i] = new LinkedList<>();
        }
    }

    public int hash(String chave){
        return 0;
    }

    public void inserir(Jogo jogo){
        int posicao = hash(jogo.getNome());

        boolean existe = false;
        for (Jogo jogoExistente : jogos[posicao]) {
            if (!jogoExistente.getNome().equals(jogo.getNome())){
                existe = true;
            };
        }

        if (!existe){
            if(!rehashing && fatorDeCarga() > 0.75){
                rehash();
            }
            jogos[posicao].add(jogo);
        }

    }
}
