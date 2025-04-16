// Soil.java
package com.example.soilappadv1;

public class Soil {
    private String name;
    private int imageResource;
    private String description;
    private String irrigation;
    private String suitableCrops;
    private String phLevel;
    private String nutrientContent;

    public Soil(String name, int imageResource, String description,
                String irrigation, String suitableCrops, String phLevel, String nutrientContent) {
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
    public String getPhLevel() { return phLevel; }
    public String getNutrientContent() { return nutrientContent; }
}
