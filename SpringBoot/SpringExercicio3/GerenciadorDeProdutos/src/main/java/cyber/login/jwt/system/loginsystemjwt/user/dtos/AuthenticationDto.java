package cyber.login.jwt.system.loginsystemjwt.user.dtos;

public class AuthenticationDto {

    private String email;
    private String password;
    public AuthenticationDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}