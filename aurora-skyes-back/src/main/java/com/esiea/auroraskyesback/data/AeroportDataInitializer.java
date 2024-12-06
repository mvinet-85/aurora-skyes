package com.esiea.auroraskyesback.data;

import com.esiea.auroraskyesback.aeroport.dao.AeroportDAO;
import com.esiea.auroraskyesback.aeroport.entity.AeroportEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("aeroportInitializer")
public class AeroportDataInitializer {

    private final AeroportDAO aeroportDAO;

    public AeroportDataInitializer(AeroportDAO aeroportDAO) {
        this.aeroportDAO = aeroportDAO;
    }

    @Bean
    @Transactional
    public CommandLineRunner initData() {
        return args -> {
            AeroportEntity cdg = new AeroportEntity();
            cdg.setNom("CDG");
            cdg.setVille("Paris");

            AeroportEntity jfk = new AeroportEntity();
            jfk.setNom("JFK");
            jfk.setVille("New York");

            AeroportEntity dtw = new AeroportEntity();
            dtw.setNom("DTW");
            dtw.setVille("Detroit");

            aeroportDAO.save(cdg);
            aeroportDAO.save(jfk);
            aeroportDAO.save(dtw);

            aeroportDAO.flush();
        };
    }
}
