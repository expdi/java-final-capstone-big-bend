package trackService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import trackService.mockData.MockAlbumFactory;
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
    Album album = MockAlbumFactory.defaultAlbum();
    Album savedAlbum = albumService.create(album);

    assertEquals(0, savedAlbum.getId());
    assertEquals("The Wheels On The Bus", savedAlbum.getTitle());
    assertEquals(MockTrackFactory.defaultTrack().getTitle(), savedAlbum.getTracks().get(0).getTitle());
  }

  @Test
  public void testUpdateAlbum() {
    Album album = MockAlbumFactory.defaultAlbum();
    Album savedAlbum = albumService.create(album);
    Integer albumId = savedAlbum.getId();
    Track newTrack = MockTrackFactory.defaultTracks().get(0);

    savedAlbum.setTracks( Set.of(newTrack));

    boolean updateRes = albumService.update(savedAlbum);
    assertEquals(true, updateRes);

    Album updatedAlbum = albumService.get(albumId);

    assertEquals(1, updatedAlbum.getTracks().size());
    assertEquals(newTrack.getTitle(), updatedAlbum.getTracks().get(0).getTitle());
  }

  @Test
  public void testDelete() {
    albumService.create(MockAlbumFactory.defaultAlbum());
    assertEquals(1, albumService.getAll().size());

    albumService.delete(0);
    assertEquals(0, albumService.getAll().size());
  }

  @Test
  public void testGetAll() {
    MockAlbumFactory.defaultAlbums().forEach(alb -> albumService.create(alb));

    assertEquals(4, albumService.getAll().size());
  }

  @Test
  public void testGetById() {
    MockAlbumFactory.defaultAlbums().forEach(alb -> albumService.create(alb));

    Album retrievedAlbum = albumService.get(2);

    assertEquals("Steal My Sunshine", retrievedAlbum.getTitle());
  }
}