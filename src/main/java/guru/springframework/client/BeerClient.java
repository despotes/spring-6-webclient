package guru.springframework.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.concurrent.Flow;

public interface BeerClient {
    Flux<String> listBeers();
    Flux<Map> listBeersMap();
    Flux<JsonNode> listBeersJsonNode();
}
