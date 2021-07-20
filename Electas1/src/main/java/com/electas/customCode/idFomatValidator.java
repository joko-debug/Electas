package com.electas.customCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.electas.custom_anotations.IdFormat;
// class to validate id Number
public class idFomatValidator implements ConstraintValidator<IdFormat, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
	 return idFormatTypeCheck(value);
	}

	private boolean idFormatTypeCheck(String id) {
		
		if(id.length()!=13) {
			return false;
		}
		boolean valid = false;
		// check invalid charaters
		String validChar = "0123456789";
		for (int i = 0; i < id.length(); i++) {
			for (int j = 0; j < validChar.length(); j++) {
				if (id.charAt(i) == validChar.charAt(j)) {
					valid = true;
					break;
				}
			}
			if (valid == false) {
				return false;
			}
			valid = false;
		}

		// format check
		// check date
		// check month
		int month = Integer.parseInt(id.substring(2, 4));
		if (month > 12) {
			return false;
		}
		// check day
		int day = Integer.parseInt(id.substring(4, 6));
		if (day > 31) {
			return false;
		}

		// check sum

		if (checkLuhn(id)) {

		} else {
			return false;
		}
		return true;
	}

	static boolean checkLuhn(String cardNo) {
		int nDigits = cardNo.length();

		int nSum = 0;
		boolean isSecond = false;
		for (int i = nDigits - 1; i >= 0; i--) {

			int d = cardNo.charAt(i) - '0';

			if (isSecond == true) {
				d = d * 2;
			}

			// We add two digits to handle
			// cases that make two digits
			// after doubling
			nSum += d / 10;
			nSum += d % 10;

			isSecond = !isSecond;
		}
		return (nSum % 10 == 0);
	}

}
