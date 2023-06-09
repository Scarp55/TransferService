package ru.pivovarov.transferservice;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import ru.pivovarov.transferservice.model.*;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContainerTest {

    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> prodApp = new GenericContainer<>("transfer:1.0").withExposedPorts(5500);

    @BeforeAll
    public static void setUp() {
        prodApp.start();
    }

    @AfterAll
    public static void setDown() {
        prodApp.stop();
    }

    @Test
    void appDoTransferTest() {
        TransferRq transfer = new TransferRq(
                new Amount("RUR", 500),
                "345",
                "0000111122223333",
                "12/24",
                "0000111122224444"
        );
        TransferRs response = restTemplate.postForObject("http://localhost:" + prodApp.getMappedPort(5500) + "/transfer", transfer, TransferRs.class);
        Assertions.assertEquals(response.operationId(), 1 + "");
    }

    @Test
    void appValidTransferTest() {
        TransferRq transfer = new TransferRq(
                new Amount("RUR", 500),
                null,
                "",
                "",
                ""
        );
        ResponseEntity<ErrorResponse> response = restTemplate.postForEntity("http://localhost:" + prodApp.getMappedPort(5500) + "/transfer", transfer, ErrorResponse.class);
        Assertions.assertEquals(Objects.requireNonNull(response.getBody()).message(), "");
    }

    @Test
    void appConfirmOperationTest() {
        ConfirmRq confirmRq = new ConfirmRq("0000", "1");

        ConfirmRs response = restTemplate.postForObject("http://localhost:" + prodApp.getMappedPort(5500) + "/confirmOperation", confirmRq, ConfirmRs.class);
        Assertions.assertEquals(response.operationId(), 1 + "");
    }
}
