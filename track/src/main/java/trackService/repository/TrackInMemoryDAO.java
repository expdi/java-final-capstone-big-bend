package trackService.repository;

import org.springframework.stereotype.Repository;
import trackService.model.track.Track;
import java.util.HashMap;
import java.util.List;

@Repository
public class TrackInMemoryDAO implements BaseDAO<Track>{

    private static int nextId;
    private HashMap<Integer, Track> tracks = new HashMap<>();

    @Override
    public Track create(Track track) {
        track.setId(nextId++);
        this.tracks.put(track.getId(), track);
        return track;
    }

    @Override
    public void update(Track track) {this.tracks.put(track.getId(), track);}

    @Override
    public void delete(int id) {this.tracks.remove(id);}

    @Override
    public Track get(int id) {return this.tracks.get(id);}

    @Override
    public List<Track> getAll() {
        return this.tracks.values().stream().toList();}

    @Override
    public void clearDatabase() {
        this.tracks = null;
    }

    @Override
    public void initDatabase() {
        nextId = 0;
        this.tracks = new HashMap<Integer, Track>();

    }

}
