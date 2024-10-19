package br.com.etec.aula20240906.model.database;

public class DatabaseFactory {
    // CLASES DO TIPO STATIC NÃO SÃO INSTANCIADAS

    public static Database getDatabase(String nome) {
        if (nome.equals("postgreesql")) {
            return new DatabasePostGreeSQL();
        } else {
            return new DatabaseMySQL();
        }
    }
}
