package guru.springframework.client;

import guru.springframework.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient client;

    @Test
    void testCreateBeer() {
        AtomicBoolean called = new AtomicBoolean(false);

        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("Mango Bros")
                .beerStyle("IPA")
                .price(new BigDecimal("10.99"))
                .quantityOnHand(500)
                .upc("123456789")
                .build();

        client.createBeer(beerDTO)
                .subscribe(savedDto -> {
                    called.set(true);
                    System.out.println(savedDto.toString());
                });

        await().untilTrue(called);
    }

    @Test
    void testListBeersByBeerStyle() {
        AtomicBoolean called = new AtomicBoolean(false);
        client.listBeersByBeerStyle("Pale Ale")
                .subscribe(beerDTO -> {
                    called.set(true);
                    System.out.println(beerDTO.toString());
                });

        await().untilTrue(called);
    }

    @Test
    void testGetBeerById() {
        AtomicBoolean called = new AtomicBoolean(false);
        client.listBeersDto()
                .flatMap(dto -> client.getBeerById(dto.getId()))
                .subscribe(beerDTO -> {
                    called.set(true);
                    System.out.println(beerDTO.getBeerName());
                });

        await().untilTrue(called);
    }

    @Test
    void testListBeersDto() {
        AtomicBoolean called = new AtomicBoolean(false);
        client.listBeersDto()
                .subscribe(beerDTO -> {
                    called.set(true);
                    System.out.println(beerDTO.getBeerName());
                });

        await().untilTrue(called);
    }

    @Test
    void listBeersJson() {
        AtomicBoolean called = new AtomicBoolean(false);
        client.listBeersJsonNode()
                .subscribe(jsonNode -> {
                    called.set(true);
                    System.out.println(jsonNode.toPrettyString());
                });

        await().untilTrue(called);
    }

    @Test
    void listBeersMap() {
        AtomicBoolean called = new AtomicBoolean(false);
        client.listBeersMap()
                .subscribe(response -> {
                    called.set(true);
                    System.out.println(response);
                });

        await().untilTrue(called);
    }

    @Test
    void listBeers() {
        AtomicBoolean called = new AtomicBoolean(false);
        client.listBeers()
                .subscribe(response -> {
                    called.set(true);
                    System.out.println(response);
                });

        await().untilTrue(called);
    }
}