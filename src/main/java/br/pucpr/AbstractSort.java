package br.pucpr;

public abstract class AbstractSort implements SortAlgorithm {

    protected boolean comparar(Jogo a, Jogo b, String criterio) {
        try {
            switch (criterio.toLowerCase()) {
                case "titulo":
                    return a.getTitulo().compareToIgnoreCase(b.getTitulo()) > 0;
                case "genero":
                    return a.getGenero().compareToIgnoreCase(b.getGenero()) > 0;
                case "ano":
                    return Integer.parseInt(a.getAno()) > Integer.parseInt(b.getAno());
                default:
                    System.out.println("Critério inválido. Usando título por padrão.");
                    return a.getTitulo().compareToIgnoreCase(b.getTitulo()) > 0;
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro ao comparar anos: " + e.getMessage());
            return false;
        }
    }

    protected void swap(Jogo[] arr, int i, int j) {
        Jogo temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}