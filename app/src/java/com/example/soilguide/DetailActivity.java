package com.example.soilguide;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Retrieve the Soil object passed from MainActivity
        Soil soil = getIntent().getParcelableExtra("SOIL_DATA");

        // Find views in the layout
        ImageView imageView = findViewById(R.id.detail_image);
        TextView name = findViewById(R.id.detail_name);
        TextView description = findViewById(R.id.detail_description);
        TextView irrigation = findViewById(R.id.detail_irrigation);
        TextView crops = findViewById(R.id.detail_crops);
        TextView phLevel = findViewById(R.id.detail_ph); // New TextView for pH level
        TextView nutrientContent = findViewById(R.id.detail_nutrients); // New TextView for nutrient content

        // Display the soil data
        if (soil != null) {
            imageView.setImageResource(soil.getImageResource());
            name.setText(soil.getName());
            description.setText(soil.getDescription());
            irrigation.setText("Irrigation: " + soil.getIrrigation()); // Add label
            crops.setText("Suitable Crops: " + soil.getSuitableCrops()); // Add label
            phLevel.setText("pH Level: " + soil.getPhLevel()); // Display pH level
            nutrientContent.setText("Nutrient Content: " + soil.getNutrientContent()); // Display nutrient content
        }
    }
}