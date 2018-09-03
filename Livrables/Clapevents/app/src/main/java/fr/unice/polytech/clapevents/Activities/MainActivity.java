package fr.unice.polytech.clapevents.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import fr.unice.polytech.clapevents.R;
import fr.unice.polytech.clapevents.fragments.EventsFragment;
import fr.unice.polytech.clapevents.fragments.FavoritesFragment;
import fr.unice.polytech.clapevents.fragments.TicketsFragment;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private String city;
    private String category;
    private Boolean isPrice = false;
    private String category2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu);

        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        category = intent.getStringExtra("category");
        isPrice = intent.getBooleanExtra("isPrice", false);
        if(intent.hasExtra("category2")){
            category2 = intent.getStringExtra("category2");
        }
        Log.e("category2", category2);


        Fragment fragment = null;
        Class fragmentClass;
        fragmentClass = EventsFragment.class;

        try {

            fragment = (Fragment) fragmentClass.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString("city", city);
            bundle.putString("category", category);
            bundle.putString("category2", category2);
            bundle.putBoolean("isPrice", isPrice);
            category2 = "";
            fragment.setArguments(bundle);

        } catch (Exception e) {

            e.printStackTrace();

        }
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


        mDrawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;

    }

    public void selectDrawerItem(MenuItem menuItem) {

        // Create a new fragment and specify the fragment to show based on nav item clicked

        Fragment fragment = null;

        Class fragmentClass;


        // Add code here to update the UI based on the item selected
        // For example, swap UI fragments here
        switch(menuItem.getItemId()){
            case R.id.start:
                fragmentClass = EventsFragment.class;
                break;
            case R.id.nav_favorites:
                fragmentClass = FavoritesFragment.class;
                break;
            case R.id.nav_tickets:
                fragmentClass = TicketsFragment.class;
                break;
            default:
                fragmentClass = EventsFragment.class;

        }

        try {

            fragment = (Fragment) fragmentClass.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString("city", city);
            bundle.putString("category", category);
            bundle.putString("category2", category2);
            bundle.putBoolean("isPrice", isPrice);
            fragment.setArguments(bundle);

        } catch (Exception e) {

            e.printStackTrace();

        }

        // Insert the fragment by replacing any existing fragment

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();



        // Highlight the selected item has been done by NavigationView

        menuItem.setChecked(true);

        // Set action bar title
        if(menuItem.getItemId() != R.id.start){
            setTitle(menuItem.getTitle());
        }else{
            setTitle("Clap events");
        }


        // Close the navigation drawer

        mDrawerLayout.closeDrawers();

    }
                });
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void restart(View view){
        recreate();
    }
}
