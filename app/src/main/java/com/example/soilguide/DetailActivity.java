// DetailActivity.java
package com.example.soilappadv1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivDetailImage;
    private TextView tvDetailName, tvDetailDescription, tvDetailIrrigation,
            tvDetailCrops, tvDetailPh, tvDetailNutrients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        ivDetailImage = findViewById(R.id.ivDetailImage);
        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        tvDetailIrrigation = findViewById(R.id.tvDetailIrrigation);
        tvDetailCrops = findViewById(R.id.tvDetailCrops);
        tvDetailPh = findViewById(R.id.tvDetailPh);
        tvDetailNutrients = findViewById(R.id.tvDetailNutrients);

        // Retrieve the passed data
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            int imageResource = extras.getInt("imageResource");
            String description = extras.getString("description");
            String irrigation = extras.getString("irrigation");
            String suitableCrops = extras.getString("suitableCrops");
            String phLevel = extras.getString("phLevel");
            String nutrientContent = extras.getString("nutrientContent");

            ivDetailImage.setImageResource(imageResource);
            tvDetailName.setText(name);
            tvDetailDescription.setText("Description: " + description);
            tvDetailIrrigation.setText("Irrigation: " + irrigation);
            tvDetailCrops.setText("Suitable Crops: " + suitableCrops);
            tvDetailPh.setText("pH Level: " + phLevel);
            tvDetailNutrients.setText("Nutrient Content: " + nutrientContent);
        }
    }
}
