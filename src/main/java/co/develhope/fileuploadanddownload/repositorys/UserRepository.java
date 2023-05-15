package co.develhope.fileuploadanddownload.repositorys;

import co.develhope.fileuploadanddownload.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
