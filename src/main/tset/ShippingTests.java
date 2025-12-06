package main.tset;



import main.java.ProductStock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Shipping Test Suite")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShippingTests {
	

private ProductStock stock;

@BeforeAll
static void setupAll() {
    System.out.println(">>> Starting ShippingTests suite");
}

@BeforeEach
void setup() {
    stock = new ProductStock("P100", "WH-1-A3", 10, 5, 50);
}

@AfterEach
void cleanup() {
    System.out.println("Finished test: state = " + stock);
}

@AfterAll
static void tearDownAll() {
    System.out.println(">>> ShippingTests suite finished");
}

@Nested
@DisplayName("Ship Reserved Stock Scenarios")
class ShipReservedScenarios {

    @Test
    @DisplayName("Ship reserved stock successfully")
    @Tag("sanity")
    @Timeout(1)
    void testShipReserved() {
        stock.reserve(4);
        stock.shipReserved(4);
        assertAll("Ship reserved assertions",
                () -> assertEquals(6, stock.getOnHand(), "OnHand should decrease correctly"),
                () -> assertEquals(0, stock.getReserved(), "Reserved should be zero after shipping")
        );
    }

    @Test
    @DisplayName("Shipping more than reserved ")
    @Tag("regression")
    @Timeout(1)
    void testShipReservedTooMuch() {
        assertThrows(IllegalStateException.class, () -> stock.shipReserved(3));
    }
}

@Disabled("Future feature: multi-location shipping")
@Test
@DisplayName("Future shipping feature test")
void testFutureFeature() {}


}
