package pigi.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pigi.model.CommunityBean;

public class CommunityValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CommunityBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "CTITLE", "required");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "CWRITER", "required");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "CCONTENT", "required");    
	}

}