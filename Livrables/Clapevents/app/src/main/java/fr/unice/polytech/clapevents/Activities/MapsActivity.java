package fr.unice.polytech.clapevents.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import fr.unice.polytech.clapevents.R;


public class MapsActivity extends AppCompatActivity {

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        ((TextView) findViewById(R.id.title)).setText(getIntent().getStringExtra("title"));
        ((TextView) findViewById(R.id.place)).setText("Ville: " + getIntent().getStringExtra("place"));
        ((TextView) findViewById(R.id.address)).setText("Adresse: " + getIntent().getStringExtra("address"));

        ImageView image = (ImageView) findViewById(R.id.imagePlace);
        try{
            String path = getIntent().getStringExtra("image_place");
            bitmap = BitmapFactory.decodeFile(path);
            image.setImageBitmap(bitmap);
        }catch (Exception e){}
    }
}
