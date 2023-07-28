package DAO;

import DTO.UserDTO;
import org.mindrot.jbcrypt.BCrypt;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        preparedStatement.execute();
        connection.close();
    }
    public UserDTO findUser (String email, String password) throws SQLException, ClassNotFoundException {
        connect();
        String query = "select * from users where " +
                "email=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        UserDTO userDTO;
        if(resultSet.next() && BCrypt.checkpw(password, resultSet.getString(3))){
            userDTO =  UserDTO.builder()
                    .email(resultSet.getString(2))
                    .dateOfBirth(resultSet.getString(4))
                    .firstName(resultSet.getString(5))
                    .lastName(resultSet.getString(6)).build();
            connection.close();
            return userDTO;
        }
        connection.close();
        return null;
    }

    public List<UserDTO> findAllUsers() throws SQLException, ClassNotFoundException {
        connect();
        String query = "select * from users";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        UserDTO userDTO;
        List<UserDTO> userDTOList = new ArrayList<>();
        while(resultSet.next()){
            userDTO = UserDTO.builder()
                    .firstName(resultSet.getString(1))
                    .lastName(resultSet.getString(2))
                    .email(resultSet.getString(3))
                    .gender(resultSet.getString(4))
                    .dateOfBirth(resultSet.getString(5)).build();

            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

}
