package uz.najottalim.javan6.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.najottalim.javan6.dto.ErrorDto;
import uz.najottalim.javan6.exeption.NoResourceFoundException;

@ControllerAdvice
@ResponseBody
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> noResourceFoundExceptionHandler(NoResourceFoundException ex){
        return ResponseEntity.badRequest().body(ErrorDto.builder().errors(ex.getMessage()).build());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e){
        return ResponseEntity.badRequest().body(ErrorDto.builder().errors(e.getMessage()).build());
    }
}
