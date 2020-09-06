package ir.maktab.finalproject.model.dao;

import ir.maktab.finalproject.model.entity.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface ConfirmationTokenDao extends CrudRepository<ConfirmationToken, Integer> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
