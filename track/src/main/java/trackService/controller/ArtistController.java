package trackService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trackService.model.artist.Artist;
import trackService.service.ArtistService;


import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private UriCreator uriCreator;

    @GetMapping
    public List<Artist> getAllArtist() {
        List<Artist> artists = artistService.getAllArtists();
        return artists;
    }

    @PostMapping
    public ResponseEntity<?> createArtist(@RequestBody Artist artist) {
        Artist resultArtist = this.artistService.create(artist.getName(),artist.getMusicGenre());
        URI newResource = uriCreator.getURI(resultArtist.getId());
        return ResponseEntity.created(newResource).body(resultArtist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArtist(@PathVariable("id") int id,@RequestBody Artist artist) {
        boolean updated = this.artistService.updateArtist(artist);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        URI newResource = uriCreator.getURI(artist.getId());
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable("id") int id) {
        try {
            this.artistService.deleteArtist(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

}



//public class ArtistController {
//
//    @Autowired
//    private ArtistService artistService;
//
//    @Autowired
//    private UriCreator uriCreator;
//
//    @GetMapping
//    public List<Artist> getAllArtists() {
//        List<Artist> allArtists = artistService.getAllArtists();
//        return allArtists;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> addArtist(@RequestBody String artist, String musicGenre) {
//        Artist newArtist = artistService.create(artist, musicGenre);
//
//        URI newResource = uriCreator.getURI(newArtist.getId());
//        return ResponseEntity.ok(artist);
//    }
//}

//@DeleteMapping
//    public ResponseEntity<?> deleteArtist(@PathVariable("id") int id){
//        void result = artistService.deleteArtist(id);
//        if(!result){
//            return ResponseEntity.status.I_AM_A_TEAPOT);body("No artist with id: " + id);
//        }
//        return ResponseEntity.noContent().build();
//        }
//}
//

