package trackService.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trackService.model.album.Album;
import trackService.service.AlbumService;

@RestController
@RequestMapping("/albums")
public class AlbumController {

  private AlbumService albumService;
  private UriCreator uriCreator;

  @Autowired
  public AlbumController(AlbumService albumService, UriCreator uriCreator) {
    this.albumService = albumService;
    this.uriCreator = uriCreator;
  }

  @GetMapping
  public ResponseEntity findAll() {
    List<Album> albums = albumService.getAll();

    return ResponseEntity.ok().body(albums);
  }

  @PostMapping
  public ResponseEntity create(@RequestBody Album album) {
    Album createdAlbum = albumService.create(album);
    URI newResource = uriCreator.getURI(createdAlbum.getId());
    return ResponseEntity.created(newResource).body(createdAlbum);
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable int id, @RequestBody Album album) {
    boolean updated = albumService.update(album);
    if (!updated) {
      return ResponseEntity.unprocessableEntity().build();
    }
    URI newResource = uriCreator.getURI(id);
    return ResponseEntity.accepted().body(album);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable int id) {
    try {
      albumService.delete(id);
      return ResponseEntity.accepted().build();
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
