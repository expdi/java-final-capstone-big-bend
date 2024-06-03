package trackService.model.artist;

import trackService.model.track.Track;
import java.util.List;

public class ArtistBuilder {

    private String name;

    private String musicGender;

    private List<Track> listOfTrack;

    private String nationality;

    public ArtistBuilder startArtistBuilder(String name) {
        this.name = name;
        return this;
    }
    public  ArtistBuilder addMusicGender(String musicGender) {
        this.musicGender = musicGender;
        return this;
    }

    public ArtistBuilder addListOfTrack(List<Track> listOfTrack) {
        this.listOfTrack = listOfTrack;
        return this;
    }

    public ArtistBuilder addNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public Artist build() {

        Artist artist = new Artist(this.name);
        artist.setMusicGenre(this.musicGender);
        artist.setListOfTracks(this.listOfTrack);
        artist.setNationality(this.nationality);

        return artist;
    }

}