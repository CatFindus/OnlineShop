package ru.puchinets.ratesservice.model.dto.cb;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.puchinets.ratesservice.config.MapperConfig;

import java.math.BigDecimal;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JacksonXmlRootElement(localName = "Valute")
public class CbValuteDto {
    @JacksonXmlProperty(localName = "ID")
    private String id;
    @JacksonXmlProperty(localName = "CharCode")
    private String code;
    @JacksonXmlProperty(localName = "Name")
    private String name;
    @JacksonXmlProperty(localName = "Nominal")
    private Integer nominal;
    @JacksonXmlProperty(localName = "Value")
    @JsonDeserialize(using = MapperConfig.BigDecimalCustomDeserializer.class)
    private BigDecimal amount;
}
