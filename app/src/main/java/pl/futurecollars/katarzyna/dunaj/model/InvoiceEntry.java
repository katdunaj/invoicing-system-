package pl.futurecollars.katarzyna.dunaj.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InvoiceEntry {
    private String description;
    private BigDecimal price;
    private BigDecimal vatValue;
    private Vat vatRate;

}