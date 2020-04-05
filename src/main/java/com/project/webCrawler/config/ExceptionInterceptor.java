package com.project.webCrawler.config;

import com.project.webCrawler.exception.CrawlerException;
import com.project.webCrawler.exception.NoRecordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author parul.mishra
 * @created 04 / 04 /2020
 */


@RestControllerAdvice
public class ExceptionInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(MethodArgumentNotValidException ex) {

        logger.error("Got MethodArgumentNotValidException for :" , ex);

        FieldError fieldError = ex.getBindingResult().getFieldError();

        String errorMessage = fieldError.getDefaultMessage();
        return new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRecordException.class)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<String> handleNoRecordException(NoRecordException e) {

        logger.error("Got NoRecordException for exceptionId: " + e);

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ResponseEntity<Object> handleException(Exception ex) {

        logger.error("Got un-handled exception for exceptionId: " + ex);

        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CrawlerException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleStanzaException(CrawlerException e) {

        logger.error("Got StanzaException for exceptionId: " + e);

        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
