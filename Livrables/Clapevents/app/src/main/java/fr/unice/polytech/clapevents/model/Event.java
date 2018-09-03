package fr.unice.polytech.clapevents.model;


public class Event {
    private int key;
    private String title;
    private String artists;
    private String date;
    private String place;
    private String address;
    private String category;
    private String description;
    private String pathToPhoto;
    private String pathToPhoto2;
    private String pathToPhotoPlace;
    private String pathToPhoto4;
    private int age;
    private int price;
    private boolean favorite;
    private int tickets;


    public Event(int key, String title, String artists, String date, String place, String address, String category, String description, String pathToPhoto,  String pathToPhoto2,  String pathToPhotoPlace,  String pathToPhoto4, int age, int price, boolean favorite, int tickets) {
        this.key = key;
        this.title = title;
        this.artists = artists;
        this.date = date;
        this.place = place;
        this.address = address;
        this.category = category;
        this.description = description;
        this.pathToPhoto = pathToPhoto;
        this.pathToPhoto2 = pathToPhoto2;
        this.pathToPhotoPlace = pathToPhotoPlace;
        this.pathToPhoto4 = pathToPhoto4;
        this.age = age;
        this.price = price;
        this.favorite = favorite;
        this.tickets = tickets;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPathToPhoto2() {
        return pathToPhoto2;
    }

    public void setPathToPhoto2(String pathToPhoto2) {
        this.pathToPhoto2 = pathToPhoto2;
    }

    public String getPathToPhotoPlace() {
        return pathToPhotoPlace;
    }

    public void setPathToPhotoPlace(String pathToPhotoPlace) {
        this.pathToPhotoPlace = pathToPhotoPlace;
    }

    public String getPathToPhoto4() {
        return pathToPhoto4;
    }

    public void setPathToPhoto4(String pathToPhoto4) {
        this.pathToPhoto4 = pathToPhoto4;
    }


    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public String getTitle() {
        return title;
    }

    public int getKey() {
        return key;
    }


    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPathToPhoto(String pathToPhoto) {
        this.pathToPhoto = pathToPhoto;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
