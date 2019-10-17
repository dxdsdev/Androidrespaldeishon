package com.empresa.reciclyerviewrepaso.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Pokemon implements Parcelable {

    private int id;
    private String nombre;
    private String url;

    public Pokemon(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;
    }

    protected Pokemon(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        url = in.readString();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public int getId() {
        String[] arrayUrl=url.split("/");
        id=Integer.parseInt(arrayUrl[arrayUrl.length-1]);
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(url);
    }
}
