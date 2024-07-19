package com.example.exercice_todolist_03.service;

import com.example.exercice_todolist_03.config.jwt.JwtTokenProvider;
import com.example.exercice_todolist_03.dto.user.UserDtoGet;
import com.example.exercice_todolist_03.dto.user.UserDtoPost;
import com.example.exercice_todolist_03.entity.Role;
import com.example.exercice_todolist_03.entity.User;
import com.example.exercice_todolist_03.exception.NotFoundException;
import com.example.exercice_todolist_03.repository.RoleRepository;
import com.example.exercice_todolist_03.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements BaseService<UserDtoGet, User>, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public boolean deleteRole(int id) {
        Role role = roleRepository.findById(id).orElseThrow(NotFoundException::new);
        roleRepository.delete(role);
        return true;
    }

    public Role findRoleByGrade(String grade) {
        return roleRepository.findByGrade(grade).orElseThrow(NotFoundException::new);
    }

    public boolean createUser(UserDtoPost userDtoPost) {
        User user = userDtoPost.convertToUser();
        user.setRole(findRoleByGrade("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(userDtoPost.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean createAdmin(UserDtoPost userDtoPost) {
        User user = userDtoPost.convertToUser();
        user.setRole(findRoleByGrade("ROLE_ADMIN"));
        user.setPassword(passwordEncoder.encode(userDtoPost.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDtoGet update(int id, User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public UserDtoGet findById(int id) {
        return null;
    }

    @Override
    public List<UserDtoGet> findAll() {
        return List.of();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    public boolean verifyUser(String email, String password) {
        return userRepository.findByEmail(email).map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean checkUserNameExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public String generateToken(String email, String password, int id) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication, id);

        return token;
    }

    public int getIdByEmail(String email){
        return userRepository.findByEmail(email).get().getId();
    }
}
