package trackService.model.track;

import trackService.model.artist.Artist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrackBuilder {

    private String title;

    private Track.TrackMediaType mediaType;

    private int durationInSeconds;

    private LocalDate issueDate;

    private String album;

    private List<Artist> artists = new ArrayList<>();


    public TrackBuilder startBuilder(String title) {
        this.title = title;
        return this;
    }

    public TrackBuilder addTrackMediaType(Track.TrackMediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public TrackBuilder addDurationInSeconds(int durationInSeconds) {
        if (durationInSeconds <= 0){
            throw new IllegalArgumentException("durationInSeconds must be a positive integer");
        }
        this.durationInSeconds = durationInSeconds;
        return this;
    }

    public TrackBuilder addIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
        return this;

    }

    public TrackBuilder addAlbum(String album) {
        this.album = album;
        return this;
    }

    public TrackBuilder addArtist(Artist artist) {
        this.artists.add(artist);
        return this;
    }

    public Track build() {

        Track track = new Track(this.title);
        track.setTrackMediaType(this.mediaType);
        track.setDurationInSeconds(this.durationInSeconds);
        track.setIssueDate(this.issueDate);
        track.setAlbum(this.album);
        track.setArtists(artists);
        return track;
    }

}