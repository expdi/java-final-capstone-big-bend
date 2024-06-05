package trackService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import trackService.DAO.ArtistInMemoryDAO;
import trackService.DAO.BaseDAO;
import trackService.model.artist.Artist;
import trackService.DAO.ArtistJpaDAO;

@Configuration
public class DataSourceConfig {
  @Bean
  @Profile("test")
  public BaseDAO<Artist> artistDAO() { return new ArtistInMemoryDAO(); }

  @Bean(name="artistDAO")
  @Profile("! test")
  public BaseDAO<Artist> artistJpaDAO() { return new ArtistJpaDAO(); }
}

