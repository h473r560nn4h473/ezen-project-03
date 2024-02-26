package pigi.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pigi.model.ProductBean;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PNAME", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PIMAGE", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PCOM", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PLOC", "required");
	}
	
}