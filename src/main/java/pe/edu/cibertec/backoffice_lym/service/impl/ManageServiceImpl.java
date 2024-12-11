package pe.edu.cibertec.backoffice_lym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.backoffice_lym.dto.UserDetailDto;
import pe.edu.cibertec.backoffice_lym.dto.UserDto;
import pe.edu.cibertec.backoffice_lym.entity.User;
import pe.edu.cibertec.backoffice_lym.repository.UserRepository;
import pe.edu.cibertec.backoffice_lym.service.ManageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() throws Exception {

        List<UserDto> users = new ArrayList<UserDto>();
        Iterable<User> iterable = userRepository.findAll();
        iterable.forEach(user -> {
            UserDto userDto = new UserDto(user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail());
            users.add(userDto);
        });
        return users;

    }

    public Optional<UserDto> getAllUsersById(int id) throws Exception {

        Optional<User> optional = userRepository.findById(id);
        return optional.map(user -> new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()));

    }

    @Override
    public Optional<UserDetailDto> getUserById(int id) throws Exception {

        Optional<User> optional = userRepository.findById(id);
        return optional.map(user -> new UserDetailDto(user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()));

    }

    @Override
    public boolean updateUser(UserDto userDto) throws Exception {

        Optional<User> optional = userRepository.findById(userDto.id());
        return optional.map(user -> {
            user.setFirstName(userDto.firstName());
            user.setLastName(userDto.lastName());
            user.setEmail(userDto.email());
            user.setUpdatedAt(new Date());
            userRepository.save(user);
            return true;
        }).orElse(false);

    }

    @Override
    public boolean deleteUserById(int id) throws Exception {

        Optional<User> optional = userRepository.findById(id);
        return optional.map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);

    }

    @Override
    public boolean addUser(UserDetailDto userDetailDto, String password) throws Exception {

        Optional<User> optional = userRepository.findById(userDetailDto.id());
        if (optional.isPresent())
            return false;
        else {
            User user = new User();
            user.setUsername(userDetailDto.username());
            user.setFirstName(userDetailDto.firstName());
            user.setLastName(userDetailDto.lastName());
            user.setEmail(userDetailDto.email());
            user.setPassword(password); // encriptado
            user.setCreatedAt(new Date());
            userRepository.save(user);
            return true;
        }

    }

}
