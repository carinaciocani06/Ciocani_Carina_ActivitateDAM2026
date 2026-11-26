package ro.ase.dam.seminar09_1076;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivitateDeschisa extends AppCompatActivity {
    private static final String API_KEY = "zpka_97658b05e05b428a89cbeea63987b168_d690dd00";
    private EditText etOras;
    private TextView tvRezultat;
    private Spinner spZile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_deschisa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        etOras = findViewById(R.id.ET_oras);
        tvRezultat = findViewById(R.id.TV_rezultat);
        spZile = findViewById(R.id.SP_zile);
    }


    public void cautaOras(View view) {
        String oras = etOras.getText().toString().trim();
        if(oras.isEmpty()){
            Toast.makeText(this, "Scrie numele unui oras", Toast.LENGTH_SHORT).show();
            return;
        }

        String metoda = (spZile.getSelectedItemPosition() == 0) ? "1day" : "5day";

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                String link="https://dataservice.accuweather.com/locations/v1/cities/search" +
                        "?apikey=" + API_KEY + "&q=" + oras;
                try{
                    URL url = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder builder = new StringBuilder("");
                    String linie;
                    while ((linie = reader.readLine()) != null) {
                        builder.append(linie);
                    }

                    JSONArray vector = new JSONArray(builder.toString());
                    JSONObject obiect = vector.getJSONObject(0);
                    String cheieOras = obiect.getString("Key");

                    Log.d("VREME", "Cheia orasului: " + cheieOras);

                    link = "https://dataservice.accuweather.com/forecasts/v1/daily/" + metoda + "/" +
                            cheieOras + "?apikey=" + API_KEY + "&metric=true";

                    url = new URL(link);
                    conn = (HttpURLConnection) url.openConnection();
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    builder = new StringBuilder("");
                    while((linie = reader.readLine()) != null){
                        builder.append(linie);
                    }

                    JSONObject raspuns = new JSONObject(builder.toString());
                    JSONArray zile = raspuns.getJSONArray("DailyForecasts");

                    StringBuilder rezultat = new StringBuilder("");
                    rezultat.append("Cheia orasului: ").append(cheieOras).append("\n\n");

                    for(int i=0; i<zile.length();i++){
                        JSONObject zi = zile.getJSONObject(i);
                        String data = zi.getString("Date").substring(0,10);

                        JSONObject temperatura = zi.getJSONObject("Temperature");
                        double min = temperatura.getJSONObject("Minimum").getDouble("Value");
                        double max = temperatura.getJSONObject("Maximum").getDouble("Value");

                        rezultat.append(data)
                                .append("\nMinima: ").append(min).append(" °C")
                                .append("\nMaxima: ").append(max).append(" °C\n\n");
                    }

                    String text = rezultat.toString();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            tvRezultat.setText(text);
                        }
                    });

                } catch(Exception e){
                    String eroare = "Eroare: " + e.getMessage();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            tvRezultat.setText(eroare);
                        }
                    });
                }
            }
        });
    }
}