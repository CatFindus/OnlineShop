package ru.puchinets.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.puchinets.userservice.mapper.UserMapper;
import ru.puchinets.userservice.model.dto.request.UserRequest;
import ru.puchinets.userservice.model.dto.response.UserResponse;
import ru.puchinets.userservice.model.entity.User;
import ru.puchinets.userservice.repository.UserRepository;
import ru.puchinets.userservice.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public Optional<UserResponse> getUserById(Long id) {
        return repository.findById(id)
                .map(mapper::entityToDto);
    }

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    @Override
    @Transactional
    public boolean authenticateUser(String username, String passwordHash) {
        return repository.findByUsername(username)
                .map(user -> {
                    boolean authenticated = user.getPasswordHash().equals(passwordHash);
                    if (authenticated) {
                        user.setLastLogin(LocalDateTime.now());
                        repository.saveAndFlush(user);
                    }
                    return authenticated;
                })
                .orElse(false);
    }

    @Transactional
    @Override
    public UserResponse registerUser(UserRequest request) {
        User user = mapper.dtoToEntity(request);
        user = repository.saveAndFlush(user);
        return mapper.entityToDto(user);
    }

    @Transactional
    @Override
    public Optional<UserResponse> update(Long id, UserRequest request) {
        var mayBeUser = repository.findById(id);
        return mayBeUser
                .map(user -> {
                    User updated = mapper.update(user, request);
                    repository.saveAndFlush(updated);
                    return updated;
                })
                .map(mapper::entityToDto);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        var mayBeUser = repository.findById(id);
        mayBeUser.ifPresent(repository::delete);
        return mayBeUser.isPresent();
    }
}
