package pl.futurecollars.katarzyna.dunaj.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Company {
    private UUID id;
    private String name;
    private long taxIdentificationNumber;
    private String address;

}
