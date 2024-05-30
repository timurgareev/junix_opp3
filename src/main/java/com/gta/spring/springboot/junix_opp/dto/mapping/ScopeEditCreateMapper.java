package com.gta.spring.springboot.junix_opp.dto.mapping;

import com.gta.spring.springboot.junix_opp.dto.dto.ScopeEditCreateDTO;
import com.gta.spring.springboot.junix_opp.entity.*;
import com.gta.spring.springboot.junix_opp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ScopeEditCreateMapper implements Mapper<ScopeEditCreateDTO, Scope> {

private final DrawingRepository drawingRepository;
private final RevisionRepository revisionRepository;
private final TypeOfWorkRepository typeOfWorkRepository;
private final UnitRepository unitRepository;
private  final UserRepository userRepository;


    @Override
    public Scope map(ScopeEditCreateDTO fromObject, Scope toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Scope map(ScopeEditCreateDTO object) {
        Scope scope = new Scope();
        copy(object,scope);

        return scope;
    }

    private void copy(ScopeEditCreateDTO object, Scope scope){
        scope.setName(object.getName());
        scope.setDrawing(getDrawing(object.getDrawingId()));
        scope.setRevision(getRevision(object.getRevisionId()));
        scope.setTypeOfWork(getTypeOfWork(object.getTypeOfWorkId()));
        scope.setUnit(getUnit(object.getUnitId()));
        scope.setQuantity(object.getQuantity());
        scope.setComment(object.getComment());
//        scope.setUser(getUser(object.getUsername()));
    }

    public Drawing getDrawing(Long drawingId){
        return Optional.ofNullable(drawingId)
                .flatMap(drawingRepository::findById)
                .orElse(null);
    }

    public Revision getRevision(Long revisionId){
        return Optional.ofNullable(revisionId)
                .flatMap(revisionRepository::findById)
                .orElse(null);
    }

    public TypeOfWork getTypeOfWork(Integer typeOfWorkId){
        return Optional.ofNullable(typeOfWorkId)
                .flatMap(typeOfWorkRepository::findById)
                .orElse(null);
    }
    public Unit getUnit(Integer unitId){
        return Optional.ofNullable(unitId)
                .flatMap(unitRepository::findById)
                .orElse(null);
    }

//    public User getUser(String username){
//        return Optional.ofNullable(username)
//                .flatMap(userRepository::findUserByUsername)
//                .orElse(null);
//    }
}
