package fr.unice.polytech.clapevents.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import fr.unice.polytech.clapevents.R;


public class CityActivity extends AppCompatActivity {
    public static String EXTRA_MESSAGE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    public void chooseCity(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        switch(view.getId()){
            case R.id.imageButton2:
                intent.putExtra("city", "New York");
                startActivity(intent);
                break;
            case R.id.imageButton3:
                intent.putExtra("city", "Nice");
                startActivity(intent);
                break;
            case R.id.imageButton4:
                intent.putExtra("city", "Paris");
                startActivity(intent);
                break;
            case R.id.imageButton5:
                intent.putExtra("city", "Londres");
                startActivity(intent);
                break;
            case R.id.imageButton6:
                intent.putExtra("city", "Madrid");
                startActivity(intent);
                break;
            case R.id.imageButton7:
                intent.putExtra("city", "Rome");
                startActivity(intent);
                break;
            case R.id.imageButton8:
                intent.putExtra("city", "Marseille");
                startActivity(intent);
                break;
            default:
                intent.putExtra(CityActivity.EXTRA_MESSAGE, "Erreur");
                startActivity(intent);
        }

    }
}
