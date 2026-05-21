package Base;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();

        String browserName = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
        double slowMotion = Double.parseDouble(ConfigReader.get("slow.motion"));

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setSlowMo(slowMotion);

        if (browserName.equalsIgnoreCase("chromium")) {
            browser = playwright.chromium().launch(launchOptions);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            browser = playwright.firefox().launch(launchOptions);
        } else if (browserName.equalsIgnoreCase("webkit")) {
            browser = playwright.webkit().launch(launchOptions);
        } else {
            throw new RuntimeException("Unsupported browser: " + browserName);
        }

        context = browser.newContext();
        page = context.newPage();
    }
    protected void loginAsAdminFromHomePage() {
        HomePage homePage = new HomePage(page);
        LoginPage loginPage = new LoginPage(page);

        homePage.openHomePage();
        homePage.clickSignIn();
        loginPage.loginAsAdmin();
    }

    @AfterMethod
    public void tearDown() {
        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}