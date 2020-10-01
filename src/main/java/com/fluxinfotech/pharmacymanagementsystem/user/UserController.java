package com.fluxinfotech.pharmacymanagementsystem.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveOrUpdate(@RequestBody UserDTO payload) {
        UserDTO userDTO = userService.saveOrUpdate(payload);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public Page<UserDTO> getAllUser(Pageable pageable) {
        return userService.getAllUser(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO companyDTO = userService.getById(id);
        return ResponseEntity.ok(companyDTO);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        Boolean value = userService.deleteUser(id);
        return ResponseEntity.ok(value);
    }
}
