package taxType;

public class IncomeTaxType extends TaxType {
    //    @Override
    public double calculateTaxFor(double amount) {
        // TODO override me!
        return amount * 0.13;
    }
}
