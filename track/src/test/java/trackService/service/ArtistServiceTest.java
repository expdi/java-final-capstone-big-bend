package trackService.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import trackService.model.artist.Artist;

import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

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
        String artistName = "Rihanna";
        String musicGender = "Pop";
        Artist newArtist = this.artistService.create(artistName, musicGender);

        Assertions.assertEquals(0, newArtist.getId());
        Assertions.assertEquals(artistName, newArtist.getName());
        Assertions.assertEquals(musicGender, newArtist.getMusicGenre());
}

@Test
public void testUpdateArtist(){
    String artistName = "Rihanna";
    String musicGender = "Pop";
    Artist newArtist = this.artistService.create(artistName, musicGender);

    System.out.println(newArtist);

    String artistNewName = "Beyonce";
    Artist artist = this.artistService.getArtistById(newArtist.getId());

    System.out.println(artist);

    artist.setName(artistNewName);
    this.artistService.updateArtist(artist);
    Artist updatedArtist = this.artistService.getArtistById(newArtist.getId());

    Assertions.assertEquals("Beyonce", updatedArtist.getName());
    System.out.println("name: "+ artist.getName());

}//


@Test
    public void DeleteArtistTest(){
    String artistName = "Rihanna";
    String musicGender = "Pop";
    Artist newArtist = this.artistService.create(artistName, musicGender);
     this.artistService.deleteArtist(0);

     Artist artistRemoval = this.artistService.getArtistById(0);
     Assertions.assertNull(artistRemoval, "This Artist was removed");

}

@Test
    public void getArtistByIdTest(){
        String artistName = "Rihanna";
        String musicGender = "Pop";
        Artist newArtist = this.artistService.create(artistName, musicGender);

        this.artistService.getArtistById(0);
        Assertions.assertEquals("Rihanna", newArtist.getName());

}

    @Test
    public void getAllArtistTest(){
        String artistName = "Rihanna";
        String musicGender = "Pop";
        String artistName2 = "John";
        String musicGender2 = "Rock";
        Artist newArtist = this.artistService.create(artistName, musicGender);
        Artist newArtist2 = this.artistService.create(artistName2, musicGender2);

        List<Artist> result = artistService.getAllArtists();
        Assertions.assertEquals(2, result.size());
    }


    @Test
    public void getArtistByNameTest(){
        String artistName = "Mariana";
        String musicGender = "Pop";
        String artistName2 = "Mariana";
        String musicGender2 = "Rock";
        Artist newArtist = this.artistService.create(artistName, musicGender);
        Artist newArtist2 = this.artistService.create(artistName2, musicGender2);


        List<Artist> resultNames = artistService.getArtistsByName("Mariana");
        Assertions.assertEquals(2, resultNames.size());
    }

    //@Test
    //public void getArtistsbyTrack()


}
