package vn.spring.bookstore.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Builder
public class UserCreationRequest {

    @Size(min = 3, message = "USERNAME_INVALID")
    private String fullName;
    private String email;

    @Size(min = 8, message = "PASWORD_INVALID")
    private String password;

}
