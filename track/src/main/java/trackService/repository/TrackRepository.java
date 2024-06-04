package trackService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trackService.model.track.Track;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {

    Track getTrackById(int Id);

    @Query("SELECT t.title,t.album,t.issueDate,t.trackMediaType FROM Track t WHERE t.album = :album")
    List<Object> getTracksByAlbum(String album);
}
