package dao;

import util.CriadorDeTabela;
import util.Resolve;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GenericDao<T> implements IGenericDao<T>{

    public Connection conn;

    public GenericDao (Connection conn, T objetoPrototipo) throws Exception {
        this.conn = conn;

        CriadorDeTabela.seNaoExistirCriar(this.conn, objetoPrototipo);
    }

    @Override
    public void salvar(T objeto) throws SQLException, InvocationTargetException, IllegalAccessException {
        PreparedStatement ps = Resolve.insert(this.conn, objeto);
        ps.executeUpdate();
    }

    @Override
    public T buscar(String identificador, Class classe) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ResultSet rs = Resolve.selectComId(this.conn, classe, identificador).executeQuery();
        T instancia = (T) Resolve.buildObject(rs, classe);
        return instancia;
    }

    @Override
    public void atualizar(T objeto) {

    }

    @Override
    public List<T> buscarTodos() {
        return null;
    }
}
