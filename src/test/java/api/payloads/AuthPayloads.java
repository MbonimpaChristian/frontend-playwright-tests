package api.payloads;

public class AuthPayloads {

    public static String loginPayload(String email, String password) {
        return """
                {
                  "email": "%s",
                  "password": "%s"
                }
                """.formatted(email, password);
    }

    public static String emptyLoginPayload() {
        return """
                {
                  "email": "",
                  "password": ""
                }
                """;
    }

    public static String emptyRegisterPayload() {
        return """
                {}
                """;
    }

    public static String forgotPasswordPayload(String email) {
        return """
                {
                  "email": "%s"
                }
                """.formatted(email);
    }

    public static String resetPasswordPayload(String password) {
        return """
                {
                  "password": "%s"
                }
                """.formatted(password);
    }

    private AuthPayloads() {
    }
}