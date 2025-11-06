package br.pucpr;

public class QuickSort {

    public void sort(Jogo[] array, int low, int high, String sortKey) {
        if (low < high) {
            int pi = particionamento(array, low, high, sortKey);

            // recursividade
            sort(array, low, pi - 1, sortKey); //esquerda do pivot
            sort(array, pi + 1, high, sortKey); //direita do pivot
        }
    }

    private int particionamento(Jogo[] arr, int low, int high, String sortKey) {
        Jogo pivot = arr[high]; //pivo como ultimo elemento
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparar(arr[j], pivot, sortKey)) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high); //coloca o pivô na posição correta
        return i + 1;
    }

    private boolean comparar(Jogo a, Jogo b, String chave) {
        switch (chave.toLowerCase()) {
            case "titulo":
                return a.getTitulo().compareTo(b.getTitulo()) <= 0;
            case "genero":
                return a.getGenero().compareTo(b.getGenero()) <= 0;
            case "ano":
                try {
                    int anoA = Integer.parseInt(String.valueOf(a.getAno()));
                    int anoB = Integer.parseInt(String.valueOf(b.getAno()));
                    return anoA <= anoB;
                } catch (NumberFormatException e) {
                    return false;
                }
            default:
                return a.getTitulo().compareTo(b.getTitulo()) <= 0;
        }
    }

    // Troca dois elementos no array
    private void swap(Jogo[] arr, int i, int j) {
        Jogo temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}