package com.ricardovz.api.model;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import com.ricardovz.api.dto.InformationMessageDTO;
import com.ricardovz.api.util.SpelParser;
import com.ricardovz.api.util.VzMailSender;
import com.ricardovz.api.vo.SubscriberVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;

/**
 * Represents a Subscriber object
 * <p/>
 * Created by ricardo on 04/05/2015.
 */
@Data
@Slf4j
public class Subscriber {

    /**
     * Identification of the subscriber
     */
    private String id;

    /**
     * Name of the subscriber
     */
    private String email;

    /**
     * Logically deleted
     */
    private boolean isDeleted;

    /**
     * Transforms VO object into a model object
     *
     * @param subscriberVO VO source object
     * @return {@link Subscriber} instance representation of the provided DTO, if the provided VO is null it returns null
     */
    public static Subscriber fromVO(SubscriberVO subscriberVO) {

        if (subscriberVO == null) {
            return null;
        }

        Subscriber subscriber = new Subscriber();
        subscriber.setId(subscriberVO.getId());
        subscriber.setEmail(subscriberVO.getEmail());

        return subscriber;
    }

    public boolean sendWelcomeMessage(VzMailSender vzMailSender) {

        if (Strings.isNullOrEmpty(email)) {
            return false;
        }

        // build the message context
        InformationMessageDTO informationMessage = new InformationMessageDTO();
        informationMessage.setCalendar(Calendar.getInstance());
        informationMessage.setSubject("Welcome");
        informationMessage.setMessageTeaser("Thanks for follow me");
        informationMessage.setTitle("Welcome!");
        informationMessage.setSubtitle("You have just subscribed to my page site updates");
        informationMessage.setMessageBody("I Wanted to say thank you for following my messages.");
        informationMessage.setUnsubscribeUrl("http://www.ricardovz.com/unsubscribe");
        informationMessage.setUrl("http://www.ricardovz.com");

        String template;
        ClassPathResource classPathResource = new ClassPathResource("mail-templates/information.html");
        try {

            InputStreamReader inputStreamReader = new InputStreamReader(classPathResource.getInputStream(), Charsets.UTF_8);
            template = CharStreams.toString(inputStreamReader);

        } catch (IOException e) {
            log.warn("Error reading the mail template for new subscribers: template file = '{}'", classPathResource.getPath(), e);
            return false;
        }


        SpelParser spelParser = new SpelParser();
        String message = spelParser.parse(template, informationMessage);

        if (message == null) {
            return false;
        }

        // send email
        List<String> bcc = Lists.newArrayList(email);

        try {

            vzMailSender.send(informationMessage.getSubject(), message, bcc);

        } catch (MessagingException e) {
            log.warn("It was not possible to deliver to '{}', the message:\n{}", email, message, e);
            return false;
        }

        return true;

    }
}
