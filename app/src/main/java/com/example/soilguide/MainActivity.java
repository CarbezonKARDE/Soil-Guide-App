package com.example.soilappadv1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SoilAdapter.OnSoilClickListener {

    private EditText etSearch;
    private Button btnFilter;
    private RecyclerView rvSoils;
    private SoilAdapter adapter;
    private ArrayList<Soil> soilList;
    private DatabaseHelper dbHelper;

    // Filter object to hold multiple filter criteria
    private Filter currentFilter = new Filter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);
        btnFilter = findViewById(R.id.btnFilter);
        rvSoils = findViewById(R.id.rvSoils);

        dbHelper = new DatabaseHelper(this);
        soilList = new ArrayList<>();

        // Set up RecyclerView
        adapter = new SoilAdapter(soilList, this);
        rvSoils.setLayoutManager(new LinearLayoutManager(this));
        rvSoils.setAdapter(adapter);

        // Load initial data
        loadSoils("", currentFilter);

        // Listen for search text changes
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadSoils(s.toString(), currentFilter);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Set up filter button to show a dialog
        btnFilter.setOnClickListener(v -> showFilterDialog());
    }

    private void showFilterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Filters");

        // Create a view for the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_filter, null);
        EditText etPhLevel = dialogView.findViewById(R.id.etPhLevel);
        EditText etCrops = dialogView.findViewById(R.id.etCrops);
        EditText etNutrients = dialogView.findViewById(R.id.etNutrients);

        // Pre-fill current filter values
        etPhLevel.setText(currentFilter.phLevel);
        etCrops.setText(currentFilter.suitableCrops);
        etNutrients.setText(currentFilter.nutrientContent);

        builder.setView(dialogView);

        builder.setPositiveButton("Apply", (dialog, which) -> {
            // Update filter values
            currentFilter.phLevel = etPhLevel.getText().toString().trim();
            currentFilter.suitableCrops = etCrops.getText().toString().trim();
            currentFilter.nutrientContent = etNutrients.getText().toString().trim();

            // Update button text to indicate active filters
            btnFilter.setText(currentFilter.isEmpty() ? "Filter: Off" : "Filter: On");

            // Reload data with new filters
            loadSoils(etSearch.getText().toString(), currentFilter);
        });

        builder.setNegativeButton("Clear", (dialog, which) -> {
            // Clear all filters
            currentFilter = new Filter();
            btnFilter.setText("Filter: Off");
            loadSoils(etSearch.getText().toString(), currentFilter);
        });

        builder.setNeutralButton("Cancel", null);
        builder.show();
    }

    private void loadSoils(String searchText, Filter filter) {
        soilList.clear();
        Cursor cursor = dbHelper.getSoils(searchText, filter);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
                int imageRes = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_IMAGE));
                String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DESCRIPTION));
                String irrigation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_IRRIGATION));
                String suitableCrops = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CROPS));
                String phLevel = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PH));
                String nutrientContent = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NUTRIENTS));

                Soil soil = new Soil(name, imageRes, description, irrigation, suitableCrops, phLevel, nutrientContent);
                soilList.add(soil);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSoilClick(Soil soil) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("name", soil.getName());
        intent.putExtra("imageResource", soil.getImageResource());
        intent.putExtra("description", soil.getDescription());
        intent.putExtra("irrigation", soil.getIrrigation());
        intent.putExtra("suitableCrops", soil.getSuitableCrops());
        intent.putExtra("phLevel", soil.getPhLevel());
        intent.putExtra("nutrientContent", soil.getNutrientContent());
        startActivity(intent);
    }

    // Filter class to hold multiple filter criteria
    static class Filter {
        String phLevel = "";
        String suitableCrops = "";
        String nutrientContent = "";

        boolean isEmpty() {
            return phLevel.isEmpty() && suitableCrops.isEmpty() && nutrientContent.isEmpty();
        }
    }
}
