package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.event.EventReadDTO;
import com.gta.spring.springboot.junix_opp.dto.scope.*;
import com.gta.spring.springboot.junix_opp.dto.task.TaskReadDTO;
import com.gta.spring.springboot.junix_opp.entity.*;
import com.gta.spring.springboot.junix_opp.repository.ScopeRepository;
import com.gta.spring.springboot.junix_opp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScopeService {
//    @Autowired
    private final ScopeRepository scopeRepository;
    private final UserRepository userRepository;
    private final ScopeEditCreateMapper scopeEditCreateMapper;
    private final UserService userService;
    private final ScopeReadMapper scopeReadMapper;
    private final ScopeFullReadMapper scopeFullReadMapper;

    @Transactional
    public Scope createScope(ScopeEditCreateDTO scopeEditCreateDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        Scope scope = scopeEditCreateMapper.map(scopeEditCreateDTO);
        scope.setUser(user);
//        scope.setCreatedDate(LocalDateTime.now());
        return scopeRepository.save(scope);
    }

    @Transactional
    public void update(Long scopeId, ScopeEditCreateDTO scopeEditCreateDTO, Principal principal) {
//        User user = userService.getUserByPrincipal(principal); добавить позже в EditUser;
        Scope scope = findScopeById(scopeId);
        scopeEditCreateMapper.map(scopeEditCreateDTO, scope);
        scopeRepository.save(scope);
        //надо тестить
    }

    public Scope findScopeById(Long scopeId){
        return Optional.ofNullable(scopeId)
                .flatMap(scopeRepository::findById)
                .orElse(null);
    }

    @Transactional
    public void deleteScope(Long scopeId) {
        Optional<Scope> scopeOpt = scopeRepository.findById(scopeId);
        scopeOpt.ifPresent(scope -> scopeRepository.delete(scope));
    }

    public List<ScopeReadDTO> findByDrawingId(Long id) {
        return scopeRepository.findAllByDrawingId(id).stream()
                .map(scopeReadMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<ScopeReadDTO> findById(Long id) {
        return scopeRepository.findById(id)
                .map(scopeReadMapper::map);
    }

    public List<ScopeFullReadDTO> findAllScopeFull() {
        return scopeRepository.findAll().stream()
                .map(scopeFullReadMapper::map)
                .collect(Collectors.toList());
    }
}
