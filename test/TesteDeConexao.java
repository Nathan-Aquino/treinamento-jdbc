import dao.jdbc.FabricaDeConexao;
import domain.Cliente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import util.CriadorDeTabela;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TesteDeConexao {

    @After
    public void fechaConexao () throws SQLException, IOException{
        Connection conn = FabricaDeConexao.getConexao();
        conn.close();
    }

    @Test
    public void testaConexao () {
        try {
            Connection conn = FabricaDeConexao.getConexao();

            Assert.assertNotNull(conn);
        } catch (SQLException | IOException ex) {
            Assert.fail("SQLException ou IOException lançada");
        }
    }
}
