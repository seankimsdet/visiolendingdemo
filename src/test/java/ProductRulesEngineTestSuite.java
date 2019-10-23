import PersonModel.IPerson;
import PersonModel.Person;
import ProductModel.IProduct;
import ProductModel.Product;
import RulesEngineModel.IRulesEngine;
import RulesEngineModel.RulesEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import java.util.HashMap;
import java.util.stream.Stream;
import static RulesEngineModel.RulesEngine.loadRules;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@DisplayName("Rules Engine Test Suite")
class ProductRulesEngineTestSuite extends TestBase{

    @Test
    @DisplayName("Simple test")
    void SimpleTest() {
        IPerson person = new Person(720, "Florida");
        IProduct product = new Product("7-1 ARM", 5.0);
        IRulesEngine rules_engine = new RulesEngine();
        HashMap rules = loadRules();
        rules_engine.runRules(person, product, rules);

        double expectedInterestRate = 5.2;
        double actualInterestRate = product.getProductInterestRate();
        assertEquals(expectedInterestRate, actualInterestRate);

        boolean isDisqualified = product.getIsDisqualified();
        assertTrue(isDisqualified);
    }


    @TestFactory
    Stream<DynamicTest> TestFactoryTest() {
        Stream<Integer> inputStream = Stream.of(721, 720, 719);
        return inputStream.map(
                input -> dynamicTest("Display name for input" + input, () -> {
                    System.out.println("Testing " + input);
                    boolean qualifiesForDiscount = input >= 720;
                    assertTrue(qualifiesForDiscount);
                }));
    }



    /* Test Data Arguments order:

        CreditScore || State || ProductName || RateOffset || Expected: InterestRate || Expected: IsDisqualified
    */
    static Stream<Arguments> arguments = Stream.of(
            Arguments.of(721, "Florida", "7-1 ARM", 5.0, 5.2, true),
            Arguments.of(719, "Florida", "7-1 ARM", 5.0, 6.0, true),
            Arguments.of(720, "California", "7-1 ARM", 5.0, 5.2, false),
            Arguments.of(720, "California", "VA", 0, 4.7, false)
    );

    @ParameterizedTest
    @VariableSource("arguments")
    void ParameterizedTest(Integer creditScore, String state, String productName,
                           double rateOffset, double expectedInterest, boolean expectedIsDisqualified) {

        IPerson person = new Person(creditScore, state);
        IProduct product = new Product(productName, rateOffset);
        IRulesEngine rules_engine = new RulesEngine();
        HashMap rules = loadRules();
        rules_engine.runRules(person, product, rules);

        assertEquals(expectedInterest, product.getProductInterestRate());
        assertEquals(expectedIsDisqualified, product.getIsDisqualified());
    }
}
