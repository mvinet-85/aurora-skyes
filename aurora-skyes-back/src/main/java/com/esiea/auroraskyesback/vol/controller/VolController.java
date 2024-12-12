package com.esiea.auroraskyesback.vol.controller;

import com.esiea.auroraskyesback.vol.dto.VolDTO;
import com.esiea.auroraskyesback.vol.mapper.VolMapper;
import com.esiea.auroraskyesback.vol.service.VolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vols")
public class VolController {

    /** {@link VolService} */
    private final VolService volService;

    /** {@link VolMapper} */
    private final VolMapper volMapper;

    public VolController(VolService volService,
                         VolMapper volMapper) {
        this.volService = volService;
        this.volMapper = volMapper;
    }

    /**
     * Récupère tous les vols
     * @return les vols disponibles
     */
    @GetMapping
    public List<VolDTO> getAllVols() {
        return this.volMapper.volBDDTOSToVolDTO(this.volService.getAllVols());
    }

    /**
     * Endpoint permettant de récupérer un vol
     * @param id du vol a récupérer
     * @return le vol
     */
    @GetMapping("/{id}")
    public VolDTO getVolById(@PathVariable Long id) {
        return this.volMapper.volBDDTOToVolDTO(this.volService.findVolById(id));
    }

}
