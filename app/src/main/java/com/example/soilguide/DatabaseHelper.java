package com.example.soilappadv1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "soils.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "soil";
    public static final String COL_NAME = "name";
    public static final String COL_IMAGE = "imageResource";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_IRRIGATION = "irrigation";
    public static final String COL_CROPS = "suitableCrops";
    public static final String COL_PH = "phLevel";
    public static final String COL_NUTRIENTS = "nutrientContent";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_IMAGE + " INTEGER, " +
                COL_DESCRIPTION + " TEXT, " +
                COL_IRRIGATION + " TEXT, " +
                COL_CROPS + " TEXT, " +
                COL_PH + " TEXT, " +
                COL_NUTRIENTS + " TEXT" +
                ")";
        db.execSQL(createTable);

        // Initial data (unchanged)
        String insertData = "INSERT INTO " + TABLE_NAME + " (" +
                COL_NAME + ", " + COL_IMAGE + ", " +
                COL_DESCRIPTION + ", " + COL_IRRIGATION + ", " +
                COL_CROPS + ", " + COL_PH + ", " +
                COL_NUTRIENTS + ") VALUES " +
                "('Alluvial Soil', " + R.drawable.alluvial_soil + ", " +
                "'Alluvial soil, also known as riverine soil, is one of the most fertile soil types globally, formed through the deposition of fine silt, clay, and sand by river systems over centuries. Found predominantly in floodplains, deltas, and river basins, it is rich in organic matter and minerals washed down from upstream regions. Its texture varies from sandy loam to silty clay, offering excellent water retention and drainage balance. This soil supports intensive agriculture due to its high fertility and ability to sustain diverse crops year-round. Common in regions like the Indo-Gangetic plains, Nile Delta, and Mississippi River Basin, it is ideal for large-scale farming but requires careful management to prevent erosion during floods. Challenges include occasional waterlogging and the need for periodic nutrient replenishment due to intensive cultivation.', " +
                "'Moderate to High (depends on crop and season; irrigation needed during dry periods, but natural flooding often supplements water needs)', " +
                "'Rice, Wheat, Sugarcane, Pulses, Maize, Cotton, Jute, Oilseeds, Vegetables (e.g., Tomatoes, Cauliflower), Fruits (e.g., Bananas, Mangoes)', " +
                "'6.5 to 7.5 (slightly acidic to neutral, optimal for most crops)', " +
                "'Rich in potash, lime, and organic matter; moderate in nitrogen and phosphorus; may require nitrogen supplements for high-yield crops')," +

                "('Black Soil', " + R.drawable.black_soil + ", " +
                "'Black soil, often called Regur or Black Cotton Soil, is a dark, clay-rich soil formed from the weathering of basaltic rocks in volcanic regions. Its high clay content (up to 60%) gives it exceptional moisture retention, making it ideal for crops that thrive in wet conditions. Found extensively in India’s Deccan Plateau, parts of East Africa, and northern Australia, it develops deep cracks during dry seasons, aiding natural aeration. This self-plowing characteristic enhances its suitability for rain-fed agriculture. Black soil is moderately fertile but sticky when wet, posing challenges for tillage. Its high mineral content supports robust crop growth, though drainage issues can arise in heavy rains. Management practices include adding organic matter to improve structure and prevent compaction.', " +
                "'Low to Moderate (high water retention reduces irrigation needs; supplemental watering during prolonged dry spells)', " +
                "'Cotton, Soybean, Millets (Jowar, Bajra), Sorghum, Groundnut, Sugarcane, Chickpeas, Sunflower, Wheat', " +
                "'6.5 to 7.5 (neutral to slightly alkaline)', " +
                "'High in calcium carbonate, magnesium, potash; low to moderate in nitrogen and phosphorus; organic amendments enhance fertility')," +

                "('Chalky Soil', " + R.drawable.chalky_soil + ", " +
                "'Chalky soil is a light, free-draining soil characterized by high calcium carbonate content, often containing visible chalk or limestone fragments. Found in regions like southern England, parts of France, and Mediterranean hills, it is typically alkaline, limiting its suitability for acid-loving plants. Its coarse texture ensures rapid drainage, reducing waterlogging risks but causing nutrient leaching, particularly nitrogen and iron. Chalky soils warm quickly in spring, benefiting early planting, but their low organic matter requires frequent fertilization. Farmers often add compost or manure to improve water retention and nutrient availability. While challenging for broad agriculture, these soils support unique ecosystems, including rare wildflowers. Tillage is easy due to the loose structure, but erosion control is critical on slopes.', " +
                "'Frequent (rapid drainage necessitates regular watering, especially in summer)', " +
                "'Barley, Clover, Spinach, Grapes, Lavender, Olives, Beets, Early Potatoes, Cabbage, Carrots', " +
                "'7.5 to 8.5 (alkaline, requires amendments for acid-loving crops)', " +
                "'Low in nitrogen, iron, and organic matter; high in calcium; requires balanced fertilizers and organic amendments')," +

                "('Clay Soil', " + R.drawable.clay_soil + ", " +
                "'Clay soil is dense and heavy, composed of very fine particles (less than 0.002 mm), giving it a smooth, sticky texture when wet and a hard, compact form when dry. Found globally in low-lying areas, such as river valleys and plains, it excels at retaining water and nutrients, making it fertile for certain crops. However, poor drainage and aeration pose challenges, often leading to waterlogging and root rot. Its plasticity allows it to hold shape, but it requires careful management, such as adding gypsum or organic matter (compost, manure) to improve structure. Clay soils warm slowly in spring, delaying planting, but their nutrient richness supports robust growth once cultivated. Common in temperate regions, they demand patience and skill for optimal farming.', " +
                "'Low (high water retention; irrigation rarely needed except in extreme drought)', " +
                "'Rice, Broccoli, Cabbage, Cauliflower, Beans, Peas, Lettuce, Kale, Fruit Trees (e.g., Apples, Pears)', " +
                "'5.5 to 6.5 (slightly acidic to neutral)', " +
                "'Rich in minerals (potassium, magnesium); poor aeration limits nutrient availability; benefits from organic matter additions')," +

                "('Loamy Soil', " + R.drawable.loamy_soil + ", " +
                "'Loamy soil is the gold standard for agriculture, blending sand (40%), silt (40%), and clay (20%) in a balanced ratio. This ideal mix ensures excellent drainage, moisture retention, and nutrient availability, making it versatile for nearly all crops. Found in fertile regions like the U.S. Midwest, parts of Europe, and eastern China, loam is easy to work with, offering a crumbly texture that supports root growth and microbial activity. Its moderate fertility requires occasional fertilization, but its structure minimizes erosion and compaction risks. Loamy soils warm moderately in spring, allowing flexible planting schedules. Farmers value its adaptability, though over-cultivation can deplete nutrients, necessitating crop rotation and cover cropping to maintain soil health.', " +
                "'Moderate (balanced water retention; irrigation depends on climate and crop needs)', " +
                "'Wheat, Corn, Sugarcane, Tomatoes, Peppers, Potatoes, Soybeans, Fruits (e.g., Apples, Berries), Vegetables (e.g., Carrots, Zucchini)', " +
                "'6.0 to 7.0 (slightly acidic to neutral, ideal for most plants)', " +
                "'Balanced NPK (nitrogen, phosphorus, potassium); moderate organic matter; benefits from periodic fertilization')," +

                "('Peaty Soil', " + R.drawable.peaty_soil + ", " +
                "'Peaty soil is rich in decomposed organic matter, formed in waterlogged, anaerobic conditions like marshes, bogs, and wetlands. Dark and spongy, it retains vast amounts of water (up to 20 times its weight), making it unique but challenging for agriculture. Found in places like Scotland, Ireland, and parts of Canada, it is highly acidic, limiting crop diversity unless limed to adjust pH. Its high carbon content makes it a critical carbon sink, but drainage for farming can release greenhouse gases. Peaty soils support specialized crops and require minimal tillage due to their loose structure. Management includes balancing moisture and adding mineral amendments to counter nutrient deficiencies, particularly potassium.', " +
                "'Low to Moderate (natural moisture retention; irrigation rarely needed, but drainage may be required)', " +
                "'Vegetables (e.g., Celery, Onions), Rice, Cranberries, Blueberries, Potatoes, Lettuce, Willows, Root Crops', " +
                "'4.5 to 5.5 (highly acidic; liming needed for non-acid-loving crops)', " +
                "'High in organic matter and carbon; low in potassium and micronutrients; requires mineral supplements')," +

                "('Permafrost Soil', " + R.drawable.permafrost_soil + ", " +
                "'Permafrost soil, prevalent in Arctic and subarctic regions like Siberia, Alaska, and northern Canada, remains frozen year-round at depths below the surface, with only a shallow active layer thawing in summer. This gelisol is not naturally suited for traditional agriculture due to its cold temperature, low nutrient content, and short growing season (sometimes just 60 days). Its structure varies from organic-rich to gravelly, often overlaid with moss or lichen. Modern farming in permafrost regions uses greenhouses or controlled environments to grow cold-hardy crops. Challenges include soil instability (thawing causes subsidence) and limited microbial activity, which slows nutrient cycling. Conservation is critical, as permafrost stores significant carbon, and disturbance accelerates climate change.', " +
                "'None or Controlled (no natural irrigation; controlled environments like greenhouses provide water)', " +
                "'Mosses, Lichens, Grasses (in greenhouses: Potatoes, Cabbage, Lettuce, Radishes, Carrots, Kale)', " +
                "'4.0 to 5.0 (acidic, due to organic matter accumulation)', " +
                "'Poor in nutrients (nitrogen, phosphorus, potassium); low microbial activity; requires heavy fertilization in controlled settings')," +

                "('Red Soil', " + R.drawable.red_soil + ", " +
                "'Red soil, vibrant in hue, forms through the weathering of ancient igneous and metamorphic rocks under warm, humid conditions. Its reddish color stems from iron oxides (hematite), which dominate its mineral profile. Found in tropical and subtropical regions like southern India, East Africa, and parts of South America, it is well-drained but often low in organic matter and nitrogen. Its texture ranges from sandy to clayey, affecting its workability. Red soils support agriculture with proper management, including fertilization and crop rotation to counter nutrient deficiencies. Erosion is a concern on slopes, requiring terracing or cover crops. Their warmth and aeration suit deep-rooted crops, but farmers must address acidity and nutrient leaching.', " +
                "'Moderate (good drainage; irrigation needed during dry seasons)', " +
                "'Groundnut, Millet, Cotton, Sorghum, Pulses, Tobacco, Potatoes, Citrus Fruits, Tea, Vegetables (e.g., Okra)', " +
                "'5.5 to 6.5 (slightly acidic; liming may be needed)', " +
                "'High in iron and aluminum oxides; low in nitrogen, phosphorus, and organic matter; requires NPK fertilizers')," +

                "('Saline Soil', " + R.drawable.saline_soil + ", " +
                "'Saline soil is characterized by excessive soluble salts (e.g., sodium chloride, sulfates), often found in arid and semi-arid regions like parts of the Middle East, Australia, and coastal areas. Its high salinity impairs plant growth by disrupting water uptake, leading to poor fertility and stunted crops. Formed through natural salt accumulation or irrigation with saline water, it has a crusty, white surface when dry. Management involves leaching salts with fresh water, adding gypsum to displace sodium, and selecting salt-tolerant crops. Its structure varies from sandy to clayey, but poor aggregation limits root penetration. Saline soils challenge farmers but can be reclaimed with persistent effort, transforming marginal lands into productive fields.', " +
                "'Controlled (careful irrigation to flush salts; overwatering risks waterlogging)', " +
                "'Salt-tolerant crops: Barley, Cotton, Sugar Beet, Asparagus, Spinach, Quinoa, Date Palms, Pomegranate', " +
                "'7.0 to 8.5 (neutral to alkaline)', " +
                "'High in sodium and salts; poor in nitrogen, phosphorus, and organic matter; requires reclamation and fertilization')," +

                "('Sandy Soil', " + R.drawable.sandy_soil + ", " +
                "'Sandy soil, composed of large particles (0.05–2 mm), is loose, gritty, and well-drained, warming quickly in spring but struggling to retain water and nutrients. Found in deserts, coastal regions, and riverbeds (e.g., Sahara, Florida, Rajasthan), it is easy to till but prone to drought and nutrient leaching. Its low clay content limits fertility, requiring frequent amendments like compost or mulch to improve structure. Sandy soils suit root crops and plants adapted to dry conditions, but irrigation systems (e.g., drip) are often necessary. Wind erosion is a risk, mitigated by windbreaks or cover crops. Despite challenges, its aeration supports rapid root growth, benefiting early-season planting.', " +
                "'Frequent (poor water retention; regular irrigation needed, especially in dry climates)', " +
                "'Carrots, Onions, Potatoes, Radishes, Lettuce, Strawberries, Peanuts, Watermelons, Tulips, Lavender', " +
                "'5.0 to 6.0 (slightly acidic)', " +
                "'Low in nitrogen, phosphorus, potassium, and organic matter; requires frequent fertilization and organic amendments')," +

                "('Silty Soil', " + R.drawable.silty_soil + ", " +
                "'Silty soil, with fine particles (0.002–0.05 mm), feels smooth and silky, retaining moisture better than sand but less than clay. Found in river valleys and floodplains (e.g., Yellow River Basin, Mississippi Delta), it is fertile due to its ability to hold nutrients and water. Its soft texture supports dense root systems, but poor aggregation makes it prone to erosion and compaction when wet. Silty soils are ideal for intensive farming, though farmers must avoid over-tilling to maintain structure. Adding organic matter enhances stability, while raised beds prevent waterlogging. Its moderate warmth and workability suit a wide range of crops, making it a favorite for horticulture.', " +
                "'Moderate (good moisture retention; irrigation depends on rainfall and crop needs)', " +
                "'Rice, Tomatoes, Peppers, Cucumbers, Beans, Peas, Wheat, Soybeans, Fruits (e.g., Grapes, Apples), Flowers', " +
                "'6.0 to 7.0 (slightly acidic to neutral)', " +
                "'Moderate to high in nitrogen, potassium; variable phosphorus; benefits from organic matter to prevent erosion')," +

                "('Volcanic Soil', " + R.drawable.volcanic_soil + ", " +
                "'Volcanic soil, or Andisol, forms from weathered volcanic ash and lava, creating a fertile, mineral-rich medium. Found near active or dormant volcanoes (e.g., Hawaii, Japan, Indonesia), it is dark, porous, and well-drained, with a unique ability to retain moisture without waterlogging. Its high content of iron, magnesium, and calcium supports lush vegetation, from tropical crops to forests. Volcanic soils are naturally fertile but can be acidic, requiring pH monitoring. Their loose structure makes tillage easy, though erosion on slopes is a concern, addressed by terracing or cover crops. These soils are prized for specialty crops and contribute to vibrant agricultural economies in volcanic regions.', " +
                "'Low to Moderate (good water retention; irrigation varies by climate)', " +
                "'Coffee, Pineapple, Sugarcane, Bananas, Tea, Tobacco, Maize, Potatoes, Grapes, Orchids, Vanilla', " +
                "'5.5 to 7.0 (slightly acidic to neutral; pH varies by ash composition)', " +
                "'Rich in iron, magnesium, calcium, and organic matter; moderate nitrogen; may need phosphorus supplements');";
        db.execSQL(insertData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getSoils(String searchText, MainActivity.Filter filter) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        ArrayList<String> conditions = new ArrayList<>();

        // Add search text condition
        if (searchText != null && !searchText.isEmpty()) {
            conditions.add(COL_NAME + " LIKE '%" + searchText + "%'");
        }

        // Add filter conditions
        if (filter != null) {
            if (!filter.phLevel.isEmpty()) {
                conditions.add(COL_PH + " LIKE '%" + filter.phLevel + "%'");
            }
            if (!filter.suitableCrops.isEmpty()) {
                conditions.add(COL_CROPS + " LIKE '%" + filter.suitableCrops + "%'");
            }
            if (!filter.nutrientContent.isEmpty()) {
                conditions.add(COL_NUTRIENTS + " LIKE '%" + filter.nutrientContent + "%'");
            }
        }

        // Combine conditions
        if (!conditions.isEmpty()) {
            query += " WHERE " + String.join(" AND ", conditions);
        }

        return db.rawQuery(query, null);
    }
}
