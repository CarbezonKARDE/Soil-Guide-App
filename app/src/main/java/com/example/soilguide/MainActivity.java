package com.example.soilguide;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SoilAdapter.OnSoilClickListener {
    private SoilAdapter adapter;
    private List<Soil> soilList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize soil data
        initializeData();

        // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new SoilAdapter(soilList, this);
        recyclerView.setAdapter(adapter);

        // Setup SearchView
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void initializeData() {
        soilList = new ArrayList<>();

        // Existing soils
        soilList.add(new Soil("Sandy Soil", R.drawable.sandy_soil,
                "Sandy soil is light, dry, and composed of large particles, which makes it well-draining but low in nutrients. It warms up quickly in the spring but does not retain moisture well, requiring frequent watering.",
                "Requires frequent irrigation due to poor water retention. Drip irrigation or mulching helps conserve moisture.",
                "Suitable Crops: Carrots, potatoes, peanuts, watermelon, and corn",
                "6.0 - 7.0", // pH level
                "Low in nutrients, requires frequent fertilization")); // Nutrient content

        soilList.add(new Soil("Clay Soil", R.drawable.clay_soil,
                "Clay soil is heavy and dense, with fine particles that retain water and nutrients well. However, it can become compacted and drain poorly.",
                "Requires careful irrigation to avoid waterlogging. Use raised beds or organic matter to improve drainage.",
                "Suitable Crops: Rice, lettuce, broccoli, cabbage, and beans",
                "5.5 - 6.5", // pH level
                "Rich in nutrients but may need aeration")); // Nutrient content

        // Add more soils here
        soilList.add(new Soil("Loamy Soil", R.drawable.loamy_soil,
                "Loamy soil is a balanced mixture of sand, silt, and clay. It is fertile, well-draining, and ideal for most plants.",
                "Moderate irrigation is sufficient. Retains moisture well without waterlogging.",
                "Suitable Crops: Wheat, sugarcane, cotton, tomatoes, and peppers",
                "6.0 - 7.0", // pH level
                "Rich in nutrients, ideal for most crops")); // Nutrient content

        soilList.add(new Soil("Peaty Soil", R.drawable.peaty_soil,
                "Peaty soil is dark, damp, and rich in organic matter. It retains moisture well but may be acidic.",
                "Requires less frequent irrigation due to high water retention. Add lime to reduce acidity if needed.",
                "Suitable Crops: Legumes, root vegetables, blueberries, and azaleas",
                "4.5 - 5.5", // pH level
                "High in organic matter, retains nutrients well")); // Nutrient content

        soilList.add(new Soil("Chalky Soil", R.drawable.chalky_soil,
                "Chalky soil is alkaline and contains stones. It drains well but may lack nutrients and require frequent fertilization.",
                "Requires frequent irrigation due to poor water retention. Add organic matter to improve fertility.",
                "Suitable Crops: Spinach, beets, cabbage, lilacs, and lavender",
                "7.5 - 8.5", // pH level
                "Low in nutrients, requires frequent fertilization")); // Nutrient content

        soilList.add(new Soil("Silty Soil", R.drawable.silty_soil,
                "Silty soil has a smooth texture and retains moisture well. It is fertile but can become compacted.",
                "Moderate irrigation is sufficient. Avoid overwatering to prevent compaction.",
                "Suitable Crops: Most vegetables and fruits, including apples, pears, and strawberries",
                "6.0 - 7.0", // pH level
                "Rich in nutrients, ideal for most crops")); // Nutrient content

        soilList.add(new Soil("Volcanic Soil", R.drawable.volcanic_soil,
                "Volcanic soil is highly fertile, rich in minerals, and retains moisture well. It is ideal for agriculture.",
                "Moderate irrigation is needed as it holds moisture efficiently.",
                "Suitable Crops: Coffee, tea, bananas, pineapples, and potatoes",
                "5.5 - 7.0", // pH level
                "High in potassium, phosphorus, and iron, supporting excellent crop growth")); // Nutrient content


        soilList.add(new Soil("Saline Soil", R.drawable.saline_soil,
                "Saline soil has a high salt content, making it challenging for most plants. It occurs in arid and semi-arid regions due to poor drainage and high evaporation rates.",
                "Requires frequent leaching with fresh water to remove excess salts. Drip irrigation is recommended.",
                "Suitable Crops: Barley, beets, date palms, and salt-tolerant grasses",
                "7.0 - 8.5", // pH level
                "High in soluble salts, but low in organic matter and nitrogen")); // Nutrient content

        soilList.add(new Soil("Alluvial Soil", R.drawable.alluvial_soil,
                "Alluvial soil is found in river plains and is one of the most fertile soils. It is rich in minerals and nutrients due to river deposits.",
                "Requires moderate irrigation as it retains moisture well.",
                "Suitable Crops: Rice, wheat, sugarcane, pulses, and cotton",
                "6.0 - 7.5", // pH level
                "Rich in phosphorus and organic matter, supports high crop yields")); // Nutrient content

        soilList.add(new Soil("Red Soil", R.drawable.red_soil,
                "Red soil gets its color from iron oxide and is common in warm, tropical regions. It has good drainage but is low in organic content.",
                "Requires frequent irrigation and organic matter to improve fertility.",
                "Suitable Crops: Millet, groundnuts, pulses, and cotton",
                "5.5 - 6.5", // pH level
                "High in iron and aluminum, but low in nitrogen and phosphorus")); // Nutrient content

        soilList.add(new Soil("Black Soil", R.drawable.black_soil,
                "Black soil, also called Regur soil, is rich in clay and retains moisture well. It is highly fertile and ideal for crops like cotton.",
                "Requires minimal irrigation due to its moisture-retaining properties.",
                "Suitable Crops: Cotton, wheat, soybean, and sunflower",
                "6.5 - 7.5", // pH level
                "Rich in calcium carbonate, magnesium, and potassium, but low in phosphorus")); // Nutrient content

        soilList.add(new Soil("Permafrost Soil", R.drawable.permafrost_soil,
                "Permafrost soil remains frozen for most of the year and is found in Arctic regions. It is unsuitable for most crops due to extreme conditions.",
                "Limited irrigation needed; greenhouse cultivation is required for plant growth.",
                "Suitable Crops: Lichen, moss, and some hardy root vegetables like potatoes (in controlled environments)",
                "4.0 - 5.5", // pH level
                "Low in nutrients and organic matter, often acidic")); // Nutrient content



    }

    @Override
    public void onSoilClick(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("SOIL_DATA", soilList.get(position));
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); // Fade animation
    }
}