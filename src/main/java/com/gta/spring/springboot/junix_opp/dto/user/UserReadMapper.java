package com.gta.spring.springboot.junix_opp.dto.user;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.EventType;
import com.gta.spring.springboot.junix_opp.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDTO> {
    @Override
    public UserReadDTO map(User object) {
        return new UserReadDTO(
                object.getId(),
                object.getUsername()
        );
    }
}
