package org.itmo.secs.repository;

import org.itmo.secs.model.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.itmo.secs.model.entity.User;


@Repository
public interface DataRepository extends JpaRepository<Data, Long> {   
    List<Data> findAllByUser(User user);
}
