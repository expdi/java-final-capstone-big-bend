package trackService.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import trackService.model.artist.Artist;
import trackService.repository.ArtistRepository;

import java.util.List;

@Repository
public class ArtistJpaDAO implements BaseDAO<Artist> {

  @Autowired
  private ArtistRepository repository;

  @Override
  public Artist create(Artist artist) {
    return repository.save(artist);
  }

  @Override
  public void update(Artist artist) {
    repository.save(artist);
  }

  @Override
  public void delete(int id) {
    repository.deleteById(id);
  }

  @Override
  public Artist get(int id) {
    return repository.getArtistById(id);
  }

  @Override
  public List<Artist> getAll() {
    return repository.findAll();
  }
}
