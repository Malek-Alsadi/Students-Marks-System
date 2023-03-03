package Marks.System.Analytic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
public class ServerController {
    private final marksService service;

    @Autowired
    public ServerController(marksService service){
        this.service = service;
    }

    @GetMapping("/")
    public List<AnalysisResult> studentMarks() {
        return service.getResults();
    }
    @GetMapping("/showAll")
    public List<AnalysisResult> show(){
        return service.getResults();
    }
    @PostMapping("/AddOne")
    public void add(@RequestBody StudentMarks studentMarks){
        service.analyzeAndInsert(studentMarks);
    }

}

