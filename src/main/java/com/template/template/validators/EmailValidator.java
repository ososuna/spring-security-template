package com.template.template.validators;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EmailValidator {

  private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
      "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  /**
   * Validates an email address using regex pattern matching
   * 
   * @param email the email string to validate
   * @return true if email is valid, false otherwise
   */
  public static boolean isValidEmail(String email) {
    if (email == null || email.trim().isEmpty()) {
      return false;
    }
    if (email.length() > 254) {
      return false;
    }
    Matcher matcher = pattern.matcher(email.trim());
    return matcher.matches();
  }
}