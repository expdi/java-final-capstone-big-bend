package trackService.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import trackService.mockData.MockAlbumFactory;
import trackService.model.album.Album;
import trackService.service.AlbumService;

@WebMvcTest(controllers = AlbumController.class)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class AlbumControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  UriCreator uriCreator;

  @MockBean
  private AlbumService albumService;

  @Test
  @DisplayName("AlbumController#GET - /albums - Finds All Albums")
  void AlbumController_GetAllAlbums_ReturnsAllAlbums() throws Exception {
    List<Album> mockAlbums = MockAlbumFactory.defaultAlbums();
    doReturn(mockAlbums).when(albumService).getAll();

    ResultActions res = mockMvc.perform(get("/albums"));
    res.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", isA(ArrayList.class)))
        .andExpect(jsonPath("$", hasSize(4)));
  }

  @Test
  @DisplayName("AlbumController#GET - /albums/{id} - Finds By Id")
  void AlbumController_GetAlbumById_ReturnsAlbumById() throws Exception {
    Album mockAlbum = MockAlbumFactory.defaultAlbum();
    doReturn(mockAlbum).when(albumService).get(7);

    ResultActions res = mockMvc.perform(get("/albums/7"));
    res.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title", is(mockAlbum.getTitle())));
  }

  @Test
  @DisplayName("AlbumController#GET - /albums/{id} - Returns 404 When Not Found")
  void AlbumController_GetAlbumById_Returns404WhenNotFound() throws Exception {
    doReturn(null).when(albumService).get(7);

    ResultActions res = mockMvc.perform(get("/albums/7"));
    res.andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("AlbumController#POST - /albums - Returns Created Album")
  void AlbumController_PostAlbum_ReturnsCreatedAlbum() throws Exception {
    Album mockAlbum = MockAlbumFactory.defaultAlbum();
    String content = objectMapper.writeValueAsString(mockAlbum); // create incoming payload before setting id
    mockAlbum.setId(1);
    doReturn(mockAlbum).when(albumService).create(Mockito.any(Album.class));
    ResultActions res = mockMvc.perform(
        post("/albums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content)
    );

    res.andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(mockAlbum.getId())))
        .andExpect(jsonPath("$.title", is(mockAlbum.getTitle())));
  }

  @Test
  @DisplayName("AlbumController#PUT - /albums/{id} - Returns Updated Album")
  void AlbumController_PutAlbum_ReturnsUpdatedAlbum() throws Exception {
    Album mockAlbum = MockAlbumFactory.defaultAlbum();
    mockAlbum.setId(1);
    doReturn(true).when(albumService).update(Mockito.any(Album.class));


    String content = objectMapper.writeValueAsString(mockAlbum);

    ResultActions res = mockMvc.perform(
        put("/albums/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content)
    );

    res.andExpect(status().isAccepted())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(mockAlbum.getId())))
        .andExpect(jsonPath("$.title", is(mockAlbum.getTitle())));
  }

  @Test
  @DisplayName("AlbumController#PUT - /albums/{id} - Returns 404 When Id Not Found")
  void AlbumController_PutAlbum_Returns404WhenIdNotFound() throws Exception {
    // TODO
  }
}
