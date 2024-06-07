package trackService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import trackService.DAO.*;
import trackService.model.album.Album;
import trackService.model.artist.Artist;
import trackService.model.track.Track;

@Configuration
public class DataSourceConfig {
  @Bean
  @Profile("test")
  public BaseDAO<Artist> artistDAO() { return new ArtistInMemoryDAO(); }

  @Bean(name="artistDAO")
  @Profile("! test")
  public BaseDAO<Artist> artistJpaDAO() { return new ArtistJpaDAO(); }


  @Bean
  @Profile("test")
  public BaseDAO<Track> trackDAO() { return new TrackInMemoryDAO(); }

  @Bean(name="trackDAO")
  @Profile("! test")
  public BaseDAO<Track> trackJpaDAO() { return new TrackJpaDAO(); }

  @Bean
  @Profile("test")
  public BaseDAO<Album> albumDAO() { return new AlbumInMemoryDAO(); }

  @Bean(name="albumDAO")
  @Profile("! test")
  public BaseDAO<Album> albumJpaDAO() { return new AlbumJpaDAO(); }
}

