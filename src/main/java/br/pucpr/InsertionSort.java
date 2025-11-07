package br.pucpr;

public class InsertionSort {

    // Método público principal (static, como os outros)
    public static void sort(Jogo[] vetor, String criterio) {
        if (vetor == null || vetor.length == 0) return;

        // Chama a versão recursiva
        sortRecursivo(vetor, vetor.length, criterio);
    }

    // Implementação recursiva do Insertion Sort
    private static void sortRecursivo(Jogo[] vetor, int n, String criterio) {
        // Caso base: se o vetor tem 1 ou 0 elementos, já está ordenado
        if (n <= 1) {
            return;
        }

        // Ordena recursivamente os primeiros n-1 elementos
        sortRecursivo(vetor, n - 1, criterio);

        // Insere o último elemento (vetor[n-1]) na sua posição correta
        // dentro do sub-vetor já ordenado (vetor[0...n-2])
        Jogo ultimoElemento = vetor[n - 1];
        int j = n - 2;

        // Move os elementos de vetor[0...n-2] que são maiores que 'ultimoElemento'
        // para uma posição à frente
        while (j >= 0 && comparar(vetor[j], ultimoElemento, criterio)) {
            vetor[j + 1] = vetor[j];
            j--;
        }
        vetor[j + 1] = ultimoElemento;
    }

    // Método auxiliar para comparar dois jogos
    private static boolean comparar(Jogo jogo1, Jogo jogo2, String criterio) {
        // Retorna true se jogo1 > jogo2
        try {
            switch (criterio.toLowerCase()) {
                case "titulo":
                    return jogo1.getTitulo().compareToIgnoreCase(jogo2.getTitulo()) > 0;
                case "genero":
                    return jogo1.getGenero().compareToIgnoreCase(jogo2.getGenero()) > 0;
                case "ano":
                    // CORREÇÃO: Precisa fazer o parse de String para int
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