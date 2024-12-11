package pe.edu.cibertec.backoffice_lym.service;

import pe.edu.cibertec.backoffice_lym.dto.UserDetailDto;
import pe.edu.cibertec.backoffice_lym.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface ManageService {

    List<UserDto> getAllUsers() throws Exception;

    Optional<UserDto> getAllUsersById(int id) throws Exception;

    Optional<UserDetailDto> getUserById(int id) throws Exception;

    boolean updateUser(UserDto userDto) throws Exception;

    boolean deleteUserById(int id) throws Exception;

    boolean addUser(UserDetailDto userDetailDto, String password) throws Exception;

}
