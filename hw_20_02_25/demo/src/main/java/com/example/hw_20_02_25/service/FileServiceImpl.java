package com.example.hw_20_02_25.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
	private final static Path UPLOAD_DIR = Paths.get("uploads", "logotypes");

	public FileServiceImpl() {
		ensureUploadDirExists();
	}

	@Override
	public String save(MultipartFile file) {
		String origFileName = file.getOriginalFilename().replace(" ", "_");
		String fileName = UUID.randomUUID() + "_" + origFileName;

		try {
			Path filePath = UPLOAD_DIR.resolve(fileName);
			Files.copy(file.getInputStream(), filePath);

			// web-accessible path
			return "/uploads/logotypes/" + fileName;
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file", e);
		}
	}

	private void ensureUploadDirExists() {
		try {
			if (!Files.exists(UPLOAD_DIR)) {
				Files.createDirectories(UPLOAD_DIR);
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not create upload directory", e);
		}
	}
}
