import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import taxType.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Stream;


public class TaxServiceTest {

    static Stream<Bill> getBill() {
        return Arrays.asList(
                new Bill(100, new IncomeTaxType(), new TaxService()),
                new Bill(100, new VATTaxType(), new TaxService()),
                new Bill(100, new ProgressiveTaxType(), new TaxService()),
                new Bill(1_000_000.1, new ProgressiveTaxType(), new TaxService()))
                .stream();
    }

    protected static ByteArrayOutputStream output;
    private static PrintStream old;

    @BeforeAll
    static void setUpStreams() {
        old = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterAll
    static void cleanUpStreams() {
        System.setOut(old);
    }

    @BeforeEach
    void resetStreams() {
        output.reset();
    }

    @Test
    void testTaxServiceTest() {
        double input = 100.0;

        String result = String.format("Уплачен налог в размере %,.2f%n", input);

        TaxService taxService = new TaxService();
        taxService.payOut(input);
        Assertions.assertEquals(result, output.toString());
    }

    @Test
    void testPayTaxes() {
        double amount = 100.0;
        Bill bill = new Bill(amount, new IncomeTaxType(), new TaxService());
        double taxPayed = (new IncomeTaxType()).calculateTaxFor(amount);

        String result = String.format("Уплачен налог в размере %,.2f%n", taxPayed);

        bill.payTaxes();

        Assertions.assertEquals(result, output.toString());
    }

    @ParameterizedTest
    @MethodSource("getBill")
    void testPayTaxesPrintsSomething(Bill bill) {
        bill.payTaxes();
        Assertions.assertNotNull(output);
    }

}
