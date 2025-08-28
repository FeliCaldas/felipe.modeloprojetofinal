package felipe.modeloprojetofinal.users.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import felipe.modeloprojetofinal.users.dto.UserCreateDTO;
import felipe.modeloprojetofinal.users.dto.UserResponseDTO;
import felipe.modeloprojetofinal.users.entities.User;
import felipe.modeloprojetofinal.users.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidationService validationService;

    @InjectMocks
    private UserCreateService userCreateService;

    @InjectMocks
    private UserQueryService userQueryService;

    @InjectMocks
    private UserDeleteService userDeleteService;

    private User user;
    private UserCreateDTO userCreateDTO;

    @BeforeEach
    void setUp() {
        user = new User("João Silva", "joao@example.com");
        user.setId(1L);
        userCreateDTO = new UserCreateDTO("João Silva", "joao@example.com");
    }

    @Test
    void shouldCreateUserSuccessfully() {
        // Given
        when(userRepository.findByEmailLikeIgnoreCase(anyString())).thenReturn(List.of());
        when(userRepository.save(any(User.class))).thenReturn(user);
        doNothing().when(validationService).validateUserCreate(any(UserCreateDTO.class));

        // When
        UserResponseDTO result = userCreateService.createUser(userCreateDTO);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("João Silva", result.name());
        assertEquals("joao@example.com", result.email());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldFindAllUsers() {
        // Given
        List<User> users = Arrays.asList(user, new User("Maria", "maria@example.com"));
        when(userRepository.findAll()).thenReturn(users);

        // When
        List<UserResponseDTO> result = userQueryService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void shouldDeleteUserSuccessfully() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(any(User.class));

        // When & Then
        assertDoesNotThrow(() -> userDeleteService.deleteUserById(1L));
        verify(userRepository).delete(user);
    }
}