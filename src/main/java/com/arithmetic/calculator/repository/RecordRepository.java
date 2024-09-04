package com.arithmetic.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arithmetic.calculator.model.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {
}