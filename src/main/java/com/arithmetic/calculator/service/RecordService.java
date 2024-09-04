package com.arithmetic.calculator.service;

import com.arithmetic.calculator.dto.response.RecordDTO;
import com.arithmetic.calculator.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.arithmetic.calculator.model.Record;
import com.arithmetic.calculator.mapper.RecordMapper;

import java.time.LocalDateTime;

@Service
public class RecordService {

    private final RecordMapper recordMapper = RecordMapper.INSTANCE;


    @Autowired
    private RecordRepository recordRepository;

    public void saveRecord(Record record) {
        record.setDate(LocalDateTime.now());
        recordRepository.save(record);
    }

    public Page<RecordDTO> getAllOperations(Pageable pageable) {
        Page<Record> recordsPage = recordRepository.findAll(pageable);
        return recordsPage.map(recordMapper::recordToRecordDTO);
    }
}
