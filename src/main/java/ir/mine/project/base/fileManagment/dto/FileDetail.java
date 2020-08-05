package ir.mine.project.base.fileManagment.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileDetail implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;

	private String fileName;

	private String filePath;

	private String mimeType;

	private String createDate;
	
	private Boolean hasThumbnail = Boolean.FALSE;

}
