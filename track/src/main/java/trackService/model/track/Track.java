package trackService.model.track;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import trackService.model.album.Album;
import trackService.model.artist.Artist;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="title")
    private String title;

    @Column(name="uuid")
    private UUID uuid;
    @Column(name="issueDate")
    private LocalDate issueDate;
    @Column(name="durationInSeconds")
    private int durationInSeconds;
    @Column(name="trackMediaType")
    private TrackMediaType trackMediaType;
    @Column(name="language")
    private String language;
    @ManyToOne
    @JoinColumn(name="artist_id",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name="album_id",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Album album;



    private Double price;



    // empty constructor //

    public Track(){this.artist = new Artist();}


    public Track(String title){this.title=title; this.artist = new Artist();}

    public enum TrackMediaType {
        OGG, MP3, FLAC, WAV;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
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

    public UUID getUuid() { return uuid; }

    public void setUuid(UUID uuid) { this.uuid = uuid; }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", uuid=" + uuid +
                ", album='" + album + '\'' +
                ", issueDate=" + issueDate +
                ", durationInSeconds=" + durationInSeconds +
                ", trackMediaType=" + trackMediaType +
                ", language='" + language + '\'' +
                ", artist=" + artist +
                ", price=" + price +
                '}';
    }

}
