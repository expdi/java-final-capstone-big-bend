package trackService.mockData;

import java.time.LocalDate;
import java.util.List;
import trackService.model.track.Track;
import trackService.model.track.TrackBuilder;

public class MockTrackFactory {

  public static Track defaultTrack() {
    return new TrackBuilder().startBuilder("The Wheels On The Bus").addDurationInSeconds(600).addIssueDate(LocalDate.now()).build();
  }

  public static List<Track> defaultTracks() {
    return List.of(
      "Dark Side Of The Moon", "Revolver", "Steal My Sunshine", "The Wheels On The Bus"
    ).stream().map(albumTitle -> MockTrackFactory.defaultTrackOf(albumTitle)).toList();
  }

  public static Track defaultTrackOf(String albumTitle) {
    if (albumTitle == "Dark Side Of The Moon") {
      return new TrackBuilder().startBuilder("Time").addDurationInSeconds(300).addIssueDate(LocalDate.of(1973, 3, 1)).build();
    } else if (albumTitle == "Revolver") {
      return new TrackBuilder().startBuilder("Taxman").addDurationInSeconds(400).addIssueDate(LocalDate.of(1966, 8, 5)).build();
    } else if (albumTitle == "Steal My Sunshine") {
      return new TrackBuilder().startBuilder("Steal My Sunshine").addDurationInSeconds(500).addIssueDate(LocalDate.of(1999, 6, 22)).build();
    } else {
      return MockTrackFactory.defaultTrack();
    }
  }
}
