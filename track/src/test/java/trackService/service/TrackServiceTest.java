package trackService.service;

import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import trackService.model.artist.Artist;
import trackService.model.artist.ArtistBuilder;
import trackService.model.track.Track;
import trackService.model.track.TrackBuilder;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class TrackServiceTest {

    @Autowired
    TrackService trackService;

    @MockBean
    PricingClient pricingClient;

    @BeforeEach
    public void setup() {
        this.trackService.clearDatabase();
        this.trackService.initDatabase();
        Track sadSong = new TrackBuilder().startBuilder("Sad Song").addDurationInSeconds(108).addIssueDate(LocalDate.now()).addTrackMediaType(Track.TrackMediaType.MP3).build();
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
        doReturn(1.23).when(pricingClient).getTrackPrice(0);

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
    public void testGetTracksByIssueYear() {
        List<Track> result = trackService.getTracksByIssueYear(LocalDate.now());
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void testGeTrackById() {
        doReturn(1.23).when(pricingClient).getTrackPrice(0);
        Track trackById = this.trackService.getTrackById(0);
        Assertions.assertEquals("Sad Song", trackById.getTitle());
    }

    @Test
    public void testGetByDuration() {
        Track happySong = new TrackBuilder().startBuilder("Happy Song").addDurationInSeconds(108).addIssueDate(LocalDate.now()).build();
        Track newTrack = this.trackService.create(happySong);

        List<Track> result = trackService.getByDuration(108);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testGetByDurationRange() {
        Track joySong = new TrackBuilder().startBuilder("Joy Song").addDurationInSeconds(101).addIssueDate(LocalDate.now()).build();
        Track newTrack = this.trackService.create(joySong);

        Track crySong = new TrackBuilder().startBuilder("Cry Song").addDurationInSeconds(200).addIssueDate(LocalDate.now()).build();
        Track newTrack2 = this.trackService.create(crySong);

        List<Track> result = trackService.getByDurationRange(100, 110);
        Assertions.assertEquals(2, result.size());

    }

    @Test
    public void testGetTrackByMediaType() {
        Track joySong = new TrackBuilder().startBuilder("JoySong").addDurationInSeconds(101).addIssueDate(LocalDate.now()).addTrackMediaType(Track.TrackMediaType.MP3).build();
        Track newTrack = this.trackService.create(joySong);

        Track crySong = new TrackBuilder().startBuilder("CrySong").addDurationInSeconds(200).addIssueDate(LocalDate.now()).addTrackMediaType(Track.TrackMediaType.OGG).build();
        Track newTrack2 = this.trackService.create(crySong);

        List<Track> result = trackService.getTracksByMediaType(Track.TrackMediaType.MP3);
        Assertions.assertEquals(2, result.size());


    }

    @Test
    public void testGetAllTracks(){
        TrackBuilder trackBuilder = new TrackBuilder();
        trackBuilder.startBuilder("Joy Song").addDurationInSeconds(108).addTrackMediaType(Track.TrackMediaType.MP3);
        Track newTrack = trackBuilder.build();
        this.trackService.create(newTrack);

        trackBuilder.startBuilder("Sad Song").addDurationInSeconds(108).addTrackMediaType(Track.TrackMediaType.OGG);
        Track newTrack2 = trackBuilder.build();
        this.trackService.create(newTrack2);

        List<Track> result = trackService.getAll();
        Assertions.assertEquals(3, result.size());
    }

}




