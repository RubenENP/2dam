package com.example.springrubenhita.data.spring.errors;

import com.example.springrubenhita.domain.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Component("MisErrores")
public class ControlErrores extends ResponseEntityExceptionHandler{
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(e.getMessage(),404));
    }


//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(new ApiError(e.getMessage(),"debug","404"));
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(InsufficientAuthenticationException.class)
//    public ResponseEntity<ApiError> handleAuthException(InsufficientAuthenticationException e) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(new ApiError(e.getMessage(),"debug","404"));
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(new ApiError(e.getMessage(),"debug","404"));
//    }
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiError> handleAccessException(AccessDeniedException e) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(new ApiError(e.getMessage(),"debug","404"));
//    }
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ApiError> handleDataIntegrityException(DataIntegrityViolationException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(new ApiError(e.getMessage(),"entidad no existe","404"));
//    }
//
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        response.getWriter().println(ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(new ApiError(accessDeniedException.getMessage(),"entidad no existe","404")).toString());
//    }
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        response.getWriter().println(exception);
//
//    }
}
