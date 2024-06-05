package trackService.DAO;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import trackService.model.album.Album;

@Repository
public class AlbumInMemoryDAO implements BaseDAO<Album> {
  private static int nextId;
  private ConcurrentHashMap<Integer, Album> albums = new ConcurrentHashMap<>();


  @Override
  public Album create(Album album) {
    album.setId(nextId++);
    this.albums.put(album.getId(), album);
    return album;
  }

  @Override
  public void update(Album album) {
    this.albums.put(album.getId(), album);
  }

  @Override
  public void delete(int id) {
    this.albums.remove(id);
  }

  @Override
  public Album get(int id) {
    return this.albums.get(id);
  }

  @Override
  public List<Album> getAll() {
    return this.albums.values().stream().toList();
  }

  @Override
  public void clearDatabase() {
    this.albums.clear();
  }

  @Override
  public void initDatabase() {
    nextId = 0;
    this.albums = new ConcurrentHashMap<>();
  }
}
