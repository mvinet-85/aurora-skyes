package com.esiea.auroraskyesdbaccess.monnaie.controller;

import com.esiea.auroraskyesdbaccess.monnaie.dto.MonnaieBDDTO;
import com.esiea.auroraskyesdbaccess.monnaie.service.MonnaieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/monnaies")
public class MonnaieController {

    @Autowired
    private MonnaieService exchangeRateService;

    @GetMapping
    public List<MonnaieBDDTO> getExchangeRates() {
        return exchangeRateService.getExchangeRates();
    }

}
