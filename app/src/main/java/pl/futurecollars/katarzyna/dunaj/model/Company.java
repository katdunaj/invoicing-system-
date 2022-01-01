package pl.futurecollars.katarzyna.dunaj.model;

import java.util.UUID;
import lombok.Data;

@Data
public class Company {

    private UUID id;
    private long taxIdentificationNumber;
    private String address;
    private String name;

    public Company(long taxIdentificationNumber, String address, String name) {
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.address = address;
        this.name = name;
    }
}

