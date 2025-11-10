package br.pucpr;

public class InsertionSort {

    public static void sort(Jogo[] vetor, String criterio) {
        if (vetor == null || vetor.length == 0) return;

        sortRecursivo(vetor, vetor.length, criterio);
    }

    private static void sortRecursivo(Jogo[] vetor, int n, String criterio) {
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
    }

    private static boolean comparar(Jogo jogo1, Jogo jogo2, String criterio) {
        try {
            switch (criterio.toLowerCase()) {
                case "titulo":
                    return jogo1.getTitulo().compareToIgnoreCase(jogo2.getTitulo()) > 0;
                case "genero":
                    return jogo1.getGenero().compareToIgnoreCase(jogo2.getGenero()) > 0;
                case "ano":
                    int ano1 = Integer.parseInt(jogo1.getAno());
                    int ano2 = Integer.parseInt(jogo2.getAno());
                    return ano1 > ano2;
                default:
                    System.out.println("InsertionSort: Critério inválido. Usando título.");
                    return jogo1.getTitulo().compareToIgnoreCase(jogo2.getTitulo()) > 0;
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro de formato de ano no InsertionSort: " + e.getMessage());
            return false;
        }
    }
}