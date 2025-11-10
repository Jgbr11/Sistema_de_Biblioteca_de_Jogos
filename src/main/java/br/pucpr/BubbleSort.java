package br.pucpr;
import java.util.LinkedList;
public class BubbleSort {
    public static void sort(Jogo[] vetor, String criterio) {

        if (vetor == null || vetor.length == 0) return;

        bubbleSortRecursivo(vetor, criterio, vetor.length);
    }

    private static void bubbleSortRecursivo(Jogo[] vetor, String criterio, int n) {
        if (n <= 1) {
            return;
        }
        boolean trocou = false;
        for (int j = 0; j < n - 1; j++) {
            boolean precisaTrocar = false;

            try {

                switch (criterio.toLowerCase()) {
                    case "titulo":
                        if (vetor[j].getTitulo().compareToIgnoreCase(vetor[j+1].getTitulo()) > 0) {
                            precisaTrocar = true;
                        }
                        break;
                    case "genero":
                        if (vetor[j].getGenero().compareToIgnoreCase(vetor[j+1].getGenero()) > 0) {
                            precisaTrocar = true;
                        }
                        break;
                    case "ano":
                        int ano1 = Integer.parseInt(vetor[j].getAno());
                        int ano2 = Integer.parseInt(vetor[j+1].getAno());
                        if (ano1 > ano2) {
                            precisaTrocar = true;
                        }
                        break;
                    default:
                        System.out.println("Critério de ordenação inválido: " + criterio);
                        return;
                }

            } catch (NumberFormatException e) {
                System.out.println("Erro: Não foi possível ordenar por ano, pois '" +
                        vetor[j].getAno() + "' ou '" + vetor[j+1].getAno() +
                        "' não é um número válido.");
                return;
            }

            if (precisaTrocar) {
                Jogo temp = vetor[j];
                vetor[j] = vetor[j+1];
                vetor[j+1] = temp;
                trocou = true;
            }
        }

        if (!trocou) {
            return;
        }

        bubbleSortRecursivo(vetor, criterio, n - 1);
    }
}