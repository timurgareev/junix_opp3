package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.typeOfWork.TypeOfWorkReadDTO;
import com.gta.spring.springboot.junix_opp.dto.typeOfWork.TypeOfWorkReadMapper;
import com.gta.spring.springboot.junix_opp.entity.TypeOfWork;
import com.gta.spring.springboot.junix_opp.repository.TypeOfWorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeOfWorkService {

    private final TypeOfWorkRepository typeOfWorkRepository;
    private final TypeOfWorkReadMapper typeOfWorkReadMapper;

    public List<TypeOfWorkReadDTO> findAll(){
        return typeOfWorkRepository.findAll().stream()
                .map(typeOfWorkReadMapper::map)
                .toList();
    }
}
