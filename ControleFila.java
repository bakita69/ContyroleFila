package controlefila;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

public class SistemaControleImpressao { // Esta classe agora é a principal

    private Deque<Documento> filaImpressao;
    private Set<Integer> idsGerados;

    public SistemaControleImpressao() {
        this.filaImpressao = new LinkedList<>();
        this.idsGerados = new HashSet<>();
    }

    class Documento {
        private int id;
        private String tipo;
        private int paginas;

        public Documento(int id, String tipo, int paginas) {
            this.id = id;
            this.tipo = tipo;
            this.paginas = paginas;
        }

        public int getId() {
            return id;
        }

        public String getTipo() {
            return tipo;
        }

        public int getPaginas() {
            return paginas;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Tipo: " + tipo + ", Páginas: " + paginas;
        }
    }

    public void adicionar_documento_normal(Documento documento) {
        filaImpressao.addLast(documento);
        System.out.println("Documento adicionado normalmente: " + documento);
    }

    public void adicionar_documento_urgente(Documento documento) {
        filaImpressao.addFirst(documento);
        System.out.println("Documento urgente adicionado: " + documento);
    }

    public Documento processar_proximo() {
        if (fila_vazia()) {
            System.out.println("Fila de impressão vazia. Nenhum documento para processar.");
            return null;
        }
        Documento documentoProcessado = filaImpressao.removeFirst();
        System.out.println("Processando próximo documento: " + documentoProcessado);
        return documentoProcessado;
    }

    public Documento processar_ultimo() {
        if (fila_vazia()) {
            System.out.println("Fila de impressão vazia. Nenhum documento para processar.");
            return null;
        }
        Documento documentoProcessado = filaImpressao.removeLast();
        System.out.println("Processando último documento: " + documentoProcessado);
        return documentoProcessado;
    }

    public void visualizar_fila() {
        if (fila_vazia()) {
            System.out.println("A fila de impressão está vazia.");
            return;
        }
        System.out.println("\n--- Documentos na Fila de Impressão ---");
        for (Documento doc : filaImpressao) {
            System.out.println(doc);
        }
        System.out.println("--------------------------------------\n");
    }

    public boolean fila_vazia() {
        return filaImpressao.isEmpty();
    }

    public int total_documentos() {
        return filaImpressao.size();
    }

    public int total_paginas() {
        int totalPaginas = 0;
        for (Documento doc : filaImpressao) {
            totalPaginas += doc.getPaginas();
        }
        return totalPaginas;
    }

    private int gerarIdUnico() {
        Random random = new Random();
        int id;
        do {
            id = random.nextInt(1000) + 1;
        } while (idsGerados.contains(id));
        idsGerados.add(id);
        return id;
    }

    private String gerarTipoDocumento() {
        String[] tipos = {"PDF", "TXT", "JPG", "PNG", "DOCX", "XLSX"};
        Random random = new Random();
        return tipos[random.nextInt(tipos.length)];
    }

    private int gerarQuantidadePaginas() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    public Documento criarNovoDocumento() {
        int id = gerarIdUnico();
        String tipo = gerarTipoDocumento();
        int paginas = gerarQuantidadePaginas();
        return new Documento(id, tipo, paginas);
    }

    public static void main(String[] args) {
        SistemaControleImpressao sistema = new SistemaControleImpressao();

        System.out.println("--- Teste do Sistema de Controle de Impressão ---");

        System.out.println("\nAdicionando documentos normais...");
        sistema.adicionar_documento_normal(sistema.criarNovoDocumento());
        sistema.adicionar_documento_normal(sistema.criarNovoDocumento());
        sistema.adicionar_documento_normal(sistema.criarNovoDocumento());
        sistema.visualizar_fila();

        System.out.println("\nAdicionando documento urgente...");
        sistema.adicionar_documento_urgente(sistema.criarNovoDocumento());
        sistema.visualizar_fila();

        System.out.println("Total de documentos na fila: " + sistema.total_documentos());
        System.out.println("Total de páginas na fila: " + sistema.total_paginas());

        System.out.println("\nProcessando o próximo documento...");
        sistema.processar_proximo();
        sistema.visualizar_fila();

        System.out.println("\nAdicionando mais documentos...");
        sistema.adicionar_documento_normal(sistema.criarNovoDocumento());
        sistema.adicionar_documento_urgente(sistema.criarNovoDocumento());
        sistema.visualizar_fila();

        System.out.println("\nProcessando o último documento...");
        sistema.processar_ultimo();
        sistema.visualizar_fila();

        System.out.println("\nProcessando todos os documentos restantes...");
        while (!sistema.fila_vazia()) {
            sistema.processar_proximo();
        }
        sistema.visualizar_fila();

        sistema.processar_proximo();
        sistema.processar_ultimo();

        System.out.println("\n--- Fim do Teste ---");
    }
}