package ro.ase.dam.seminar04_10762;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImaginiCartiActivity extends AppCompatActivity {
    private List<ImaginiDomeniu> lista = new ArrayList<>();
    private List<Bitmap> imagini = new ArrayList<>();
    private ImagineAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagini_carti);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lv = findViewById(R.id.imagini);

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://m.media-amazon.com/images/I/41Hevbr4VkL._SX342_SY445_FMwebp_.jpg");
        linkuriImagini.add("https://product-images.dc5.ro/258717-1-240.jpeg");
        linkuriImagini.add("https://m.media-amazon.com/images/I/51Scr3BJQ7L._SY445_SX342_FMwebp_.jpg");
        linkuriImagini.add("https://m.media-amazon.com/images/S/compressed.photo.goodreads.com/books/1566425108i/33.jpg");
        linkuriImagini.add("https://product-images.dc5.ro/5198649321-0-240.jpeg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link : linkuriImagini) {
                    HttpURLConnection con = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (Exception e) {
                        imagini.add(null);
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lista = new ArrayList<>();
                        lista.add(new ImaginiDomeniu("Micul Print - Antoine de Saint-Exupery",
                                imagini.get(0),
                                "https://en.wikipedia.org/wiki/The_Little_Prince"));
                        lista.add(new ImaginiDomeniu("1984 - George Orwell",
                                imagini.get(1),
                                "https://en.wikipedia.org/wiki/Nineteen_Eighty-Four"));
                        lista.add(new ImaginiDomeniu("Harry Potter si Piatra Filozofala - J.K. Rowling",
                                imagini.get(2),
                                "https://en.wikipedia.org/wiki/Harry_Potter_and_the_Philosopher%27s_Stone"));
                        lista.add(new ImaginiDomeniu("Hobbitul - J.R.R. Tolkien",
                                imagini.get(3),
                                "https://en.wikipedia.org/wiki/The_Hobbit"));
                        lista.add(new ImaginiDomeniu("Mandrie si prejudecata - Jane Austen",
                                imagini.get(4),
                                "https://en.wikipedia.org/wiki/Pride_and_Prejudice"));

                        adapter = new ImagineAdapter(lista, ImaginiCartiActivity.this, R.layout.adapter_nou);
                        lv.setAdapter(adapter);
                    }
                });
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(ImaginiCartiActivity.this, WebViewActivity.class);
                it.putExtra("link", lista.get(position).getLink());
                startActivity(it);
            }
        });
    }
}