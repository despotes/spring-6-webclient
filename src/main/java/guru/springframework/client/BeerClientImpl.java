package guru.springframework.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class BeerClientImpl implements BeerClient {

    public static final String BEER_PATH = "/api/v3/beer";
    private final WebClient webclient;

    public BeerClientImpl(WebClient.Builder webClientBuilder) {
        this.webclient = webClientBuilder
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Override
    public Flux<Map> listBeersMap() {
        return webclient.get().uri(BEER_PATH).retrieve().bodyToFlux(Map.class);
    }

    @Override
    public Flux<String> listBeers() {
        return webclient.get().uri(BEER_PATH).retrieve().bodyToFlux(String.class);
    }
}
