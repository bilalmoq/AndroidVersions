package com.example.androidversions;

public class AndroidVersion {
    public int imageResId;
    public String codeName;
    public String version;

    public AndroidVersion(int imageResId, String codeName, String version) {
        this.imageResId = imageResId;
        this.codeName = codeName;
        this.version = version;
    }
}
