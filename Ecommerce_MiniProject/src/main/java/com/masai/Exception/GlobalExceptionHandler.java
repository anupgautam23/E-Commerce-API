package com.masai.Exception;

import java.time.LocalDateTime;

import javax.persistence.RollbackException;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.masai.Model.Product;






@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<MyErrorDetails> UserExceptionHandler(ProductNotFoundException ex, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<MyErrorDetails> UserExceptionHandler(CategoryNotFoundException ex, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> UserExceptionHandler(UserException ex, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> LoginExceptionHandler(LoginException ex, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	//ConversionFailedException
	@ExceptionHandler(ConversionFailedException.class)
	public ResponseEntity<MyErrorDetails> myIllegalHandler(ConversionFailedException me, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), me.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	//IllegalArgumentException

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<MyErrorDetails> IllegalArgumentExceptionHandler(IllegalArgumentException me, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), me.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExpHandlerMain(Exception ie, WebRequest wr) {
		System.out.println("inside myHandler method...EXP");
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), wr.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException manv,
			WebRequest wr) {
		String message = manv.getBindingResult().getFieldError().getDefaultMessage();
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), message, wr.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(RollbackException.class)
	public ResponseEntity<MyErrorDetails> handleRollbackException(Exception exp, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),
				"Improper arguments passed in jason. Validation failed", req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe, WebRequest req) {
		System.out.println("Inside No Handler Found Exception. Exception is being handled");
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);

	}

	
}
