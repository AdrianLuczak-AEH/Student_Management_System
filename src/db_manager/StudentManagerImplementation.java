package db_manager;

import db.DatabaseConnection;
import model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentManagerImplementation {
    // Dodawanie studenta
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, age, grade, studentID) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setDouble(3, student.getGrade());
            pstmt.setString(4, student.getStudentID());
            pstmt.executeUpdate();
        }
    }

    // Usuwanie studenta
    public void removeStudent(String studentID) throws SQLException {
        String sql = "DELETE FROM students WHERE studentID = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, studentID);
            pstmt.executeUpdate();
        }
    }

    // Aktualizacja danych studenta
    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setDouble(3, student.getGrade());
            pstmt.setString(4, student.getStudentID());
            pstmt.executeUpdate();
        }
    }

    // Pobieranie wszystkich studentów
    public ArrayList<Student> displayAllStudents() throws SQLException {
        String sql = "SELECT * FROM students";
        ArrayList<Student> students = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("grade"),
                        rs.getString("studentID")
                ));
            }
        }
        return students;
    }

    // Obliczanie średniej ocen
    public double calculateAverageGrade() throws SQLException {
        String sql = "SELECT AVG(grade) AS averageGrade FROM students";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble("averageGrade");
            }
        }
        return 0.0;
    }
}
