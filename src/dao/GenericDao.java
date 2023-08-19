package dao;

import util.CriadorDeTabela;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GenericDao<T> implements IGenericDao<T>{

    public Connection conn;

    public GenericDao (Connection conn, T objeto) throws Exception {
        this.conn = conn;

        CriadorDeTabela.seNaoExistirCriar(this.conn, objeto.getClass());
    }

    @Override
    public void salvar(T objeto) {

    }

    @Override
    public T buscar(String identificador) {
        return null;
    }

    @Override
    public void atualizar(T objeto) {

    }

    @Override
    public List<T> buscarTodos() {
        return null;
    }
}
