package pl.futurecollars.katarzyna.dunaj.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Invoice {
    private UUID id;
    private final LocalDate issuerDate;
    private final Company from;
    private final Company to;
    private List <InvoiceEntry> invoiceEntries;
}
