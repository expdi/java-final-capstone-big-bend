package trackService.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class PricingClient {

    @Value("${pricing.api.server}")
    private String server;

    @Value("${pricing.api.url.evaluation}")
    private String trackEvaluationUrl;

    @Value("${pricing.api.user}")
    private String user;

    @Value("${pricing.api.pswd}")
    private String password;
    public PricingClient(){

    }

    public double getTrackPrice(UUID uuid){
        String plainCreds = this.user+":"+this.password;
        byte[] base64CredsBytes = Base64.encodeBase64String(plainCreds.getBytes()).getBytes();
        String base64Creds = new String(base64CredsBytes);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);

        String urlRequest = this.server + this.trackEvaluationUrl + uuid.toString();
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<Double> response = restTemplate.exchange(urlRequest, HttpMethod.GET, request, Double.class);
        Double price = response.getBody();
        return price;
    }
}
