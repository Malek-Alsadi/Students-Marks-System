package Marks.System.Analytic;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("MYSQL")
public class DB_Handler {
    private DataSource DB;

    public DB_Handler() {
        DB = getDataSource();
    }

    private DataSource getDataSource() {
        MysqlDataSource ds = new MysqlDataSource();
        try {
            ds.setServerName("mysqlDB");
            ds.setDatabaseName("sqlDB");
            ds.setUser("root");
            ds.setPassword("1234");
            ds.setUseSSL(false);
            ds.setAllowPublicKeyRetrieval(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ds;
    }

    public List<StudentMarks> allStudents() {
        List<StudentMarks> ls = new ArrayList<>();
        try (Connection conn = DB.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM marks");
            while (rs.next()) {
                StudentMarks st = new StudentMarks(rs.getInt("id")
                        , rs.getString("name"), rs.getFloat("math")
                        , rs.getFloat("english"), rs.getFloat("arabic")
                        , rs.getFloat("science"));
                ls.add(st);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ls;
    }

    public StudentMarks getStudent(int id) {
        StudentMarks student = null;
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM marks WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                student = new StudentMarks(rs.getInt("id"),
                        rs.getString("name"), rs.getFloat("math"),
                        rs.getFloat("english"), rs.getFloat("arabic"),
                        rs.getFloat("science"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

}
