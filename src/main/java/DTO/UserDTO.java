package DTO;

import Model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String dateOfBirth;

    public UserDTO(Users user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.gender = user.getGender();
        this.dateOfBirth = user.getDateOfBirth();
    }
    public UserDTO(ResultSet resultSet) throws SQLException {
        this.firstName = resultSet.getString(1);
        this.lastName = resultSet.getString(2);
        this.email = resultSet.getString(3);
        this.password = resultSet.getString(4);
        this.gender = resultSet.getString(5);
        this.dateOfBirth = resultSet.getString(6);
    }
}
