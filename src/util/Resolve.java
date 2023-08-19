package util;

import domain.annotation.NomeTabela;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Resolve {
    public static <T> String nomeTabela (T objeto) {
        Class<?> classe = objeto.getClass();

        Annotation nomeTabelaAnnotation = classe.getAnnotation(NomeTabela.class);

        return ((domain.annotation.NomeTabela) nomeTabelaAnnotation).valor();
    }

    public static <T> PreparedStatement insert (Connection conn, T objeto) throws SQLException, IllegalAccessException, InvocationTargetException {
        Field[] fields = objeto.getClass().getFields();

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

        Method[] methods = objeto.getClass().getMethods();

        for (int j = 0; j < methods.length; j++) {
            ps.setString(j + 1, methods[j].invoke(objeto.getClass(),null).toString());
        }

        return ps;
    }
}
