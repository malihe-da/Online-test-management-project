package ir.maktab.finalproject.model.dao;


import ir.maktab.finalproject.model.entity.Manager;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface ManagerDao extends CrudRepository<Manager, Integer> {
    Optional<Manager> findByEmailAddressAndPassword(String email, String password);
}
