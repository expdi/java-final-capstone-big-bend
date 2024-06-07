package trackService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trackService.model.album.Album;
import trackService.model.artist.Artist;
import trackService.model.track.Track;
import trackService.model.track.TrackBuilder;
import trackService.DAO.BaseDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackService {

    @Autowired
    private BaseDAO<Track> trackDAO;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private PricingClient pricingClient;

    //1.Basic Create, Update, Delete functionality

    public Track create(Track track) {
        TrackBuilder trackBuilder = new TrackBuilder();
        trackBuilder.startBuilder(track.getTitle()).addDurationInSeconds(track.getDurationInSeconds()).addIssueDate(track.getIssueDate()).addTrackMediaType(track.getTrackMediaType());
        Artist validArtist = this.artistService.getValidArtist(track.getArtist());
        trackBuilder.addArtist(validArtist);

        Album validAlbum = this.albumService.getValidAlbum(track.getAlbum());
        trackBuilder.addAlbum(validAlbum);
        Track builderTrack = trackBuilder.build() ;

        trackDAO.create(builderTrack);
        return builderTrack;
    }

    public boolean updateTrack(Track track){
        Track resultTrack = this.trackDAO.get(track.getId());

        if(resultTrack != null){
            trackDAO.update(track);
            return true;
        }
        return false;
    }

public boolean deleteTrack(int trackId){
    Track track = this.trackDAO.get(trackId);
    if (track != null){
        trackDAO.delete(trackId);
        return true;
    }
    return false;

}

    // ----------------------------------------------//
    //    Get Tracks

    public List<Track> getAll() {
        return trackDAO.getAll();
    }

    public Track getTrackById(int id) {
        Track track = this.trackDAO.get(id);
        if (track == null){
            return null;
        }

        double trackPrice = pricingClient.getTrackPrice(id);
        track.setPrice(trackPrice);
        return track;
    }

        public List<Track> getTracksByMediaType (Track.TrackMediaType trackMediaType) {
            List<Track> trackList = trackDAO.getAll();
            ArrayList<Track> resultList = new ArrayList<>();

            for (Track track : trackList) {
                if (track.getTrackMediaType() == (trackMediaType)) {
                    resultList.add(track);
                }
            }
            return resultList;
        }

            public List<Track> getTracksByIssueYear (LocalDate issueDate){
                List<Track> trackByYear = trackDAO.getAll();
                ArrayList<Track> resultList = new ArrayList<>();

                for (Track track : trackByYear) {
                    if (track.getIssueDate().getYear() == issueDate.getYear()) {
                        resultList.add(track);
                    }
                }
                return resultList;
            }

        public List<Track> getByDuration ( int durationInSeconds){
            List<Track> trackList = trackDAO.getAll();
            ArrayList<Track> resultList = new ArrayList<>();

            for (Track track : trackList) {
                if (track.getDurationInSeconds() == durationInSeconds){
                    resultList.add(track);
                }
            }
            return resultList;
        }

        public List<Track> getByDurationRange ( int minDuration, int maxDuration){
            List<Track> trackList = trackDAO.getAll();
            ArrayList<Track> resultList = new ArrayList<>();

            for (Track track : trackList) {
                if (track.getDurationInSeconds() >= minDuration && track.getDurationInSeconds() <= maxDuration)
                {
                    resultList.add(track);
                }
            }
            return resultList;

        }


    public void initDatabase() {
        this.trackDAO.initDatabase();
    }

    public void clearDatabase() {
        this.trackDAO.clearDatabase();
    }

    }
