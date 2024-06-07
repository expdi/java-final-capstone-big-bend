package trackService.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import trackService.model.track.Track;
import trackService.repository.TrackRepository;

import java.util.List;

@Repository
public class TrackJpaDAO implements BaseDAO<Track>{

  @Autowired
  private TrackRepository repository;

  @Override
  public Track create(Track track) {
    return repository.save(track);
  }

  @Override
  public void update(Track track) {
    repository.save(track);
  }

  @Override
  public void delete(int id) {
    repository.deleteById(id);
  }

  @Override
  public Track get(int id) {
    return repository.getTrackById(id);
  }

  @Override
  public List<Track> getAll() {
    return repository.findAll();
  }
}
