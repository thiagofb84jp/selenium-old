import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCampoTreinamento {

    @Test
    public void testeTextField() {
        String nome = "Teste de escrita";

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys(nome);
        Assert.assertEquals(nome, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void deveInteragirComTextArea() {
        
    }
}
