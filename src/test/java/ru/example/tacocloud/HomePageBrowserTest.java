package ru.example.tacocloud;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class HomePageBrowserTest {

    @LocalServerPort
    private int port;
    private static HtmlUnitDriver driver;

    @BeforeAll
    static void init() {
        driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    static void close() {
        driver.quit();
    }

    @Test
    public void testHomePage() {
        String homePage = "http://localhost:" + port;
        driver.get(homePage);

        String title = driver.getTitle();
        Assertions.assertThat(title).isEqualTo("Taco Cloud");

        String h1Text = driver.findElementByTagName("h1").getText();
        Assertions.assertThat(h1Text).isEqualTo("Welcome to ...");

        String imgSrc = driver.findElementByTagName("img").getAttribute("src");
        Assertions.assertThat(imgSrc).isEqualTo(homePage + "/images/Taco.png");
    }

}
