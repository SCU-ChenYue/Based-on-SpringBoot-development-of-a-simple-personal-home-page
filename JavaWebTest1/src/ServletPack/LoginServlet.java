package ServletPack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String sql = "SELECT count(id) FROM user WHERE username = ?" +
                "AND password = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        PrintWriter out = resp.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String driver="com.mysql.cj.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/sys?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
            String user="root";
            String password2="1013227631s";
            connection = DriverManager.getConnection(url, user, password2);

            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int count = resultSet.getInt(1);

                if(count > 0) {
                    out.print("HELLO:" + username);
                } else {
                    out.print("Sorry:" + username);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
