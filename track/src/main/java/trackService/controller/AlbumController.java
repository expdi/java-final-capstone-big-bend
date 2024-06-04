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

  @GetMapping(value="/{id}")
  public ResponseEntity findById(@PathVariable int id) {
    Album album = albumService.get(id);

    if (album == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(album);
    }
  }

  @PostMapping
  public ResponseEntity create(@RequestBody Album album) {
    Album createdAlbum = albumService.create(album);
    URI newResource = uriCreator.getURI(createdAlbum.getId());
    return ResponseEntity.created(newResource).body(createdAlbum);
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable int id, @RequestBody Album album) {
    Album loadedAlbum = albumService.get(id);
    if (loadedAlbum == null) {
      return ResponseEntity.notFound().build();
    }

    if (!albumService.update(album)) {
      return ResponseEntity.unprocessableEntity().build();
    }
    Album reloadedAlbum = albumService.get(id);
    return ResponseEntity.accepted().body(reloadedAlbum);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable int id) {
    Album loadedAlbum = albumService.get(id);
    if (loadedAlbum == null) {
      return ResponseEntity.notFound().build();
    }

    albumService.delete(id);
    Album reloadedAlbum = albumService.get(id);

    if (reloadedAlbum != null) {
      return ResponseEntity.unprocessableEntity().build();
    }

    return ResponseEntity.ok().build();
  }
}
