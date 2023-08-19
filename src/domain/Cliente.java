package domain;

import domain.annotation.NomeTabela;
import util.GeradorDeId;

@NomeTabela(valor="clientes")
public class Cliente {

    private Integer id;
    private String nome;
    private Integer idade;
    private String cpf;

    public Cliente (String nome, Integer idade, String cpf) {
        this.id = GeradorDeId.gerar(nome, cpf);
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }
}
