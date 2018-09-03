package fr.unice.polytech.clapevents.Activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import fr.unice.polytech.clapevents.R;

public class SendTicketsActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_tickets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        intent = getIntent();

    }

    public void send(View view){
        Intent newIntent = new Intent(Intent.ACTION_SEND);
        newIntent.setType("text/plain");
        newIntent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
        newIntent.putExtra(Intent.EXTRA_SUBJECT, "J'ai réservé les places!!");
        newIntent.putExtra(Intent.EXTRA_TEXT, "J'ai réservé " + intent.getIntExtra("tickets", 1) + " places pour " + intent.getStringExtra("title") + "!");
        newIntent.setType("image/*");
        Uri uri = Uri.parse("android.res://"+getPackageName()+"/"+R.drawable.billets);
        try {
            Uri imageUri = null;
                imageUri = Uri.parse(MediaStore.Images.Media.insertImage(this.getContentResolver(),
                        BitmapFactory.decodeResource(getResources(), R.drawable.billets), null, null));
                newIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                startActivity(newIntent);
            } catch (android.content.ActivityNotFoundException ex) {
            //do something else
        }}


    public void menu(View view){
        Intent newIntent = new Intent(this, MainActivity.class);
        startActivity(newIntent);
    }
}
