package fr.unice.polytech.clapevents.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import fr.unice.polytech.clapevents.R;
import fr.unice.polytech.clapevents.tools.DataBaseHelper;

public class PayActivity extends AppCompatActivity {
    Intent intent;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        intent = getIntent();

        ( (TextView) findViewById(R.id.title)).setText(getIntent().getStringExtra("title"));
        ( (TextView) findViewById(R.id.place)).setText("Ville:  " + getIntent().getStringExtra("place"));
        ( (TextView) findViewById(R.id.category)).setText("Catégorie: " + getIntent().getStringExtra("category"));
        ( (TextView) findViewById(R.id.date)).setText("Date:  " + getIntent().getStringExtra("date"));
        int age = getIntent().getIntExtra("age", 0);
        if(age != 0){
            ( (TextView) findViewById(R.id.age)).setText("Age minimum:  " + age + "ans");
        }else{
            ( (TextView) findViewById(R.id.age)).setText("Age minimum:  tout public");
        }
        ( (TextView) findViewById(R.id.address)).setText("Adresse:  " + getIntent().getStringExtra("address"));
        ( (TextView) findViewById(R.id.price)).setText("Prix:  " + String.valueOf(getIntent().getIntExtra("price", 0)) + " euros");
        editText = (EditText) findViewById(R.id.editText);

    }


    public void validate(View view){
        int tickets = Integer.valueOf(editText.getText().toString());
        DataBaseHelper DBHelper = new DataBaseHelper(getApplicationContext());
        DBHelper.tickets(intent.getIntExtra("key", 1), tickets);
        Intent newIntent = new Intent(this, SendTicketsActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        newIntent.putExtra("key",intent.getIntExtra("key", 0));
        newIntent.putExtra("title",intent.getStringExtra("title"));
        newIntent.putExtra("tickets",tickets);
        newIntent.putExtra("pathToPhoto",intent.getStringExtra("pathToPhoto"));
        startActivity(newIntent);
    }

}
