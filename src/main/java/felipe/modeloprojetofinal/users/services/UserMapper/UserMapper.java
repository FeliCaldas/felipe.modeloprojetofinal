package felipe.modeloprojetofinal.users.services.UserMapper;

import felipe.modeloprojetofinal.users.dto.UserCreateDTO;
import felipe.modeloprojetofinal.users.dto.UserResponseDTO;
import felipe.modeloprojetofinal.users.entities.User;

public class UserMapper {

    public static User toEntity(UserCreateDTO dto) {
        return new User(
                dto.name(),
                dto.email()
        );
    }

    public static UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
