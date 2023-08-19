package domain;

import domain.annotation.NomeTabela;
import util.GeradorDeId;

@NomeTabela(valor="produtos")
public class Produto {

    private Integer id;
    private String nome;
    private String codigo;
    private Integer quantidade;

    public Produto (String nome, Integer quantidade, String codigo) {
        this.id = GeradorDeId.gerar(nome, "produto");
        this.nome = nome;
        this.quantidade = quantidade;
        this.codigo = codigo;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
