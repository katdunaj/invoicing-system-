package pl.futurecollars.katarzyna.dunaj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.futurecollars.katarzyna.dunaj.db.Database;
import pl.futurecollars.katarzyna.dunaj.db.FileBasedData;
import pl.futurecollars.katarzyna.dunaj.file.FileService;
import pl.futurecollars.katarzyna.dunaj.file.JsonService;
import pl.futurecollars.katarzyna.dunaj.model.Invoice;

@Configuration
public class BeanConfiguration {

    @Bean
    public Database fileBasedDate(JsonService<Invoice> jsonService) {
        return new FileBasedData(
            new FileService(FilePathConfiguration.FILE_PATH_DATABASE),
            new FileService(FilePathConfiguration.FILE_PATH_ID_KEEPER),
            jsonService);
    }
}