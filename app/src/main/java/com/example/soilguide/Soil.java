package com.example.soilguide;

import android.os.Parcel;
import android.os.Parcelable;

public class Soil implements Parcelable {
    private String name;
    private int imageResource;
    private String description;
    private String irrigation;
    private String suitableCrops;
    private String phLevel; // New field
    private String nutrientContent; // New field

    // Constructor
    public Soil(String name, int imageResource, String description, String irrigation, String suitableCrops, String phLevel, String nutrientContent) {
        this.name = name;
        this.imageResource = imageResource;
        this.description = description;
        this.irrigation = irrigation;
        this.suitableCrops = suitableCrops;
        this.phLevel = phLevel;
        this.nutrientContent = nutrientContent;
    }

    // Getters
    public String getName() { return name; }
    public int getImageResource() { return imageResource; }
    public String getDescription() { return description; }
    public String getIrrigation() { return irrigation; }
    public String getSuitableCrops() { return suitableCrops; }
    public String getPhLevel() { return phLevel; } // Getter for pH level
    public String getNutrientContent() { return nutrientContent; } // Getter for nutrient content

    // Parcelable implementation
    protected Soil(Parcel in) {
        name = in.readString();
        imageResource = in.readInt();
        description = in.readString();
        irrigation = in.readString();
        suitableCrops = in.readString();
        phLevel = in.readString();
        nutrientContent = in.readString();
    }

    public static final Creator<Soil> CREATOR = new Creator<Soil>() {
        @Override
        public Soil createFromParcel(Parcel in) {
            return new Soil(in);
        }

        @Override
        public Soil[] newArray(int size) {
            return new Soil[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(imageResource);
        dest.writeString(description);
        dest.writeString(irrigation);
        dest.writeString(suitableCrops);
        dest.writeString(phLevel);
        dest.writeString(nutrientContent);
    }
}