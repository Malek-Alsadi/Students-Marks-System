package Marks.System.showResults;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class ResultController {
    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService){
        this.resultService = resultService;
    }

    @GetMapping("/")
    public String options(){
        return "optionsPage";
    }
    @GetMapping("/AVG")
    public String showAllGPAs(Model model){
        List<AnalysisResult> resultList = resultService.getAllAnalysis();
        model.addAttribute("resultList", resultList);
        return "table";
    }
    @GetMapping("/highest")
    public String showAllHighest(Model model){
        List<AnalysisResult> resultList = resultService.getAllAnalysis();
        model.addAttribute("resultList", resultList);
        return "highestTable";
    }
    @GetMapping("/askId")
    public String askForId(){
        return "ByIdForm";
    }


    @PostMapping("/search")
    public String searchAnalysisResult(@RequestParam("id") int id, Model model){
        AnalysisResult result = resultService.getAnalysisResultById(id);
        if(result == null){
            return "notFound";
        }
        return "redirect:/searchResult?result=" + result.getId();
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
            return "redirect:/OptionsForm";
        } else {
            return "login";
        }
    }

    @GetMapping("/OptionsForm")
    public String showOptionsForm(HttpSession session) {
        if (session.getAttribute("authenticated") != null && (Boolean) session.getAttribute("authenticated")) {
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping("/searchResult")
    public String showSearchResult(@RequestParam("result") int id, Model model){
        AnalysisResult result = resultService.getAnalysisResultById(id);
        model.addAttribute("result", result);
        return "resultDetails";
    }
}