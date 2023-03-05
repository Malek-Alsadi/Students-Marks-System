package Marks.System.Analytic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class marksService {
    private final DB_Handler mysqlHandler;
    private final MongoTemplate mongoTemplate;
    private static int id = 0;
    @Autowired
    marksService(@Qualifier("MYSQL") DB_Handler mysqlHandler,MongoTemplate mongoTemplate) {
        this.mysqlHandler = mysqlHandler;
        this.mongoTemplate = mongoTemplate;
    }

    public StudentMarks getStudentById(int id) {
        return mysqlHandler.getStudent(id);
    }

    public List<StudentMarks> getStudents() {
        return mysqlHandler.allStudents();
    }
    public List<AnalysisResult> getResults(){
        List<AnalysisResult> tmp = mongoTemplate.findAll(AnalysisResult.class, "analysis_result");
        return tmp;
    }

    public void analyzeAndInsertAll() {
        List<StudentMarks> students = mysqlHandler.allStudents();
        for(StudentMarks studentMark:students){
            analyzeAndInsert(studentMark);
        }
    }

    public void analyzeAndInsert(StudentMarks studentMark) {
        AnalysisResult result = performAnalysis(studentMark);
        int id = result.getId();
        Query query = new Query(Criteria.where("_id").is(id));
        AnalysisResult existingResult = mongoTemplate.findOne(query, AnalysisResult.class, "analysis_result");
        if (existingResult == null) {
            mongoTemplate.insert(result, "analysis_result");
        } else {
            System.out.println("id is already used");
        }
    }
    private AnalysisResult performAnalysis(StudentMarks student){
        float totalMarks = student.getMath() + student.getEnglish() + student.getArabic() + student.getScience();
        return new AnalysisResult(student.getId(), student.getName(), totalMarks / 4, student.getHighestMarkAndCourseName());
    }
}


