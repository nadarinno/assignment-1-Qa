package main.tset;



import main.java.ProductStock;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReorderAndCapacityTests {


private ProductStock stock;

@BeforeEach
void setup() {
    stock = new ProductStock("P100", "WH-1-A3", 10, 5, 50);
}

@Test
@DisplayName("Reorder needed when available < threshold")
void testReorderNeeded() {
    stock.reserve(7);
    assertTrue(stock.isReorderNeeded());
}

@Test
@DisplayName("Reorder not needed when available >= threshold")
void testReorderNotNeeded() {
    assertFalse(stock.isReorderNeeded());
}

@Test
@DisplayName("Update reorder threshold successfully")
void testUpdateReorderThreshold() {
    stock.updateReorderThreshold(8);
    assertEquals(8, stock.getReorderThreshold());
}

@Test
@DisplayName("Update reorder threshold invalid ")
void testUpdateReorderThresholdInvalid() {
    assertThrows(IllegalArgumentException.class, () -> stock.updateReorderThreshold(-1));
}

@Test
@DisplayName("Update max capacity successfully")
void testUpdateMaxCapacity() {
    stock.updateMaxCapacity(100);
    assertEquals(100, stock.getMaxCapacity());
}

@Test
@DisplayName("Update max capacity invalid ")
void testUpdateMaxCapacityInvalid() {
    assertThrows(IllegalArgumentException.class, () -> stock.updateMaxCapacity(0));
}


}
