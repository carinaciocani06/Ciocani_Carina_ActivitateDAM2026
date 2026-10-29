package ro.ase.dam.seminar04_10762;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_ADAUGA = 37;
    private static final int REQ_LISTA = 38;

    private TextView tvRezultat;
    private ArrayList<Carte> listaCarti = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvRezultat = findViewById(R.id.TV_rezultat);
    }

    public void deschideAdaugare(View view) {
        Intent intent = new Intent(getApplicationContext(), AdaugaCarte.class);
        startActivityForResult(intent, REQ_ADAUGA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_ADAUGA && resultCode == RESULT_OK && data != null) {
            Bundle b = data.getExtras();
            Carte carte = new Carte(
                    b.getString("titlu"),
                    b.getString("autor"),
                    b.getInt("an", 0),
                    b.getFloat("pret", 0f),
                    b.getBoolean("disponibila", false)
            );
            listaCarti.add(carte);
            //tvRezultat.setText(carte.toString()); aceasta era inainte de array list
            Toast.makeText(this, "Carte adaugata: " + carte.getTitlu(), Toast.LENGTH_SHORT).show();
        }

        if(requestCode == REQ_LISTA) {
            listaCarti = (ArrayList<Carte>) data.getSerializableExtra("lista");
        }

        tvRezultat.setText("Numar de carti in lista: " + listaCarti.size());
    }

    public void deschideLista(View view) {
        Intent intent = new Intent(getApplicationContext(), ListaCarti.class);
        intent.putExtra("lista",listaCarti);
        startActivityForResult(intent, REQ_LISTA);
    }
}