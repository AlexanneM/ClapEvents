package fr.unice.polytech.clapevents.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import fr.unice.polytech.clapevents.Activities.DetailActivity;
import fr.unice.polytech.clapevents.Activities.ImagesActivity;
import fr.unice.polytech.clapevents.Activities.SortActivity;
import fr.unice.polytech.clapevents.model.Event;
import fr.unice.polytech.clapevents.tools.DataBaseHelper;
import fr.unice.polytech.clapevents.tools.EventsAdapter;
import fr.unice.polytech.clapevents.R;


public class EventsFragment extends Fragment {


    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    EventsAdapter eventsAdapter;
    private String city;
    private String category;
    private Boolean isPrice = false;
    private String category2;

    public EventsFragment() {
        // Required empty public constructor
    }


    public static EventsFragment newInstance() {
        return new EventsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);

        ImageButton sort = rootView.findViewById(R.id.sort);
        sort.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getContext(), SortActivity.class);
                newIntent.putExtra("city", city);
                newIntent.putExtra("category",category);
                newIntent.putExtra("isPrice",isPrice);
                newIntent.putExtra("category2", category2);
                startActivity(newIntent);
            }
        });

        city = getArguments().getString("city");
        category = getArguments().getString("category");
        isPrice = getArguments().getBoolean("isPrice");
        category2 = getArguments().getString("category2");

        ( (TextView) rootView.findViewById(R.id.city)).setText("Ville: " + city);
        ( (TextView) rootView.findViewById(R.id.category)).setText("Catégorie: " + category);


        ((ListView) rootView.findViewById(R.id.listView)).setAdapter(eventsAdapter);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try{
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this.getContext());
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
            Log.e("category2", category2);
            if(!category2.equals("") && !category2.equals(null)){
                if(isPrice){
                    eventsAdapter = new EventsAdapter(this.getContext(), dataBaseHelper.getAllEventsCategoryPrice(city, category, category2));
                    category2 = "";
                }else{
                    Log.e("probleme", "probleme");
                    eventsAdapter = new EventsAdapter(this.getContext(), dataBaseHelper.getAllEventsCategoryDate(city, category, category2));
                    category2 = "";
                }
            }else{
                if(isPrice){
                    eventsAdapter = new EventsAdapter(this.getContext(), dataBaseHelper.getAllEventsPrice(city, category));
                } if(!isPrice){
                    eventsAdapter = new EventsAdapter(this.getContext(), dataBaseHelper.getAllEventsDate(city, category));
                }
            }


            ((ListView) getActivity().findViewById(R.id.listView)).setAdapter(eventsAdapter);
            eventsAdapter.giveDatabaseHelper(dataBaseHelper);
            ((ListView) getActivity().findViewById(R.id.listView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Event event = (Event) parent.getItemAtPosition(position);

                    Intent intent = new Intent(getContext(),DetailActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    intent.putExtra("key",event.getKey());
                    intent.putExtra("title",event.getTitle());
                    intent.putExtra("artists",event.getArtists());
                    intent.putExtra("date",event.getDate());
                    intent.putExtra("category",event.getCategory());
                    intent.putExtra("place",event.getPlace());
                    intent.putExtra("address",event.getAddress());
                    intent.putExtra("description",event.getDescription());
                    intent.putExtra("pathToPhoto",event.getPathToPhoto());
                    intent.putExtra("pathToPhoto2",event.getPathToPhoto2());
                    intent.putExtra("image_place",event.getPathToPhotoPlace());
                    intent.putExtra("pathToPhoto4",event.getPathToPhoto4());
                    intent.putExtra("age",event.getAge());
                    intent.putExtra("price",event.getPrice());
                    intent.putExtra("favorite",event.isFavorite());
                    intent.putExtra("tickets",event.getTickets());
                    startActivity(intent);

                }
            });
            dataBaseHelper.close();


        }catch (Exception e){
            System.out.print(e);
        }
    }

}
