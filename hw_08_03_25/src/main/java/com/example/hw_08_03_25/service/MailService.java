package com.example.hw_08_03_25.service;

import com.example.hw_08_03_25.dto.EmailRequest;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

public interface MailService {
	void sendMail(String to, String subject, String body,
		boolean isHtml) throws MessagingException;
	void sendMail(EmailRequest emailRequest, boolean isHtml) throws MessagingException;

	void sendMailWithAttachment(
		String to,
		String subject,
		String text,
		MultipartFile file) throws MessagingException;

	void sendMailWithAttachment(EmailRequest emailRequest, MultipartFile file) throws MessagingException;

	void sendTemplateEmail(String to, String subject, String name,
		String from) throws MessagingException;
}
