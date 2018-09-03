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
import fr.unice.polytech.clapevents.Activities.SendTicketsActivity;
import fr.unice.polytech.clapevents.R;
import fr.unice.polytech.clapevents.model.Event;
import fr.unice.polytech.clapevents.tools.DataBaseHelper;
import fr.unice.polytech.clapevents.tools.EventsAdapter;
import fr.unice.polytech.clapevents.tools.TicketsAdapter;

public class TicketsFragment extends Fragment {

    TicketsAdapter ticketsAdapter;
    public TicketsFragment() {
    }


    public static TicketsFragment newInstance() {
        return new TicketsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tickets, container, false);
        ((ListView) rootView.findViewById(R.id.listView)).setAdapter(ticketsAdapter);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try{
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this.getContext());
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
            ticketsAdapter = new TicketsAdapter(this.getContext(), dataBaseHelper.getTicketsEvents());
            ((ListView) getActivity().findViewById(R.id.listView)).setAdapter(ticketsAdapter);
            ((ListView) getActivity().findViewById(R.id.listView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Event event = (Event) parent.getItemAtPosition(position);

                    Intent intent = new Intent(getContext(),SendTicketsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    intent.putExtra("key",event.getKey());
                    intent.putExtra("title",event.getTitle());
                    intent.putExtra("date",event.getDate());
                    intent.putExtra("address",event.getAddress());
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
