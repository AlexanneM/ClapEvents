package fr.unice.polytech.clapevents.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import fr.unice.polytech.clapevents.R;

public class MoreActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        intent = getIntent();

        ( (TextView) findViewById(R.id.title)).setText(getIntent().getStringExtra("title"));
        ( (TextView) findViewById(R.id.artists)).setText("Artistes: " + getIntent().getStringExtra("artists"));
        ( (TextView) findViewById(R.id.category)).setText("Catégorie: " + getIntent().getStringExtra("category"));
        ( (TextView) findViewById(R.id.description)).setText("Description: " + getIntent().getStringExtra("description"));
        int age = getIntent().getIntExtra("age", 0);
        if(age != 0){
            ( (TextView) findViewById(R.id.age)).setText("Age minimum: " + age);
        }else{
            ( (TextView) findViewById(R.id.age)).setText("Age minimum: tout public");
        }
    }

    public void images(View view){
        Intent newIntent = new Intent(this, ImagesActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        newIntent.putExtra("pathToPhoto",intent.getStringExtra("pathToPhoto"));
        newIntent.putExtra("pathToPhoto2",intent.getStringExtra("pathToPhoto2"));
        newIntent.putExtra("pathToPhoto4",intent.getStringExtra("pathToPhoto4"));
        startActivity(newIntent);
    }
}


