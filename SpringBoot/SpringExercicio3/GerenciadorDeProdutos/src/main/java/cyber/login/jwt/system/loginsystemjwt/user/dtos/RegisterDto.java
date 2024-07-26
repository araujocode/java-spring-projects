package cyber.login.jwt.system.loginsystemjwt.user.dtos;

import cyber.login.jwt.system.loginsystemjwt.user.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public class RegisterDto {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private UserRole role;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

}
