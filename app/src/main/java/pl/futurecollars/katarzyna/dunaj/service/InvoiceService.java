package pl.futurecollars.katarzyna.dunaj.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import pl.futurecollars.katarzyna.dunaj.db.Database;
import pl.futurecollars.katarzyna.dunaj.model.Invoice;

@RequiredArgsConstructor
 public class InvoiceService {
    private final Database database;

    public Invoice save(Invoice invoice) {
        return database.save(invoice);
    }

    public Invoice getById(UUID id) {
        return database.getById(id);
    }

    public List<Invoice> getAll() {
        return database.getAll();
    }

    public Invoice update(Invoice updatedInvoice) {
        return database.update(updatedInvoice);
    }

    public boolean delete(UUID id) {
        return database.delete(id);
    }
}
