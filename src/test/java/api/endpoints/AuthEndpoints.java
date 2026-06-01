package api.endpoints;

public class AuthEndpoints {

    public static final String REGISTER = "/auth/register";
    public static final String LOGIN = "/auth/login";
    public static final String VERIFY_EMAIL = "/auth/verify-email/";
    public static final String FORGOT_PASSWORD = "/auth/forgot-password";
    public static final String RESET_PASSWORD = "/auth/reset-password/";
    public static final String CURRENT_USER = "/auth/me";
    public static final String REFRESH_TOKEN = "/auth/refresh";

    private AuthEndpoints() {
    }
}