package fr.unice.polytech.clapevents.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import fr.unice.polytech.clapevents.R;

public class CategoryActivity extends AppCompatActivity {

    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        // Get the Intent that started this activity and extract the string
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        city = intent.getStringExtra("city");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

    }

    public void chooseCategory(View view){
        Intent intent = new Intent(this, MainActivity.class);
        switch(view.getId()){
            case R.id.imageButton9:
                intent.putExtra("category", "Concerts");
                intent.putExtra("city", city);
                break;
            case R.id.imageButton10:
                intent.putExtra("category", "Spectacles");
                intent.putExtra("city", city);
                break;
            default:
                intent.putExtra(CityActivity.EXTRA_MESSAGE, "Erreur");
        }
        startActivity(intent);
    }

}
