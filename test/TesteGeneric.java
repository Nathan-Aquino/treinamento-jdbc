import dao.GenericDao;
import dao.IGenericDao;
import dao.jdbc.FabricaDeConexao;
import domain.Cliente;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TesteGeneric {
    @Test
    public void testaBuscar () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Cliente());

            Cliente cliente = new Cliente("Teste",29,"111222333");

            dao.salvar(cliente);

            dao.buscar(cliente.getId(),  cliente.getClass());

        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
