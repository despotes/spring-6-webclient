package guru.springframework.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import guru.springframework.model.BeerDTO;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.Flow;

public interface BeerClient {
    Flux<String> listBeers();
    Flux<Map> listBeersMap();
    Flux<JsonNode> listBeersJsonNode();
    Flux<BeerDTO> listBeersDto();

    Mono<BeerDTO> getBeerById(String id);
}
