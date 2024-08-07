package ru.puchinets.ratesservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Rates")
public class Rate implements Serializable {
    @Indexed
    @Id
    private String id;
    @Indexed
    private String code;
    private BigDecimal amount;
    @Indexed
    private LocalDate date;
}
