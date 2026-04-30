package BuildWeek.Group3;

import BuildWeek.Group3.services.DataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportRunner {

    @Bean
    CommandLineRunner runImport(DataService service) {
        return args -> {

            service.importProvince("src/main/resources/csv/province.csv");
            service.importComuni("src/main/resources/csv/comuni.csv");

            System.out.println("IMPORT COMPLETATO");
        };
    }
}