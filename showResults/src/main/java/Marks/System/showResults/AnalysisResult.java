package Marks.System.showResults;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "analysis_result")
public class AnalysisResult {

    private int id;
    private String name;
    private float AVG;
    private String highest;
    public AnalysisResult(){};

    public AnalysisResult(int id, String name, float AVG, String highest) {
        this.id = id;
        this.name = name;
        this.AVG = AVG;
        this.highest = highest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAVG() {
        return AVG;
    }

    public void setAVG(float AVG) {
        this.AVG = AVG;
    }

    public String getHighest() {
        return highest;
    }

    public void setHighest(String highest) {
        this.highest = highest;
    }
}
