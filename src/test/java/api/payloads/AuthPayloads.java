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

    public static String registerPayload(String firstName, String lastName, String email, String password, String phone) {
        return """
                {
                  "firstName": "%s",
                  "lastName": "%s",
                  "email": "%s",
                  "password": "%s",
                  "phone": "%s"
                }
                """.formatted(firstName, lastName, email, password, phone);
    }

    public static String registerWithoutEmailPayload() {
        return """
                {
                  "firstName": "Chris",
                  "lastName": "Mbonimpa",
                  "password": "Password@123",
                  "phone": "0780000000"
                }
                """;
    }

    public static String registerWithInvalidEmailPayload() {
        return """
                {
                  "firstName": "Chris",
                  "lastName": "Mbonimpa",
                  "email": "wrong-email-format",
                  "password": "Password@123",
                  "phone": "0780000000"
                }
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