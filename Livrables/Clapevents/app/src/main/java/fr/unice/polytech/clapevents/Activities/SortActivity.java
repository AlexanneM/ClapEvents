package fr.unice.polytech.clapevents.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import fr.unice.polytech.clapevents.R;

public class SortActivity extends AppCompatActivity {

    Intent intent;
    String category2;
    Boolean isPrice;
    EditText editText3;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        intent = getIntent();

        checkBox = findViewById(R.id.checkBox);
        checkBox.setChecked(intent.getBooleanExtra("isPrice", false));

        editText3 = (EditText) findViewById(R.id.category2);



    }

    public void sort(View view){
        category2 = editText3.getText().toString();
        if (category2.trim() == ""){
            category2 = "";
        }
        Log.e("category2", category2);

        isPrice = checkBox.isChecked();

        Intent newIntent = new Intent(this, MainActivity.class);
        newIntent.putExtra("isPrice", isPrice);
        newIntent.putExtra("category2", category2);
        newIntent.putExtra("city", intent.getStringExtra("city"));
        newIntent.putExtra("category", intent.getStringExtra("category"));
        startActivity(newIntent);
    }
}
