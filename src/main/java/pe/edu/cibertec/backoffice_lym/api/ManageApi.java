package pe.edu.cibertec.backoffice_lym.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.backoffice_lym.dto.UserDetailDto;
import pe.edu.cibertec.backoffice_lym.dto.UserDto;
import pe.edu.cibertec.backoffice_lym.response.FindUserByIdResponse;
import pe.edu.cibertec.backoffice_lym.response.FindUsersResponse;
import pe.edu.cibertec.backoffice_lym.response.UpdateUserResponse;
import pe.edu.cibertec.backoffice_lym.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-user")
public class ManageApi {

    @Autowired
    ManageService manageService;

    @GetMapping("/all")
    public FindUsersResponse findUsers(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            if (Integer.parseInt(id) > 0) {
                Optional<UserDto> optional = manageService.getAllUsersById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    UserDto userDto = optional.get();
                    return new FindUsersResponse("01", "", List.of(userDto));
                } else {
                    return new FindUsersResponse("02", "User not found", null);
                }

            } else {
                List<UserDto> users = manageService.getAllUsers();
                if (!users.isEmpty())
                    return new FindUsersResponse("01", "", users);
                else
                    return new FindUsersResponse("03", "User list not found", users);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new FindUsersResponse("99", "Service not found", null);

        }

    }

    @GetMapping("/detail")
    public FindUserByIdResponse findUserById(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            if (Integer.parseInt(id) > 0) {
                Optional<UserDetailDto> optional = manageService.getUserById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    UserDetailDto userDetailDto = optional.get();
                    return new FindUserByIdResponse("01", "", userDetailDto);
                } else {
                    return new FindUserByIdResponse("02", "User not found", null);
                }

            } else
                return new FindUserByIdResponse("03", "Parameter not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new FindUserByIdResponse("99", "Service not found", null);

        }

    }

    @PostMapping("/update")
    public UpdateUserResponse updateUser(@RequestBody UserDto userDto) {

        try {
            if (manageService.updateUser(userDto)) {
                return new UpdateUserResponse("01", "");
            } else {
                return new UpdateUserResponse("02", "User not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateUserResponse("99", "Service not found");

        }

    }

}
