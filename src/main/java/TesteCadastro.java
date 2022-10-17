import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

    private WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    }

    @Test
    public void deveFazerCadastroComSucesso() {
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
    }

    @Test
    public void deveValidarNomeObrigatorio() {
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("José");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("José");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("da Silva Soares Santos");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarComidaVegetariana() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("José");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("da Silva Soares Santos");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }

    @Test
    public void deveValidarEsportistaIndeciso() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("José");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("da Silva Soares Santos");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();

        Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
        combo.selectByVisibleText("Karate");
        combo.selectByVisibleText("O que eh esporte?");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}