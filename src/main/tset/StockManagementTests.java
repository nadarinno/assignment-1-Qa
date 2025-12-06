package main.tset;



import main.java.ProductStock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Stock Management Test ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StockManagementTests {

private ProductStock stock;

@BeforeEach
void setup() {
    stock = new ProductStock("P100", "WH-1-A3", 10, 5, 50);
}

@AfterEach
void cleanup() {
    System.out.println("Finished StockManagementTests: state = " + stock);
}

@Nested
@DisplayName("Add Stock Scenarios")
class AddStockScenarios {

    @Test
    @DisplayName("Normal add stock")
    @Tag("sanity")
    void testAddStockNormal() {
        stock.addStock(5);
        assertEquals(15, stock.getOnHand());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    @DisplayName("Add multiple valid amounts")
    @Tag("sanity")
    void testAddStockVarious(int amount) {
        stock.addStock(amount);
        assertEquals(10 + amount, stock.getOnHand());
    }

    @Test
    @DisplayName("Add invalid amount (<=0)")
    @Tag("regression")
    void testAddStockInvalid() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> stock.addStock(0)),
            () -> assertThrows(IllegalArgumentException.class, () -> stock.addStock(-3))
        );
    }

    @Test
    @DisplayName("Add beyond capacity ")
    @Tag("regression")
    void testAddStockBeyondCapacity() {
        assertThrows(IllegalStateException.class, () -> stock.addStock(100));
    }
}


}
