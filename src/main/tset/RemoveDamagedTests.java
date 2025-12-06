package main.tset;

import main.java.ProductStock;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Remove Damaged Stock Test Suite")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RemoveDamagedTests {

private ProductStock stock;

@BeforeEach
void setup() {
    stock = new ProductStock("P100", "WH-1-A3", 10, 5, 50);
}

@AfterEach
void cleanup() {
    System.out.println("Finished test: state = " + stock);
}
@Test
@DisplayName("Remove damaged normally")
@Timeout(5) 
void testRemoveDamaged() {
    stock.removeDamaged(5);
    assertEquals(5, stock.getOnHand(), "OnHand should decrease correctly after removing damaged units");
}


@Test
@DisplayName("Removing more than on-hand ")
void testRemoveDamagedTooMuch() {
    assertThrows(IllegalStateException.class, () -> stock.removeDamaged(100));
}

}