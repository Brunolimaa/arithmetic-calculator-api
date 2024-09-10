package com.arithmetic.calculator.service;

import com.arithmetic.calculator.dto.response.RecordDTO;
import com.arithmetic.calculator.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordService {

    Page<RecordDTO> getAllOperations(Pageable pageable);

    void saveRecord(Record record);

}
