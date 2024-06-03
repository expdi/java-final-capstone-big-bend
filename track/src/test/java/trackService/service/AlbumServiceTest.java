package trackService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import trackService.mockData.MockTrackFactory;
import trackService.model.album.Album;
import trackService.model.track.Track;

@SpringBootTest
public class AlbumServiceTest {

  private AlbumService albumService;

  @Autowired
  public AlbumServiceTest(AlbumService albumService) {
    this.albumService = albumService;
  }

  @BeforeEach
  public void setup() {
    albumService.clearDatabase();
    albumService.initDatabase();
  }

  @Test
  public void testCreateAlbum() {
    List<Track> tracks = MockTrackFactory.defaultTracks();
    Album album = new Album(null, "Dark Side Of The Moon", tracks, LocalDate.now());
    Album savedAlbum = albumService.create(album);

    assertEquals(0, savedAlbum.getId());
    assertEquals("Dark Side Of The Moon", savedAlbum.getTitle());
    assertEquals(tracks, savedAlbum.getTracks());
  }

  @Test
  public void testUpdateAlbum() {}
}
