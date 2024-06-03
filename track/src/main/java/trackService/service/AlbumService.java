package trackService.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trackService.model.album.Album;
import trackService.repository.BaseDAO;

@Service
public class AlbumService {

  @Autowired
  private BaseDAO<Album> albumDAO;

  public Album create(Album album) {
    Album savedAlbum = albumDAO.create(album);
    return savedAlbum;
  }

  public boolean update(Album album) {
    Album albumResult = albumDAO.get(album.getId());

    if (albumResult == null) { return false; }

    albumDAO.update(album);
    return true;
  }

  public void deleteAlbum(Integer albumId) {
    Album album = albumDAO.get(albumId);
    if (album == null) { return; }
    albumDAO.delete(albumId);
  }

  public Album getAlbumById(Integer albumId) {
    return albumDAO.get(albumId);
  }

  public List<Album> getAllAlbums() {
    return albumDAO.getAll();
  }

  public void clearDatabase() {
    albumDAO.clearDatabase();
  }

  public void initDatabase() {
    albumDAO.initDatabase();
  }
//
//  public List<Album> getAlbumByTitle(String title) {
//    return this.albumDAO.getByTitle(title);
//  }

}
