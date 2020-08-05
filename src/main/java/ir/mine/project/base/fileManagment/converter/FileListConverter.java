package ir.mine.project.base.fileManagment.converter;

import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

import ir.mine.project.base.fileManagment.dto.FileDetail;
import lombok.SneakyThrows;

public class FileListConverter implements AttributeConverter<List<FileDetail>, String> {

	@Override
	@SneakyThrows
	public String convertToDatabaseColumn(List<FileDetail> attribute) {
		if (attribute != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(attribute);
		} else
			return null;

	}

	@Override
	@SneakyThrows
	public List<FileDetail> convertToEntityAttribute(String dbData) {
		if (dbData != null && !dbData.isEmpty()) {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(dbData,
					objectMapper.getTypeFactory().constructCollectionType(List.class, FileDetail.class));
		} else
			return null;

	}

}
