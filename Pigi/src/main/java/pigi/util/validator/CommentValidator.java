package pigi.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pigi.model.CommentBean;

public class CommentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CommentBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "COMMENTWRITER", "required");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "COMMENTT", "required");
	}
	
}