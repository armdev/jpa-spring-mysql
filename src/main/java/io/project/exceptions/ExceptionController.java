package io.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NoContentException.class)
    @SuppressWarnings("unchecked")
    public ResponseEntity<GenericResponse> noContentHandler() {
        return new ResponseEntity(new GenericResponse("Nothing to see here"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @SuppressWarnings("unchecked")
    public ResponseEntity badRequestHandler() {
        return new ResponseEntity(new GenericResponse("Opps, you messed something up"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    @SuppressWarnings("unchecked")
    public ResponseEntity generalHandler() {
        return new ResponseEntity(new GenericResponse("Opps, something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
