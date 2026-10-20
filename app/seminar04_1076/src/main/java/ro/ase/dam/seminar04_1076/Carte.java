package ro.ase.dam.seminar04_1076;

public class Carte {
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
}
