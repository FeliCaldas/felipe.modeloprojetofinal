package felipe.modeloprojetofinal.users.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import felipe.modeloprojetofinal.users.dto.UserUpdateEmailDTO;
import felipe.modeloprojetofinal.users.dto.UserUpdateNameDTO;
import felipe.modeloprojetofinal.users.entities.User;
import felipe.modeloprojetofinal.users.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserRepository userRepository;

    public void updateEmail(Long id, UserUpdateEmailDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setEmail(dto.email());
        userRepository.save(user);
    }

    public void updateName(Long id, UserUpdateNameDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found"));
        user.setName(dto.name());
        userRepository.save(user);
    }
}
