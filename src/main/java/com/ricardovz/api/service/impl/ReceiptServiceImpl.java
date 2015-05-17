package com.ricardovz.api.service.impl;

import com.google.common.base.Strings;
import com.ricardovz.api.model.Receipt;
import com.ricardovz.api.model.User;
import com.ricardovz.api.persistence.ReceiptRepository;
import com.ricardovz.api.persistence.UserRepository;
import com.ricardovz.api.service.PersistenceService;
import com.ricardovz.api.vo.ReceiptVO;
import com.ricardovz.api.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Group all the available operations for receipts
 * <p/>
 * Created by ricardo on 03/05/2015.
 */
@Slf4j
@Service
public class ReceiptServiceImpl implements PersistenceService<Receipt> {

    /**
     * Receipt repository
     */
    private ReceiptRepository receiptRepository;
    /**
     * User repository
     */
    private UserRepository userRepository;

    /**
     * Builds an instance of the current service based on the provided services
     *
     * @param receiptRepository Receipt repository
     * @param userRepository    User repository
     */
    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository, UserRepository userRepository) {
        this.receiptRepository = receiptRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Receipt save(Receipt receipt) {

        log.debug("Saving receipt");

        if (Strings.isNullOrEmpty(receipt.getId())) {
            receipt.setId(new ObjectId().toHexString());
        }

        String newImagePath = moveImage(receipt.getImageFile(), receipt.getId());

        ReceiptVO receiptVO = new ReceiptVO(receipt.getId(), receipt.getUserId(), receipt.getAmount(),
                receipt.getDate(), newImagePath, receipt.isDeleted());

        receipt.getImageFile();

        return Receipt.fromVO(receiptRepository.save(receiptVO));
    }

    @Override
    public List<Receipt> list() {

        log.debug("Getting list of all receipts ordered by date");

        List<ReceiptVO> receiptVOs = receiptRepository.findByDeletedInOrderByDateDesc(null, false);
        if (receiptVOs == null) {
            return null;
        }

        List<Receipt> receipts = new ArrayList<>(receiptVOs.size());

        for (ReceiptVO receiptVO : receiptVOs) {

            Receipt receipt = Receipt.fromVO(receiptVO);
            UserVO userVO = userRepository.findOne(receipt.getUserId());
            receipt.setUser(User.fromVO(userVO));
            receipts.add(receipt);

        }

        return receipts;
    }

    @Override
    public Receipt get(String id) {

        log.debug("Getting a single receipt for the id '{}'", id);

        ReceiptVO receiptVO = receiptRepository.findOne(id);

        Receipt receipt = Receipt.fromVO(receiptVO);
        UserVO userVO = userRepository.findOne(receipt.getUserId());
        receipt.setUser(User.fromVO(userVO));

        return receipt;
    }

    @Override
    public Receipt delete(String id) {

        log.debug("Marking Receipt '{}' as deleted", id);

        ReceiptVO receiptVO = receiptRepository.findOne(id);
        if (receiptVO == null) {
            return null;
        }

        Receipt receipt = Receipt.fromVO(receiptVO);
        receipt.setDeleted(true);

        return save(receipt);
    }

    /**
     * Move image to a directory related with the collection
     *
     * @param imageFile File with the image
     * @return String with the value of the new path for the file or null if the provided file is null or does not exists
     */
    private String moveImage(File imageFile, String id) {

        if (imageFile == null || !imageFile.exists()) {
            return null;
        }

        Path target = Paths.get("fs", "receipts", id);
        target.toFile().mkdirs();
        try {


            String path = Files.move(imageFile.toPath(), target, StandardCopyOption.REPLACE_EXISTING).toAbsolutePath().toString();
            log.debug("The image have been moved to the path '{}'", path);
            return path;

        } catch (IOException e) {

            log.error("An error occurred trying to move image from '{}' to '{}'",
                    imageFile.toPath(), target, e);

            return null;

        }
    }
}
