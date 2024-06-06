package trackService.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import trackService.model.album.Album;
import trackService.repository.AlbumRepository;

import java.util.List;

@Repository
public class AlbumJpaDAO implements BaseDAO<Album>{

  @Autowired
  private AlbumRepository repository;
  @Override
  public Album create(Album album) {
    return repository.save(album);
  }

  @Override
  public void update(Album album) {
    repository.save(album);
  }

  @Override
  public void delete(int id) {
    repository.deleteById(id);
  }

  @Override
  public Album get(int id) {
    return repository.getAlbumById(id);
  }

  @Override
  public List<Album> getAll() {
    return repository.findAll();
  }
}
