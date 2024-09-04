package com.arithmetic.calculator.mapper;

import com.arithmetic.calculator.dto.response.OperationDTO;
import com.arithmetic.calculator.dto.response.RecordDTO;
import com.arithmetic.calculator.dto.response.UserDTO;
import com.arithmetic.calculator.model.Operation;
import com.arithmetic.calculator.model.User;
import com.arithmetic.calculator.model.Record;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecordMapper {

    RecordMapper INSTANCE = Mappers.getMapper(RecordMapper.class);

    RecordDTO recordToRecordDTO(Record record);

    OperationDTO operationToOperationDTO(Operation operation);

    UserDTO userToUserDTO(User user);
}
