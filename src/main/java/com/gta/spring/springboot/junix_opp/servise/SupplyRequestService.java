package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.supplyRequest.*;
import com.gta.spring.springboot.junix_opp.entity.SupplyRequest;
import com.gta.spring.springboot.junix_opp.entity.Task;
import com.gta.spring.springboot.junix_opp.entity.User;
import com.gta.spring.springboot.junix_opp.repository.SupplyRequestRepository;
import com.gta.spring.springboot.junix_opp.repository.TaskRepository;
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
    private final TaskRepository taskRepository;
    private final SupplyRequestWithTasksReadMapper supplyRequestWithTasksReadMapper;

@Transactional
public SupplyRequest createSupply(SupplyRequestCreateDTO supplyRequestCreateDTO, Principal principal) {
    User user = userService.getUserByPrincipal(principal);
    SupplyRequest supplyRequest = supplyRequestCreateMapper.map(supplyRequestCreateDTO);
    supplyRequest.setUser(user);
    return supplyRequestRepository.save(supplyRequest);
}

    @Transactional //2
    public void update(Long supplyId, SupplyRequestCreateDTO supplyCreateDTO, Principal principal) {
//        User user = userService.getUserByPrincipal(principal); добавить позже в EditUser;
        SupplyRequest supply = findSupplyRequestById(supplyId);
        supplyRequestCreateMapper.map(supplyCreateDTO, supply);
        supplyRequestRepository.save(supply);
    }

    @Transactional //3
    public void deleteSupplyRequest(Long supplyId) {
        Optional<SupplyRequest> supplyOpt = supplyRequestRepository.findById(supplyId);
        if (supplyOpt.isPresent()) {
            SupplyRequest supply = supplyOpt.get();
            for (Task task : supply.getTasks()) { //удалим связи с task
                task.getSupplies().remove(supply);
                taskRepository.save(task);
            }
            supplyRequestRepository.deleteById(supplyId);
        }
    }

    public Optional<SupplyRequestReadDTO> findById(Long id) {
        return supplyRequestRepository.findById(id)
                .map(supplyRequestReadMapper::map);
    }

    public Optional<SupplyRequestWithTasksReadDTO> findByIdWithTasks(Long id) {
        return supplyRequestRepository.findById(id)
                .map(supplyRequestWithTasksReadMapper::map);
    }

    public SupplyRequest findSupplyRequestById(Long supplyId){
        return Optional.ofNullable(supplyId)
                .flatMap(supplyRequestRepository::findById)
                .orElse(null);
    }

public List<SupplyRequestReadDTO> findByDrawingId(Long id) {
    return supplyRequestRepository.findAllByDrawingId(id).stream()
            .map(supplyRequestReadMapper::map)
            .toList();
}
    public List<SupplyRequestReadDTO> findAll() {
        return supplyRequestRepository.findAll().stream()
                .map(supplyRequestReadMapper::map)
                .toList();
    }

    public List<SupplyRequestWithTasksReadDTO> findByDrawingIdWithTasks(Long id) {
        return supplyRequestRepository.findAllByDrawingId(id).stream()
                .map(supplyRequestWithTasksReadMapper::map)
                .toList();
    }

    @Transactional //13**
    public SupplyRequest createNewSupplyWithTaskId(Long taskId, SupplyRequestCreateDTO supplyRequestCreateDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return taskRepository.findById(taskId).map(task -> {
            SupplyRequest supply = supplyRequestCreateMapper.map(supplyRequestCreateDTO);
            supply.setUser(user);
            supply.getTasks().add(task);
            return supplyRequestRepository.save(supply);
        }).orElse(null); // или выбросить исключение
    }


}

