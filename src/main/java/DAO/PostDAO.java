package DAO;

import DTO.PostDTO;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PostDAO {
    private Connection connection;
    public void connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        if(connection != null || connection.isClosed()){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","Okafor.com");
        }
    }

    public void savePost(String postContent, Long userId) throws SQLException, ClassNotFoundException {
        System.out.println("this is the content: "+postContent+" and user id: "+userId);
        connect();
        String query ="insert into Demo_FB.posts (content, user_id) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, postContent);
        preparedStatement.setLong(2, userId);
        preparedStatement.execute();
        connection.close();
    }

    public List<PostDTO> findAllPostsByUser(Long userId) throws SQLException, ClassNotFoundException {
        connect();
        String query = "select * from posts where " +
                "user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<PostDTO> postDTOList = new ArrayList<>();
        while (resultSet.next()){
            PostDTO postDTO = PostDTO.builder()
                    .id(resultSet.getLong(1))
                    .content(resultSet.getString(2))
                    .build();
            postDTOList.add(postDTO);
        }
        return postDTOList;
    }


    public void updateUserPost(Long postId, String updateContent) throws SQLException, ClassNotFoundException {
        connect();
        String query = "update posts set content = ? where " +
                "posts.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, updateContent);
        preparedStatement.setLong(2, postId);
        preparedStatement.executeUpdate();
    }


    public boolean deleteUserPost(Long postId) throws SQLException, ClassNotFoundException {
        connect();
        String query = "delete from posts where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, postId);
        return preparedStatement.execute();
    }

    public PostDTO findById(Long postId) throws SQLException, ClassNotFoundException {
        connect();
        String query = "select * from posts where " +
                "id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, postId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return PostDTO.builder()
                    .id(resultSet.getLong(1))
                    .content(resultSet.getString(2))
                    .build();
        }
        return null;
    }
}
