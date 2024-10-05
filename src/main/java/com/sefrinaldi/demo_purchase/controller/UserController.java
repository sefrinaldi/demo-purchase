package com.sefrinaldi.demo_purchase.controller;

import com.sefrinaldi.demo_purchase.dto.UserReqDto;
import com.sefrinaldi.demo_purchase.dto.UserRespDto;
import com.sefrinaldi.demo_purchase.entity.Users;
import com.sefrinaldi.demo_purchase.service.UserService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Created : 02/10/24 - 15.20
 * @Author : caniago
 */

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<UserRespDto> createUser(@RequestBody UserReqDto userReqDto) {
        return ResponseEntity.ok(userService.createUser(userReqDto));
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<UserRespDto> updateUser(
            @PathVariable(value = "id") Long id,
            @RequestBody UserReqDto userReqDto) throws NotFoundException {
        return ResponseEntity.ok(userService.updateUser(userReqDto, id));
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<UserRespDto> getUserById(@PathVariable(value = "id") Long id) throws NotFoundException {
        return ResponseEntity.ofNullable(userService.getById(id));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) throws NotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-user-all")
    public ResponseEntity<Page<Users>> getAllUser(Pageable pageable) {
        return ResponseEntity.ofNullable(userService.getAllUser(pageable));
    }
}
