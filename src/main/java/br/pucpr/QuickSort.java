package br.pucpr;

public class QuickSort extends AbstractSort{

    @Override
    public void sort(Jogo[] array, String sortKey) {
        if (array == null || array.length == 0) return;
        sortRecursivo(array, 0, array.length - 1, sortKey);
    }

    private void sortRecursivo(Jogo[] array, int low, int high, String sortKey) {
        if (low < high) {
            int pi = particionamento(array, low, high, sortKey);
            sortRecursivo(array, low, pi - 1, sortKey);  // Esquerda
            sortRecursivo(array, pi + 1, high, sortKey); // Direita
        }
    }

    private int particionamento(Jogo[] arr, int low, int high, String sortKey) {
        Jogo pivot = arr[high]; //PIVÔ É O ULTIMO
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // A lógica de 'comparar' aqui é (a <= b)
            if (comparar(arr[j], pivot, sortKey)) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

}