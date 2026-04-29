package BuildWeek.Group3.config;

import BuildWeek.Group3.entities.Ruolo;
import BuildWeek.Group3.repositories.RuoloRepository;
import org.springframework.boot.CommandLineRunner;

public class RuoliRunner implements CommandLineRunner {

    private final RuoloRepository ruoloRepository;

    public RuoliRunner(RuoloRepository ruoloRepository) {
        this.ruoloRepository = ruoloRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (ruoloRepository.count() == 0) {
            ruoloRepository.save(new Ruolo("USER"));
            ruoloRepository.save(new Ruolo("ADMIN"));
            System.out.println("Ruoli USER e ADMIN creati con successo nel database");
        } else {
            System.out.println("Ruoli già presenti");
        }
    }

}
