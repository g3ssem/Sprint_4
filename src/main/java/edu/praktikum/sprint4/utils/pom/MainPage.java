package edu.praktikum.sprint4.utils.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {
    private static final String URL = "https://qa-scooter.praktikum-services.ru/"; // Главная страница Я.Самоката
    private final WebDriver webDriver;
    private final By accordionTextFirst = By.xpath(".//div[@id='accordion__heading-0']"); //Первая стрелка списка
    private final String accordionLocator = "accordion__heading-%s"; //Каждая стрелка списка
    private final String accordionText = ".//div[@aria-labelledby='accordion__heading-%s']/p"; // Текст каждого списка
    private final By cookieLocator = By.id("rcc-confirm-button"); // Кнопка куки




    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;

    }

    // Открывем страницу Я.Самоката
    public void open() {
        webDriver.get(URL);
    }

    //Скролл страницы вниз
    public void srollMainPage() {
        WebElement element = webDriver.findElement(accordionTextFirst);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);

    }
    //Закрытие сообщения Cookies
    public void cookieClose() {
        webDriver.findElement(cookieLocator).click();
    }
    //Полкчение текста из текстового модуля аккардион
    public String getTextAccordionOnElement (int index) {
        By locator = By.id(String.format(accordionLocator,index));
        By accordText = By.xpath(String.format(accordionText, index));
        webDriver.findElement(locator).click();
       new WebDriverWait(webDriver,
                Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(accordText));
        return webDriver.findElement(accordText).getText();
            }




}
