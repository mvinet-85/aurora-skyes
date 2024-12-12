package com.esiea.auroraskyesapi.aeroport.controller;

import com.esiea.auroraskyesapi.aeroport.dto.AeroportGlobaleDTO;
import com.esiea.auroraskyesapi.aeroport.mapper.AeroportMapper;
import com.esiea.auroraskyesapi.aeroport.service.AeroportService;
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
    public List<AeroportGlobaleDTO> getAllAeroports() {
        return this.aeroportMapper.aeroportBDDTOSToAeroportGlobaleDTO(this.aeroportService.getAllAeroports());
    }

    @PostMapping()
    public AeroportGlobaleDTO createVol(@RequestBody AeroportGlobaleDTO dto) {
        return this.aeroportMapper.aeroportBDDTOToAeroportGlobaleDTO(this.aeroportService.createAeroport(dto));
    }

}
