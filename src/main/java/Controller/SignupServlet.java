package Controller;

import DAO.UserDAO;
import DTO.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SignupServlet", value = "/index")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        try {
            UserDAO.saveUser(UserDTO.builder()
                            .firstName(req.getParameter("firstname"))
                            .lastName(req.getParameter("lastname"))
                            .email(req.getParameter("email"))
                            .password(req.getParameter("password"))
                            .gender(req.getParameter("gender"))
                            .dateOfBirth(req.getParameter("dateOfBirth")).build());

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Login.jsp");
            requestDispatcher.forward(req,resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        req.setAttribute("name", req.getAttribute("firstname"));
        dispatcher.forward(req, resp);
    }
}
