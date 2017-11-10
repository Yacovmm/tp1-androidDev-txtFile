package com.yacov.tp1android;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static com.yacov.tp1android.MainActivity.Arquivos;

public class ContactActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACTN0 = "contactn0";

    private EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        int contactn0 = (Integer) getIntent().getExtras().get(EXTRA_CONTACTN0);

        String array = Arquivos.get(contactn0);

        txt = (EditText) findViewById(R.id.txtID);
        Carregar();


    }

    public void Carregar()
    {
        int contactn0 = (Integer) getIntent().getExtras().get(EXTRA_CONTACTN0);
        String array = Arquivos.get(contactn0);
        String lstrNomeArq;
        File arq;
        String lstrlinha;
        try
        {
            lstrNomeArq = array;

            txt.setText("");

            arq = new File(Environment.getExternalStorageDirectory(), lstrNomeArq);
            BufferedReader br = new BufferedReader(new FileReader(arq));

            while ((lstrlinha = br.readLine()) != null)
            {
                if (!txt.getText().toString().equals(""))
                {
                    txt.append("\n");
                }
                txt.append(lstrlinha);
            }

            Mensagem("Texto Carregado com sucesso!");

        }
        catch (Exception e)
        {
            Mensagem("Erro : " + e.getMessage());
        }
    }

    private void Mensagem(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
