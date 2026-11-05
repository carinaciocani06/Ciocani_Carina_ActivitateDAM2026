package ro.ase.dam.seminar04_10762;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaCarte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_carte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it=getIntent();
        if(it.hasExtra("carte")){
            Carte carte = it.getParcelableExtra("carte");
            EditText titluET = findViewById(R.id.ET_titlu);
            EditText autorET = findViewById(R.id.ET_autor);
            Spinner anSP = findViewById(R.id.SP_an);
            EditText pretET = findViewById(R.id.ET_pret);
            CheckBox disponibilaCB = findViewById(R.id.CB_disponibila);

            titluET.setText(carte.getTitlu());
            autorET.setText(carte.getAutor());
            pretET.setText(String.valueOf(carte.getPret()));
            disponibilaCB.setChecked(carte.isEsteDisponibila());

            String[] ani = getResources().getStringArray(R.array.lista_ani);
            for(int i=0;i<ani.length;i++) {
                if(ani[i].equals(String.valueOf(carte.getAnAparitie()))) {
                    anSP.setSelection(i);
                }
            }
        }
    }

    public void preluareDate(View view) {
        EditText etTitlu = findViewById(R.id.ET_titlu);
        EditText etAutor = findViewById(R.id.ET_autor);
        Spinner spAn = findViewById(R.id.SP_an);
        EditText etPret = findViewById(R.id.ET_pret);
        CheckBox cbDisponibila = findViewById(R.id.CB_disponibila);

        String titlu = etTitlu.getText().toString();
        String autor = etAutor.getText().toString();
        int an = Integer.parseInt(spAn.getSelectedItem().toString());
        float pret = Float.parseFloat(etPret.getText().toString());
        boolean disponibila = cbDisponibila.isChecked();

        Carte carte = new Carte(titlu, autor, an, pret, disponibila);

        //seminar 5 - cu serializable, nu parcelable
        /*Bundle bundle = new Bundle();
        bundle.putString("titlu", carte.getTitlu());
        bundle.putString("autor", carte.getAutor());
        bundle.putInt("an", carte.getAnAparitie());
        bundle.putFloat("pret", carte.getPret());
        bundle.putBoolean("disponibila", carte.isEsteDisponibila()); */

        Intent it = new Intent();
        //it.putExtras(bundle);
        it.putExtra("carte",carte); // parcelable
        setResult(RESULT_OK, it);
        finish();

    }
}