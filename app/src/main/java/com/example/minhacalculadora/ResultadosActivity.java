package com.example.minhacalculadora;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultadosActivity extends AppCompatActivity {

    private ListView listView;
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_resultados);


        dbHelper = new MyDatabaseHelper(this);
        db = dbHelper.getReadableDatabase();

        voltar = findViewById(R.id.btnVoltar);
        listView = findViewById(R.id.listView);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultadosActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Cursor cursor = db.query("minha_tabela", null, null, null, null, null, null);
        MyCursorAdapter adapter = new MyCursorAdapter(this, cursor);

        listView.setAdapter(adapter);
    }
}