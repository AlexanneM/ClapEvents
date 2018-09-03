package fr.unice.polytech.clapevents.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.unice.polytech.clapevents.R;

public class ImagesActivity extends AppCompatActivity {

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();


        ImageView image1 = (ImageView) findViewById(R.id.image1);
        try{
            String path = getIntent().getStringExtra("pathToPhoto");
            bitmap = BitmapFactory.decodeFile(path);
            image1.setImageBitmap(bitmap);
        }catch (Exception e){}

        ImageView image2 = (ImageView) findViewById(R.id.image2);
        try{
            String path = getIntent().getStringExtra("pathToPhoto2");
            bitmap = BitmapFactory.decodeFile(path);
            image2.setImageBitmap(bitmap);
        }catch (Exception e){}

        ImageView image3 = (ImageView) findViewById(R.id.imagePlace);
        try{
            String path = getIntent().getStringExtra("pathToPhoto4");
            bitmap = BitmapFactory.decodeFile(path);
            image3.setImageBitmap(bitmap);
        }catch (Exception e){}
    }

}
