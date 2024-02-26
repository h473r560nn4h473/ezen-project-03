package pigi.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pigi.model.OrderBean;

public class OrderValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return OrderBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "OMAIL", "required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "OPRODUCT", "required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "OADDRESS1", "required");
		

	}

}