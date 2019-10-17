package com.croweloper.apps.apppruebavolleyreycler.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Cedes implements Parcelable {

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Cedes() {

    }

    public Cedes(int albumId, int id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    protected Cedes(Parcel in) {
        albumId = in.readInt();
        id = in.readInt();
        title = in.readString();
        url = in.readString();
        thumbnailUrl = in.readString();
    }

    public static final Creator<Cedes> CREATOR = new Creator<Cedes>() {
        @Override
        public Cedes createFromParcel(Parcel in) {
            return new Cedes(in);
        }

        @Override
        public Cedes[] newArray(int size) {
            return new Cedes[size];
        }
    };

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(albumId);
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(thumbnailUrl);
    }
}
