import dao.GenericDao;
import dao.IGenericDao;
import dao.jdbc.FabricaDeConexao;
import domain.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class TesteProduto {

    public Produto criarProduto () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Produto());

            Produto produto = new Produto("Teste",29,"111222333");

            dao.salvar(produto);

            return produto;
        }catch (Exception ex) {
           System.out.println(ex.getMessage());
           return null;
        }
    }

    public void limparTabela () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Produto());

            dao.deletarTodos(Produto.class);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void testaSalvar () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Produto());

            Produto produto = new Produto("Teste",29,"111222333");

            dao.salvar(produto);

            Assert.assertNotNull(dao.buscar(produto.getId(),  produto.getClass()));

            this.limparTabela();
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testaBuscar () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Produto());

            Produto produto = this.criarProduto();

            Assert.assertNotNull(dao.buscar(produto.getId(),  produto.getClass()));

            this.limparTabela();
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testaBuscarTodos () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Produto());

            Produto produto = this.criarProduto();

            List teste = dao.buscarTodos(Produto.class);

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

            IGenericDao dao = new GenericDao(conn, new Produto());

            Produto produto = this.criarProduto();

            dao.deletar(Produto.class, produto.getId());

            List teste = dao.buscarTodos(Produto.class);

            Assert.assertEquals(0, teste.size());
        }catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testaAtualizar () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            IGenericDao dao = new GenericDao(conn, new Produto());

            Produto produto = this.criarProduto();

            Produto produto2 = new Produto("Teste2",30,"111222333");

            dao.atualizar(produto.getId(), produto2);

            Produto produtoRetornado = (Produto) dao.buscar(produto.getId(), Produto.class);

            Assert.assertNotEquals(produto.getNome(),produtoRetornado.getNome());
            Assert.assertNotEquals(produto.getQuantidade(),produtoRetornado.getQuantidade());

            Assert.assertEquals(produto2.getNome(),produtoRetornado.getNome());
            Assert.assertEquals(produto2.getQuantidade(),produtoRetornado.getQuantidade());

            this.limparTabela();
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
