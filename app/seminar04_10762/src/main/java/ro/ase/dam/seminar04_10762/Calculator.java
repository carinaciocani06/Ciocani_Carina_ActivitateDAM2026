//package ro.ase.dam.seminar04_10762;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//public class Calculator implements Parcelable {
//    private int id;
//    private String model;
//    private int RAM;
//    private double pret;
//
//    public Calculator(int id, double pret, String model, int RAM) {
//        this.id = id;
//        this.pret = pret;
//        this.model = model;
//        this.RAM = RAM;
//    }
//
//    protected Calculator(Parcel in) {
//        id = in.readInt();
//        model = in.readString();
//        RAM = in.readInt();
//        pret = in.readDouble();
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(id);
//        dest.writeString(model);
//        dest.writeInt(RAM);
//        dest.writeDouble(pret);
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
//        @Override
//        public Calculator createFromParcel(Parcel in) {
//            return new Calculator(in);
//        }
//
//        @Override
//        public Calculator[] newArray(int size) {
//            return new Calculator[size];
//        }
//    };
//
//    public int getId() {
//        return id;
//    }
//
//
//}
