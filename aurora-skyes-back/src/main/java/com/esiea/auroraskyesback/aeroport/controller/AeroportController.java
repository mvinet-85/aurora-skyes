package com.esiea.auroraskyesback.aeroport.controller;

import com.esiea.auroraskyesback.aeroport.dto.AeroportDTO;
import com.esiea.auroraskyesback.aeroport.mapper.AeroportMapper;
import com.esiea.auroraskyesback.aeroport.service.AeroportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aeroports")
public class AeroportController {

    /** {@link AeroportService} */
    private final AeroportService aeroportService;

    /** {@link AeroportMapper} */
    private final AeroportMapper aeroportMapper;

    public AeroportController(AeroportService aeroportService,
                              AeroportMapper aeroportMapper) {
        this.aeroportService = aeroportService;
        this.aeroportMapper = aeroportMapper;
    }

    /**
     * Endpoint permettant de recupérer le référentiel des aéroports
     * @return la liste des aéroports
     */
    @GetMapping
    public List<AeroportDTO> getAllAeroports() {
        return this.aeroportMapper.aeroportBDDTOSToAeroportDTO(this.aeroportService.getAllAeroports());
    }

}
