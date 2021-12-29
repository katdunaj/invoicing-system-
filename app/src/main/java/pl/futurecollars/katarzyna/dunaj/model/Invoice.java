package pl.futurecollars.katarzyna.dunaj.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class Invoice {
    private UUID id;
    private LocalDate date;
    private Company from;
    private Company to;
    private List<InvoiceEntry> entries;

    public Invoice(LocalDate date, Company from, Company to, List<InvoiceEntry> entries) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.entries = entries;
    }
}
