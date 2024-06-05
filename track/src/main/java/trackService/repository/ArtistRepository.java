package trackService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trackService.model.artist.Artist;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Integer> {


    Artist getArtistById(int idArtist);
    List<Artist> getArtistsByName(String name);
    int deleteArtistById(int id);
    @Modifying
    @Query("UPDATE Artist artist set artist.name=?1, artist.musicGenre =?2, artist.nationality =?3 where artist.id =?4")
    Artist updateArtist(String name, String musicGenre, String nationality, int id);
    @Query("SELECT a.name,t.title FROM Artist a INNER JOIN Track t ON a.id = t.artist.id WHERE a.id = :id")
    List<Object> getSongsAndArtistNameByArtistId(int id);

}
