package util;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class GeradorDeId {
    public static Integer gerar (String arg1, String arg2) {
        return Objects.hash(arg1, arg2, new Date().getTime(), gerarLetraAleatoria());
    }

    public static char gerarLetraAleatoria() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(26);

        char letra = (char) ('a' + numeroAleatorio);

        return letra;
    }
}
