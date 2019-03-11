package android.edu.colorestag.dto;

        import android.os.Parcel;
        import android.os.Parcelable;

public class PuntuacionParcelable implements Parcelable {

    private String nombre;
    private long tiempo;


    public static final Creator<PuntuacionParcelable> CREATOR = new Creator<PuntuacionParcelable>(){

        @Override
        public PuntuacionParcelable createFromParcel(Parcel parcel) {
            return new PuntuacionParcelable(parcel);
        }

        @Override
        public PuntuacionParcelable[] newArray(int size) {
            return new PuntuacionParcelable[size];
        }
    };

    public PuntuacionParcelable (String nombre, long tiempo)
    {
        this.nombre = nombre;
        this.tiempo = tiempo;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTiempo() {
        return tiempo;
    }


    public PuntuacionParcelable (Parcel parcel)
    {
        this.nombre = parcel.readString();
        this.tiempo = parcel.readLong();

    }

    @Override
    public String toString() {
        return this.nombre + " " + this.tiempo;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.nombre);
        parcel.writeLong(this.tiempo);


    }

    @Override
    public int describeContents() {
        return 0;
    }

}

