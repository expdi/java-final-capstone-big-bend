package trackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import trackService.model.track.Track;
import trackService.model.track.TrackBuilder;
import trackService.service.TrackService;

@SpringBootApplication
public class CapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneApplication.class, args);
	}


}

@Component
class TrackDataInit implements CommandLineRunner {

	@Autowired
	private TrackService service;
	public void run(String... args) throws Exception {
		System.out.println("Expeditors Big Bend - Java Capstone Application Started");
	}
}