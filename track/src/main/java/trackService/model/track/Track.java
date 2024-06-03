package trackService.model.track;

import trackService.model.artist.Artist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Track {

    private int id;
    private String title;
    private List<Artist> artists;
    private String album;
    private LocalDate issueDate;
    private int durationInSeconds;
    private TrackMediaType trackMediaType;
    private String language;


    private Double price;



    // empty constructor //

    public Track(){this.artists= new ArrayList<>();}


    public Track(String title){this.title=title; this.artists= new ArrayList<>();}

    public enum TrackMediaType {
        OGG, MP3, FLAC, WAV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public TrackMediaType getTrackMediaType() {
        return trackMediaType;
    }

    public void setTrackMediaType(TrackMediaType trackMediaType) {
        this.trackMediaType = trackMediaType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artists=" + artists +
                ", album=" + album +
                ", issueDate=" + issueDate +
                ", durationInSeconds=" + durationInSeconds +
                ", trackMediaType=" + trackMediaType +
                ", language='" + language + '\'' +
                '}';
    }

}
