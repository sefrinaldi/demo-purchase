package com.sefrinaldi.demo_purchase.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sefrinaldi.demo_purchase.dto.UserReqDto;
import com.sefrinaldi.demo_purchase.dto.UserRespDto;
import com.sefrinaldi.demo_purchase.entity.Users;
import com.sefrinaldi.demo_purchase.repository.UserRepository;
import com.sefrinaldi.demo_purchase.service.validation.ValidationUserService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Created : 02/10/24 - 15.22
 * @Author : caniago
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ValidationUserService validationUserService;
    private final ObjectMapper objectMapper;

    public UserRespDto createUser(UserReqDto userReqDto) {
        Users users = objectMapper.convertValue(userReqDto, Users.class);

        Users response = userRepository.save(users);

        return objectMapper.convertValue(response, UserRespDto.class);
    }

    public UserRespDto updateUser(UserReqDto userReqDto, Long id) throws NotFoundException {
        Users users = validationUserService.getUserById(id);

        users.setFirstName(userReqDto.getFirstName());
        users.setLastName(userReqDto.getLastName());
        users.setEmail(userReqDto.getEmail());
        users.setPhone(userReqDto.getPhone());

        userRepository.save(users);
        return objectMapper.convertValue(users, UserRespDto.class);
    }

    public UserRespDto getById(Long id) throws NotFoundException {
        Users users = validationUserService.getUserById(id);
        return objectMapper.convertValue(users, UserRespDto.class);
    }

    public void deleteUser(Long id) throws NotFoundException {
        Users users = validationUserService.getUserById(id);
        userRepository.delete(users);
    }

    public Page<Users> getAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
