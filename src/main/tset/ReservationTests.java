package main.tset;


import main.java.ProductStock;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationTests {


private ProductStock stock;

@BeforeEach
void setup() {
    stock = new ProductStock("P100", "WH-1-A3", 10, 5, 50);
}

@Test
@DisplayName("Reserve stock successfully")
@Tag("sanity")
void testReserve() {
    stock.reserve(3);
    assertAll("Reserve assertions",
            () -> assertEquals(3, stock.getReserved()),
            () -> assertEquals(7, stock.getAvailable())
    );
}

@Test
@DisplayName("Reserve invalid amounts ")
@Tag("regression")
void testReserveInvalid() {
    assertAll("Invalid reserve",
            () -> assertThrows(IllegalArgumentException.class, () -> stock.reserve(0)),
            () -> assertThrows(IllegalArgumentException.class, () -> stock.reserve(-1))
    );
}

@Test
@DisplayName("Reserve more than available")
void testReserveTooMuch() {
    assertThrows(IllegalStateException.class, () -> stock.reserve(20));
}

@Test
@DisplayName("Release reserved stock normally")
void testReleaseReservation() {
    stock.reserve(4);
    stock.releaseReservation(2);
    assertAll("Release assertions",
            () -> assertEquals(2, stock.getReserved()),
            () -> assertEquals(8, stock.getAvailable())
    );
}

@Test
@DisplayName("Release more than reserved ")
void testReleaseTooMuch() {
    stock.reserve(3);
    assertThrows(IllegalStateException.class, () -> stock.releaseReservation(5));
}


}

