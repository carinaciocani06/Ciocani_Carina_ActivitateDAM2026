package ro.ase.dam.seminar04_10762;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaCarti extends AppCompatActivity {

    private static final int REQ_MODIFICA = 209;
    private ArrayList<Carte> lista;
    private int idModificat = 0;
    private CarteAdapter adapter = null;
    //private ArrayAdapter<Carte> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_carti);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //lista = (ArrayList<Carte>) getIntent().getSerializableExtra("lista");
        lista = getIntent().getParcelableArrayListExtra("lista");

        ListView lvCarti = findViewById(R.id.LV_carti);
        //(seminarul 5, adapter predefinit)
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        //lvCarti.setAdapter(adapter);

        //seminar 6, adapter personalizat
        adapter = new CarteAdapter(lista,getApplicationContext(),R.layout.adapter_personalizat);
        lvCarti.setAdapter(adapter);

        lvCarti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentModifica = new Intent(getApplicationContext(), AdaugaCarte.class);
                intentModifica.putExtra("carte",lista.get(i));
                idModificat = i;
                startActivityForResult(intentModifica,REQ_MODIFICA);
                Toast.makeText(ListaCarti.this, lista.get(i).toString(), Toast.LENGTH_LONG).show();
            }
        });

        lvCarti.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lista.remove(position);
                adapter.notifyDataSetChanged();
                trimiteListaInapoi();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data!=null) {
            if(requestCode == REQ_MODIFICA) {
                lista.set(idModificat, data.getParcelableExtra("carte"));
                adapter.notifyDataSetChanged();
                trimiteListaInapoi();
            }
        }
    }

    private void trimiteListaInapoi() {
        Intent it = new Intent();
        //it.putExtra("lista",lista);
        it.putParcelableArrayListExtra("lista", lista);
        setResult(RESULT_OK, it);
    }
}