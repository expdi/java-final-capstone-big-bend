package trackService.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import trackService.model.track.Track;
import trackService.model.track.TrackBuilder;
import trackService.service.TrackService;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class TrackControllerTest {

    @Mock
    private TrackService trackService;

    @Mock
    private UriCreator uriCreator;

    @InjectMocks
    private TrackController controller;

    @Test
    public void testGetOk(){
        Track sadSong = new TrackBuilder().startBuilder("Sad Song").addDurationInSeconds(108).addIssueDate(LocalDate.now()).build();

        Mockito.when(trackService.getTrackById(0)).thenReturn(sadSong);

        ResponseEntity<Track> track = controller.getTrack(0);

        Assertions.assertEquals(HttpStatus.OK, track.getStatusCode());

        Mockito.verify(trackService).getTrackById(0);

    }

    @Test
    public void testGetNotFound(){

        Mockito.when(trackService.getTrackById(99)).thenReturn(null);

        ResponseEntity<Track> track = controller.getTrack(99);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, track.getStatusCode());

        Mockito.verify(trackService).getTrackById(99);

    }

    @Test
    public void testPost() throws URISyntaxException {
        Track sadSong = new TrackBuilder().startBuilder("Sad Song").addDurationInSeconds(108).addIssueDate(LocalDate.now()).build();
        sadSong.setId(0);

        String uri = "http://localhost:8080/track/0";

        URI uri1 =  new URI(uri);

        Mockito.when(trackService.create(sadSong)).thenReturn(sadSong);

        Mockito.when(uriCreator.getURI(0)).thenReturn(uri1);

        ResponseEntity<Track> track = controller.createTrack(sadSong);

        Assertions.assertEquals(HttpStatus.CREATED, track.getStatusCode());

        String locHdr = track.getHeaders().get("Location").get(0);

        Assertions.assertEquals(uri, locHdr);

        Mockito.verify(trackService).create(sadSong);

    }


    @Test
    public void deleteControllerOk(){

        Mockito.when(trackService.deleteTrack(0)).thenReturn(true);

        ResponseEntity<?> track = controller.deleteTrack(0);

        Assertions.assertEquals(HttpStatus.OK, track.getStatusCode());

        Mockito.verify(trackService).deleteTrack(0);

    }

    @Test
    public void deleteControllerNotFound(){

        Mockito.when(trackService.deleteTrack(99)).thenReturn(false);

        ResponseEntity<?> track = controller.deleteTrack(99);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, track.getStatusCode());

        Mockito.verify(trackService).deleteTrack(99);

    }

    @Test
    public void updateTrackNotFound(){

        Track sadSong = new TrackBuilder().startBuilder("Sad Song").addDurationInSeconds(108).addIssueDate(LocalDate.now()).build();
        sadSong.setId(0);

        Mockito.when(trackService.updateTrack(sadSong)).thenReturn(false);

        ResponseEntity<?> track = controller.updateTrack(sadSong);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, track.getStatusCode());

        Mockito.verify(trackService).updateTrack(sadSong);

    }

    @Test
    public void updateTrackOK() {

        Track sadSong = new TrackBuilder().startBuilder("Sad Song").addDurationInSeconds(108).addIssueDate(LocalDate.now()).build();
        sadSong.setId(0);

        Mockito.when(trackService.updateTrack(sadSong)).thenReturn(true);

        ResponseEntity<?> track = controller.updateTrack(sadSong);

        Assertions.assertEquals(HttpStatus.OK, track.getStatusCode());

        Mockito.verify(trackService).updateTrack(sadSong);

    }

}
