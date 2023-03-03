package Marks.System.showResults;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WhitePageHandle {
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException() {
        return "error";
    }
}
