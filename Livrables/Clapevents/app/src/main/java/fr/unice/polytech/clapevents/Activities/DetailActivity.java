package fr.unice.polytech.clapevents.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import fr.unice.polytech.clapevents.R;
import fr.unice.polytech.clapevents.tools.DataBaseHelper;

import static java.security.AccessController.getContext;

public class DetailActivity extends AppCompatActivity {
    Bitmap bitmap;
    Intent intent;
    String newDate;
    boolean favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        intent = getIntent();


        ( (TextView) findViewById(R.id.title)).setText(getIntent().getStringExtra("title"));
        ( (TextView) findViewById(R.id.price)).setText(String.valueOf(getIntent().getIntExtra("price", 0)));

        ( (TextView) findViewById(R.id.category)).setText(getIntent().getStringExtra("category"));

        String date = intent.getStringExtra("date");
        String[] items = date.split("-");
        int year = Integer.valueOf(items[0]);
        int month = Integer.valueOf(items[1]);
        int day = Integer.valueOf(items[2]);

        newDate = items[2] + "/" + items[1] + "/" + items[0];
        ( (TextView) findViewById(R.id.date)).setText(newDate);

        ImageView image = (ImageView) findViewById(R.id.image);
        try{
            String path = getIntent().getStringExtra("pathToPhoto");
            bitmap = BitmapFactory.decodeFile(path);
            Log.e("Path", path);
            image.setImageBitmap(bitmap);
        }catch (Exception e){}
        DataBaseHelper DB = new DataBaseHelper(this);
        favorite = DB.isFavorite(String.valueOf(getIntent().getIntExtra("key", 0)));
        Log.e("favorite", String.valueOf(favorite));
        ToggleButton heart2 = findViewById(R.id.heart2);
        heart2.setChecked(favorite);
        heart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!favorite) {
                    new DataBaseHelper(getApplicationContext()).addToFavorite(getIntent().getIntExtra("key",0));

                } else {
                    new DataBaseHelper(getApplicationContext()).deleteFromFavorite(getIntent().getIntExtra("key",0));

                }


            }
        });

    }
    public void more(View view){
        Intent newIntent = new Intent(this, MoreActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        newIntent.putExtra("key", intent.getIntExtra("key", 0));
        newIntent.putExtra("title", intent.getStringExtra("title"));
        newIntent.putExtra("artists",intent.getStringExtra("artists"));
        newIntent.putExtra("category",intent.getStringExtra("category"));
        newIntent.putExtra("description",intent.getStringExtra("description"));
        newIntent.putExtra("pathToPhoto",intent.getStringExtra("pathToPhoto"));
        newIntent.putExtra("pathToPhoto2",intent.getStringExtra("pathToPhoto2"));
        newIntent.putExtra("image_path",intent.getStringExtra("image_path"));
        newIntent.putExtra("pathToPhoto4",intent.getStringExtra("pathToPhoto4"));
        newIntent.putExtra("age",intent.getIntExtra("age", 0));
        startActivity(newIntent);
    }

    public void map(View view){
        Intent newIntent = new Intent(this, MapsActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        newIntent.putExtra("key", intent.getIntExtra("key", 0));
        newIntent.putExtra("place", intent.getStringExtra("place"));
        newIntent.putExtra("address",intent.getStringExtra("address"));
        newIntent.putExtra("image_place",intent.getStringExtra("image_place"));
        startActivity(newIntent);
    }

    public void pay(View view){
        Intent newIntent = new Intent(this, PayActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        newIntent.putExtra("key", intent.getIntExtra("key", 0));
        newIntent.putExtra("title", intent.getStringExtra("title"));
        newIntent.putExtra("date",intent.getStringExtra("date"));
        newIntent.putExtra("category",intent.getStringExtra("category"));
        newIntent.putExtra("place",intent.getStringExtra("place"));
        newIntent.putExtra("address",intent.getStringExtra("address"));
        newIntent.putExtra("price",intent.getIntExtra("price", 0));
        newIntent.putExtra("age",intent.getIntExtra("age", 0));
        newIntent.putExtra("pathToPhoto",intent.getStringExtra("pathToPhoto"));
        startActivity(newIntent);
    }

    public void calendar(View view){
        Intent newIntent = new Intent(this, CalendarActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        newIntent.putExtra("key", intent.getIntExtra("key", 0));
        newIntent.putExtra("title", intent.getStringExtra("title"));
        newIntent.putExtra("date", newDate);
        newIntent.putExtra("place",intent.getStringExtra("place"));
        newIntent.putExtra("address",intent.getStringExtra("address"));
        startActivity(newIntent);
    }
}
