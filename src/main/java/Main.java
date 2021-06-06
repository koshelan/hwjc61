import taxType.*;

public class Main {
    public static void main(String[] args) {
        TaxService taxService = new TaxService();
        Bill[] payments = new Bill[] {
                new Bill (100,new IncomeTaxType(),new TaxService()),
                new Bill (100,new VATTaxType(),new TaxService()),
                new Bill (100,new ProgressiveTaxType(),new TaxService()),
                new Bill (1_000_000.1,new ProgressiveTaxType(),new TaxService())
        };
        for (int i = 0; i < payments.length; ++i) {
            Bill bill = payments[i];
            bill.payTaxes();
        }
    }
}