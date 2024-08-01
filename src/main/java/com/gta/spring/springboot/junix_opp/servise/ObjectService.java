package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.entity.Object;
import com.gta.spring.springboot.junix_opp.repository.ObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObjectService {


    private final ObjectRepository objectRepository;



    public Object findObjectById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(objectRepository::findById)
                .orElse(null);
    }


}
