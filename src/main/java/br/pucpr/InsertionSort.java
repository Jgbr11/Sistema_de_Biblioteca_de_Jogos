package br.pucpr;

public class InsertionSort extends AbstractSort{

    public void sort(Jogo[] vetor, String criterio) {
        if (vetor == null || vetor.length == 0) return;

        sortRecursivo(vetor, vetor.length, criterio);
    }

    private void sortRecursivo(Jogo[] vetor, int n, String criterio) {
        if (n <= 1) {
            return;
        }

        sortRecursivo(vetor, n - 1, criterio);

        Jogo ultimoElemento = vetor[n - 1];
        int j = n - 2;

        while (j >= 0 && comparar(vetor[j], ultimoElemento, criterio)) {
            vetor[j + 1] = vetor[j];
            j--;
        }
        vetor[j + 1] = ultimoElemento;
        System.out.print("Fim da passagem (n=" + n + "): ");
        for (Jogo jogo : vetor) {
            System.out.print(jogo.getAno() + " ");
        }
        System.out.println();
    }

}