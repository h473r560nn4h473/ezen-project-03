package pigi.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pigi.model.BasketBean;

public class BasketValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return BasketBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {	
		BasketBean basket = (BasketBean) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "BNAME", "required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "BEMAIL", "required");
		
		if(basket.getBCOUNT()<0) {
			errors.rejectValue("BCOUNT", "minus");
		}
		
	}

}