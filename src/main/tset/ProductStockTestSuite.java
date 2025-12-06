package main.tset;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
ConstructorTests.class,
LocationTests.class,
StockManagementTests.class,
ReservationTests.class,
ReorderAndCapacityTests.class,
ShippingTests.class,
RemoveDamagedTests.class
})
public class ProductStockTestSuite {

}
