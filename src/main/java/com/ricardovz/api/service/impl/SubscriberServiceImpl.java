package com.ricardovz.api.service.impl;

import com.ricardovz.api.model.Subscriber;
import com.ricardovz.api.persistence.SubscriberRepository;
import com.ricardovz.api.service.PersistenceService;
import com.ricardovz.api.util.VzMailSender;
import com.ricardovz.api.vo.SubscriberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Serves the available operation over the subscriptors instances
 *
 * Created by ricardo on 04/05/2015.
 */
@Slf4j
@Service
public class SubscriberServiceImpl implements PersistenceService<Subscriber> {

    /**
     * Subscriber repository service
     */
    private SubscriberRepository subscriberRepository;

    /**
     * Mail service
     */
    private VzMailSender mailSender;

    /**
     * Build a instance of the service setting the fields with the given parameters
     * @param subscriberRepository User repository service
     */
    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository, VzMailSender mailSender) {
        this.subscriberRepository = subscriberRepository;
        this.mailSender = mailSender;
    }

    @Override
    public Subscriber save(Subscriber subscriber) {

        if (subscriber == null) {
            return null;
        }

        if(subscriberRepository.findByEmail(subscriber.getEmail()) == null) {
            return null;
        }

        SubscriberVO subscriberVO = new SubscriberVO(subscriber.getId(), subscriber.getEmail(), subscriber.isDeleted());
        subscriberVO = subscriberRepository.save(subscriberVO);

        Subscriber persistedSubscriber = Subscriber.fromVO(subscriberVO);
        persistedSubscriber.sendWelcomeMessage(mailSender);

        return subscriber;
    }

    @Override
    public List<Subscriber> list() {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<SubscriberVO> subscriberVOs = subscriberRepository.findAll(sort);

        List<Subscriber> subscribers = new ArrayList<>(subscriberVOs.size());

        for (SubscriberVO subscriberVO : subscriberVOs) {

            subscribers.add(Subscriber.fromVO(subscriberVO));

        }
        return subscribers;
    }

    @Override
    public Subscriber get(String id) {

        SubscriberVO subscriberVO = subscriberRepository.findOne(id);

        return Subscriber.fromVO(subscriberVO);
    }

    @Override
    public Subscriber delete(String id) {

        log.debug("Marking subscriber '{}' as deleted", id);

        SubscriberVO subscriberVO = subscriberRepository.findOne(id);
        if (subscriberVO == null) {
            return null;
        }

        Subscriber subscriber = Subscriber.fromVO(subscriberVO);
        subscriber.setDeleted(true);

        return save(subscriber);
    }
}
