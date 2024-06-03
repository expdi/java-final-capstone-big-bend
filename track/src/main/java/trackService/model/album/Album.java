package trackService.model.album;

import java.time.LocalDate;
import java.util.Collection;
import trackService.model.track.Track;

public class Album {

  private Integer id;
  private String title;
  Collection<Track>  tracks;
  LocalDate issueDate;

  public Album(Integer id, String title, Collection<Track> tracks, LocalDate issueDate) {
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

  public Collection<Track> getTracks() {
    return tracks;
  }

  public void setTracks(Collection<Track> tracks) {
    this.tracks = tracks;
  }

  public LocalDate getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(LocalDate issueDate) {
    this.issueDate = issueDate;
  }
}
