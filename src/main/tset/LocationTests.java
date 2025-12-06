package main.tset;



import main.java.ProductStock;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Location Test ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LocationTests {


private ProductStock stock;

@BeforeEach
void setup() {
    stock = new ProductStock("P100", "WH-1-A3", 10, 5, 50);
}

@AfterEach
void cleanup() {
    System.out.println("Finished LocationTests: state = " + stock);
}

@Nested
@DisplayName("Change Location Scenarios")
class ChangeLocationScenarios {

    @Test
    @DisplayName("Change location successfully")
    @Tag("sanity")
    @Timeout(1)
    void testChangeLocation() {
        stock.changeLocation("NEW-LOC");
        assertEquals("NEW-LOC", stock.getLocation());
    }

    @Test
    @DisplayName("Invalid location")
    @Tag("regression")
    @Timeout(1)
    void testChangeLocationInvalid() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> stock.changeLocation("")),
            () -> assertThrows(IllegalArgumentException.class, () -> stock.changeLocation(null))
        );
    }
}


}
