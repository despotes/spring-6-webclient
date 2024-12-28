package guru.springframework.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class BeerClientImpl implements BeerClient {

    private final WebClient webclient;

    public BeerClientImpl(WebClient.Builder webClientBuilder) {
        this.webclient = webClientBuilder
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Override
    public Flux<String> listBeers() {
        return webclient.get().uri("/api/v3/beer").retrieve().bodyToFlux(String.class);
    }
}
