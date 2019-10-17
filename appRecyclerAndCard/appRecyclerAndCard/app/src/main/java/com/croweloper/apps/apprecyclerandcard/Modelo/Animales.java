package com.croweloper.apps.apprecyclerandcard.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Animales implements Parcelable {

    private String nombre,urlImage;

    public Animales(String nombre, String urlImage) {
        this.nombre = nombre;
        this.urlImage = urlImage;
    }

    protected Animales(Parcel in) {
        nombre = in.readString();
        urlImage = in.readString();
    }

    public static final Creator<Animales> CREATOR = new Creator<Animales>() {
        @Override
        public Animales createFromParcel(Parcel in) {
            return new Animales(in);
        }

        @Override
        public Animales[] newArray(int size) {
            return new Animales[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public String getUrlImage() {
        return urlImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(urlImage);
    }
}
