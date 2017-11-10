package com.yacov.tp1android;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText nome, telefone, email, cidade;
    static ArrayList<String> Arquivos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (EditText) findViewById(R.id.nomeID);
        telefone = (EditText) findViewById(R.id.telefoneID);
        email = (EditText) findViewById(R.id.emailID);
        cidade = (EditText) findViewById(R.id.cidadeID);

        Listar();


    }

    public void Listar()
    {
        File diretorio = new File(ObterDiretorio());
        File[] arquivos = diretorio.listFiles();
        if(arquivos != null)
        {
            int length = arquivos.length;
            for(int i = 0; i < length; ++i)
            {
                File f = arquivos[i];
                if (f.isFile())
                {
                    Arquivos.add(f.getName());
                }
            }

        }
    }

    public void btnsave(View view){
        String lstrNomeArq;
        File arq;
        byte[] dados;
        try{
            lstrNomeArq = nome.getText().toString() + ".txt";

            arq = new File(Environment.getExternalStorageDirectory(), lstrNomeArq);
            FileOutputStream fos;

//            dados = nome.getText().toString().getBytes();
            StringBuilder builder = new StringBuilder();
            builder.append("Nome - " + nome.getText().toString() + "\n" );
            builder.append("Telefone - " + telefone.getText().toString() + "\n" );
            builder.append("Email - " + email.getText().toString() + "\n" );
            builder.append("Cidade - " + cidade.getText().toString() + "\n" );



            fos = new FileOutputStream(arq);
            fos.write(builder.toString().getBytes());
            fos.flush();
            fos.close();
            Mensagem("Texto Salvo com sucesso!");
            Listar();
        }
        catch (Exception e){
            Mensagem("Erro : " + e.getMessage());
        }

    }

    private String ObterDiretorio(){

        File root = android.os.Environment.getExternalStorageDirectory();
        return root.toString();
    }

    public void btnlistar(View view){
        try {
            Intent intent = new Intent(MainActivity.this, ListaActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("lista", Arquivos);
//            intent.putExtra("lista1", Arquivos);
            startActivity(intent);
        }catch (Exception e){
            Mensagem("Erro : " + e.getMessage());
        }
    }

    public void btnLimpar(View view){
        nome.setText("");
        telefone.setText("");
        email.setText("");
        cidade.setText("");

        Mensagem("Campos apagados!");
    }


    private void Mensagem(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
