package com.ricardovz.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.ricardovz.api.dto.ReceiptDTO;
import com.ricardovz.api.model.Receipt;
import com.ricardovz.api.service.PersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages all the requests related with the register of shopping receipts
 */
@Slf4j
@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    /**
     * Number suffix used to create a unique tempfile per attempt
     */
    private static int tempFileSuffix = 1;

    /**
     * Temp file name
     */
    private static final String TEMP_FILE_NAME = "tempReceipt";

    /**
     * Receipt service
     */
    private PersistenceService<Receipt> receiptService;

    /**
     * Builds a {@link ReceiptController} instance based on the provided services
     *
     * @param receiptService Receipt service to be used
     */
    @Autowired
    public ReceiptController(PersistenceService<Receipt> receiptService) {
        this.receiptService = receiptService;
    }

    /**
     * Saves receipt details
     *
     * @param image       picture of the receipt
     * @param receiptData JSON representation of the receipt data
     * @return result message
     */
    @RequestMapping(method = RequestMethod.POST)
    public ReceiptDTO save(@RequestParam(required = false) String image, @RequestParam String receiptData) throws IOException {

        log.debug("Attempt to save receipt data '{}'", receiptData);

        ObjectMapper mapper = new ObjectMapper();
        ReceiptDTO receiptDTO = mapper.readValue(receiptData, ReceiptDTO.class);

        Receipt receipt = Receipt.fromDTO(receiptDTO);
        receipt.setImageFile(saveMultiPartFileLocally(image));

        return getReceiptDTO(receiptService.save(receipt));
    }

    /**
     * Returns a List of the current Receipts on the system
     *
     * @return {@link List} of {@link ReceiptDTO} instances
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<ReceiptDTO> list() {

        log.debug("Attempt to get list of receipts");

        List<Receipt> receipts = receiptService.list();
        List<ReceiptDTO> receiptDTOs = new ArrayList<>(receipts.size());

        for (Receipt receipt : receipts) {
            receiptDTOs.add(getReceiptDTO(receipt));
        }

        return receiptDTOs;
    }

    /**
     * Returns the image related with a given receipt id
     *
     * @param response response with the image
     * @param receiptId identification String of the receipt
     * @throws IOException if an error occurred copying the file from server to the client
     */
    @RequestMapping("/{receiptId}/image")
    public void getReceiptImage(HttpServletResponse response, @PathVariable String receiptId) throws IOException {

        log.debug("Attempt to get the receipt image for the receipt id '{}'", receiptId);

        Receipt receipt = receiptService.get(receiptId);
        if (receipt == null || Strings.isNullOrEmpty(receipt.getImagePath())) {
            return;
        }

        IOUtils.copy(new FileInputStream(receipt.getImageFile()), response.getOutputStream());
        response.flushBuffer();
    }


    /**
     * Returns the image related with a given receipt id
     *
     * @param receiptId identification String of the receipt
     */
    @RequestMapping(value = "/{receiptId}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String receiptId) {

        log.debug("Attempt to delete receipt with id '{}'", receiptId);

        if (Strings.isNullOrEmpty(receiptId)) {
            return false;
        }

        receiptService.delete(receiptId);
        return true;
    }


    /**
     * Saves a file a temporary file locally
     *
     * @param base64String {@link String} containing the base64 encoded file
     * @return {@link File} instance representing the saved file
     */
    private File saveMultiPartFileLocally(String base64String) {

        if (base64String == null || base64String.isEmpty()) {
            return null;
        }

        String fileName = TEMP_FILE_NAME + tempFileSuffix++;
        File file = new File(fileName);

        try {

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));

            int startingIndex = base64String.indexOf(",") + 1;
            String code = base64String.substring(startingIndex);

            byte[] bytes = Base64Utils.decodeFromString(code);

            stream.write(bytes);
            stream.close();

            log.trace("Temp file '{}' successfully saved", fileName);

            return file;

        } catch (Exception e) {
            log.warn("Failed attempt to save the temp file '{}'", fileName, e);
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Coverts a model object into a Data transfer object
     *
     * @param receipt Model Object
     * @return DTO representation of the model object
     */
    private ReceiptDTO getReceiptDTO(Receipt receipt) {
        if (receipt == null) {
            return null;
        }
        ReceiptDTO receiptDTO = new ReceiptDTO();

        receiptDTO.setId(receipt.getId());
        receiptDTO.setUserId(receipt.getUserId());
        receiptDTO.setDate(LocalDate.fromDateFields(receipt.getDate()).toString());
        receiptDTO.setAmount(receipt.getAmount());
        receiptDTO.setUserName(receipt.getUser().getName());
        receiptDTO.setHasImage(!Strings.isNullOrEmpty(receipt.getImagePath()));

        return receiptDTO;
    }
}
