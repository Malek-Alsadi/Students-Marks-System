package Marks.System.showResults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResultService {
    private final MongoTemplate mongoTemplate;
    @Autowired
    public ResultService(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    public List<AnalysisResult> getAllAnalysis(){
        return mongoTemplate.findAll(AnalysisResult.class,"analysis_result");
    }

    public AnalysisResult getAnalysisResultById(int id) {
        return mongoTemplate.findById(id, AnalysisResult.class);
    }


}
