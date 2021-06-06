import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import taxType.IncomeTaxType;
import taxType.ProgressiveTaxType;
import taxType.TaxType;
import taxType.VATTaxType;


public class AppTest {
    @Test
    void test_IncomeTaxType() {
        Assertions.assertEquals((new IncomeTaxType()).calculateTaxFor(100.0), 13.0);
    }

    @Test
    void test_VATTaxType() {
        Assertions.assertEquals((new VATTaxType()).calculateTaxFor(100.0), 20.0);
    }

    @ParameterizedTest
    @CsvSource({"100.0,10.0", "1000000.0,150000.0", "0.0,0.0", "100000.0,10000.0"})
    void test_ProgressiveTaxType(String input, String expected) {
        TaxType tax = new ProgressiveTaxType();
        Assertions.assertEquals(tax.calculateTaxFor(Double.parseDouble(input)), Double.parseDouble(expected));
    }


}
