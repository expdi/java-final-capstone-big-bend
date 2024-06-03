package trackService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trackService.model.artist.Artist;
import trackService.model.artist.ArtistBuilder;
import trackService.model.track.Track;
import trackService.repository.BaseDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private BaseDAO<Artist> artistDAO;

// create update delete

    public Artist create(String name, String musicGender) {
        ArtistBuilder artistBuilder = new ArtistBuilder();
        Artist artist = artistBuilder.startArtistBuilder(name).addMusicGender(musicGender).build();
        artistDAO.create(artist);
        return artist;
    }

    public boolean updateArtist(Artist artist){
        Artist artistResult = this.artistDAO.get(artist.getId());

        if(artistResult != null){
            artistDAO.update(artist);
            return true;
        }
        return false;
    }

    public void deleteArtist(int artistId) {
        Artist artist = this.artistDAO.get(artistId);
        if (artist != null)
            artistDAO.delete(artistId);
    }

    //2.Get an Artist by id

    public Artist getArtistById(int id) {
        return this.artistDAO.get(id);
    }

    //5.Get all Artists
    public List<Artist> getAllArtists() {

        return this.artistDAO.getAll();
    }

    //3.Get Artists by name

    public List<Artist> getArtistsByName(String name) {

        List<Artist> artistsList = artistDAO.getAll();
        ArrayList<Artist> resultList = new ArrayList<>();

        for (Artist artist : artistsList) {
            if (artist.getName().equals(name)) {
                resultList.add(artist);
            }
        }

        return resultList;
    }

    public Artist getValidArtist(Artist artist) {
        List<Artist> artistsByName = this.getArtistsByName(artist.getName());

        if (artistsByName.size() > 0) {
            return artistsByName.get(0);
        }
        return this.create(artist.getName(), artist.getMusicGenre());
    }

    //4.Get Artists for a particular track

    public List<Artist> getArtistsByTrack (String trackName) {
        // 1 - buscar artistas, criar lista vazia
        List<Artist> artistsList = artistDAO.getAll();

        // 2 - correr lista de artistas
        ArrayList<Artist> resultList = new ArrayList<>();
        for (Artist artist : artistsList) {

            // 3 - correr a lista de tracks do artista
            for (Track track: artist.getListOfTracks()) {

                // 4 - se track.title = track, add artista nova lista
                if (track.getTitle().equals(trackName)) {
                    resultList.add(artist);
                }
            }
        }
        // 5 - retornar nova lista
        return resultList;
   }

    public void clearDatabase() {this.artistDAO.clearDatabase();}

    public void initDatabase(){
        this.artistDAO.initDatabase();
    }

}





//
//
//1.Basic Create, Update, Delete functionality
//    public Artist create(String artistName, String musicGenre) {
//        Artist artist = new Artist(artistName);
//        artist.setMusicGenre(musicGenre);
//        artist = artistDAO.create(artist);
//        return artist;
//    }
//
//
//
//
//    public void updateArtist(Artist artist) {
//
//        this.artistDAO.update(artist);
//    }
//
//
//    public void deleteArtist(int artistId) {
//        Artist artist = artistDAO.get(artistId);
//        if (artist != null)
//            artistDAO.delete(artistId);
//    }
//
//     buscar pra ver se existe
//     se  existir, Adicionar a lista
//     se não existir, adicionar ao banco
//
