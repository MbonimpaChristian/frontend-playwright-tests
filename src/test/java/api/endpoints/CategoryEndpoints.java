package api.endpoints;

public class CategoryEndpoints {

    public static final String CATEGORY_BASE = "/categories";
    public static final String CATEGORIES = CATEGORY_BASE;

    public static String categoryBySlug(String slug) {
        return CATEGORY_BASE + "/" + slug;
    }

    private CategoryEndpoints() {
    }
}