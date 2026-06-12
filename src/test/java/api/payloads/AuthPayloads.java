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

    private AuthPayloads() {
    }
}