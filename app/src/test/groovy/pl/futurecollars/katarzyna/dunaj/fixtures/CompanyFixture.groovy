package pl.futurecollars.katarzyna.dunaj.fixtures

import pl.futurecollars.katarzyna.dunaj.model.Company

class CompanyFixture {

    static company(int id) {

        new Company(UUID.randomUUID(),"Telnet", 12345,"Ul.Ogrodowa 3, 05-085 Kampinos")

    }
}