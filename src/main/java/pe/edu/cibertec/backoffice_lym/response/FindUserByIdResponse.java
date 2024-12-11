package pe.edu.cibertec.backoffice_lym.response;

import pe.edu.cibertec.backoffice_lym.dto.UserDetailDto;

public record FindUserByIdResponse(String code,
                                   String error,
                                   UserDetailDto user) {
}
