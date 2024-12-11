package pe.edu.cibertec.backoffice_lym.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.backoffice_lym.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
