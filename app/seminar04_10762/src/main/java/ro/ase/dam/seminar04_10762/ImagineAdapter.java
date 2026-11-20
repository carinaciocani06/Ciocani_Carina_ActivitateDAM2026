package ro.ase.dam.seminar04_10762;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private List<ImaginiDomeniu> imagini=null;
    private Context ctx;
    private int resursaLayout;

    public ImagineAdapter(List<ImaginiDomeniu> imagini, Context ctx, int resursaLayout) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int i) {
        return imagini.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, parent, false);

        ImageView imagineIV = v.findViewById(R.id.imagineIV);
        TextView textTV = v.findViewById(R.id.textTV);

        ImaginiDomeniu imagine = (ImaginiDomeniu) getItem(i);

        textTV.setText(imagine.getTextAfisat());

        if (imagine.getImagine() != null) {
            imagineIV.setImageBitmap(imagine.getImagine());
        } else {
            imagineIV.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        return v;

    }
}
