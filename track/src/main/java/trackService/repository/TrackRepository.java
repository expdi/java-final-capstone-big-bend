package trackService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trackService.model.artist.Artist;
import trackService.model.track.Track;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {

    Track getTrackById(int Id);
    List<Track> getTracksByTrackMediaType(Track.TrackMediaType mediaType);
    List<Track> getTracksByIssueDateGreaterThan(LocalDate date);
    @Modifying
    @Query("UPDATE Track track set track.title=?1, track.issueDate=?2, track.durationInSeconds=?3,track.language=?4 where track.id =?5")
    Artist updateTrack(String title, LocalDate issueDate,int durationInSeconds,String language, int id);
    @Query("SELECT t.title,t.album,t.issueDate,t.trackMediaType FROM Track t WHERE t.album = :album")
    List<Object> getTracksByAlbum(String album);
}
