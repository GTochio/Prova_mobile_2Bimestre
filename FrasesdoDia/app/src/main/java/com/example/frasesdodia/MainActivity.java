package com.example.frasesdodia;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            // Criação de Banco de Dados

            SQLiteDatabase db = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Criação de Tabela

            db.execSQL("CREATE TABLE IF NOT EXISTS frase (id INT(2) PRIMARY KEY AUTOINCREMENT, frase VARCHAR(254))");

            //Inserir dados na tabela fraseVocê nunca será velho demais para sonhar um novo sonho

          //  db.execSQL("INSERT INTO frase (frase) VALUES ('Agora é a hora de começar a surpreender aqueles que duvidaram de você!')");
          //  db.execSQL("INSERT INTO frase (frase) VALUES ('Não importa o que você decidiu. O que importa é que isso te faz feliz.')");
          //  db.execSQL("INSERT INTO frase (frase) VALUES ('Você nunca será velho demais para sonhar um novo sonho.')");
          //  db.execSQL("INSERT INTO frase (frase) VALUES ('Quando pensar em desistir, lembre-se porque começou.')");
          //  db.execSQL("INSERT INTO frase (frase) VALUES ('Por mais difícil que algo possa parecer, jamais desista antes de tentar!')");
          //  db.execSQL("INSERT INTO frase (frase) VALUES ('Acreditar é a força que nos permite subir os maiores degraus da vida')");
          //  db.execSQL("INSERT INTO frase (frase) VALUES ('No fim tudo dá certo, e se não deu certo é porque ainda não chegou ao fim')");

            //Deletar Tabela

           // db.execSQL("DROP TABLE frase");

            // Criação de Consulta dos dados

            String dados = "SELECT id, frase FROM frases";

            //Criação do cursor

            Cursor cursor = db.rawQuery(dados, null);

            //Criação de Indices da Tabela

            int indexId = cursor.getColumnIndex("id");
            int indexFrase = cursor.getColumnIndex("frase");

            if(cursor != null && cursor.moveToFirst()){
                do{
                    String id = cursor.getString(indexId);
                    String frase = cursor.getString(indexFrase);
                    Log.i("RESULTADO", "ID: " + id + " - FRASE: " + frase + ".");
                }while(cursor.moveToNext());
            }

            //Resgatando numero de Registros

            int quant_reg = cursor.getCount();

            //Gerando numeros Randomicos

            Random random = new Random();
            int numeroRandomico = random.nextInt(quant_reg) +1;

            //Realizar Consulta no Banco de Dados pelo id

            String buscarId = "SELECT frase FROM frase WHERE id = "+ numeroRandomico;
            cursor = db.rawQuery(buscarId, null);
            cursor.moveToFirst();

            //Criação de Indice e cursor para localizar a frase por id
            int indiceFrase = cursor.getColumnIndex("frase");
            String getFrase = cursor.getString(indiceFrase);

            TextView txtFrase = findViewById(R.id.TextoFrase);

            txtFrase.setText(getFrase);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}