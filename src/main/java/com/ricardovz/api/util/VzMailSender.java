package com.ricardovz.api.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Sender of emails
 */
@Service
@Slf4j
public class VzMailSender {

    /**
     * Spring Mail Sender
     */
    private JavaMailSender springMailSender;

    /**
     * Builds a VzMailSender Service
     * @param mailSender spring mail sender
     */
    @Autowired
    public VzMailSender(JavaMailSender mailSender) {

        this.springMailSender = mailSender;
        ((JavaMailSenderImpl)this.springMailSender).setProtocol("smtps");

    }

    /**
     * send email using GMail SMTP server.
     *
     * @param recipients TO recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    public void send(String title, String message, List<String> recipients) throws MessagingException {

        ArrayList<String> emptyList = Lists.newArrayList();

        send(emptyList, emptyList, recipients, title, message);
    }

    /**
     * send email using GMail SMTP server.
     *
     * @param recipientEmail TO recipient
     * @param ccEmail CC recipient. Can be empty if there is no CC recipient
     * @param bccEmail BCC recipient. Can be empty if there is no BCC recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    public void send(List<String> recipientEmail, List<String> ccEmail, List<String> bccEmail, String title, String message) throws MessagingException {

        if ((recipientEmail == null || recipientEmail.isEmpty()) &&
                (ccEmail == null || ccEmail.isEmpty()) &&
                (bccEmail == null || bccEmail.isEmpty())) {

            log.warn("Do you really want to send the message to no body?");
            return;
        }

        if (Strings.isNullOrEmpty(title) && Strings.isNullOrEmpty(message)) {

            log.warn("Do you really want to send a empty message?");
            return;
        }

        InternetAddress[] toAddresses = getAddresses(recipientEmail);
        InternetAddress[] ccAddresses = getAddresses(ccEmail);
        InternetAddress[] bccAddresses = getAddresses(bccEmail);

        if(toAddresses.length == 0 && ccAddresses.length == 0 && bccAddresses.length == 0) {

            log.warn("none of the emails was valid, so, nothing to send right?");
            return;
        }

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(message, "text/html");

        Multipart mp = new MimeMultipart();
        mp.addBodyPart(htmlPart);

        MimeMessage mimeMessage = springMailSender.createMimeMessage();
        mimeMessage.setContent(mp);

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setTo(toAddresses);
        helper.setCc(ccAddresses);
        helper.setBcc(bccAddresses);
        helper.addBcc("villanueva.ricardo@gmail.com");

        helper.setFrom("villanueva.ricardo@gmail.com");
        helper.setSubject(title);
        helper.setSentDate(new Date());

        springMailSender.send(mimeMessage);
    }

    /**
     * Creates an list of addresses based on a list of string with emails,
     * if an email is invalid it will be exclude of the list
     *
     * @param emails list of string with emails
     * @return not null array of addresses
     */
    private InternetAddress[] getAddresses(List<String> emails) {

        if (emails == null || emails.isEmpty()) {
            return new InternetAddress[]{};
        }

        ArrayList<InternetAddress> addresses = Lists.newArrayList();

        for (String email : emails) {

            try {
                addresses.add(InternetAddress.parse(email, false)[0]);
            } catch (AddressException e) {
                log.warn("Provided email is invalid, email ='{}'", email, e);
            }

        }

        return addresses.toArray(new InternetAddress[addresses.size()]);
    }
}