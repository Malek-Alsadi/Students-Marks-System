package Marks.System.Enter.data;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("MYSQL")
public class  DB_Handler {
    private DataSource DB;

    public DB_Handler() {
        DB = getDataSource();
        if(!isTableExist()){
            createTable();
        }
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

    public boolean isTableExist() {
        try {
            Connection connection = DB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'marks'");
            boolean exists = resultSet.next();
            if (!exists) {
                statement.execute("CREATE TABLE your_table_name (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))");
            }
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTable() {
        try (Connection conn = DB.getConnection()) {
            String sql = "CREATE TABLE marks (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(50) NOT NULL," +
                    "math FLOAT NOT NULL," +
                    "english FLOAT NOT NULL," +
                    "arabic FLOAT NOT NULL," +
                    "science FLOAT NOT NULL" +
                    ")";
            PreparedStatement pStmt = conn.prepareCall(sql);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertStudent(StudentMarks marks) {
        try (Connection conn = DB.getConnection()) {
            String sql = "INSERT INTO marks(id, name, math, english, arabic, science) VALUES(?,?,?,?,?,?)";
            PreparedStatement pStmt = conn.prepareCall(sql);
            pStmt.setInt(1, 0);
            pStmt.setString(2, marks.getName());
            pStmt.setFloat(3, marks.getMath());
            pStmt.setFloat(4, marks.getEnglish());
            pStmt.setFloat(5, marks.getArabic());
            pStmt.setFloat(6, marks.getScience());

            pStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
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
}
