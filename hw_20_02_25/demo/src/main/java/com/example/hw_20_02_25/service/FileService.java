package com.example.hw_20_02_25.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	String save(MultipartFile file);
}
