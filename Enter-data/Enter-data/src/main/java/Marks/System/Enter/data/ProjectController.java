package Marks.System.Enter.data;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class ProjectController {

    private final markService marks;
    private int cnt = 0;
    @Autowired
    public ProjectController(markService marks) {
        this.marks = marks;
    }

    @GetMapping("/")
    public String Home() {
        return "home";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


    @PostMapping("/authenticate")
    public String authenticate(@RequestParam("UserName") String username, @RequestParam("Password") String password, HttpSession session) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://auth:2106/auth";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("UserName", username);
        map.add("Password", password);


        Boolean result = restTemplate.postForObject(url, map, Boolean.class);

        if (result) {
            session.setAttribute("authenticated", true);
            return "redirect:/submitGradesForm";
        } else {
            return "login";
        }
    }

    @GetMapping("/submitGradesForm")
    public String showSubmitGradesForm(HttpSession session) {
        if (session.getAttribute("authenticated") != null && (Boolean) session.getAttribute("authenticated")) {
            return "submitGrades";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/submitGrades")
    public String submitGrades(@RequestParam("StudentName") String studentName,
                               @RequestParam("MathGrade") float mathGrade,
                               @RequestParam("EnglishGrade") float englishGrade,
                               @RequestParam("ArabicGrade") float arabicGrade,
                               @RequestParam("ScienceGrade") float scienceGrade,
                               HttpSession session) {


        if (session.getAttribute("authenticated") == null || !(Boolean) session.getAttribute("authenticated")) {
            return "redirect:/login";
        }

        StudentMarks marks1 = new StudentMarks(cnt++ ,studentName, mathGrade, englishGrade, arabicGrade, scienceGrade);
        marks.addStudent(marks1);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://analytic:2107/AddOne";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<StudentMarks> requestEntity = new HttpEntity<>(marks1, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return "redirect:/";
    }
}

