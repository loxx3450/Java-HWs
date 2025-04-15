package com.example.hw_08_03_25.controller;

import com.example.hw_08_03_25.dto.EmailRequest;
import com.example.hw_08_03_25.service.MailService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/mail")
public class MailController {

	private final MailService mailService;

	public MailController(MailService mailService) {
		this.mailService = mailService;
	}

	@PostMapping("send-text")
	public ResponseEntity<Void> sendText(@RequestBody EmailRequest emailRequest) {
		try {
			mailService.sendMail(emailRequest, false);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok().build();
	}

	@PostMapping("send-html")
	public ResponseEntity<Void> sendHtml(@RequestBody EmailRequest emailRequest) {
		try {
			mailService.sendMail(emailRequest, true);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok().build();
	}

	@PostMapping(path = "send-attachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> sendAttachment(
		@RequestPart String to,
		@RequestPart String subject,
		@RequestPart String text,
		@RequestPart MultipartFile file) {

		try {
			mailService.sendMailWithAttachment(to, subject, text, file);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok().build();
	}

	@PostMapping(path = "send-template")
	public ResponseEntity<Void> sendTemplate(
		@RequestPart String to,
		@RequestPart String subject,
		@RequestPart String name,
		@RequestPart String from
	) {

		try {
			mailService.sendTemplateEmail(to, subject, name, from);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok().build();
	}

}
