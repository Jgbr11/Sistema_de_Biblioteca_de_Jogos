package br.pucpr;

import java.util.LinkedList;

public class InsertionSort {
    private LinkedList<Jogo>[] jogos;

    public InsertionSort(LinkedList<Jogo>[] jogos){
        this.jogos = jogos;
    }

    public LinkedList<Jogo> sort(LinkedList<Jogo> jogos, String criterio){
        sortRec(jogos, jogos.size(), criterio);
        return jogos;
    }

    private void sortRec(LinkedList<Jogo> jogos, int elem, String criterio){

        if (elem <= 1){
            return;
        }

        sortRec(jogos, elem - 1, criterio);

        Jogo chave = jogos.get(elem - 1);
        int anterior = elem - 2;

        while(anterior >= 0 && comparar(jogos.get(anterior), chave, criterio)){
            jogos.set((anterior + 1), jogos.get(anterior));
            anterior--;
        }

        jogos.set((anterior + 1), chave);

    }

    private boolean comparar(Jogo jogo1, Jogo jogo2, String criterio){
        boolean precisaTrocar = false;

        switch (criterio.toLowerCase()){
            case "titulo":
                if (jogo1.getTitulo().compareToIgnoreCase(jogo2.getTitulo()) > 0){
                    precisaTrocar = true;
                }
                break;

            case "genero":
                if (jogo1.getGenero().compareToIgnoreCase(jogo2.getGenero()) > 0) {
                    precisaTrocar = true;
                }
                break;

            case "ano":
                int ano1 = jogo1.getAno();
                int ano2 = jogo2.getAno();

                if (ano1 > ano2){
                    precisaTrocar = true;
                }

        }

        return precisaTrocar;
    }
}
