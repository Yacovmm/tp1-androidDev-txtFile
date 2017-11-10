package com.yacov.tp1android;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.yacov.tp1android.MainActivity.Arquivos;

public class ListaActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            ListView nossaLista = getListView();

//            Bundle bundle = new Bundle();
//            Intent intent = new Intent();
//            Bundle listaTest = intent.getBundleExtra("lista");
//            ArrayList arrayList;
//            arrayList = (ArrayList) listaTest.getSerializable("lista");

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, Arquivos);
            nossaLista.setAdapter(arrayAdapter);
        }catch (Exception e){
            Mensagem("Erro : " + e.getMessage());
        }
    }

    private void Mensagem(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onListItemClick(ListView listView, View itemView, int position, long id) {
        super.onListItemClick(listView, itemView, position, id);

        Intent intent = new Intent(ListaActivity.this, ContactActivity.class);
        intent.putExtra(ContactActivity.EXTRA_CONTACTN0,(int) id);
        startActivity(intent);
    }
}
