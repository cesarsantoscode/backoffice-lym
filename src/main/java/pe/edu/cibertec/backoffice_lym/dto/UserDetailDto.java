package pe.edu.cibertec.backoffice_lym.dto;

import java.util.Date;

public record UserDetailDto(Integer id,
                            String username,
                            String firstName,
                            String lastName,
                            String email,
                            Date createdAt,
                            Date updatedAt) {
}
