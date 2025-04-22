package com.quocdoansam.schoolsystem.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.quocdoansam.schoolsystem.dto.response.BaseResponse;
import com.quocdoansam.schoolsystem.enums.ErrorMessage;
import com.quocdoansam.schoolsystem.enums.SalaryStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(value = BaseException.class)
        ResponseEntity<BaseResponse<Object>> handleBaseException(BaseException exception) {
                return ResponseEntity.status(exception.getStatus()).body(
                                BaseResponse.builder()
                                                .success(false)
                                                .statusCode(exception.getStatus().value())
                                                .message(exception.getMessage())
                                                .build());
        }

        @ExceptionHandler(NoHandlerFoundException.class)
        public ResponseEntity<BaseResponse<Object>> handleNotFound(NoHandlerFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.builder()
                                .success(false)
                                .statusCode(HttpStatus.NOT_FOUND.value())
                                .message("URL not foud: " + ex.getRequestURL())
                                .build());
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<BaseResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {
                String errorMessage = ex.getBindingResult().getFieldErrors()
                                .stream()
                                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                .collect(Collectors.joining("; "));

                return ResponseEntity.badRequest().body(BaseResponse.builder()
                                .success(false)
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .message(errorMessage)
                                .build());
        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<BaseResponse<Object>> handleRuntime(RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.builder()
                                .success(false)
                                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message("Server internal error: " + ex.getMessage())
                                .build());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<BaseResponse<Object>> handleGeneric(Exception ex) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.builder()
                                .success(false)
                                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message("Unknown error: " + ex.getMessage())
                                .build());
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<BaseResponse<?>> handleEnumBindingException(
                        HttpMessageNotReadableException exception) {
                Throwable rootCause = exception.getMostSpecificCause();

                if (rootCause instanceof IllegalArgumentException && exception.getMessage().contains("SalaryStatus")) {
                        String message = extractInvalidEnumValue(exception.getMessage(), SalaryStatus.class);
                        return ResponseEntity
                                        .status(HttpStatus.BAD_REQUEST)
                                        .body(BaseResponse
                                                        .builder()
                                                        .success(false)
                                                        .statusCode(HttpStatus.BAD_REQUEST.value())
                                                        .message(message)
                                                        .build());
                }

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(BaseResponse
                                                .builder()
                                                .success(false)
                                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                                .message(ErrorMessage.INVALID_SALARY_STATUS.getMessage())
                                                .build());
        }

        private String extractInvalidEnumValue(String rawMessage, Class<? extends Enum<?>> enumClass) {
                String invalidValue = rawMessage.replaceAll(".*from String \\\"(.*?)\\\".*", "$1");
                String validValues = Arrays.stream(enumClass.getEnumConstants())
                                .map(Enum::name)
                                .collect(Collectors.joining(", "));
                return "Invalid salary status: '" + invalidValue + "'. Accepted values are: " + validValues + ".";
        }
}
