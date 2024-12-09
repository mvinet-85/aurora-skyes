package com.esiea.auroraskyesback.monnaie.service;

import com.esiea.auroraskyesback.monnaie.dto.MonnaieDTO;
import com.esiea.auroraskyesback.monnaie.entity.MonnaieEntity;
import com.esiea.auroraskyesback.monnaie.dao.MonnaieDAO;
import jakarta.annotation.PostConstruct;
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

    private final MonnaieDAO monnaieDAO;

    public MonnaieService(MonnaieDAO monnaieDAO) {
        this.monnaieDAO = monnaieDAO;
    }

    public List<MonnaieDTO> getExchangeRates() {

        List<MonnaieEntity> monnaies = monnaieDAO.findAll();

        List<MonnaieDTO> monnaieDTOs = new ArrayList<>();

        for (MonnaieEntity monnaie : monnaies) {
            monnaieDTOs.add(new MonnaieDTO(monnaie.getNom(), monnaie.getTaux()));
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
            System.err.println("Failed to update exchange rates: " + e.getMessage());
        }
    }

}
