package DAO;

import DTO.UserDTO;
import org.mindrot.jbcrypt.BCrypt;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        if (connection==null||connection.isClosed()){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Okafor.com");
        }
    }
    public void saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        connect();
        String password = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
        String query ="insert into Demo_FB.users (firstname, lastname, email, password, gender, dateofbirth) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,userDTO.getFirstName());
        preparedStatement.setString(2,userDTO.getLastName());
        preparedStatement.setString(3,userDTO.getEmail());
        preparedStatement.setString(4,userDTO.getPassword());
        preparedStatement.setString(5,userDTO.getGender());
        preparedStatement.setString(6,userDTO.getDateOfBirth());

        connection.close();
    }
}
