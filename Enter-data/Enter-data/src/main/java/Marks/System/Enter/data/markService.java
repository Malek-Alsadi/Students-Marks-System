package Marks.System.Enter.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class markService {
    private final DB_Handler db_handler;

    @Autowired
    markService(@Qualifier("MYSQL") DB_Handler db_handler) {
        this.db_handler = db_handler;
    }

    public int addStudent(StudentMarks marks) {
        return db_handler.insertStudent(marks);
    }

    public List<StudentMarks> getStudents() {
        return db_handler.allStudents();
    }
}
