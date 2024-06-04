package trackService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trackService.model.artist.Artist;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Integer> {
    Artist getArtistById(int idArtist);

    @Query("SELECT a.name,t.title FROM Artist a INNER JOIN Track t ON a.id = t.artists.id WHERE a.id = :id")
    List<Object> getSongsAndArtistNameByArtistId(int id);

}
