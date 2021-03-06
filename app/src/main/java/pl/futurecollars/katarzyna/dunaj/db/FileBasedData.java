package pl.futurecollars.katarzyna.dunaj.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import pl.futurecollars.katarzyna.dunaj.file.FileService;
import pl.futurecollars.katarzyna.dunaj.file.JsonService;
import pl.futurecollars.katarzyna.dunaj.model.Invoice;

@RequiredArgsConstructor
public class FileBasedData implements Database {

    private final FileService fileService;
    private final FileService idFileService;
    private final JsonService<Invoice> invoiceService;

    private void setRandomID(Invoice invoice) {
        UUID id = UUID.randomUUID();
        invoice.setId(id);
    }

    @Override
    public Invoice save(Invoice invoice) {
        if (invoice.getId() == null) {
            setRandomID(invoice);
        }

        if (containsID(invoice.getId())) {
            setRandomID(invoice);
            return save(invoice);
        }

        fileService.writeToDatabase(invoiceService.convertToJson(invoice));
        return invoice;
    }

    @Override
    public Invoice getById(UUID id) {
        if (containsID(id)) {
            return getAll()
                .stream()
                .filter(invoice -> invoice.getId().equals(id))
                .collect(Collectors.toList())
                .get(0);
        }
        return null;

    }

    @Override
    public List<Invoice> getAll() {
        return fileService.readFromDatabase()
            .stream()
            .map(s -> invoiceService.convertToObject(s, Invoice.class))
            .collect(Collectors.toList());
    }

    @Override
    public Invoice update(Invoice updatedInvoice) {
        delete(updatedInvoice.getId());
        save(updatedInvoice);
        return updatedInvoice;
    }

    @Override
    public boolean delete(UUID id) {
        if (containsID(id)) {
            List<Invoice> databaseCopy = getAll();
            fileService.clearDatabase();
            databaseCopy
                .stream()
                .filter(invoice -> !invoice.getId().equals(id))
                .forEach(this::save);
            return true;
        }
        return false;
    }

    public boolean containsID(UUID id) {
        try {
            return Files.readAllLines(Paths.get(String.valueOf(idFileService)))
                .stream()
                .anyMatch(line -> line.contains(id.toString()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
