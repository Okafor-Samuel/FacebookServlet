package DAO;

import DTO.UserDTO;
import org.mindrot.jbcrypt.BCrypt;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static Connection connection;
    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        if (connection==null||connection.isClosed()){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook", "root", "Okafor.com");
        }
    }
    public static void saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        connect();
        String password = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
        String query ="insert into mysql.users (firstname, lastname, email, userPassword, gender, dateOfBirth) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,userDTO.getFirstName());
        preparedStatement.setString(2,userDTO.getLastName());
        preparedStatement.setString(3,userDTO.getEmail());
        preparedStatement.setString(4,password);
        preparedStatement.setString(5,userDTO.getGender());
        preparedStatement.setString(6,userDTO.getDateOfBirth());
        preparedStatement.execute();
        connection.close();
    }
    public UserDTO findUser (String email, String password) throws SQLException, ClassNotFoundException {
        connect();
        String query = "SELECT * FROM users WHERE " +
                "email=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        UserDTO userDTO;
        if(resultSet.next() && BCrypt.checkpw(password, resultSet.getString(4))){
            userDTO =  UserDTO.builder()
                    .firstName(resultSet.getString(1))
                    .lastName(resultSet.getString(2))
                    .email(resultSet.getString(3))
                    .gender(resultSet.getString(5))
                    .dateOfBirth(resultSet.getString(6)).build();

            connection.close();
            return userDTO;
        }
        connection.close();
        return null;
    }

    public List<UserDTO> findAllUsers() throws SQLException, ClassNotFoundException {
        connect();
        String query = "SELECT * from users";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        UserDTO userDTO;
        List<UserDTO> userDTOList = new ArrayList<>();
        while(resultSet.next()){
            userDTO = UserDTO.builder()
                    .firstName(resultSet.getString(1))
                    .lastName(resultSet.getString(2))
                    .email(resultSet.getString(3))
                    .gender(resultSet.getString(5))
                    .dateOfBirth(resultSet.getString(6)).build();

            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

}
