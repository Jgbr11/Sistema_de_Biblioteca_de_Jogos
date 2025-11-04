package br.pucpr;
import java.util.LinkedList;
public class BubbleSort {
    private LinkedList<Jogo>[] jogos;

    public static void BubbleSort (Jogo[] vetor, String criterio){
        if (vetor == null) return;

        int n = vetor.length;
        boolean trocou;

        for (int i = 0; i < n - 1; i++) {
            trocou = false;
            for (int j = 0; j < n - 1 - i; j++) {
                boolean precisaTrocar = false;

                try {
                    switch (criterio.toLowerCase()) {
                        case "titulo":
                            if (vetor[j].getNome().compareToIgnoreCase(vetor[j+1].getNome()) > 0) {
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
                break;
            }
        }
    }
}