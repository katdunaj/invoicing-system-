package pl.futurecollars.katarzyna.dunaj.fixtures

import pl.futurecollars.katarzyna.dunaj.model.InvoiceEntry
import pl.futurecollars.katarzyna.dunaj.model.Vat

class InvoiceEntryFixture {

    static product(int id) {
        new InvoiceEntry("Description $id",
                BigDecimal.valueOf(100 * id)
                , BigDecimal.valueOf(100+23),
                Vat.VAT_23)
    }
}
