package trackService.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PricingClient {

    @Value("${pricing.api.server}")
    private String server;

    @Value("${pricing.api.url}")
    private String url;
    public PricingClient(){

    }

    public double getTrackPrice(long id){
        RestClient restClient = RestClient.builder().baseUrl(this.server).build();
        String body = restClient.get().uri(this.url).retrieve().body(String.class);
        Double price = Double.parseDouble(body);
        return price;
    }
}
