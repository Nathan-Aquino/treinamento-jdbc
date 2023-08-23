package util;

import domain.Cliente;
import domain.annotation.NomeTabela;
import domain.annotation.OrdemMetodos;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Resolve {
    public static <T> String nomeTabela (T objeto) {
        Class<?> classe = objeto.getClass();

        Annotation nomeTabelaAnnotation = classe.getAnnotation(NomeTabela.class);

        return ((domain.annotation.NomeTabela) nomeTabelaAnnotation).valor();
    }

    public static Object buildObject (ResultSet rs, Class classe) throws NoSuchMethodException, SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor con = classe.getConstructor(HashMap.class);

        Field[] fields = classe.getDeclaredFields();

        Map<Integer,String> map = new HashMap<Integer,String>();

        for (int k = 0; k < fields.length; k++) {
            int indiceColuna = rs.findColumn(fields[k].getName());
            map.put(k, rs.getObject(indiceColuna).toString());
        }

        return con.newInstance(map);
    }

    public static <T> PreparedStatement insert (Connection conn, T objeto) throws SQLException, IllegalAccessException, InvocationTargetException {
        Field[] fields = objeto.getClass().getDeclaredFields();

        StringBuilder insertSb = new StringBuilder("INSERT INTO " + nomeTabela(objeto) + " VALUES(");

        for (int i = 0; i < fields.length; i++) {
            if (i == 0) {
                insertSb.append("?");
            } else if (i == fields.length - 1) {
                insertSb.append(",?);");
            } else {
                insertSb.append(",?");
            }
        }

        PreparedStatement ps = conn.prepareStatement(insertSb.toString());

        Method[] methods = objeto.getClass().getDeclaredMethods();

        for (int z = 0; z < methods.length; z++) {
            for (int x = 0; x < methods.length - z - 1; x++) {
                int indice1 = methods[x].getAnnotation(OrdemMetodos.class).valor();
                int indice2 = methods[x + 1].getAnnotation(OrdemMetodos.class).valor();

                if (indice1 > indice2) {
                    Method temp = methods[x];
                    methods[x] = methods[x + 1];
                    methods[x + 1] = temp;
                }
            }
        }

        for (int j = 0; j < methods.length; j++) {
            if (methods[j].getReturnType().equals(Integer.class)) {
                ps.setInt(j + 1, Integer.parseInt(methods[j].invoke(objeto).toString()));
            } else {
                ps.setString(j + 1, methods[j].invoke(objeto).toString());
            }
        }

        return ps;
    }

    public static <T> PreparedStatement selectComId (Connection conn, Class classe, String id) throws SQLException {
        Annotation nomeTabelaAnnotation = classe.getAnnotation(NomeTabela.class);

        String nomeTabela = ((domain.annotation.NomeTabela) nomeTabelaAnnotation).valor();

        String select = "SELECT * FROM " + nomeTabela + " WHERE id = ?;";

        PreparedStatement ps = conn.prepareStatement(select);

        ps.setString(1, id);

        return ps;
    }
}
