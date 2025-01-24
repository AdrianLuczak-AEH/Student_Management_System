package db;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Student;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:src/db/students.db";
    private static final String JSON_FILE = "src/db/students.json";

    static {
        try {
            // Załaduj sterownik JDBC
            Class.forName("org.sqlite.JDBC");

            // Połącz z bazą danych
            try (Connection conn = connect()) {
                createTableIfNotExists(conn); // Tworzenie tabeli
                populateTableFromJson(conn); // Wypełnienie danymi z JSON
            }
        } catch (Exception e) {
            System.err.println("Błąd podczas inicjalizacji bazy danych: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    private static void createTableIfNotExists(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "name TEXT NOT NULL, " +
                "age INTEGER NOT NULL, " +
                "grade REAL NOT NULL, " +
                "studentID TEXT PRIMARY KEY)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Błąd podczas tworzenia tabeli: " + e.getMessage());
        }
    }

    private static void populateTableFromJson(Connection conn) {
        String sql = "INSERT OR IGNORE INTO students (name, age, grade, studentID) VALUES (?, ?, ?, ?)";
        try (FileReader reader = new FileReader(JSON_FILE);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Wczytanie danych z JSON
            Gson gson = new Gson();
            Type studentListType = new TypeToken<List<Student>>() {}.getType();
            List<Student> students = gson.fromJson(reader, studentListType);

            // Dodanie studentów do tabeli
            for (Student student : students) {
                pstmt.setString(1, student.getName());
                pstmt.setInt(2, student.getAge());
                pstmt.setDouble(3, student.getGrade());
                pstmt.setString(4, student.getStudentID());
                pstmt.executeUpdate();
            }

            System.out.println("Tabela students została wypełniona danymi z JSON.");
        } catch (Exception e) {
            System.err.println("Błąd podczas wypełniania tabeli danymi z JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
