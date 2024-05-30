package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.dto.ScopeEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.mapping.ScopeEditCreateMapper;
import com.gta.spring.springboot.junix_opp.entity.Scope;
import com.gta.spring.springboot.junix_opp.entity.User;
import com.gta.spring.springboot.junix_opp.repository.ScopeRepository;
import com.gta.spring.springboot.junix_opp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScopeService {
//    @Autowired
    private final ScopeRepository scopeRepository;
    private final UserRepository userRepository;
    private final ScopeEditCreateMapper scopeEditCreateMapper;

    @Transactional
    public Scope createScope(ScopeEditCreateDTO scopeEditCreateDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        Scope scope = scopeEditCreateMapper.map(scopeEditCreateDTO);
        scope.setUser(user);
        scope.setCreatedDate(LocalDateTime.now());
        return scopeRepository.save(scope);
    }




//    public Post createPost(PostDTO postDTO, Principal principal) {
//        User user = getUserByPrincipal(principal);
//        Post post = new Post();
//        post.setUser(user);
//        post.setCaption(postDTO.getCaption());
//        post.setLocation(postDTO.getLocation());
//        post.setTitle(postDTO.getTitle());
//        post.setLikes(0);
//
//        LOG.info("Saving Post for User: {}", user.getEmail());
//        return postRepository.save(post);
//    }


    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));

    }
}
