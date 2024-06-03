package trackService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import trackService.model.artist.Artist;
import trackService.model.track.Track;
import trackService.model.track.TrackBuilder;
import trackService.repository.BaseDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackService {

    @Autowired
    private BaseDAO<Track> trackDAO;

    @Autowired
    private ArtistService artistService;


    //1.Basic Create, Update, Delete functionality

    public Track create(Track track) {
        TrackBuilder trackBuilder = new TrackBuilder();
        trackBuilder.startBuilder(track.getTitle()).addDurationInSeconds(track.getDurationInSeconds()).addIssueDate(track.getIssueDate());
        for (Artist artist: track.getArtists()){
            Artist validArtist = this.artistService.getValidArtist(artist);
            trackBuilder.addArtist(validArtist);
        }
        Track builderTrack = trackBuilder.build();

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
    //    Get Tracks by ID

    public Track getTrackById(int id) {
        Track track = this.trackDAO.get(id);
        if (track == null){
            return null;
        }
//        RestClient restClient = RestClient.builder().baseUrl("http://localhost:8089").build();
//        String body = restClient.get().uri("/track/" + track.getDurationInSeconds()).retrieve().body(String.class);
//        Double price = Double.parseDouble(body);
//        track.setPrice(price);
        return track;
    }

    //    Get Tracks with a specific media type
        public List<Track> getTracksByMediaType (Track.TrackMediaType trackMediaType){
            List<Track> trackList = trackDAO.getAll();
            ArrayList<Track> resultList = new ArrayList<>();

            for (Track track : trackList) {
                if (track.getTrackMediaType().equals(trackMediaType)) {
                    resultList.add(track);
                }
            }
            return resultList;
        }

    // ----------------------------------------------//
        //Get Tracks for a particular year
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

        //-----------------------------------------------//

        //Get Tracks longer/shorter/equal to a specific duration
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

    //---------------------------------------------------//

    public void initDatabase() {
        this.trackDAO.initDatabase();
    }

    public void clearDatabase() {
        this.trackDAO.clearDatabase();
    }

    }

// public void deleteTrack(int id) {
// this.trackDAO.delete(id);
//}

///------- CREATE A BUILDER ----------- ///
// public void updateTrack(Track track) {
//   this.trackDAO.update(track);
//}

//-----------------------------------------------------//

// Get tracks for a particular artist
//    public List<Track> getTracksByArtist(String artistName) {
//
//        List<Track> trackList = trackDAO.getAll();
//
//        ArrayList<Track> resultList = new ArrayList<>();
//        for (Track track : trackList) {
//
//            for (Artist artist : track.getArtists()) {
//
//
//                if (artist.getName().equals(artistName)) {
//                    resultList.add(track);}
//            }
//        }
//        return resultList;}