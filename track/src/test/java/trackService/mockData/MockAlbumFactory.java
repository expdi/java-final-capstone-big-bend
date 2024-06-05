package trackService.mockData;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import trackService.model.album.Album;

public class MockAlbumFactory {
  public static Album defaultAlbum() {
    return new Album(null, "The Wheels On The Bus", Set.of(MockTrackFactory.defaultTrack()), LocalDate.now());
  }

  public static List<Album> defaultAlbums() {
    return List.of(
      new Album(null, "Dark Side Of The Moon", Set.of(MockTrackFactory.defaultTrackOf("Dark Side Of The Moon")), LocalDate.of(1973, 3, 1)),
      new Album(null, "Revolver", Set.of(MockTrackFactory.defaultTrackOf("Revolver")), LocalDate.of(1973, 3, 1)),
      new Album(null, "Steal My Sunshine", Set.of(MockTrackFactory.defaultTrackOf("Steal My Sunshine")), LocalDate.of(1973, 3, 1)),
      MockAlbumFactory.defaultAlbum()
    );
  }
}
