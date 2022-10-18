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
        dsl.escrever("elementosForm:nome", "José");
        dsl.escrever("elementosForm:sobrenome", "da Silva Soares Santos");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarRadio("elementosForm:comidaFavorita:2");

        dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.escrever("elementosForm:sugestoes", "Esta são as minhas informações favoritas.");
        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertEquals("Cadastrado!", dsl.obterTextoByXpath("//*[@id=\"resultado\"]/span"));
        Assert.assertEquals("José", dsl.obterTextoByXpath("//*[@id=\"descNome\"]/span"));
        Assert.assertEquals("da Silva Soares Santos", dsl.obterTextoByXpath("//*[@id=\"descSobrenome\"]/span"));
        Assert.assertEquals("Masculino", dsl.obterTextoByXpath("//*[@id=\"descSexo\"]/span"));
        Assert.assertEquals("Pizza", dsl.obterTextoByXpath("//*[@id=\"descComida\"]/span"));
        Assert.assertEquals("mestrado", dsl.obterTextoByXpath("//*[@id=\"descEscolaridade\"]/span"));
        Assert.assertEquals("Corrida", dsl.obterTextoByXpath("//*[@id=\"descEsportes\"]/span"));
    }

    @Test
    public void deveValidarNomeObrigatorio() {
        dsl.clicarBotao("elementosForm:cadastrar");

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        dsl.escrever("elementosForm:nome", "José");
        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        dsl.escrever("elementosForm:nome", "José");
        dsl.escrever("elementosForm:sobrenome", "da Silva Soares Santos");
        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarComidaVegetariana() {
        dsl.escrever("elementosForm:nome", "José");
        dsl.escrever("elementosForm:sobrenome", "da Silva Soares Santos");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarCheck("elementosForm:comidaFavorita:0");
        dsl.clicarCheck("elementosForm:comidaFavorita:3");
        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarEsportistaIndeciso() {
        dsl.escrever("elementosForm:nome", "José");
        dsl.escrever("elementosForm:sobrenome", "da Silva Soares Santos");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarCheck("elementosForm:comidaFavorita:0");
        dsl.selecionarCombo("elementosForm:esportes", "Karate");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}