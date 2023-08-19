package domain;

import util.GeradorDeId;

public class Produto {

    private Integer id;
    private String nome;
    private String codigo;
    private Integer quantidade;

    public static String nomeTabela;

    public Produto (String nome, Integer quantidade, String codigo) {
        this.id = GeradorDeId.gerar(nome, "produto");
        this.nome = nome;
        this.quantidade = quantidade;
        this.codigo = codigo;
    }

    public static void setNomeTabela (String nomeTabela) {
        nomeTabela = nomeTabela;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
