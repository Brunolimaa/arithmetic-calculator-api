package com.arithmetic.calculator.service.impl;

import com.arithmetic.calculator.dto.response.RecordDTO;
import com.arithmetic.calculator.repository.RecordRepository;
import com.arithmetic.calculator.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.arithmetic.calculator.model.Record;
import com.arithmetic.calculator.mapper.RecordMapper;

import java.time.LocalDateTime;

@Service
public class RecordServiceImpl implements RecordService {

    private final RecordMapper recordMapper = RecordMapper.INSTANCE;


    @Autowired
    private RecordRepository recordRepository;

    @Override
    public void saveRecord(Record record) {
        record.setDate(LocalDateTime.now());
        recordRepository.save(record);
    }

    @Override
    public Page<RecordDTO> getAllOperations(Pageable pageable) {
        Page<Record> recordsPage = recordRepository.findAll(pageable);
        return recordsPage.map(recordMapper::recordToRecordDTO);
    }
}
