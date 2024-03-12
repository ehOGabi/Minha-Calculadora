package com.example.minhacalculadora;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "meu_banco_de_dados.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS minha_tabela (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "valor_a REAL," +
                "valor_b REAL," +
                "operacao TEXT," +
                "resultado REAL," +
                "data_hora DATETIME DEFAULT CURRENT_TIMESTAMP)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
