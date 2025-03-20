package com.ohgiraffers.restapi.section03.valid;

import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(UserNotFoundException e) {
        String code = "ERROR_CODE_OOOO1";
        String description = "회원 정보 조회 실패";
        String detail = e.getMessage();

        return new ResponseEntity<>(new ErrorResponse(code, description, detail),
            HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e) {
        String code = "";
        String description = "";
        String detail = "";

        if (e.getBindingResult().hasErrors()) {
            detail = e.getBindingResult().getFieldError().getDefaultMessage();  // e.getMessage()
            System.out.println("detail = " + detail);

            String bindResultCode = e.getBindingResult().getFieldError()
                .getCode(); // NotNull, Size... 반환

            switch (bindResultCode) {
                case "NotBlank":
                    code = "ERROR_CODE_OOOO2";
                    description = "필수 값이 누락되었습니다.";
                    break;
                case "Size":
                    code = "ERROR_CODE_OOOO3";
                    description = "글자 수 를 확인해야 합니다.";
                    break;
            }
        }
        return new ResponseEntity<>(new ErrorResponse(code, description, detail),
            HttpStatus.BAD_REQUEST);
    }

}
