package dao;

import model.Todo;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    // Add a new task
    public void addTodo(Todo todo) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
                con.prepareStatement(
                        "INSERT INTO todo(task, completed) VALUES (?, ?)");

        ps.setString(1, todo.getTask());
        ps.setBoolean(2, todo.isCompleted());
        ps.executeUpdate();

        con.close();
    }

    // View all tasks
    public List<Todo> getAllTodos() throws Exception {
        List<Todo> list = new ArrayList<>();

        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM todo");

        while (rs.next()) {
            Todo t = new Todo();
            t.setId(rs.getInt("id"));
            t.setTask(rs.getString("task"));
            t.setCompleted(rs.getBoolean("completed"));
            list.add(t);
        }

        con.close();
        return list;
    }

    // Mark task as completed
    public void markCompleted(int id) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
                con.prepareStatement(
                        "UPDATE todo SET completed = true WHERE id = ?");

        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }

    // Delete task
    public void deleteTodo(int id) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
                con.prepareStatement(
                        "DELETE FROM todo WHERE id = ?");

        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }
}