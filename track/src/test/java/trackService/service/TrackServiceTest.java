package trackService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import trackService.exception.AlbumNotFoundException;
import trackService.mockData.MockAlbumFactory;
import trackService.model.album.Album;
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

    @Autowired
    AlbumService albumService;

    @MockBean
    PricingClient pricingClient;

    @BeforeEach
    public void setup() throws AlbumNotFoundException {
        this.trackService.clearDatabase();
        this.trackService.initDatabase();
//        Track sadSong = new TrackBuilder().startBuilder("Sad Song").addDurationInSeconds(108).addIssueDate(LocalDate.now()).addTrackMediaType(Track.TrackMediaType.MP3).build();
//        this.trackService.create(sadSong);
    }

    @Test
    public void testCreateTrackWithoutAlbum() throws AlbumNotFoundException {
        Track sadSong = new TrackBuilder().startBuilder("Sad Song").addDurationInSeconds(108).addIssueDate(LocalDate.now()).build();
        AlbumNotFoundException exception = assertThrows(AlbumNotFoundException.class, () ->
                this.trackService.create(sadSong));

        assertEquals("No album found for track.", exception.getMessage());
    }
    @Test
    public void testCreateTrack() throws AlbumNotFoundException {
        Album album = MockAlbumFactory.defaultAlbum();
        Album savedAlbum = albumService.create(album);
        Track sadSong = new TrackBuilder().startBuilder("Sad Song")
                .addDurationInSeconds(108)
                .addIssueDate(LocalDate.now())
                .addAlbum(album).build();
        Track newTrack = this.trackService.create(sadSong);

        assertEquals(0, newTrack.getId());
        assertEquals(sadSong.getTitle(), newTrack.getTitle());
        assertEquals(sadSong.getDurationInSeconds(), newTrack.getDurationInSeconds());

    }

//    @Test
//    public void testUpdateTrack() {
//        doReturn(1.23).when(pricingClient).getTrackPrice(0);
//
//        String trackNewTitle = "Sun Song";
//        Track track = this.trackService.getTrackById(0);
//        track.setTitle(trackNewTitle);
//        this.trackService.updateTrack(track);
//
//
//        Track updatedTrackTitle = this.trackService.getTrackById(0);
//
//        Assertions.assertEquals(trackNewTitle, updatedTrackTitle.getTitle());
//    }

    @Test
    public void testDeleteTrack() throws AlbumNotFoundException {
        Album album = MockAlbumFactory.defaultAlbum();
        Album savedAlbum = albumService.create(album);
        Track sadSong = new TrackBuilder().startBuilder("Sad Song")
                .addDurationInSeconds(108)
                .addIssueDate(LocalDate.now())
                .addAlbum(album).build();
        Track newTrack = this.trackService.create(sadSong);
        this.trackService.deleteTrack(0);

        Track trackRemoval = this.trackService.getTrackById(0);

        Assertions.assertNull(trackRemoval);

    }

    @Test
    public void testGetTracksByIssueYear() throws AlbumNotFoundException {
        Album album = MockAlbumFactory.defaultAlbum();
        Album savedAlbum = albumService.create(album);
        Track sadSong = new TrackBuilder().startBuilder("Sad Song")
                .addDurationInSeconds(108)
                .addIssueDate(LocalDate.now())
                .addAlbum(album).build();
        Track newTrack = this.trackService.create(sadSong);
        List<Track> result = trackService.getTracksByIssueYear(LocalDate.now());
        assertEquals(1, result.size());
    }

//    @Test
//    public void testGeTrackById() {
//        doReturn(1.23).when(pricingClient).getTrackPrice(0);
//        Track trackById = this.trackService.getTrackById(0);
//        Assertions.assertEquals("Sad Song", trackById.getTitle());
//    }

    @Test
    public void testGetByDuration() throws AlbumNotFoundException {
        Album album = MockAlbumFactory.defaultAlbum();
        Album savedAlbum = albumService.create(album);

        Track happySong = new TrackBuilder().startBuilder("Happy Song")
                .addDurationInSeconds(108)
                .addIssueDate(LocalDate.now())
                .addAlbum(album).build();
        Track newTrack = this.trackService.create(happySong);

        List<Track> result = trackService.getByDuration(108);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetByDurationRange() throws AlbumNotFoundException {
        Album album = MockAlbumFactory.defaultAlbum();
        Album savedAlbum = albumService.create(album);
        Track joySong = new TrackBuilder().startBuilder("Joy Song")
                .addDurationInSeconds(101)
                .addIssueDate(LocalDate.now())
                .addAlbum(album)
                .build();
        Track newTrack = this.trackService.create(joySong);

        Track crySong = new TrackBuilder().startBuilder("Cry Song")
                .addDurationInSeconds(200)
                .addIssueDate(LocalDate.now())
                .addAlbum(album).build();
        Track newTrack2 = this.trackService.create(crySong);

        List<Track> result = trackService.getByDurationRange(100, 110);
        assertEquals(1, result.size());

    }

    @Test
    public void testGetTrackByMediaType() throws AlbumNotFoundException {
        Album album = MockAlbumFactory.defaultAlbum();
        Album savedAlbum = albumService.create(album);
        Track joySong = new TrackBuilder().startBuilder("JoySong")
                .addDurationInSeconds(101)
                .addIssueDate(LocalDate.now())
                .addTrackMediaType(Track.TrackMediaType.MP3)
                .addAlbum(album)
                .build();
        Track newTrack = this.trackService.create(joySong);

        Track crySong = new TrackBuilder().startBuilder("CrySong")
                .addDurationInSeconds(200)
                .addIssueDate(LocalDate.now())
                .addTrackMediaType(Track.TrackMediaType.OGG)
                .addAlbum(album)
                .build();
        Track newTrack2 = this.trackService.create(crySong);

        List<Track> result = trackService.getTracksByMediaType(Track.TrackMediaType.MP3);
        assertEquals(1, result.size());


    }

    @Test
    public void testGetAllTracks() throws AlbumNotFoundException {
        Album album = MockAlbumFactory.defaultAlbum();
        Album savedAlbum = albumService.create(album);

        TrackBuilder trackBuilder = new TrackBuilder();
        trackBuilder.startBuilder("Joy Song")
                .addDurationInSeconds(108)
                .addTrackMediaType(Track.TrackMediaType.MP3)
                .addAlbum(album);
        Track newTrack = trackBuilder.build();
        this.trackService.create(newTrack);

        trackBuilder.startBuilder("Sad Song")
                .addDurationInSeconds(108)
                .addTrackMediaType(Track.TrackMediaType.OGG)
                .addAlbum(album);
        Track newTrack2 = trackBuilder.build();
        this.trackService.create(newTrack2);

        List<Track> result = trackService.getAll();
        assertEquals(2, result.size());
    }

}




