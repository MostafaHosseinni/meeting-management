package ir.mine.project.domain.convertor;

import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

public class StringListConvertor implements AttributeConverter<List<String>, String> {

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(attribute);
        } else
            return null;

    }

    @Override
    @SneakyThrows
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData != null && !dbData.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(dbData,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, String.class));
        } else
            return null;

    }

}
