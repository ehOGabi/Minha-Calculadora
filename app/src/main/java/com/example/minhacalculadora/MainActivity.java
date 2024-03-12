package com.example.minhacalculadora;

import static android.os.Build.VERSION_CODES.R;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private EditText inputValorA;
    private EditText inputValorB;
    private Button btnAdicao;
    private Button btnSubtracao;
    private Button btnMultiplicacao;
    private Button btnDivisao;
    String operacao = "";
    private Button btnResultados;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.minhacalculadora.R.layout.activity_main);

        dbHelper = new MyDatabaseHelper(this);




        inputValorA = findViewById(com.example.minhacalculadora.R.id.inputValorA);
        inputValorB = findViewById(com.example.minhacalculadora.R.id.inputValorB);
        btnAdicao = findViewById(com.example.minhacalculadora.R.id.btnAdicao);
        btnSubtracao = findViewById(com.example.minhacalculadora.R.id.btnSubtracao);
        btnMultiplicacao = findViewById(com.example.minhacalculadora.R.id.btnMultiplicacao);
        btnDivisao = findViewById(com.example.minhacalculadora.R.id.btnDivisao);

        btnAdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao = "Adição";
            }
        });
        btnSubtracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao = "Subtração";
            }
        });
        btnMultiplicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao = "Multiplicação";
            }
        });
        btnDivisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao = "Divisão";
            }
        });

        btnResultados = findViewById(com.example.minhacalculadora.R.id.btnResultados);

        btnResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickResultados();
            }
        });

    }

    public void onClickResultados(){
        double valorA = Double.parseDouble(inputValorA.getText().toString());
        double valorB = Double.parseDouble(inputValorB.getText().toString());
        String dataHora = dataHora();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("valor_a", valorA);
        values.put("valor_b", valorB);
        values.put("operacao", operacao);
        values.put("data_hora", dataHora);
        long idRegistro = db.insert("minha_tabela", null, values);

        if (idRegistro != -1){
            Toast.makeText(this, "Cálculo armazenado com sucesso", Toast.LENGTH_SHORT).show();
            
            inputValorA.setText("");
            inputValorB.setText("");

            Intent intent = new Intent(MainActivity.this, ResultadosActivity.class);
            startActivity(intent);

        }else {
            Toast.makeText(this, "Erro ao armazenar o cálculo", Toast.LENGTH_SHORT).show();
        }



    }

    private String dataHora() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return date.format(new Date());
    }


}