package br.pucpr;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private int tamanho;
    private LinkedList<Jogo>[] jogos;
    private boolean rehashing = false;
    private int numElementos = 0;

    public Biblioteca(int tamanho) {
        this.tamanho = tamanho;
        this.jogos = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            jogos[i] = new LinkedList<>();
        }
    }

    public double fatorDeCarga() {
        return (double) numElementos / tamanho;
    }

    public int hash(String chave) {
        return Math.abs(chave.hashCode() % tamanho);
    }

    public void inserir(Jogo jogo) {
        int posicao = hash(jogo.getTitulo());

        for (Jogo jogoExistente : jogos[posicao]) {
            if (jogoExistente.getTitulo().equalsIgnoreCase(jogo.getTitulo())) {
                System.out.println("Erro: Jogo '" + jogo.getTitulo() + "' já existe.");
                return;
            }
        }

        if (!rehashing && fatorDeCarga() > 0.75) {
            System.out.println("Fator de carga > 0.75. Realizando rehash...");
            rehash();
            posicao = hash(jogo.getTitulo());
        }

        jogos[posicao].add(jogo);
        numElementos++;
        System.out.println("Jogo '" + jogo.getTitulo() + "' inserido na posição " + posicao);
    }

    private void rehash() {
        rehashing = true;
        LinkedList<Jogo>[] tabelaAntiga = jogos;
        int tamanhoAntigo = tamanho;

        tamanho = tamanho * 2;
        this.jogos = new LinkedList[tamanho];
        this.numElementos = 0;

        for (int i = 0; i < tamanho; i++) {
            jogos[i] = new LinkedList<>();
        }

        for (int i = 0; i < tamanhoAntigo; i++) {
            for (Jogo jogo : tabelaAntiga[i]) {
                inserir(jogo);
            }
        }

        rehashing = false;
    }

    public Jogo buscar(String titulo) {
        int posicao = hash(titulo);
        for (Jogo jogo : jogos[posicao]) {
            if (jogo.getTitulo().equalsIgnoreCase(titulo)) {
                return jogo;
            }
        }
        return null;
    }

    public boolean remover(String titulo) {
        int posicao = hash(titulo);
        Jogo jogoParaRemover = null;

        for (Jogo jogo : jogos[posicao]) {
            if (jogo.getTitulo().equalsIgnoreCase(titulo)) {
                jogoParaRemover = jogo;
                break;
            }
        }

        if (jogoParaRemover != null) {
            jogos[posicao].remove(jogoParaRemover);
            numElementos--;
            System.out.println("Jogo '" + titulo + "' removido.");
            return true;
        } else {
            System.out.println("Erro: Jogo '" + titulo + "' não encontrado para remoção.");
            return false;
        }
    }

    public Jogo[] exportarParaVetor() {
        Jogo[] vetorJogos = new Jogo[numElementos];
        int indiceVetor = 0;

        for (int i = 0; i < tamanho; i++) {
            for (Jogo jogo : jogos[i]) {
                if (indiceVetor < numElementos) {
                    vetorJogos[indiceVetor] = jogo;
                    indiceVetor++;
                }
            }
        }
        return vetorJogos;
    }
}