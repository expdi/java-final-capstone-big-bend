package trackService.model.artist;

import trackService.model.track.Track;

import java.util.ArrayList;
import java.util.List;

public class Artist {

    private int id;
    private String name;
    private String musicGenre;
    private String nationality;
    private List<Track> listOfTracks;

    //-----------------------------
    //contrutor
    public Artist(String name){
        this.listOfTracks = new ArrayList<>();
        this.name= name;}

    public Artist() {
    }

    //-----------------------------

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Track> getListOfTracks() {
        return listOfTracks;
    }

    public void setListOfTracks(List<Track> listOfTracks) {
        this.listOfTracks = listOfTracks;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", musicGenre='" + musicGenre + '\'' +
                ", nationality='" + nationality + '\'' +
                ", tracks=" + listOfTracks +
                '}';
    }

}
