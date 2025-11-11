package br.pucpr;

public class BubbleSort extends AbstractSort {

    @Override
    public void sort(Jogo[] vetor, String criterio) {
        if (vetor == null || vetor.length == 0) return;
        bubbleSortRecursivo(vetor, criterio, vetor.length);
    }

    private void bubbleSortRecursivo(Jogo[] vetor, String criterio, int n) {
        if (n <= 1) return;

        boolean trocou = false;

        for (int j = 0; j < n - 1; j++) {

            if (comparar(vetor[j], vetor[j + 1], criterio)) {
                swap(vetor, j, j + 1);
                trocou = true;
            }
        }



        if (trocou) {
            bubbleSortRecursivo(vetor, criterio, n - 1);
        }
    }
}