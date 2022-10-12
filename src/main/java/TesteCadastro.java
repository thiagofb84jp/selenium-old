import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

    @FindBy(id = "elementosForm:nome")
    WebElement nome;

    @Test
    public void deveFazerCadstroComSucesso() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("José");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("da Silva Soares Santos");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Mestrado");
        new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Corrida");
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Esta são as minhas informações favoritas.");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Assert.assertEquals("Cadastrado!", driver.findElement(By.xpath("//*[@id=\"resultado\"]/span")).getText());
        Assert.assertEquals("José", driver.findElement(By.xpath("//*[@id=\"descNome\"]/span")).getText());
        Assert.assertEquals("da Silva Soares Santos", driver.findElement(By.xpath("//*[@id=\"descSobrenome\"]/span")).getText());
        Assert.assertEquals("Masculino", driver.findElement(By.xpath("//*[@id=\"descSexo\"]/span")).getText());
        Assert.assertEquals("Pizza", driver.findElement(By.xpath("//*[@id=\"descComida\"]/span")).getText());
        Assert.assertEquals("mestrado", driver.findElement(By.xpath("//*[@id=\"descEscolaridade\"]/span")).getText());
        Assert.assertEquals("Corrida", driver.findElement(By.xpath("//*[@id=\"descEsportes\"]/span")).getText());

        driver.quit();
    }
}