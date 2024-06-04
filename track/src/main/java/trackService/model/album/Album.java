package trackService.model.album;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import trackService.model.track.Track;

public class Album {
  private Integer id;
  private String title;
  Set<Track> tracks;
  LocalDate issueDate;

  public Album(Integer id, String title, Set<Track> tracks, LocalDate issueDate) {
    this.id = id;
    this.title = title;
    this.tracks = tracks;
    this.issueDate = issueDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Track> getTracks() {
    return Optional.ofNullable(tracks).orElse(Collections.emptySet()).stream().toList();
  }

  public void addTrack(Track track) {
    if (tracks == null) {
      tracks = Set.of(track);
    } else {
      tracks.add(track);
    }

  }

  public void setTracks(Set<Track> tracks) {
    this.tracks = tracks;
  }

  public LocalDate getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(LocalDate issueDate) {
    this.issueDate = issueDate;
  }
}
