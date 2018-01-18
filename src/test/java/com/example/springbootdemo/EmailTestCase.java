package com.example.springbootdemo;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmailTestCase {
    private static final Log log = LogFactory.getLog(SpringBootdemoApplicationTests.class);
	@Autowired
	private JavaMailSender mailSender;
//	@Autowired
//	private VelocityEngine velocityEngine;

	@Test
	public void sendSimpleMail() throws Exception {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("594100991@qq.com");
		message.setTo("15123005664@139.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");

		mailSender.send(message);
		
		try {
		    mailSender.send(message);
		    log.info("简单邮件已经发送。");
		    } catch (Exception e) {
		            log.error("发送简单邮件时发生异常！", e);
		    }

	}

	@Test
	public void sendAttachmentsMail() throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("594100991@qq.com");
		helper.setTo("594100991@qq.com");
		helper.setSubject("主题：有附件");
		helper.setText("有附件的邮件");

		FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
		helper.addAttachment("附件-1.jpg", file);
		helper.addAttachment("附件-2.jpg", file);

		mailSender.send(mimeMessage);
	}

	@Test
	public void sendInlineMail() throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("594100991@qq.com");
		helper.setTo("594100991@qq.com");
		helper.setSubject("主题：嵌入静态资源");
		helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);

		FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
		helper.addInline("weixin", file);

		mailSender.send(mimeMessage);
	}

//	@Test
//	public void sendTemplateMail() throws Exception {
//
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//		helper.setFrom("dyc87112@qq.com");
//		helper.setTo("dyc87112@qq.com");
//		helper.setSubject("主题：模板邮件");
//
//		Map<String, Object> model = new HashedMap();
//		model.put("username", "didi");
//		String text = VelocityEngineUtils.mergeTemplateIntoString(
//				velocityEngine, "template.vm", "UTF-8", model);
//		helper.setText(text, true);
//
//		mailSender.send(mimeMessage);
//	}

}
