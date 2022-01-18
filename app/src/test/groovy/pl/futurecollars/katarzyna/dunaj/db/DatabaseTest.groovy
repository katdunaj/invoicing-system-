package pl.futurecollars.katarzyna.dunaj.db
import pl.futurecollars.katarzyna.dunaj.model.Company
import pl.futurecollars.katarzyna.dunaj.model.Invoice
import pl.futurecollars.katarzyna.dunaj.model.InvoiceEntry
import spock.lang.Specification

import java.time.LocalDate

abstract class DatabaseTest extends Specification {

    abstract Database getDatabaseInstance();

    def from = new Company(UUID.randomUUID(), "Telnet", 123456, "ul.Ogrodowa 3 Kampinos")
    def to = new Company(UUID.randomUUID(),"NetPlus", 23456,"Ul.Kwiatowa 5, 05-085 Kampinos")
    def issuerDate = LocalDate.of(2017, 7, 21)
    def invoiceEntries = new ArrayList<InvoiceEntry>()
    def invoice = new Invoice(UUID.randomUUID(), issuerDate, from, to, invoiceEntries)
    Database database

    def setup(){
        database = getDatabaseInstance()
    }
    def "should save invoice"() {
        when:
        def result = database.save(invoice)

        then:
        database.getById(result.getId()) != null
        database.getById(result.getId()).getFrom().getName() == "Telnet"
    }
    def "should get invoice from by id"() {
        setup:
        database.save(invoice)

        when:
        def result = database.getById(invoice.getId())

        then:
        result != null
        result.getFrom().getName() == "Telnet"
    }

    def "should get list of all invoice "(){
        setup:
        def invoice2 = new Invoice(UUID.randomUUID(), issuerDate, from, to, invoiceEntries)
        def invoice3 = new Invoice(UUID.randomUUID(), issuerDate, from, to, invoiceEntries)
        database.save(invoice)
        database.save(invoice2)
        database.save(invoice3)

        when:
        def result = database.getAll()

        then:
        result.size() == 3
    }

    def "should delete invoice"() {
        setup:
        database.save(invoice)

        when:
        def result = database.delete(invoice.getId())

        then:
        result
        database.getAll().size() == 0
    }

    def "should update invoice in the database"() {
        setup:
        database.save(invoice)
        def invoiceUpdated = new Invoice(UUID.randomUUID(), issuerDate, from, to, invoiceEntries)
        invoiceUpdated.setId(invoice.getId())

        when:
        def result = database.update(invoiceUpdated)

        then:
        database.getById(result.getId()) != null
        database.getById(result.getId()).getFrom().getName() == "Telnet"
    }

    def "should delete not existing UUID"(){
        when:
        def result = database.delete(UUID.randomUUID())

        then:
        result

        database.getAll().size() == 0
    }
}