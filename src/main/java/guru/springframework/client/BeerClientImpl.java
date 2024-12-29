package guru.springframework.client;

import com.fasterxml.jackson.databind.JsonNode;
import guru.springframework.model.BeerDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Map;

@Service
public class BeerClientImpl implements BeerClient {

    public static final String BEER_PATH = "/api/v3/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";
    private final WebClient webclient;

    public BeerClientImpl(WebClient.Builder webClientBuilder) {
        this.webclient = webClientBuilder.build();
    }

    @Override
    public Flux<Map> listBeersMap() {
        return webclient.get().uri(BEER_PATH).retrieve().bodyToFlux(Map.class);
    }

    @Override
    public Flux<JsonNode> listBeersJsonNode() {
        return webclient.get().uri(BEER_PATH).retrieve().bodyToFlux(JsonNode.class);
    }

    @Override
    public Flux<BeerDTO> listBeersDto() {
        return webclient.get().uri(BEER_PATH).retrieve().bodyToFlux(BeerDTO.class);
    }

    @Override
    public Mono<BeerDTO> getBeerById(String id) {
        return webclient.get().uri(uriBuilder -> uriBuilder.path(BEER_PATH_ID).build(id)).retrieve().bodyToMono(BeerDTO.class);
    }

    @Override
    public Flux<BeerDTO> listBeersByBeerStyle(String beerStyle) {
        return webclient.get().uri(uriBuilder -> uriBuilder
                .path(BEER_PATH)
                .queryParam("beerStyle", beerStyle)
                .build()).retrieve().bodyToFlux(BeerDTO.class);
    }

    @Override
    public Mono<BeerDTO> createBeer(BeerDTO beerDTO) {
        return webclient.post().uri(BEER_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(beerDTO), BeerDTO.class)
                .retrieve()
                .toBodilessEntity()
                .map(responseEntity -> {
                    String location = responseEntity.getHeaders().get("Location").get(0);
                    return location.split("/")[location.split("/").length - 1];
                })
                .flatMap(this::getBeerById);
    }

    @Override
    public Flux<String> listBeers() {
        return webclient.get().uri(BEER_PATH).retrieve().bodyToFlux(String.class);
    }
}
