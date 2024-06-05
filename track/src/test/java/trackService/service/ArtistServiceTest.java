package trackService.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import trackService.model.artist.Artist;
import trackService.model.artist.ArtistBuilder;

import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ArtistServiceTest {


    @Autowired
    ArtistService artistService;

    @BeforeEach
    public void setup() {
        this.artistService.clearDatabase();
        this.artistService.initDatabase();
    }

@Test
    public void testCreateArtist(){
    ArtistBuilder artistBuilder = new ArtistBuilder();
    artistBuilder.startArtistBuilder("Michael Jackson").addMusicGender("POP");
    Artist artist = artistBuilder.build();
    this.artistService.create(artist);

    Assertions.assertEquals(0, artist.getId());
    Assertions.assertEquals("Michael Jackson", artist.getName());
    Assertions.assertEquals("POP", artist.getMusicGenre());
}

    @Test
    public void testUpdateArtist(){
        ArtistBuilder artistBuilder = new ArtistBuilder();
        artistBuilder.startArtistBuilder("Michael Jackson").addMusicGender("POP");
        Artist artist = artistBuilder.build();
        this.artistService.create(artist);

        Artist newArtist = this.artistService.create(artist);

        String artistNewName = "Beyonce";
        Artist getArtist = this.artistService.getArtistById(newArtist.getId());

        getArtist.setName(artistNewName);
        this.artistService.updateArtist(getArtist);
        Artist updatedArtist = this.artistService.getArtistById(newArtist.getId());

        Assertions.assertEquals("Beyonce", updatedArtist.getName());
        System.out.println("name: "+ artist.getName());

    }

    @Test
    public void testDeleteArtist(){
        ArtistBuilder artistBuilder = new ArtistBuilder();
        artistBuilder.startArtistBuilder("Rihanna").addMusicGender("POP");
        Artist artist = artistBuilder.build();
        this.artistService.create(artist);
        this.artistService.deleteArtist(artist.getId());
        Artist deletedArtist = this.artistService.getArtistById(artist.getId());
        Assertions.assertNull(deletedArtist);
    }

    @Test
    public void testGetAllArtists(){
        ArtistBuilder artistBuilder = new ArtistBuilder();
        artistBuilder.startArtistBuilder("Rihanna").addMusicGender("POP");
        Artist artist = artistBuilder.build();
        this.artistService.create(artist);

        artistBuilder.startArtistBuilder("Michael Jackson").addMusicGender("POP");
        Artist artist2 = artistBuilder.build();
        this.artistService.create(artist2);

        List<Artist> result = artistService.getAllArtists();
        Assertions.assertEquals(2, result.size());
    }


    @Test
    public void testGetArtistById(){
        ArtistBuilder artistBuilder = new ArtistBuilder();
        artistBuilder.startArtistBuilder("Rihanna").addMusicGender("POP");
        Artist artist = artistBuilder.build();
        this.artistService.create(artist);
        Artist result = artistService.getArtistById(artist.getId());
        Assertions.assertEquals("Rihanna", result.getName());

    }

    @Test
    public void testGetArtistByName(){
        ArtistBuilder artistBuilder = new ArtistBuilder();
        artistBuilder.startArtistBuilder("Luan Santana").addMusicGender("POP");
        Artist artist = artistBuilder.build();
        this.artistService.create(artist);

        artistBuilder.startArtistBuilder("Luan Santana").addMusicGender("Sertanejo");
        Artist artist2 = artistBuilder.build();
        this.artistService.create(artist2);

        List<Artist> resultNames = artistService.getArtistsByName("Luan Santana");
        Assertions.assertEquals(2, resultNames.size());

    }


}