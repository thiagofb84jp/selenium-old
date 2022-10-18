import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {

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
    public void testeTextField() {
        String valorTextField = "Teste de escrita";
        dsl.escreve("elementosForm:nome", valorTextField);
        Assert.assertEquals(valorTextField, dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void deveInteragirComTextArea() {
        String valorTextArea = "Teste de escrita \n Testando para ver se de fato algo foi escrito";
        dsl.escreve("elementosForm:sugestoes", valorTextArea);
        Assert.assertEquals(valorTextArea, dsl.obterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void deveInteragirComRadioButton() {
        dsl.clicarRadio("elementosForm:sexo:0");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckbox() {
        dsl.clicarCheckbox("elementosForm:comidaFavorita:2");
        Assert.assertTrue(dsl.isCheckboxMarcado("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void deveInteragirComCombo() {
        String valor = "Mestrado";
        dsl.selecionarCombo("elementosForm:escolaridade", valor);
        Assert.assertEquals(valor, dsl.obterValorCombo("elementosForm:escolaridade"));
    }

    @Test
    public void deveVerificarValoresComboMultiplo() {
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size());

        combo.deselectByVisibleText("Corrida");
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());
    }

    @Test
    public void deveInteragirComBotoes() {
        dsl.clicarBotao("buttonSimple");

        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void deveInteragirComLinks() {
        Assert.assertEquals("Status: Nao cadastrado", dsl.obterTexto("resultado"));
        dsl.clicarLink("Voltar");
        Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }

    @Test
    public void deveBuscarTextosNaPagina() {
//        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}