package ru.puchinets.ratesservice.repository;

import org.springframework.data.repository.CrudRepository;
import ru.puchinets.ratesservice.model.entity.Rate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RateRepository extends CrudRepository<Rate, String> {
    List<Rate> findAllByCodeInAndDate(List<String> codes, LocalDate date);
    Optional<Rate> findByCodeAndDate(String code, LocalDate date);
}
