package com.esiea.auroraskyesback.data;

import com.esiea.auroraskyesback.aeroport.dao.AeroportDAO;
import com.esiea.auroraskyesback.aeroport.entity.AeroportEntity;
import com.esiea.auroraskyesback.vol.dao.VolDAO;
import com.esiea.auroraskyesback.vol.entity.VolEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@DependsOn("aeroportInitializer")
public class VolDataInitializer {

    @Bean
    public CommandLineRunner initVols(VolDAO volDAO, AeroportDAO aeroportDAO) {
        return args -> {

            AeroportEntity cdg = aeroportDAO.findByNom("CDG")
                    .orElseThrow(() -> new RuntimeException("Aéroport 'CDG' introuvable"));

            AeroportEntity jfk = aeroportDAO.findByNom("JFK")
                    .orElseThrow(() -> new RuntimeException("Aéroport 'JFK' introuvable"));

            AeroportEntity dtw = aeroportDAO.findByNom("DTW")
                    .orElseThrow(() -> new RuntimeException("Aéroport 'DTW' introuvable"));

            VolEntity volOne = new VolEntity();
            volOne.setPlaceDisponible(10);
            volOne.setDateDepart(new Date(2024 - 1900, 11, 10, 14, 0));
            volOne.setDateArrive(new Date(2024 - 1900, 11, 10, 20, 0));
            volOne.setAeroportDepart(cdg);
            volOne.setAeroportArrivee(jfk);

            VolEntity volTwo = new VolEntity();
            volTwo.setPlaceDisponible(50);
            volTwo.setDateDepart(new Date(2024 - 1900, 12, 15, 9, 0));
            volTwo.setDateArrive(new Date(2024 - 1900, 12, 15, 11, 30));
            volTwo.setAeroportDepart(cdg);
            volTwo.setAeroportArrivee(dtw);

            VolEntity volThree = new VolEntity();
            volThree.setPlaceDisponible(25);
            volThree.setDateDepart(new Date(2024 - 1900, 12, 20, 22, 0));
            volThree.setDateArrive(new Date(2024 - 1900, 12, 21, 8, 0));
            volThree.setAeroportDepart(jfk);
            volThree.setAeroportArrivee(cdg);

            VolEntity volFour = new VolEntity();
            volFour.setPlaceDisponible(30);
            volFour.setDateDepart(new Date(2024 - 1900, 12, 18, 13, 0));
            volFour.setDateArrive(new Date(2024 - 1900, 12, 18, 21, 0));
            volFour.setAeroportDepart(jfk);
            volFour.setAeroportArrivee(dtw);

            VolEntity volFive = new VolEntity();
            volFive.setPlaceDisponible(40);
            volFive.setDateDepart(new Date(2024 - 1900, 12, 25, 6, 0));
            volFive.setDateArrive(new Date(2024 - 1900, 12, 25, 18, 0));
            volFive.setAeroportDepart(dtw);
            volFive.setAeroportArrivee(cdg);

            VolEntity volSix = new VolEntity();
            volSix.setPlaceDisponible(20);
            volSix.setDateDepart(new Date(2024 - 1900, 12, 31, 16, 0));
            volSix.setDateArrive(new Date(2025 - 1900, 1, 1, 2, 0));
            volSix.setAeroportDepart(dtw);
            volSix.setAeroportArrivee(jfk);

            volDAO.save(volOne);
            volDAO.save(volTwo);
            volDAO.save(volThree);
            volDAO.save(volFour);
            volDAO.save(volFive);
            volDAO.save(volSix);

        };
    }
}
