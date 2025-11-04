package br.pucpr;
import java.util.LinkedList;
public class Biblioteca {

    private int tamanho;
    private LinkedList<Jogo>[] jogos;
    private boolean rehashing = false;

    public Biblioteca(int tamanho){
        this.tamanho = tamanho;
        this.jogos = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++){
            jogos[i] = new LinkedList<>();
        }
    }


    public double fatorDeCarga(){
        int numElementos = 0;
        for (LinkedList<Jogo> lista : jogos)
            numElementos += lista.size();
        return (double) numElementos / tamanho;
    }

    public int hash(String chave){
        return 0;
    }

    public void inserir(Jogo jogo) {
        int posicao = hash(jogo.getNome());

        boolean existe = false;
        for (Jogo jogoExistente : jogos[posicao]) {
            if (!jogoExistente.getNome().equals(jogo.getNome())) {
                existe = true;
            }
            ;
        }

        if (!existe) {
            if (!rehashing && fatorDeCarga() > 0.75) {
                rehash();
            }
            jogos[posicao].add(jogo);
        }
    }

        private void rehash(){
            rehashing = true;
            tamanho = tamanho * 2;
            LinkedList<Jogo>[] tabelaAntiga = jogos;
            LinkedList<Jogo>[] tabelaNova = new LinkedList[tamanho];
            jogos = tabelaNova;
            for (int i = 0; i < tamanho; i++){
                tabelaNova[i] = new LinkedList<>();
            }
            for (LinkedList<Jogo> listaJogos : tabelaAntiga){
                for (Jogo jogo : listaJogos){
                    inserir(jogo);
                }
            }
            rehashing = false;
        }


}
