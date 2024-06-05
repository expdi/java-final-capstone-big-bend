package trackService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trackService.model.artist.Artist;
import trackService.service.ArtistService;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private UriCreator uriCreator;

    @GetMapping
    public ResponseEntity<?> getAllArtist() {
        List<Artist> artists = artistService.getAllArtists();
        return ResponseEntity.ok().body(artists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable int id) {
        Artist artistResult = artistService.getArtistById(id);
        if (artistResult == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(artistResult);
    }

    @PostMapping
    public ResponseEntity<?> createArtist(@RequestBody Artist artist) {
        Artist resultArtist = this.artistService.create(artist);
        URI newResource = uriCreator.getURI(resultArtist.getId());
        return ResponseEntity.created(newResource).body(resultArtist);
    }

    @PutMapping
    public ResponseEntity<?> updateArtist(@RequestBody Artist artist) {
        boolean updated = this.artistService.updateArtist(artist);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        URI newResource = uriCreator.getURI(artist.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable("id") int id) {
        boolean updated = this.artistService.deleteArtist(id);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        URI newResource = uriCreator.getURI(id);
        return ResponseEntity.ok(newResource);
    }
}



