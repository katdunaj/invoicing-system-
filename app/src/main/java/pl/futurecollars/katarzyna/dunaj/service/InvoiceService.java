package pl.futurecollars.katarzyna.dunaj.service;

import java.util.List;
import java.util.UUID;
import pl.futurecollars.katarzyna.dunaj.db.Database;
import pl.futurecollars.katarzyna.dunaj.model.Invoice;

class InvoiceService{

    private final Database database;

    public InvoiceService(Database database) {
        this.database = database;

    }
    public Invoice save (Invoice invoice){
        return database.save(invoice);

    }
    public Invoice getById(UUID id){
        return database.getById(id);
    }
    public List<Invoice> getAll(){
        return (List<Invoice>) database.getAll();
    }
    public Invoice update(Invoice updatedInvoice){
        return database.update(updatedInvoice);
    }
    public boolean delete(UUID id) {
        return database.delete(id);
    }
}