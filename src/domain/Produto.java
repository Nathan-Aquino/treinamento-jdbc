package domain;

import domain.annotation.NomeTabela;
import util.GeradorDeId;

import java.util.Map;

@NomeTabela(valor="produtos")
public class Produto {

    private String id;
    private String nome;
    private String codigo;
    private Integer quantidade;

    public Produto (String nome, Integer quantidade, String codigo) {
        this.id = GeradorDeId.gerar(nome, "produto").toString();
        this.nome = nome;
        this.quantidade = quantidade;
        this.codigo = codigo;
    }

    public Produto (Map<Integer, String> map) {
        this.id = map.get(0);
        this.nome = map.get(1);
        this.codigo = map.get(2);
        this.quantidade = Integer.parseInt(map.get(3));
    }

    public String getId() {
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
