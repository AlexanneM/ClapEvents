package fr.unice.polytech.clapevents.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import fr.unice.polytech.clapevents.Activities.MainActivity;
import fr.unice.polytech.clapevents.R;
import fr.unice.polytech.clapevents.model.Event;

public class EventsAdapter extends ArrayAdapter<Event> {

    private ImageView heart;
    private Event event;
    private DataBaseHelper DBHelper;
    private Bitmap bitmap;


    public EventsAdapter(Context context, List<Event> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_single_event, null);
        }

        event = getItem(position);


        TextView title = convertView.findViewById(R.id.title);
        TextView price = convertView.findViewById(R.id.price);
        TextView category = convertView.findViewById(R.id.category);
        TextView date = convertView.findViewById(R.id.date);
        ImageView image = convertView.findViewById(R.id.image);
        heart = convertView.findViewById(R.id.heart);


        title.setText(event.getTitle());
        price.setText(String.valueOf(event.getPrice()));
        category.setText(event.getCategory());

        String date2 = event.getDate();
        String[] items = date2.split("-");
        int year = Integer.valueOf(items[0]);
        int month = Integer.valueOf(items[1]);
        int day = Integer.valueOf(items[2]);

        String newDate = items[2] + "/" + items[1] + "/" + items[0];
        date.setText(newDate);

        try{
            String pathToPhoto = event.getPathToPhoto();
            bitmap = BitmapFactory.decodeFile(pathToPhoto);
                Log.e("Path", pathToPhoto);
                image.setImageBitmap(bitmap);


        }catch (Exception e){}

        if(event.isFavorite()){
            heart.setImageDrawable(heart.getContext().getResources().getDrawable(R.drawable.baseline_favorite_black_48));
        }else{
            heart.setImageDrawable(heart.getContext().getResources().getDrawable(R.drawable.heart));
        }
        return convertView;
    }


    @Override
    public Event getItem(int position) {
        return super.getItem(position);
    }

    public void giveDatabaseHelper(DataBaseHelper DBHelper){
        this.DBHelper = DBHelper;
    }

}
