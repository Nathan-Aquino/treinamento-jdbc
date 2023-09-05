package domain;

import domain.annotation.NomeTabela;
import domain.annotation.OrdemMetodos;
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

    public Produto () {
    }

    @OrdemMetodos(valor = 1)
    public String getId() {
        return id;
    }

    @OrdemMetodos(valor = 2)
    public String getNome() {
        return nome;
    }

    @OrdemMetodos(valor = 3)
    public String getCodigo() {
        return codigo;
    }

    @OrdemMetodos(valor = 4)
    public Integer getQuantidade() {
        return quantidade;
    }
}
