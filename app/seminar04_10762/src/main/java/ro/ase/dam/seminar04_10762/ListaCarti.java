package ro.ase.dam.seminar04_10762;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaCarti extends AppCompatActivity {

    private ArrayList<Carte> lista;
    private ArrayAdapter<Carte> adapter;

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

        lista = (ArrayList<Carte>) getIntent().getSerializableExtra("lista");

        ListView lvCarti = findViewById(R.id.LV_carti);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        lvCarti.setAdapter(adapter);

        lvCarti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListaCarti.this, lista.get(position).toString(), Toast.LENGTH_LONG).show();
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

    private void trimiteListaInapoi() {
        Intent it = new Intent();
        it.putExtra("lista",lista);
        setResult(RESULT_OK, it);
    }
}