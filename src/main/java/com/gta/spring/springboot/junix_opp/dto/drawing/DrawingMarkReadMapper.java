//package com.gta.spring.springboot.junix_opp.dto.drawing;
//
//import com.gta.spring.springboot.junix_opp.dto.Mapper;
//import com.gta.spring.springboot.junix_opp.dto.mark.MarkReadMapper;
//import com.gta.spring.springboot.junix_opp.dto.mark.MarkReadDTO;
//import com.gta.spring.springboot.junix_opp.entity.DrawingsMark;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//public class DrawingMarkReadMapper implements Mapper<DrawingsMark, DrawingMarkReadDTO> {
//    @Autowired
//    private MarkReadMapper markReadMapper ;
//
//    @Override
//    public DrawingMarkReadDTO map(DrawingsMark object) {
//        MarkReadDTO markReadDTO = Optional.ofNullable(object.getMark())
//                .map(markReadMapper::map)
//                .orElse(null);
//        return new DrawingMarkReadDTO(
//                object.getId(),
//                object.getMarkdrawing(),
//                object.getMarkkey(),
//                markReadDTO
//        );
//    }
//
//    не нужно
//}
//
//
//
