package br.com.darioklein.ecopass.controller;

import br.com.darioklein.ecopass.domain.dto.userDTO.*;
import br.com.darioklein.ecopass.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
        List<UserResponseDTO> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/by-pending-balance")
    public ResponseEntity<List<UserResponseDTO>> findAllUsersByDescPendingBalance() {
        List<UserResponseDTO> userList = userService.findAllByDescPendingBalance();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/by-birth-date")
    public ResponseEntity<List<UserResponseDTO>> findAllUsersByBirthDate() {
        List<UserResponseDTO> userList = userService.findAllByBirthDate();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("pending-balance-between")
    public ResponseEntity<List<UserResponseDTO>> findByUserBetweenPendingBalance(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        List<UserResponseDTO> userList = userService.findByBetweenPendingBalance(min, max);
        return ResponseEntity.ok(userList);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO dto) {
        UserResponseDTO user = userService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto) {
        UserResponseDTO user = userService.update(id, dto);
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Void> updatePartialUser(@PathVariable Long id, @Valid @RequestBody UserPatchDTO dto) {
        userService.updatePartial(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/password/{id}")
    public ResponseEntity<Void> updateUserPassword(@PathVariable Long id, @Valid @RequestBody UserUpdatePasswordDTO dto) {
        userService.updatePassword(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
