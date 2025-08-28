package felipe.modeloprojetofinal.users.services;

import org.springframework.stereotype.Service;
import felipe.modeloprojetofinal.users.dto.UserCreateDTO;
import felipe.modeloprojetofinal.users.dto.UserUpdateEmailDTO;
import felipe.modeloprojetofinal.users.dto.UserUpdateNameDTO;

import java.util.regex.Pattern;

@Service
public class UserValidationService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );

    public void validateUserCreate(UserCreateDTO dto) {
        validateName(dto.name());
        validateEmail(dto.email());
    }

    public void validateUserUpdateName(UserUpdateNameDTO dto) {
        validateName(dto.name());
    }

    public void validateUserUpdateEmail(UserUpdateEmailDTO dto) {
        validateEmail(dto.email());
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (name.trim().length() < 2) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");
        }
        if (name.trim().length() > 100) {
            throw new IllegalArgumentException("Nome deve ter no máximo 100 caracteres");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new IllegalArgumentException("Formato de email inválido");
        }
        if (email.trim().length() > 255) {
            throw new IllegalArgumentException("Email deve ter no máximo 255 caracteres");
        }
    }
}