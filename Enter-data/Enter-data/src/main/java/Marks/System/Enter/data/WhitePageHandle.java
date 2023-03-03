package Marks.System.Enter.data;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WhitePageHandle {
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException() {
        return "error"; // return the name of the error page
    }
}
