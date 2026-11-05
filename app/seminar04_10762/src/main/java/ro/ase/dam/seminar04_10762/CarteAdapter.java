package ro.ase.dam.seminar04_10762;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CarteAdapter extends BaseAdapter {
    private List<Carte> carti = null;
    private Context ctx;
    private int resursaLayout;

    public CarteAdapter(List<Carte> carti, Context ctx, int resursaLayout) {
        this.carti = carti;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return carti.size();
    }

    @Override
    public Object getItem(int i) {
        return carti.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, parent, false);

        ImageView pozaIV = v.findViewById(R.id.IV_poza);
        TextView titluTV = v.findViewById(R.id.TV_titlu);
        TextView autorTV = v.findViewById(R.id.TV_autor);
        TextView detaliiTV = v.findViewById(R.id.TV_detalii);
        TextView disponibilaTV = v.findViewById(R.id.TV_disponibila);

        Carte carte = (Carte) getItem(i);

        titluTV.setText(carte.getTitlu());
        autorTV.setText(carte.getAutor());
        detaliiTV.setText("An aparitie: " + carte.getAnAparitie() + " , Pret: " + carte.getPret());

        if (carte.isEsteDisponibila()) {
            disponibilaTV.setText("Disponibila");
            pozaIV.setImageResource(R.drawable.carte_disponibila);
        } else {
            disponibilaTV.setText("Indisponibila");
            pozaIV.setImageResource(R.drawable.carte_indisponibila);
        }

        return v;
    }
}
