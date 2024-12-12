package com.esiea.auroraskyesback.monnaie.controller;

import com.esiea.auroraskyesback.monnaie.dto.MonnaieDTO;
import com.esiea.auroraskyesback.monnaie.mapper.MonnaieMapper;
import com.esiea.auroraskyesback.monnaie.service.MonnaieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/monnaies")
public class MonnaieController {

    /** {@link MonnaieMapper} */
    private final MonnaieMapper monnaieMapper;

    /** {@link MonnaieService} */
    private final MonnaieService monnaieService;

    public MonnaieController(final MonnaieService monnaieService,
                             final MonnaieMapper monnaieMapper) {
        this.monnaieService = monnaieService;
        this.monnaieMapper = monnaieMapper;
    }

    @GetMapping
    public List<MonnaieDTO> getExchangeRates() {
        return this.monnaieMapper.monnaieBDDTOSToMonnaieDTO(this.monnaieService.getAllMonnaies());
    }

}
