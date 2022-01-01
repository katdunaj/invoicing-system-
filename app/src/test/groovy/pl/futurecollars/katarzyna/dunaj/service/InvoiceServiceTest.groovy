package pl.futurecollars.katarzyna.dunaj.service

import pl.futurecollars.katarzyna.dunaj.db.InMemoryDatabase
import pl.futurecollars.katarzyna.dunaj.model.Company
import pl.futurecollars.katarzyna.dunaj.model.Invoice
import pl.futurecollars.katarzyna.dunaj.model.InvoiceEntry
import pl.futurecollars.katarzyna.dunaj.model.Vat
import spock.lang.Specification
import java.time.LocalDate


class InvoiceServiceTest extends Specification {

    def from = new Company(12345, "Ul.Ogrodowa 3, 05-085 Kampinos", "Telnet")
    def from2 = new Company(23456, "Ul.Ogrodowa 7, 05-085 Kampinos", "Telnet")
    def to = new Company(23456, "Ul.Kwiatowa 5, 05-085 Kampinos", "NetPlus")
    def to2 = new Company(76589, "Ul.Kwiatowa 5, 05-085 Kampinos", "NetPlus")
    def date = LocalDate.of(2017, 8, 17)
    def invoiceEntry1 = new InvoiceEntry("Cukier", BigDecimal.valueOf(20), BigDecimal.valueOf(17), Vat.VAT_8)
    def invoiceEntry2 = new InvoiceEntry("Sól", BigDecimal.valueOf(30), BigDecimal.valueOf(16), Vat.VAT_5)
    def entries = Arrays.asList(invoiceEntry1, invoiceEntry2)
    def invoice = new Invoice(date, from, to, entries)
    def invoice2 = new Invoice(date, from2, to, entries)
    def invoice3 = new Invoice(date, from, to2, entries)
    def invoiceUpdated = new Invoice(date, from, to, entries)
    def database = new InMemoryDatabase()

    def "should save invoice in to database"() {
        setup:
        def invoiceService = new InvoiceService(database)

        when:
        def result = invoiceService.save(invoice)

        then:
        database.getById(result.getId()) != null
        database.getById(result.getId()).getFrom().getName() == "Telnet"
    }

    def "should get invoice from database by Id"() {
        setup:
        database.save(invoice)
        def invoiceService = new InvoiceService(database)

        when:
        def result = invoiceService.getById(invoice.getId())

        then:
        result != null
        result.getFrom().getName() == "Telnet"
    }

    def "should get list of all invoices from database"() {
        setup:
        database.save(invoice)
        database.save(invoice2)
        database.save(invoice3)
        def invoiceService = new InvoiceService(database)

        when:
        def result = invoiceService.getAll()

        then:
        result.size() == 3
    }

    def "should update invoice in the database"() {
        setup:
        database.save(invoice)
        def invoiceService = new InvoiceService(database)
        invoiceUpdated.setId(invoice.getId())

        when:
        def result = invoiceService.update(invoiceUpdated)

        then:
        database.getById(result.getId()) != null
        database.getById(result.getId()).getFrom().getName() == "Telnet"
    }

    def "should delete invoice from database"() {
        setup:
        database.save(invoice)
        def invoiceService = new InvoiceService(database)

        when:
        def result = invoiceService.delete(invoice.getId())

        then:
        result
        invoiceService.getAll().size() == 0
    }
}
