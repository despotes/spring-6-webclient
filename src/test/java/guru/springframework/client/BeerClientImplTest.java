package guru.springframework.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient client;

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