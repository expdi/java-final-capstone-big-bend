package trackService.repository;

import org.springframework.stereotype.Repository;
import trackService.model.artist.Artist;

import java.util.HashMap;
import java.util.List;

@Repository
public class ArtistInMemoryDAO implements BaseDAO<Artist>{

    private static int nextId;
    private HashMap<Integer, Artist> artists = new HashMap<>();

    @Override
    public Artist create(Artist artist) {
        artist.setId(nextId++);
        this.artists.put(artist.getId(), artist);
        return artist;
    }

    @Override
    public void update(Artist artist) {this.artists.put(artist.getId(), artist);}

    @Override
    public void delete(int id) {this.artists.remove(id);}

    @Override
    public Artist get(int id) {
        return this.artists.get(id);}

    @Override
    public List<Artist> getAll() {
        return this.artists.values().stream().toList();
    }

    @Override
    public void clearDatabase() {
        this.artists = null;
    }

    @Override
    public void initDatabase() {
        nextId = 0;
        this.artists = new HashMap<Integer, Artist>();

    }


}
