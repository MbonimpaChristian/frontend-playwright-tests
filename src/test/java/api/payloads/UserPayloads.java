package api.payloads;

public class UserPayloads {

    public static String updateProfilePayload(String firstName, String lastName) {
        return """
                {
                  "firstName": "%s",
                  "lastName": "%s"
                }
                """.formatted(firstName, lastName);
    }

    public static String changePasswordPayload(String currentPassword, String newPassword) {
        return """
                {
                  "currentPassword": "%s",
                  "newPassword": "%s"
                }
                """.formatted(currentPassword, newPassword);
    }

    public static String addressPayload(String street, String city, String country, String phone) {
        return """
                {
                  "street": "%s",
                  "city": "%s",
                  "country": "%s",
                  "phone": "%s"
                }
                """.formatted(street, city, country, phone);
    }
    public static String emptyPayload() {
        return """
            {}
            """;
    }
    private UserPayloads() {
    }
}