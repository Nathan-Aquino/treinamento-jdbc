package dao;

import java.util.List;

public interface IGenericDao<T> {
    public void salvar (T objeto);
    public T buscar (String identificador);
    public void atualizar (T objeto);
    public List<T> buscarTodos ();
}
