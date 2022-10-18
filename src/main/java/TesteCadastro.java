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
    private DSL dsl;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @Test
    public void deveFazerCadastroComSucesso() {
        dsl.escreve("elementosForm:nome", "José");
        dsl.escreve("elementosForm:sobrenome", "da Silva Soares Santos");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarRadio("elementosForm:comidaFavorita:2");

        dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.escreve("elementosForm:sugestoes", "Esta são as minhas informações favoritas.");
        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertEquals("Cadastrado!", dsl.obterTexto("//*[@id=\"resultado\"]/span"));
        Assert.assertEquals("José", dsl.obterTexto("//*[@id=\"descNome\"]/span"));
        Assert.assertEquals("da Silva Soares Santos", dsl.obterTexto("//*[@id=\"descSobrenome\"]/span"));
        Assert.assertEquals("Masculino", dsl.obterTexto("//*[@id=\"descSexo\"]/span"));
        Assert.assertEquals("Pizza", dsl.obterTexto("//*[@id=\"descComida\"]/span"));
        Assert.assertEquals("mestrado", dsl.obterTexto("//*[@id=\"descEscolaridade\"]/span"));
        Assert.assertEquals("Corrida", dsl.obterTexto("//*[@id=\"descEsportes\"]/span"));
    }

    @Test
    public void deveValidarNomeObrigatorio() {
        dsl.clicarBotao("elementosForm:cadastrar");

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        dsl.escreve("elementosForm:nome", "José");
        dsl.clicarBotao("elementosForm:cadastrar");

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        dsl.escreve("elementosForm:nome", "José");
        dsl.escreve("elementosForm:sobrenome", "da Silva Soares Santos");
        dsl.clicarBotao("elementosForm:cadastrar");

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarComidaVegetariana() {
        dsl.escreve("elementosForm:nome", "José");
        dsl.escreve("elementosForm:sobrenome", "da Silva Soares Santos");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarCheckbox("elementosForm:comidaFavorita:0");
        dsl.clicarCheckbox("elementosForm:comidaFavorita:3");
        dsl.clicarBotao("elementosForm:cadastrar");

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }

    @Test
    public void deveValidarEsportistaIndeciso() {
        dsl.escreve("elementosForm:nome", "José");
        dsl.escreve("elementosForm:sobrenome", "da Silva Soares Santos");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarCheckbox("elementosForm:comidaFavorita:0");
        dsl.selecionarCombo("elementosForm:esportes", "Karate");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
        dsl.clicarBotao("elementosForm:cadastrar");

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}