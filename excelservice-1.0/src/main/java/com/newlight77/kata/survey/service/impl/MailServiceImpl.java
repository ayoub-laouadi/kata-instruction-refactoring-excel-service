package com.newlight77.kata.survey.service.impl;

import com.newlight77.kata.survey.config.MailServiceConfig;
import com.newlight77.kata.survey.service.MailService;
import com.newlight77.kata.survey.service.utils.MimeMessagePreparatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailServiceImpl implements MailService {

  private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
  private JavaMailSender mailSender;
  private MailServiceConfig mailServiceConfig;

  public MailServiceImpl(@Autowired final JavaMailSender mailSender,@Autowired final MailServiceConfig mailServiceConfig) {
    this.mailSender = mailSender;
    this.mailServiceConfig = mailServiceConfig;
  }

  @Override
  public void send(File attachment) {
    MimeMessagePreparator messagePreparator = MimeMessagePreparatorFactory.createMimeMessagePreparator(mailServiceConfig, attachment);
    try {
      mailSender.send(messagePreparator);
      logger.info("Email sent successfully");
    } catch (MailException e) {
      logger.error("Error while sending the email");
      throw new RuntimeException("An error occurred while sending an email", e);
    }
  }

}
