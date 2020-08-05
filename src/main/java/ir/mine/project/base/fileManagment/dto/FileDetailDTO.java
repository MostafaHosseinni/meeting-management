package ir.mine.project.base.fileManagment.dto;

import java.io.IOException;
import java.io.Serializable;

import ir.mine.project.base.Util.ApplicationContextProvider;
import ir.mine.project.base.fileManagment.util.FileDetailUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A DTO for the Brand entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileDetailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String fileName;

	private String filePath;

	private String mimeType;

	private String createDate;

	private Boolean hasThumbnail = Boolean.FALSE;

	private String file;

	public String getFile() {

		FileDetailUtil fileDetailUtil = ApplicationContextProvider.getApplicationContext()
				.getBean(FileDetailUtil.class);
		try {
			String str = "";
			if("mp4".equals(getMimeType())) {
				str = "data:video/" + getMimeType() + ";base64,";
			}
			else {
				str = "data:image/" + getMimeType() + ";base64,";
			}
			return str + fileDetailUtil.getImage(fileName, mimeType, getHasThumbnail());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
