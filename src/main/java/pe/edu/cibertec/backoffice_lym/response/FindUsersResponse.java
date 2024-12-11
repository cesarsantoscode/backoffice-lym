package pe.edu.cibertec.backoffice_lym.response;

import pe.edu.cibertec.backoffice_lym.dto.UserDto;

public record FindUsersResponse(String code,
                                String error,
                                Iterable<UserDto> users) {
}
