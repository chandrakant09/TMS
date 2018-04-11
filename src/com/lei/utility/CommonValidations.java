package com.lei.utility;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class CommonValidations {
	public boolean isValidEmail(String email){
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if(email==null){
			return false;
		}else{
			return email.matches(EMAIL_REGEX);
		}
	}
	public static boolean validateFirstName( String firstName )
	{
		return firstName.matches( "[A-Z][a-zA-Z]*" );
	}

	public static boolean validateLastName( String lastName )
	{
		return lastName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" );
	}
}
