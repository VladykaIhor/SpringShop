package com.service.impl;

import com.entity.User;
import com.repository.UserJpaRepository;
import com.service.UserService;
import com.utils.SaltHashingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserJpaRepository userJpaRepository;
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImp(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Transactional
    @Override
    public void add(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userJpaRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userJpaRepository.findAll();
    }

    @Transactional
    @Override
    public void remove(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Optional<User> getUserByLogin(String login) {
        return userJpaRepository.findByLogin(login);
    }

    @Transactional
    @Override
    public Optional<User> getUserById(Long id) {
        return userJpaRepository.findById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userJpaRepository.save(user);
    }

    @Transactional
    @Override
    public void remove(User user) {
        userJpaRepository.delete(user);
    }

    @Transactional
    @Override
    public Optional<User> authentication(User user) {
        Optional<User> userFromBase = userJpaRepository.findByLogin(user.getLogin());
        if (userFromBase.isPresent()) {
            String saltAndHashPassword = SaltHashingUtil.saltAndHashPassword
                    (user.getPassword(), userFromBase.get().getSalt());
            if (saltAndHashPassword.equals(userFromBase.get().getPassword())) {
                return userFromBase;
            }
        }
        return Optional.empty();
    }
}
