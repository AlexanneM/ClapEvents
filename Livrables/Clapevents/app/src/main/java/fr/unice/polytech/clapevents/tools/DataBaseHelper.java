package fr.unice.polytech.clapevents.tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.clapevents.model.Event;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/fr.unice.polytech.clapevents/databases/";
    private static String DB_NAME = "clap";
    private Context myContext;
    private SQLiteDatabase myDataBase;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                // Copy the database in assets to the application database.
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database", e);
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            //database doesn't exist yet.
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException, IOException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public void openDataBaseWrite() throws SQLException, IOException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            try {
                copyDataBase();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public List<Event> getAllEventsDate(String city, String category) {
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM event WHERE place=? AND bigCategory=? ORDER BY date", new String[] {city, category});
        cursor.moveToFirst();
        List<Event> events = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            events.add(new Event(cursor.getInt(cursor.getColumnIndexOrThrow("_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("artists")),
                    cursor.getString(cursor.getColumnIndexOrThrow("date")),
                    cursor.getString(cursor.getColumnIndexOrThrow("place")),
                    cursor.getString(cursor.getColumnIndexOrThrow("address")),
                    cursor.getString(cursor.getColumnIndexOrThrow("category")),
                    cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path2")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path3")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path4")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("age")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("price")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("favorite"))==1,
                    cursor.getInt(cursor.getColumnIndexOrThrow("tickets"))

            ));

            cursor.moveToNext();
        }
        cursor.close();
        return events;
    }

    public List<Event> getAllEventsPrice(String city, String category) {
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM event WHERE place=? AND bigCategory=? ORDER BY price", new String[] {city, category});
        cursor.moveToFirst();
        List<Event> events = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            events.add(new Event(cursor.getInt(cursor.getColumnIndexOrThrow("_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("artists")),
                    cursor.getString(cursor.getColumnIndexOrThrow("date")),
                    cursor.getString(cursor.getColumnIndexOrThrow("place")),
                    cursor.getString(cursor.getColumnIndexOrThrow("address")),
                    cursor.getString(cursor.getColumnIndexOrThrow("category")),
                    cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path2")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path3")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path4")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("age")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("price")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("favorite"))==1,
                    cursor.getInt(cursor.getColumnIndexOrThrow("tickets"))

            ));

            cursor.moveToNext();
        }
        cursor.close();
        return events;
    }

    public List<Event> getAllEventsCategoryDate(String city, String category, String category2) {
        Log.e("category", category);
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM event WHERE place=? AND bigCategory=? AND category=? ORDER BY date", new String[] {city, category, category2});
        cursor.moveToFirst();
        List<Event> events = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            events.add(new Event(cursor.getInt(cursor.getColumnIndexOrThrow("_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("artists")),
                    cursor.getString(cursor.getColumnIndexOrThrow("date")),
                    cursor.getString(cursor.getColumnIndexOrThrow("place")),
                    cursor.getString(cursor.getColumnIndexOrThrow("address")),
                    cursor.getString(cursor.getColumnIndexOrThrow("category")),
                    cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path2")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path3")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path4")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("age")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("price")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("favorite"))==1,
                    cursor.getInt(cursor.getColumnIndexOrThrow("tickets"))

            ));

            cursor.moveToNext();
        }
        cursor.close();
        return events;
    }

    public List<Event> getAllEventsCategoryPrice(String city, String category, String category2) {
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM event WHERE place=? AND bigCategory=? AND category=? ORDER BY price", new String[] {city, category, category2});
        cursor.moveToFirst();
        List<Event> events = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            events.add(new Event(cursor.getInt(cursor.getColumnIndexOrThrow("_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("artists")),
                    cursor.getString(cursor.getColumnIndexOrThrow("date")),
                    cursor.getString(cursor.getColumnIndexOrThrow("place")),
                    cursor.getString(cursor.getColumnIndexOrThrow("address")),
                    cursor.getString(cursor.getColumnIndexOrThrow("category")),
                    cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path2")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path3")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path4")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("age")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("price")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("favorite"))==1,
                    cursor.getInt(cursor.getColumnIndexOrThrow("tickets"))

            ));

            cursor.moveToNext();
        }
        cursor.close();
        return events;
    }

    public List<Event> getFavoriteEvents() {
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM event WHERE favorite = 1 ORDER BY date", null);
        cursor.moveToFirst();
        List<Event> events = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            events.add(new Event(cursor.getInt(cursor.getColumnIndexOrThrow("_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("artists")),
                    cursor.getString(cursor.getColumnIndexOrThrow("date")),
                    cursor.getString(cursor.getColumnIndexOrThrow("place")),
                    cursor.getString(cursor.getColumnIndexOrThrow("address")),
                    cursor.getString(cursor.getColumnIndexOrThrow("category")),
                    cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path2")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path3")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path4")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("age")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("price")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("favorite"))==1,
                    cursor.getInt(cursor.getColumnIndexOrThrow("tickets"))
            ));
            cursor.moveToNext();
        }
        cursor.close();
        return events;
    }

    public List<Event> getTicketsEvents() {
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM event WHERE tickets != 0 ORDER BY date DESC", null);
        cursor.moveToFirst();
        List<Event> events = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            events.add(new Event(cursor.getInt(cursor.getColumnIndexOrThrow("_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("artists")),
                    cursor.getString(cursor.getColumnIndexOrThrow("date")),
                    cursor.getString(cursor.getColumnIndexOrThrow("place")),
                    cursor.getString(cursor.getColumnIndexOrThrow("address")),
                    cursor.getString(cursor.getColumnIndexOrThrow("category")),
                    cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path2")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path3")),
                    cursor.getString(cursor.getColumnIndexOrThrow("media_path4")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("age")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("price")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("favorite"))==1,
                    cursor.getInt(cursor.getColumnIndexOrThrow("tickets"))
            ));
            cursor.moveToNext();
        }
        cursor.close();
        return events;
    }

    public void addToFavorite(int id){
        try{
            openDataBaseWrite();
            myDataBase.execSQL("UPDATE event SET favorite = 1 WHERE _id = "+id);
            close();
        }catch (Exception e){
        System.out.print(e);
    }
    }

    public void deleteFromFavorite(int id){
        try{
        openDataBaseWrite();
        myDataBase.execSQL("UPDATE event SET favorite = 0 WHERE _id = "+id);
        close();
    }catch (Exception e){
        System.out.print(e);
    }
    }

    public void tickets(int id, int tickets){
        try{
            openDataBaseWrite();
            myDataBase.execSQL("UPDATE event SET tickets = "+ tickets +" WHERE _id = "+id);
            close();
        }catch (Exception e){
            System.out.print(e);
        }
    }
    public boolean isFavorite(String id){
        int favorite = 0;
        try{
            openDataBaseWrite();
            Cursor cursor = myDataBase.rawQuery("SELECT * FROM event WHERE _id = ?", new String[] {id});
            cursor.moveToFirst();
            favorite = cursor.getInt(cursor.getColumnIndexOrThrow("favorite"));
            close();
            return favorite == 1;
        }catch (Exception e){
            System.out.print(e);
            return false;
        }


    }


}
