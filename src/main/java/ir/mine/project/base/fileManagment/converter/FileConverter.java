package ir.mine.project.base.fileManagment.converter;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

import ir.mine.project.base.fileManagment.dto.FileDetail;
import lombok.SneakyThrows;

public class FileConverter implements AttributeConverter<FileDetail, String> {

	@Override
	@SneakyThrows
	public String convertToDatabaseColumn(FileDetail attribute) {
		if (attribute != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(attribute);
		} else
			return null;

	}

	@Override
	@SneakyThrows
	public FileDetail convertToEntityAttribute(String dbData) {
		if (dbData != null && !dbData.isEmpty()) {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(dbData, FileDetail.class);
		} else
			return null;

	}

}
