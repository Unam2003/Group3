package BuildWeek.Group3.services;


import BuildWeek.Group3.entities.Comune;
import BuildWeek.Group3.entities.Provincia;
import BuildWeek.Group3.repositories.ComuneRepository;
import BuildWeek.Group3.repositories.ProvinciaRepository;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class DataService {
    private final ProvinciaRepository provinciaRepository;
    private final ComuneRepository comuneRepository;

    public DataService(ProvinciaRepository provinciaRepository, ComuneRepository comuneRepository) {
        this.provinciaRepository = provinciaRepository;
        this.comuneRepository = comuneRepository;
    }


public void importProvince(String path) {
    int totali = 0;
    int salvate = 0;
    int errori = 0;
    try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)).withSkipLines(1).withCSVParser(new CSVParserBuilder()
            .withSeparator(';').build()
            ).build()){
        String[] row;
        while ((row = reader.readNext())!= null) {
            totali++;
            try {
                String sigla = row[0].trim();
                String nome = row[1].trim();
                if (nome.isEmpty() || sigla.isEmpty()) {
                    throw new RuntimeException("campi da inserire obbligatoriamente");
                }
                if (!provinciaRepository.existsBySigla(sigla)) {
                    Provincia provincia = new Provincia();
                    provincia.setNomeProvincia(nome);
                    provincia.setSigla(sigla);
                    provinciaRepository.save(provincia);
                    salvate++;
                }
            } catch (Exception ex) {
                errori++;
            }
        }
    }
    catch (Exception exception) {
            throw new RuntimeException("ERRORE IMPORT PROVINCIA", exception);
    }}

    public void importComuni(String path) {

        int totali = 0;
        int salvati = 0;
        int errori = 0;

        try (CSVReader reader = new CSVReaderBuilder(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)).withSkipLines(1)
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(';')
                        .build())
                .build()) {

            String[] row;

            while ((row = reader.readNext()) != null) {
                totali++;

                try {
                    String nomeComune = row[2].trim();
                    String nomeProvincia = row[3].trim();

                    if (nomeComune.isEmpty() || nomeProvincia.isEmpty()) {
                        throw new RuntimeException("Campi vuoti");
                    }

                    Provincia provincia = provinciaRepository
                            .findByNomeProvincia(nomeProvincia)
                            .orElseThrow(() ->
                                    new RuntimeException("Provincia non trovata: " + nomeProvincia)
                            );

                    boolean exists = comuneRepository
                            .existsByNomeAndProvincia(nomeComune, provincia);

                    if (!exists) {
                        Comune comune = new Comune();
                        comune.setNome(nomeComune);
                        comune.setProvincia(provincia);

                        comuneRepository.save(comune);
                        salvati++;
                    }

                } catch (Exception e) {
                    errori++;
                    System.err.println("Errore riga comuni #" + totali + ": " + e.getMessage());
                }
            }

            System.out.println("=== IMPORT COMUNI ===");
            System.out.println("Totali: " + totali);
            System.out.println("Salvati: " + salvati);
            System.out.println("Errori: " + errori);

        } catch (Exception e) {
            throw new RuntimeException("Errore globale import comuni", e);
        }
    }
}