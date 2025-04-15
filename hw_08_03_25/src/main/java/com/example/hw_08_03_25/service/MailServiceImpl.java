package com.example.hw_08_03_25.service;

import com.example.hw_08_03_25.dto.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class MailServiceImpl implements MailService {

	private final JavaMailSender mailSender;
	private final SpringTemplateEngine templateEngine;

	public MailServiceImpl(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}

	@Override
	public void sendMail(String to, String subject, String body, boolean isHtml) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, isHtml);

		mailSender.send(message);
	}

	@Override
	public void sendMail(EmailRequest emailRequest, boolean isHtml) throws MessagingException {
		sendMail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody(), isHtml);
	}

	@Override
	public void sendMailWithAttachment(
		String to,
		String subject,
		String text,
		MultipartFile file) throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, true);

		helper.addAttachment(file.getOriginalFilename(), file);

		mailSender.send(message);
	}

	@Override
	public void sendMailWithAttachment(EmailRequest emailRequest, MultipartFile file) throws MessagingException {
		sendMailWithAttachment(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody(), file);
	}

	@Override
	public void sendTemplateEmail(String to, String subject, String name, String from) throws MessagingException {
		Context context = new Context();
		context.setVariable("name", name);
		context.setVariable("from", from);

		String html = templateEngine.process("email-template", context);
		sendMail(to, subject, html, true);
	}
}
