package com.ricardovz.api.persistence;

import com.ricardovz.api.vo.ReceiptVO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository used to manage Receipt persistence
 * <p/>
 * Created by ricardo on 03/05/2015.
 */
public interface ReceiptRepository extends MongoRepository<ReceiptVO, String> {

    /**
     * Find all the receipts which match the deleted parameter and ordered by Date in descended order
     *
     * @param isDeleted filter to apply
     * @return Descended list of receipt that matched the filter
     */
    List<ReceiptVO> findByDeletedInOrderByDateDesc(Boolean... isDeleted);
}
