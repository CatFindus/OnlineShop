package ru.puchinets.ratesservice.repository;

import org.springframework.data.repository.CrudRepository;
import ru.puchinets.ratesservice.model.entity.Currency;

import java.util.List;


public interface CurrencyRepository extends CrudRepository<Currency, String> {
    List<Currency> findAll();
}
