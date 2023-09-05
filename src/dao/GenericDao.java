package dao;

import util.CriadorDeTabela;
import util.Resolve;
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
        T instancia = (T) Resolve.buildObject(rs, classe).get(0);
        return instancia;
    }

    @Override
    public void atualizar(String identificador, T objeto) throws SQLException, IllegalAccessException, InvocationTargetException{
        PreparedStatement ps = Resolve.update(this.conn, identificador, objeto);
        ps.executeUpdate();
    }

    @Override
    public List<Object> buscarTodos(Class classe) throws NoSuchMethodException, SQLException, InvocationTargetException, InstantiationException, IllegalAccessException{
        PreparedStatement ps = Resolve.selectTodos(this.conn, classe);

        ResultSet rs = ps.executeQuery();

        return Resolve.buildObject(rs, classe);
    }

    @Override
    public void deletar(Class classe, String identificador) throws SQLException{
        PreparedStatement ps = Resolve.deletarPs(this.conn, classe, identificador);
        ps.executeUpdate();
    }

    @Override
    public void deletarTodos(Class classe) throws SQLException {
        PreparedStatement ps = Resolve.deletarTodosPs(this.conn, classe);
        ps.executeUpdate();
    }
}