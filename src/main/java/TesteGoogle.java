import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.text.Position;
import java.sql.Driver;

public class TesteGoogle {

    @Test
    public void teste() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com.br");

        System.out.println(driver.getTitle());
        Assert.assertEquals("Google", driver.getTitle());

        driver.quit();
    }
}
