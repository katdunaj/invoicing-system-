package pl.futurecollars.katarzyna.dunaj.db

import pl.futurecollars.katarzyna.dunaj.model.Company
import pl.futurecollars.katarzyna.dunaj.model.Invoice
import pl.futurecollars.katarzyna.dunaj.model.InvoiceEntry
import spock.lang.Specification
import java.time.LocalDate

class InMemoryDatabaseTest extends Specification {

    def from = new Company(12345, "Ul.Ogrodowa 3, 05-085 Kampinos", "Telnet")
    def to = new Company(56796, "Ul.Ogrodowa 7, 05-085 Kampinos", "Telnet")
    def date = LocalDate.of(2021, 05, 05)
    def entries = new ArrayList<InvoiceEntry>()
    def invoice = new Invoice(date, from, to, entries)
    def database = new InMemoryDatabase()
    def "should save invoice in to database"() {
        when:
        def result = database.save(invoice)

        then:
        database.getById(result.getId()) != null
        database.getById(result.getId()).getFrom().getName() == "Telnet"
    }

    def "should get invoice from database by Id"() {
        setup:
        database.save(invoice)

        when:
        def result = database.getById(invoice.getId())

        then:
        result != null
        result.getFrom().getName() == "Telnet"
    }

    def "should get list of all invoices from database"() {
        setup:
        def invoice2 = new Invoice(date, from, to, entries)
        def invoice3 = new Invoice(date, from, to, entries)
        database.save(invoice)
        database.save(invoice2)
        database.save(invoice3)

        when:
        def result = database.getAll()

        then:
        result.size() == 3
    }

    def "should update invoice in the database"() {
        setup:
        def invoiceUpdated = new Invoice(date, from, to, entries)
        database.save(invoice)
        invoiceUpdated.setId(invoice.getId())

        when:
        def result = database.update(invoiceUpdated)

        then:
        database.getById(result.getId()) != null
        database.getById(result.getId()).getFrom().getName() == "Telnet"
    }

    def "should delete invoice from database"() {
        setup:
        database.save(invoice)

        when:
        def result = database.delete(invoice.getId())

        then:
        result
        database.getAll().size() == 0
    }

    def "should delete not existing invoice from database"() {
        when:
        def result = database.delete(UUID.randomUUID())

        then:
        result
        database.getAll().size() == 0
    }
}