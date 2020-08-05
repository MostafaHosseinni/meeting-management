package ir.mine.project.base.fileManagment.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import ir.mine.project.base.Util.Base64EncodeDecode;
import ir.mine.project.base.Util.FileUtil;
import ir.mine.project.base.Util.date.CalenderUtil;
import ir.mine.project.base.fileManagment.dto.FileDetail;

@Component
public class FileDetailUtil {

	@Value("${file.path}")
	private String filePath;

	@Value("${file.thumbnail.path}")
	private String thumbnailPath;

	public FileDetail getFileDetail(MultipartFile fileName) throws IOException {
		return getFileDetail(fileName, false);
	}

	public FileDetail getFileDetailWithThumbnail(MultipartFile fileName) throws IOException {
		return getFileDetail(fileName, true);
	}

	public FileDetail getFileDetail(String fileStr, String mimeType) throws IOException {
		FileDetail file = new FileDetail();
		file.setCreateDate(CalenderUtil.dateToJalaliCalendar(ZonedDateTime.now()));
		file.setName(null);
		file.setFileName(UUID.randomUUID().toString());
		file.setFilePath(filePath);
		file.setMimeType(mimeType);
		FileUtil.storeFile(new ByteArrayInputStream(Base64EncodeDecode.convertBase64StringToByteArray(fileStr)), filePath,
				file.getFileName() + "." + file.getMimeType());
		return file;
	}

	public FileDetail getFileDetail(MultipartFile fileName, boolean hasThumbnail) throws IOException {
		FileDetail file = new FileDetail();

		file.setCreateDate(CalenderUtil.dateToJalaliCalendar(ZonedDateTime.now()));

		file.setName(fileName.getOriginalFilename());
		file.setFileName(UUID.randomUUID().toString());
		file.setFilePath(filePath);
		file.setMimeType(FileUtil.getFileExtention(fileName));

		FileUtil.storeFile(fileName.getInputStream(), filePath, file.getFileName() + "." + file.getMimeType());

		if (hasThumbnail) {
			FileUtil.storeFileTh(fileName.getInputStream(), thumbnailPath,
					file.getFileName() + "." + file.getMimeType());
			file.setHasThumbnail(true);
		}

		return file;
	}

	public FileDetail getFileExtention(File fileName) throws FileNotFoundException, IOException {
		return getFileDetail(fileName, false);
	}

	public FileDetail getFileExtentionWithThumbnail(File fileName) throws FileNotFoundException, IOException {
		return getFileDetail(fileName, true);
	}

	public FileDetail getFileDetail(File fileName, boolean hasThumbnail) throws IOException {
		FileDetail file = new FileDetail();

		file.setCreateDate(CalenderUtil.dateToJalaliCalendar(ZonedDateTime.now()));

		file.setName(fileName.getName());
		file.setFileName(UUID.randomUUID().toString());
		file.setFilePath(filePath);
		file.setMimeType(FileUtil.getFileExtention(fileName));

		FileUtil.storeFile(new FileInputStream(fileName), filePath, file.getFileName() + "." + file.getMimeType());

		if (hasThumbnail) {
			FileUtil.storeFileTh(new FileInputStream(fileName), thumbnailPath,
					file.getFileName() + "." + file.getMimeType());
			file.setHasThumbnail(true);
		}

		return file;
	}

	public List<FileDetail> getFileDetailWithThumbnail(List<MultipartFile> fileNames) throws IOException {
		return getFileDetail(fileNames, true);
	}

	public List<FileDetail> getFileDetail(List<MultipartFile> fileNames) throws IOException {
		return getFileDetail(fileNames, false);
	}

	public List<FileDetail> getFileDetail(List<MultipartFile> fileNames, boolean hasThumbnail) throws IOException {

		List<FileDetail> files = new ArrayList<FileDetail>();

		if (fileNames != null && !fileNames.isEmpty()) {

			for (MultipartFile fileName : fileNames) {
				files.add(getFileDetail(fileName, hasThumbnail));
			}
		}

		return files;
	}

	public String getImage(String name, String mimeType, boolean hasThumbnail) throws IOException {

		if (hasThumbnail) {
			InputStream is = FileUtil.getFileByNameAndPath(thumbnailPath, name, mimeType);
			return Base64EncodeDecode.convertToBase64String(is);
		} else {
			InputStream is = FileUtil.getFileByNameAndPath(filePath, name, mimeType);
			return Base64EncodeDecode.convertToBase64String(is);
		}
	}

	public String getImage(FileDetail fd, boolean hasThumbnail) throws IOException {

		if (hasThumbnail) {
			InputStream is = FileUtil.getFileByNameAndPath(thumbnailPath, fd.getFileName(), fd.getMimeType());
			return Base64EncodeDecode.convertToBase64String(is);
		} else {
			InputStream is = FileUtil.getFileByNameAndPath(filePath, fd.getFileName(), fd.getMimeType());
			return Base64EncodeDecode.convertToBase64String(is);
		}
	}
}
