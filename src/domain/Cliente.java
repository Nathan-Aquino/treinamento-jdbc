package domain;

import domain.annotation.NomeTabela;
import domain.annotation.OrdemMetodos;
import util.GeradorDeId;

import java.util.Map;

@NomeTabela(valor="clientes")
public class Cliente {

    private String id;
    private String nome;
    private Integer idade;
    private String cpf;

    public Cliente (String nome, Integer idade, String cpf) {
        this.id = GeradorDeId.gerar(nome, cpf).toString();
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

    public Cliente (Map<Integer,String> map) {
        this.id = map.get(0);
        this.nome = map.get(1);
        this.idade = Integer.parseInt(map.get(2));
        this.cpf = map.get(3);
    }

    public Cliente () {
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
    public Integer getIdade() {
        return idade;
    }

    @OrdemMetodos(valor = 4)
    public String getCpf() {
        return cpf;
    }
}
