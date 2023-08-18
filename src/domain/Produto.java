package domain;

import util.GeradorDeId;

public class Produto {

    private Integer id;
    private String nome;
    private Integer quantidade;

    public Produto (String nome, Integer quantidade) {
        this.id = GeradorDeId.gerar(nome, "produto");
        this.nome = nome;
        this.quantidade = quantidade;
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
