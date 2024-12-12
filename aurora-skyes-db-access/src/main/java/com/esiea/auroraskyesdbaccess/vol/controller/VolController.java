package com.esiea.auroraskyesdbaccess.vol.controller;

import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;
import com.esiea.auroraskyesdbaccess.vol.dto.VolBDDTO;
import com.esiea.auroraskyesdbaccess.vol.mapper.VolMapper;
import com.esiea.auroraskyesdbaccess.vol.service.VolService;
import org.springframework.web.bind.annotation.*;

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
    public List<VolBDDTO> getAllVols() {
        return this.volMapper.volEntitiesToVolBDDTO(this.volService.getAllVols());
    }

    /**
     * Endpoint permettant de récupérer un vol
     * @param id du vol a récupérer
     * @return le vol
     */
    @GetMapping("/{id}")
    public VolBDDTO getVolById(@PathVariable Long id) {
        return this.volMapper.volEntityToVolBDDTO(volService.findVolById(id));
    }

    //TODO voir pour modifier juste des places
    /**
     * Endpoint permettant de modifier le vol
     * @param volBDDTO informations mise a jour du vol
     * @return le vol modifie
     */
    @PutMapping
    public void updateVol(@RequestBody VolBDDTO volBDDTO) {
        this.volService.modifierVol(this.volMapper.volBDDTOToVolVolEntity(volBDDTO));
    }

}
