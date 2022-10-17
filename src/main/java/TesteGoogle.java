import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.text.Position;
import java.sql.Driver;

public class TesteGoogle {

    private WebDriver driver;

    @Before
    public void inicializa() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com.br");
    }

    @Test
    public void teste() {
        System.out.println(driver.getTitle());
        Assert.assertEquals("Google", driver.getTitle());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
