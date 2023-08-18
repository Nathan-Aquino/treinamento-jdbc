package dao.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FabricaDeConexao {
    private static Connection conn;

    private FabricaDeConexao () throws IOException, SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ebac", this.dbProperties());
    }

    private Properties dbProperties () throws IOException {
        Properties props = new Properties();

        FileInputStream file = new FileInputStream(
                "./jdbc.properties");
        props.load(file);

        return props;
    }

    public static Connection getConexao () throws IOException, SQLException{
        if (conn == null) {
            new FabricaDeConexao();
        }

        return conn;
    }
}
