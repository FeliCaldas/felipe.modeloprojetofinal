package felipe.modeloprojetofinal.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import felipe.modeloprojetofinal.users.dto.UserCreateDTO;
import felipe.modeloprojetofinal.users.dto.UserResponseDTO;
import felipe.modeloprojetofinal.users.dto.UserUpdateEmailDTO;
import felipe.modeloprojetofinal.users.dto.UserUpdateNameDTO;
import seunomeaqui.modeloprojetofinal.users.services.UserCreateService;
import felipe.modeloprojetofinal.users.services.UserDeleteService;
import felipe.modeloprojetofinal.users.services.UserQueryService;
import felipe.modeloprojetofinal.users.services.UserUpdateService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*") // Para desenvolvimento - remova em produção
public class UserController {

    private final UserCreateService userCreateService;
    private final UserQueryService userQueryService;
    private final UserUpdateService userUpdateService;
    private final UserDeleteService userDeleteService;

    @GetMapping("/find-all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userQueryService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        // Este método precisa ser implementado no UserQueryService
        return ResponseEntity.ok().build(); // Placeholder
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<List<UserResponseDTO>> findUsersByName(@RequestParam String name) {
        List<UserResponseDTO> users = userQueryService.findAllByUsername(name);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/find-by-email")
    public ResponseEntity<List<UserResponseDTO>> findUsersByEmail(@RequestParam String email) {
        List<UserResponseDTO> users = userQueryService.findAllByEmail(email);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        UserResponseDTO createdUser = userCreateService.createUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
                                                      @RequestBody UserCreateDTO userUpdateDTO) {
        // Implementar método completo de update
        return ResponseEntity.ok().build(); // Placeholder
    }

    @PatchMapping("/{id}/update-name")
    public ResponseEntity<Void> updateUserName(@PathVariable Long id,
                                               @RequestBody UserUpdateNameDTO dto) {
        userUpdateService.updateName(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/update-email")
    public ResponseEntity<Void> updateUserEmail(@PathVariable Long id,
                                                @RequestBody UserUpdateEmailDTO dto) {
        userUpdateService.updateEmail(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userDeleteService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}