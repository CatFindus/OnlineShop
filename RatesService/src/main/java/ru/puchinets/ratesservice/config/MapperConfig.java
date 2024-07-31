package ru.puchinets.ratesservice.config;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;

@Configuration
public class MapperConfig {

    @Bean
    public XmlMapper xmlMapper() {
        var xmlMapper =  new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.registerModule(new JavaTimeModule());
        return xmlMapper;
    }

    public static class BigDecimalCustomDeserializer extends JsonDeserializer<BigDecimal> {

        @Override
        public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            String bigDecimalString = jsonParser.getText();
            if (bigDecimalString.contains(","))
                bigDecimalString=bigDecimalString.replace(",",".");
            return new BigDecimal(bigDecimalString);
        }
    }
}
