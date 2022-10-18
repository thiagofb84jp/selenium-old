import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void escreve(String idCampo, String texto) {
        driver.findElement(By.id(idCampo)).sendKeys(texto);
    }

    public String obterValorCampo(String idCampo) {
        return driver.findElement(By.id(idCampo)).getAttribute("value");
    }

    public void clicarRadio(String idCampo) {
        driver.findElement(By.id(idCampo)).click();
    }

    public boolean isRadioMarcado(String idCampo) {
        return driver.findElement(By.id(idCampo)).isSelected();
    }

    public void clicarCheckbox(String idCampo) {
        driver.findElement(By.id(idCampo)).click();
    }

    public boolean isCheckboxMarcado(String idCampo) {
        return driver.findElement(By.id(idCampo)).isSelected();
    }

    public void selecionarCombo(String idCampo, String valor) {
        WebElement element = driver.findElement(By.id(idCampo));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public String obterValorCombo(String idCampo) {
        WebElement element = driver.findElement(By.id(idCampo));
        Select combo = new Select(element);

        return combo.getFirstSelectedOption().getText();
    }

    public void clicarBotao(String idCampo) {
        driver.findElement(By.id(idCampo)).click();
    }

    public void clicarLink(String link) {
        driver.findElement(By.linkText(link)).click();
    }

    public String obterTexto(By by) {
        return driver.findElement(by).getText();
    }

    public String obterTexto(String idCampo) {
        return obterTexto(By.id(idCampo));
    }

}
