package dao;

import util.CriadorDeTabela;
import util.Resolve;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDao<T> implements IGenericDao<T>{

    public Connection conn;

    public GenericDao (Connection conn, T objeto) throws Exception {
        this.conn = conn;

        CriadorDeTabela.seNaoExistirCriar(this.conn, objeto);
    }

    @Override
    public void salvar(T objeto) throws SQLException, InvocationTargetException, IllegalAccessException {
        PreparedStatement ps = Resolve.insert(this.conn, objeto);
        ps.executeUpdate();
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
