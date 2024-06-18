package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.supplyRequest.SupplyRequestCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.supplyRequest.SupplyRequestCreateMapper;
import com.gta.spring.springboot.junix_opp.dto.supplyRequest.SupplyRequestReadDTO;
import com.gta.spring.springboot.junix_opp.dto.supplyRequest.SupplyRequestReadMapper;
import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskReadDTO;
import com.gta.spring.springboot.junix_opp.entity.SupplyRequest;
import com.gta.spring.springboot.junix_opp.entity.Task;
import com.gta.spring.springboot.junix_opp.entity.User;
import com.gta.spring.springboot.junix_opp.repository.SupplyRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplyRequestService {

    private final UserService userService;
    private final SupplyRequestReadMapper supplyRequestReadMapper;
    private final SupplyRequestCreateMapper supplyRequestCreateMapper;
    private final SupplyRequestRepository supplyRequestRepository;

@Transactional
public SupplyRequest createSupplyRequest(SupplyRequestCreateDTO supplyRequestCreateDTO, Principal principal) {
    User user = userService.getUserByPrincipal(principal);
    SupplyRequest supplyRequest = supplyRequestCreateMapper.map(supplyRequestCreateDTO);
    supplyRequest.setUser(user);
    return supplyRequestRepository.save(supplyRequest);
}

public Optional<SupplyRequestReadDTO> findById(Long id) {
    return supplyRequestRepository.findById(id)
            .map(supplyRequestReadMapper::map);
}
//    findByDrawingID
public List<SupplyRequestReadDTO> findByDrawingId(Long id) {
    return supplyRequestRepository.findAllByDrawingId(id).stream()
            .map(supplyRequestReadMapper::map)
            .toList();
}
//    delete
//    update

//    findByIdWithTasks
//    findByDrawingIdWithTasks
//    createSupplyWithNewTasks


}

