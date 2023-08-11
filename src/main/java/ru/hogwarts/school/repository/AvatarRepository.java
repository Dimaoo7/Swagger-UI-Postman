package ru.hogwarts.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Avatar;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudentId(Long studentId);

    Optional<Avatar> findAvatarByStudentId(Long studentId);

    Page<Avatar> findAll(Pageable pageable);



    //Добавьте пагинацию для репозитория AvatarRepository и контроллер в
    // AvatarController, чтобы можно было получать списки аватарок постранично.


}
