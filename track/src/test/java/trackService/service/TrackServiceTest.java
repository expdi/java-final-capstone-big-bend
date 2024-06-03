package trackService.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import trackService.model.track.Track;
import trackService.model.track.TrackBuilder;

import java.time.LocalDate;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)


public class TrackServiceTest {

    @Autowired
    TrackService trackService;

    @BeforeEach
    public void setup() {
        this.trackService.clearDatabase();
        this.trackService.initDatabase();
        Track sadSong = new TrackBuilder().startBuilder("Sad Song").addDurationInSeconds(108).addIssueDate(LocalDate.now()).build();
        this.trackService.create(sadSong);
    }

    @Test
    public void testCreateTrack() {
        Track sadSong = new TrackBuilder().startBuilder("Sad Song").addDurationInSeconds(108).addIssueDate(LocalDate.now()).build();
        Track newTrack = this.trackService.create(sadSong);

        Assertions.assertEquals(1, newTrack.getId());
        Assertions.assertEquals(sadSong.getTitle(), newTrack.getTitle());
        Assertions.assertEquals(sadSong.getDurationInSeconds(), newTrack.getDurationInSeconds());
    }

    @Test
    public void testUpdateTrack() {

        String trackNewTitle = "Sun Song";
        Track track = this.trackService.getTrackById(0);
        track.setTitle(trackNewTitle);
        this.trackService.updateTrack(track);

        Track updatedTrackTitle = this.trackService.getTrackById(0);

        Assertions.assertEquals(trackNewTitle, updatedTrackTitle.getTitle());
    }

    @Test
    public void testDeleteTrack() {
        this.trackService.deleteTrack(0);

        Track trackRemoval = this.trackService.getTrackById(0);

        Assertions.assertNull(trackRemoval);

    }


    @Test
    public void testGeTrackById() {
        Track trackById = this.trackService.getTrackById(0);


        Assertions.assertEquals("Sad Song", trackById.getTitle());
    }
}


//    @Test
//    public void testGetTrackByArtist(){
//        List<Track> resultTracks = trackService.getTracksByArtist(null);
//        Assertions.assertEquals(2, resultTracks.size());
//    }
//}



    // public List<Track> getTracksByMediaType (Track.TrackMediaType trackMediaType){
    // public List<Track> getTracksByIssueYear (LocalDate issueDate){
    // public List<Track> getByDuration ( int durationInSeconds){
    // public List<Track> getByDurationRange ( int minDuration, int maxDuration){



