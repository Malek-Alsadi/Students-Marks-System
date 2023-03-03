package Marks.System.Enter.data;

public class StudentMarks {
    private int id;
    private String name;
    private float math, english, arabic, science;

    public StudentMarks(int id, String name, float math, float english, float arabic, float science) {
        this.id = id;
        this.name = name;
        this.math = math;
        this.english = english;
        this.arabic = arabic;
        this.science = science;
    }

    public StudentMarks(String name, float math, float english, float arabic, float science) {
        this.name = name;
        this.math = math;
        this.english = english;
        this.arabic = arabic;
        this.science = science;
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

    public float getMath() {
        return math;
    }

    public void setMath(float math) {
        this.math = math;
    }

    public float getEnglish() {
        return english;
    }

    public void setEnglish(float english) {
        this.english = english;
    }

    public float getArabic() {
        return arabic;
    }

    public void setArabic(float arabic) {
        this.arabic = arabic;
    }

    public float getScience() {
        return science;
    }

    public void setScience(float science) {
        this.science = science;
    }
}
