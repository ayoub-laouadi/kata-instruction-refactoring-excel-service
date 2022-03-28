package com.newlight77.kata.survey.service.utils;

import com.newlight77.kata.survey.config.MailServiceConfig;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.io.File;

public class  MimeMessagePreparatorFactory {

    public static MimeMessagePreparator createMimeMessagePreparator(MailServiceConfig mailServiceConfig, File attachment) {
        MimeMessagePreparator campaign_results = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(mailServiceConfig.getFrom());
            messageHelper.setTo(mailServiceConfig.getTo());
            messageHelper.setSubject("Campaign Results");
            messageHelper.setText("Hi,\n\nYou will find in the attached file the campaign results.");

            FileSystemResource file = new FileSystemResource(attachment);
            messageHelper.addAttachment(file.getFilename(), file);
        };
        return campaign_results;
    }
}
