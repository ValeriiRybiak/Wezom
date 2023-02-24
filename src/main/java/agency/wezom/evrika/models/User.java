package agency.wezom.evrika.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private AccountType accountType;
    private String name;
    private String lastName;
    private String middleName;
    private String phone;
    private String email;
    private String password;
}
