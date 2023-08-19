package util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CriadorDeTabela {

    public static void seNaoExistirCriar (Connection conn, Class classe) throws NoSuchFieldException, IllegalAccessException, SQLException {
        Field nomeTabelaCampo = classe.getDeclaredField("nomeTabela");

        String nomeTabela = (String) nomeTabelaCampo.get(null);

        String select = "SELECT 1 FROM information_schema.tables WHERE table_name = '" + nomeTabela + "'";

        PreparedStatement ps = conn.prepareStatement(select);

        ResultSet rs = ps.executeQuery();

        if (rs == null) {
            switch (nomeTabela) {
                case "clientes":
                    String createCliente = "CREATE TABLE clientes (" +
                            "id varchar(100) primary key," +
                            "nome varchar(30) not null," +
                            "idade integer not null," +
                            "cpf varchar(13) not null" +
                            ")";

                    conn.prepareStatement(createCliente).executeUpdate();
                    break;
                case "produtos":
                    String createProduto = "CREATE TABLE produtos (" +
                            "id varchar(100) primary key," +
                            "nome varchar(30) not null," +
                            "codigo varchar(10) not null," +
                            "quantidade integer not null" +
                            ")";
                    conn.prepareStatement(createProduto).executeUpdate();
                    break;
            }
        }
    }

}
