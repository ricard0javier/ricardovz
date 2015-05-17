package com.ricardovz.api.model;

import com.google.common.base.Strings;
import com.ricardovz.api.dto.ReceiptDTO;
import com.ricardovz.api.vo.ReceiptVO;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.Date;

/**
 * Receipt model
 * <p/>
 * Created by ricardo on 27/04/2015.
 */
@Data
public class Receipt {

    /**
     * Unique identifier of the object
     */
    private String id;

    /**
     * User that created the receipt
     */
    private User user;

    /**
     * Receipt amount
     */
    private Double amount;

    /**
     * Receipt Date
     */
    private Date date;

    /**
     * Image File
     */
    private File imageFile;

    /**
     * Logically Deleted
     */
    private boolean deleted;

    /**
     * Returns the image path, it returns null when the image file is null
     */
    public String getImagePath() {
        if (imageFile == null) {
            return null;
        }
        return imageFile.getPath();
    }

    /**
     * Returns the userID, it returns null when the user is null
     */
    public String getUserId() {
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    /**
     * Transforms DTO object into a model object
     *
     * @param receiptDTO DTO source object
     * @return {@link Receipt} instance representation of the provided DTO, if the provided DTO is null it returns null
     */
    public static Receipt fromDTO(ReceiptDTO receiptDTO) {

        if (receiptDTO == null) {
            return null;
        }

        Receipt receipt = new Receipt();
        receipt.setUser(User.createUser(receiptDTO.getUserId()));
        receipt.setAmount(receiptDTO.getAmount());
        receipt.setDate(DateTime.parse(receiptDTO.getDate()).toDate());

        return receipt;
    }

    /**
     * Transforms VO object into a model object
     *
     * @param receiptVO VO source object
     * @return {@link Receipt} instance representation of the provided DTO, if the provided VO is null it returns null
     */
    public static Receipt fromVO(ReceiptVO receiptVO) {

        if (receiptVO == null) {
            return null;
        }

        Receipt receipt = new Receipt();
        receipt.setId(receiptVO.getId());
        receipt.setUser(User.createUser(receiptVO.getUserId()));
        receipt.setAmount(receiptVO.getAmount());
        receipt.setDate(receiptVO.getDate());

        if (!Strings.isNullOrEmpty(receiptVO.getImagePath())) {
            FileSystemResource fileSystemResource = new FileSystemResource(receiptVO.getImagePath());
            receipt.setImageFile(fileSystemResource.getFile());
        }

        return receipt;
    }
}
