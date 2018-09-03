package fr.unice.polytech.clapevents.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import fr.unice.polytech.clapevents.Activities.DetailActivity;
import fr.unice.polytech.clapevents.R;
import fr.unice.polytech.clapevents.model.Event;
import fr.unice.polytech.clapevents.tools.DataBaseHelper;
import fr.unice.polytech.clapevents.tools.EventsAdapter;


public class FavoritesFragment extends Fragment {


    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    EventsAdapter eventsAdapter;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
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
            eventsAdapter = new EventsAdapter(this.getContext(), dataBaseHelper.getFavoriteEvents());
            ((ListView) getActivity().findViewById(R.id.listView)).setAdapter(eventsAdapter);
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
