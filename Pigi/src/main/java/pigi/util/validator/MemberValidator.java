package pigi.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pigi.model.MemberBean;

public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		MemberBean member = (MemberBean) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "EMAIL", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PASSWORD", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "NAME", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "JUMIN1", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "JUMIN2", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ADDRESS1", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "POSTCODE", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "MOBILE", "required");
		
		if(member.getJUMIN1().length()!=6) {
			errors.rejectValue("JUMIN1", "length");
		}
		if(member.getJUMIN2().length()!=7) {
			errors.rejectValue("JUMIN2", "length");
		}
		if(member.getPOSTCODE().length()<5) {
			errors.rejectValue("POSTCODE", "length");
		}		
		
	}
}