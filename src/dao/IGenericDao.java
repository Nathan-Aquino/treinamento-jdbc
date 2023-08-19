package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IGenericDao<T> {
    public void salvar (T objeto) throws SQLException, InvocationTargetException, IllegalAccessException;
    public T buscar (String identificador);
    public void atualizar (T objeto);
    public List<T> buscarTodos ();
}
