package br.pucpr;

public class QuickSort {

    public static void sort(Jogo[] array, String sortKey) {
        if (array == null || array.length == 0) return;
        sortRecursivo(array, 0, array.length - 1, sortKey);
    }

    private static void sortRecursivo(Jogo[] array, int low, int high, String sortKey) {
        if (low < high) {
            int pi = particionamento(array, low, high, sortKey);
            sortRecursivo(array, low, pi - 1, sortKey);  // Esquerda
            sortRecursivo(array, pi + 1, high, sortKey); // Direita
        }
    }

    private static int particionamento(Jogo[] arr, int low, int high, String sortKey) {
        Jogo pivot = arr[high];
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

    private static boolean comparar(Jogo a, Jogo b, String chave) {
        try {
            switch (chave.toLowerCase()) {
                case "titulo":
                    return a.getTitulo().compareToIgnoreCase(b.getTitulo()) <= 0;
                case "genero":
                    return a.getGenero().compareToIgnoreCase(b.getGenero()) <= 0;
                case "ano":
                    // CORREÇÃO: Parse direto, sem String.valueOf()
                    int anoA = Integer.parseInt(a.getAno());
                    int anoB = Integer.parseInt(b.getAno());
                    return anoA <= anoB;
                default:
                    System.out.println("QuickSort: Critério inválido. Usando título.");
                    return a.getTitulo().compareToIgnoreCase(b.getTitulo()) <= 0;
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro de formato de ano no QuickSort: " + e.getMessage());
            return false;
        }
    }

    private static void swap(Jogo[] arr, int i, int j) {
        Jogo temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}