package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IGenericDao<T> {
    public void salvar (T objeto) throws SQLException, InvocationTargetException, IllegalAccessException;
    public T buscar (String identificador, Class classe) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
    public void atualizar (String identificador, T objeto) throws SQLException, IllegalAccessException, InvocationTargetException;
    public List<Object> buscarTodos (Class classe) throws NoSuchMethodException, SQLException, InvocationTargetException, InstantiationException, IllegalAccessException;
    public void deletar (Class classe, String identificador) throws SQLException;
    public void deletarTodos (Class classe) throws SQLException;
}
