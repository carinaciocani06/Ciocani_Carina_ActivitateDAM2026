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

public class MainActivity extends AppCompatActivity {

    private static final int REQ_ADAUGA = 37;
    private TextView tvRezultat;

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
            tvRezultat.setText(carte.toString());
            Toast.makeText(this, "Carte adaugata: " + carte.getTitlu(), Toast.LENGTH_SHORT).show();
        }
    }
}