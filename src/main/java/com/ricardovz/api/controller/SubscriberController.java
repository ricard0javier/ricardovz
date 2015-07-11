package com.ricardovz.api.controller;

import com.ricardovz.api.model.Subscriber;
import com.ricardovz.api.service.PersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * User Subscription Controller service
 * Created by ricardo on 05/07/2015.
 */
@Slf4j
@RestController
@RequestMapping("/subscribe")
public class SubscriberController {

    /**
     * Receipt service
     */
    private PersistenceService<Subscriber> subscriberService;

    /**
     * Builds a {@link SubscriberController} instance based on the provided services
     *
     * @param subscriberService subscriber service to be used
     */
    @Autowired
    public SubscriberController(PersistenceService<Subscriber> subscriberService) {
        this.subscriberService = subscriberService;
    }

    /**
     * Saves user details
     *
     * @param email email to subscribe
     */
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody String email) {

        log.debug("Subscribing email '{}'", email);

        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(email);

        subscriberService.save(subscriber);
    }
}
