import dao.GenericDao;
import dao.IGenericDao;
import dao.jdbc.FabricaDeConexao;
import domain.Cliente;
import org.junit.Assert;
import org.junit.Test;
import java.sql.Connection;
import java.util.List;

public class TesteCliente {

    public Cliente criarCliente () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Cliente());

            Cliente cliente = new Cliente("Teste",29,"111222333");

            dao.salvar(cliente);

            return cliente;
        }catch (Exception ex) {
           System.out.println(ex.getMessage());
           return null;
        }
    }

    public void limparTabela () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Cliente());

            dao.deletarTodos(Cliente.class);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void testaSalvar () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Cliente());

            Cliente cliente = new Cliente("Teste",29,"111222333");

            dao.salvar(cliente);

            Assert.assertNotNull(dao.buscar(cliente.getId(),  cliente.getClass()));

            this.limparTabela();
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testaBuscar () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Cliente());

            Cliente cliente = this.criarCliente();

            Assert.assertNotNull(dao.buscar(cliente.getId(),  cliente.getClass()));

            this.limparTabela();
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testaBuscarTodos () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Cliente());

            Cliente cliente = this.criarCliente();

            List teste = dao.buscarTodos(Cliente.class);

            Assert.assertNotNull(teste);

            this.limparTabela();
        }catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testaDeletar () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Cliente());

            Cliente cliente = this.criarCliente();

            dao.deletar(Cliente.class, cliente.getId());

            List teste = dao.buscarTodos(Cliente.class);

            Assert.assertEquals(0, teste.size());
        }catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testaAtualizar () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Cliente());

            Cliente cliente = this.criarCliente();

            Cliente cliente2 = new Cliente("Teste2",30,"111222333");

            dao.atualizar(cliente.getId(), cliente2);

            Cliente clienteRetornado = (Cliente) dao.buscar(cliente.getId(), Cliente.class);

            Assert.assertNotEquals(cliente.getNome(),clienteRetornado.getNome());
            Assert.assertNotEquals(cliente.getIdade(),clienteRetornado.getIdade());

            Assert.assertEquals(cliente2.getNome(),clienteRetornado.getNome());
            Assert.assertEquals(cliente2.getIdade(),clienteRetornado.getIdade());

            this.limparTabela();
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
