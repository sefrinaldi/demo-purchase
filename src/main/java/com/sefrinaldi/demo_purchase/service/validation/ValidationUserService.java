package com.sefrinaldi.demo_purchase.service.validation;

import com.sefrinaldi.demo_purchase.entity.Users;
import com.sefrinaldi.demo_purchase.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

/**
 * @Created : 03/10/24 - 00.37
 * @Author : caniago
 */

@Service
@RequestScope
@RequiredArgsConstructor
public class ValidationUserService {

    private final UserRepository userRepository;
    private HashMap<Long, Users> usersHashMap = new HashMap<>();

    public Users getUserById(Long id) throws NotFoundException {
        if (!usersHashMap.containsKey(id)) {
            Optional<Users> stockOptional = userRepository.findById(id);
            if (stockOptional.isEmpty()) {
                throw new NotFoundException("user is not found");
            }
            usersHashMap.put(id, stockOptional.get());
        }
        return usersHashMap.get(id);
    }
}
