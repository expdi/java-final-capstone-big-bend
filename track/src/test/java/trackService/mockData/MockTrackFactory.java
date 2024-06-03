package trackService.mockData;

import java.time.LocalDate;
import java.util.List;
import trackService.model.track.Track;
import trackService.model.track.TrackBuilder;

public class MockTrackFactory {

  public static Track defaultTrack() {
    return MockTrackFactory.defaultTracks().get(0);
  }

  public static List<Track> defaultTracks() {
    return List.of(
      new TrackBuilder().startBuilder("Time").addDurationInSeconds(300).addIssueDate(LocalDate.now()).build(),
      new TrackBuilder().startBuilder("The Great Gig In The Sky").addDurationInSeconds(400).addIssueDate(LocalDate.now()).build(),
      new TrackBuilder().startBuilder("Us and Them").addDurationInSeconds(500).addIssueDate(LocalDate.now()).build()
    );
  }
}
