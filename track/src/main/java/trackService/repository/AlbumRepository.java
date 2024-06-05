package trackService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trackService.model.album.Album;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album,Integer> {

     Album getAlbumById(int id);
    @Query("SELECT album.id,album.title,album.issueDate FROM Album album WHERE album.title = :album")
    List<Object> getAlbumInfo(String album);
}
