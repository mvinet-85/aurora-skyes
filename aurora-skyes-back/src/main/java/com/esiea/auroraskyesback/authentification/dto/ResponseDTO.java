package com.esiea.auroraskyesback.authentification.dto;

import lombok.Data;

@Data
public class ResponseDTO {

    /** Token */
    private String token;

    /** Id de l'utilisateur */
    private Long id;
}
