package ir.mine.project.base.fileManagment.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ir.mine.project.base.Util.FileUtil;
import ir.mine.project.base.fileManagment.dto.FileDetail;
import ir.mine.project.base.fileManagment.util.FileDetailUtil;

/**
 * REST controller for managing City.
 */
@RestController
@RequestMapping("/File")
public class FileResource {

	@Autowired
	FileDetailUtil fileDetailUtil;

	@Value("${file.path}")
	private String filePath;

	@PostMapping("/uploadFile")
	public ResponseEntity<FileDetail> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			FileDetail fileDetail = fileDetailUtil.getFileDetail(file, false);
			return ResponseEntity.ok(fileDetail);
		} catch (IOException e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@PostMapping("/uploadFileWithThumbnail")
	public ResponseEntity<FileDetail> uploadFileWithThumbnail(@RequestParam("file") MultipartFile file) {
		try {
			FileDetail fileDetail = fileDetailUtil.getFileDetail(file, true);
			return ResponseEntity.ok(fileDetail);
		} catch (IOException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/uploadBatchFile")
	public ResponseEntity<List<FileDetail>> uploadBatchFile(@RequestParam("files") List<MultipartFile> files) {
		try {
			List<FileDetail> fileDetail = fileDetailUtil.getFileDetail(files, false);
			return ResponseEntity.ok(fileDetail);
		} catch (IOException e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@PostMapping("/uploadBatchFileWithThumbnail")
	public ResponseEntity<List<FileDetail>> uploadBatchFileWithThumbnail(
			@RequestParam("files") List<MultipartFile> files) {
		try {
			List<FileDetail> fileDetail = fileDetailUtil.getFileDetail(files, true);
			return ResponseEntity.ok(fileDetail);
		} catch (IOException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/getImage/{name}/{mimeType}")
	public ResponseEntity<String> getImage(@RequestParam("name") String name,
			@RequestParam("mimeType") String mimeType) {
		try {
			return ResponseEntity.ok(fileDetailUtil.getImage(name, mimeType, false));
		} catch (IOException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/getImageThumbnail/{name}/{mimeType}")
	public ResponseEntity<String> getImageThumbnail(@RequestParam("name") String name,
			@RequestParam("mimeType") String mimeType) {
		try {
			return ResponseEntity.ok(fileDetailUtil.getImage(name, mimeType, true));
		} catch (IOException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/downloadFile")
	public ResponseEntity<Void> downloadFile(@RequestParam("name") String name,
			@RequestParam("mimeType") String mimeType, @RequestParam("originalName") String originalName,
			HttpServletResponse response) {

		InputStream inputStream = FileUtil.getFileByNameAndPath(filePath, name, mimeType);

		try {
			IOUtils.copy(inputStream, response.getOutputStream());
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + originalName + "\"");
			response.flushBuffer();
			return ResponseEntity.ok().build();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();

	}

}