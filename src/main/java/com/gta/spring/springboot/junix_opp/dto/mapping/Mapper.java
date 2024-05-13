package com.gta.spring.springboot.junix_opp.dto.mapping;

public interface Mapper<F, T> {

    T map(F object);

    default T map(F fromObject, T toObject) {
        return toObject;
    }
}
