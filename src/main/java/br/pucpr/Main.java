package br.pucpr;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    // A biblioteca é o "banco de dados" da nossa aplicação
    private Biblioteca biblioteca = new Biblioteca(10); // Tamanho inicial de 10

    // Componentes da UI
    private TextField tfTitulo = new TextField();
    private TextField tfPlataforma = new TextField();
    private TextField tfGenero = new TextField();
    private TextField tfAno = new TextField();

    // Campo de texto para buscar/remover
    private TextField tfBuscarRemover = new TextField();

    // Área para mostrar resultados
    private TextArea taResultados = new TextArea();

    // ComboBox para escolher o algoritmo
    private ComboBox<String> cbAlgoritmos = new ComboBox<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Biblioteca de Jogos");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // 1. Topo: Formulário de Inserção
        root.setTop(criarFormularioInsercao());

        // 2. Centro: Área de Resultados
        taResultados.setEditable(false);
        taResultados.setWrapText(true);
        root.setCenter(taResultados);

        // 3. Direita: Ações de Busca e Ordenação
        root.setRight(criarPainelAcoes());

        // Inicializa a ComboBox
        cbAlgoritmos.getItems().addAll("BubbleSort", "InsertionSort", "QuickSort");
        cbAlgoritmos.setValue("QuickSort"); // Padrão

        // Adiciona alguns jogos de exemplo
        adicionarExemplos();
        listarTodosJogos();

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox criarFormularioInsercao() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Título:"), 0, 0);
        tfTitulo.setPromptText("Ex: Elden Ring");
        grid.add(tfTitulo, 1, 0);

        grid.add(new Label("Plataforma:"), 0, 1);
        tfPlataforma.setPromptText("Ex: PC");
        grid.add(tfPlataforma, 1, 1);

        grid.add(new Label("Gênero:"), 0, 2);
        tfGenero.setPromptText("Ex: RPG");
        grid.add(tfGenero, 1, 2);

        grid.add(new Label("Ano:"), 0, 3);
        tfAno.setPromptText("Ex: 2022");
        grid.add(tfAno, 1, 3);

        Button btnAdicionar = new Button("Adicionar Jogo");
        btnAdicionar.setOnAction(e -> adicionarJogo());

        vbox.getChildren().addAll(new Label("--- Adicionar Novo Jogo ---"), grid, btnAdicionar);
        return vbox;
    }

    private VBox criarPainelAcoes() {
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(0, 0, 0, 20)); // Espaço à esquerda
        vbox.setAlignment(Pos.TOP_CENTER);

        // --- Seção de Busca/Remoção ---
        VBox boxBusca = new VBox(10);
        boxBusca.setAlignment(Pos.CENTER);
        tfBuscarRemover.setPromptText("Digite o título aqui...");

        Button btnBuscar = new Button("Buscar Jogo");
        btnBuscar.setOnAction(e -> buscarJogo());

        Button btnRemover = new Button("Remover Jogo");
        btnRemover.setOnAction(e -> removerJogo());

        Button btnListarTodos = new Button("Listar Todos (Sem Ordenar)");
        btnListarTodos.setOnAction(e -> listarTodosJogos());

        boxBusca.getChildren().addAll(
                new Label("--- Buscar / Remover ---"),
                tfBuscarRemover,
                new HBox(10, btnBuscar, btnRemover),
                btnListarTodos
        );

        // --- Seção de Ordenação ---
        VBox boxOrdenacao = new VBox(10);
        boxOrdenacao.setAlignment(Pos.CENTER);

        Button btnOrderTitulo = new Button("Ordenar por Título");
        btnOrderTitulo.setOnAction(e -> ordenarJogos("titulo"));

        Button btnOrderGenero = new Button("Ordenar por Gênero");
        btnOrderGenero.setOnAction(e -> ordenarJogos("genero"));

        Button btnOrderAno = new Button("Ordenar por Ano");
        btnOrderAno.setOnAction(e -> ordenarJogos("ano"));

        boxOrdenacao.getChildren().addAll(
                new Label("--- Ordenar Biblioteca ---"),
                new Label("Algoritmo:"),
                cbAlgoritmos,
                btnOrderTitulo,
                btnOrderGenero,
                btnOrderAno
        );

        vbox.getChildren().addAll(boxBusca, new Separator(), boxOrdenacao);
        return vbox;
    }

    // --- Métodos de Ação ---

    private void adicionarJogo() {
        String titulo = tfTitulo.getText();
        String plataforma = tfPlataforma.getText();
        String genero = tfGenero.getText();
        String ano = tfAno.getText();

        if (titulo.isEmpty() || plataforma.isEmpty() || genero.isEmpty() || ano.isEmpty()) {
            taResultados.setText("ERRO: Todos os campos são obrigatórios!");
            return;
        }

        try {
            // Validação simples do ano
            Integer.parseInt(ano);
        } catch (NumberFormatException e) {
            taResultados.setText("ERRO: O ano deve ser um número válido!");
            return;
        }

        Jogo novoJogo = new Jogo(titulo, plataforma, genero, ano);
        biblioteca.inserir(novoJogo);

        taResultados.setText("Jogo '" + titulo + "' adicionado com sucesso!");
        limparCamposInsercao();
    }

    private void buscarJogo() {
        String titulo = tfBuscarRemover.getText();
        if (titulo.isEmpty()) {
            taResultados.setText("ERRO: Digite um título para buscar.");
            return;
        }

        Jogo encontrado = biblioteca.buscar(titulo);
        if (encontrado != null) {
            taResultados.setText("Jogo Encontrado:\n" + encontrado.toString());
        } else {
            taResultados.setText("Jogo '" + titulo + "' não encontrado.");
        }
    }

    private void removerJogo() {
        String titulo = tfBuscarRemover.getText();
        if (titulo.isEmpty()) {
            taResultados.setText("ERRO: Digite um título para remover.");
            return;
        }

        boolean removido = biblioteca.remover(titulo);
        if (removido) {
            taResultados.setText("Jogo '" + titulo + "' removido com sucesso.");
            listarTodosJogos(); // Atualiza a lista
        } else {
            taResultados.setText("Jogo '" + titulo + "' não encontrado.");
        }
    }

    private void listarTodosJogos() {
        Jogo[] vetor = biblioteca.exportarParaVetor();
        exibirVetorNoResultado(vetor, "Lista de Jogos (Não Ordenada)");
    }

    private void ordenarJogos(String criterio) {
        // 1. Exporta os dados da Tabela Hash para um vetor
        Jogo[] vetor = biblioteca.exportarParaVetor();

        if (vetor.length == 0) {
            taResultados.setText("Não há jogos para ordenar.");
            return;
        }

        // 2. Pega o algoritmo escolhido
        String algoritmo = cbAlgoritmos.getValue();

        // Medir o tempo (Bônus, mas bom para análise de complexidade)
        long startTime = System.nanoTime();

        // 3. Executa o algoritmo de ordenação escolhido
        switch (algoritmo) {
            case "BubbleSort":
                BubbleSort.sort(vetor, criterio);
                break;
            case "InsertionSort":
                InsertionSort.sort(vetor, criterio);
                break;
            case "QuickSort":
                // QuickSort (static)
                QuickSort.sort(vetor, criterio);
                break;
            default:
                taResultados.setText("ERRO: Algoritmo desconhecido.");
                return;
        }

        long endTime = System.nanoTime();
        double duracaoMs = (endTime - startTime) / 1_000_000.0; // Convertendo nanos para milissegundos

        // 4. Exibe o resultado
        String titulo = "Jogos Ordenados por '" + criterio + "' usando " + algoritmo;
        String timing = String.format("\nTempo de execução: %.4f ms", duracaoMs);

        exibirVetorNoResultado(vetor, titulo + timing);
    }

    // --- Métodos Utilitários ---

    private void exibirVetorNoResultado(Jogo[] vetor, String titulo) {
        if (vetor.length == 0) {
            taResultados.setText(titulo + "\n\n(Nenhum jogo na biblioteca)");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(titulo).append("\n\n");
        for (Jogo jogo : vetor) {
            sb.append(jogo.toString()).append("\n");
        }
        taResultados.setText(sb.toString());
    }

    private void limparCamposInsercao() {
        tfTitulo.clear();
        tfPlataforma.clear();
        tfGenero.clear();
        tfAno.clear();
    }

    private void adicionarExemplos() {
        biblioteca.inserir(new Jogo("Elden Ring", "PC", "RPG", "2022"));
        biblioteca.inserir(new Jogo("The Witcher 3", "PC", "RPG", "2015"));
        biblioteca.inserir(new Jogo("Cyberpunk 2077", "PS5", "RPG", "2020"));
        biblioteca.inserir(new Jogo("Hollow Knight", "Switch", "Metroidvania", "2017"));
        biblioteca.inserir(new Jogo("Stardew Valley", "PC", "Simulação", "2016"));
        biblioteca.inserir(new Jogo("Red Dead Redemption 2", "PS4", "Ação", "2018"));
        biblioteca.inserir(new Jogo("Celeste", "PC", "Plataforma", "2018"));
    }
}