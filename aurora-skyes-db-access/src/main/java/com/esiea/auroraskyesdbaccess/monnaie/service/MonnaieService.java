package com.esiea.auroraskyesdbaccess.monnaie.service;

import com.esiea.auroraskyesdbaccess.monnaie.dto.MonnaieBDDTO;
import com.esiea.auroraskyesdbaccess.monnaie.entity.MonnaieEntity;
import com.esiea.auroraskyesdbaccess.monnaie.dao.MonnaieDAO;
import com.esiea.auroraskyesdbaccess.monnaie.exception.MonnaieNotFoundException;
import com.esiea.auroraskyesdbaccess.monnaie.exception.MonnaieUpdateException;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class MonnaieService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonnaieService.class);

    private final MonnaieDAO monnaieDAO;

    public MonnaieService(MonnaieDAO monnaieDAO) {
        this.monnaieDAO = monnaieDAO;
    }

    public List<MonnaieBDDTO> getExchangeRates() {

        List<MonnaieEntity> monnaies = monnaieDAO.findAll();

        if (monnaies.isEmpty()) {
            throw new MonnaieNotFoundException("Aucune monnaie disponible dans la base de données.");
        }

        List<MonnaieBDDTO> monnaieDTOs = new ArrayList<>();

        for (MonnaieEntity monnaie : monnaies) {
            monnaieDTOs.add(new MonnaieBDDTO(monnaie.getNom(), monnaie.getTaux()));
        }
        return monnaieDTOs;
    }

    @PostConstruct
    public void init() {
        updateExchangeRates();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateExchangeRates() {
        String urlString = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

        try {
            // Connexion à l'URL
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Lecture de la réponse
            InputStream responseStream = connection.getInputStream();

            // Analyse du fichier XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(responseStream);

            // Accès à l'élément "Cube" qui contient les taux de change
            NodeList cubeNodes = document.getElementsByTagName("Cube");

            for (int i = 0; i < cubeNodes.getLength(); i++) {
                Node node = cubeNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Vérifier si l'élément a un attribut "currency"
                    if (element.hasAttribute("currency") && element.hasAttribute("rate")) {
                        String currency = element.getAttribute("currency");
                        double rate = Double.parseDouble(element.getAttribute("rate"));

                        // Vérifier si la monnaie existe déjà
                        MonnaieEntity existingMonnaie = monnaieDAO.findByNom(currency).orElse(null);

                        if (existingMonnaie != null) {
                            // Mettre à jour le taux si la monnaie existe
                            existingMonnaie.setTaux(rate);
                            monnaieDAO.save(existingMonnaie);
                        } else {
                            // Créer une nouvelle entrée si elle n'existe pas
                            monnaieDAO.save(new MonnaieEntity(currency, rate));
                        }
                    }
                }
            }

            // Ajouter l'Euro manuellement si nécessaire
            MonnaieEntity euro = monnaieDAO.findByNom("EUR").orElse(new MonnaieEntity("EUR", 1.00));
            euro.setTaux(1.00);
            monnaieDAO.save(euro);

            System.out.println("Exchange rates updated successfully");

        } catch (Exception e) {
            LOGGER.error("Échec de la mise à jour des taux de change.");
            throw new MonnaieUpdateException("Échec de la mise à jour des taux de change.", e);
        }
    }

}
