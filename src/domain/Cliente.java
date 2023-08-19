package domain;

import util.GeradorDeId;

public class Cliente {

    private Integer id;
    private String nome;
    private Integer idade;
    private String cpf;

    private static String nomeTabela;

    public Cliente (String nome, Integer idade, String cpf) {
        this.id = GeradorDeId.gerar(nome, cpf);
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

    public static void setNomeTabela (String nomeTabela) {
        nomeTabela = nomeTabela;
    }

    public String getNomeTabela () {
        return nomeTabela;
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
