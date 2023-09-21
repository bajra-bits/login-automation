package test.utils;

public class Utils {
    private static String email = "standard_user";
    private static String password = "secret_sauce";
    private static String invalidEmail = "secret_sauce";
    private static String invalidPassword = "secret_sauce_invalid";

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static String getInvalidEmail() {
        return invalidEmail;
    }

    public static String getInvalidPassword() {
        return invalidPassword;
    }
}
