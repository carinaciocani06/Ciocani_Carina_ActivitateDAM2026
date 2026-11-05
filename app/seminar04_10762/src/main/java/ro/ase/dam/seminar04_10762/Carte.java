package ro.ase.dam.seminar04_10762;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Carte implements Parcelable {
    private char initiala = 'C';
    private String titlu;
    private String autor;
    private int anAparitie;
    private float pret;
    private boolean esteDisponibila;

    public Carte(String titlu, String autor, int anAparitie, float pret, boolean esteDisponibila) {
        this.titlu = titlu;
        this.autor = autor;
        this.anAparitie = anAparitie;
        this.pret = pret;
        this.esteDisponibila = esteDisponibila;
    }

    protected Carte(Parcel in) {
        initiala = (char) in.readInt();
        titlu = in.readString();
        autor = in.readString();
        anAparitie = in.readInt();
        pret = in.readFloat();
        esteDisponibila = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt((int) initiala);
        dest.writeString(titlu);
        dest.writeString(autor);
        dest.writeInt(anAparitie);
        dest.writeFloat(pret);
        dest.writeByte((byte) (esteDisponibila ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Carte> CREATOR = new Creator<Carte>() {
        @Override
        public Carte createFromParcel(Parcel in) {
            return new Carte(in);
        }

        @Override
        public Carte[] newArray(int size) {
            return new Carte[size];
        }
    };

    public char getInitiala() {
        return initiala;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnAparitie() {
        return anAparitie;
    }

    public float getPret() {
        return pret;
    }

    public boolean isEsteDisponibila() {
        return esteDisponibila;
    }

    @Override
    public String toString() {
        return titlu + " - " + autor + " (" + anAparitie + "), "
                + pret + " lei, disponibila: " + (esteDisponibila ? "da" : "nu");
    }

    public void setInitiala(char initiala) {
        this.initiala = initiala;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAnAparitie(int anAparitie) {
        this.anAparitie = anAparitie;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public void setEsteDisponibila(boolean esteDisponibila) {
        this.esteDisponibila = esteDisponibila;
    }
}
