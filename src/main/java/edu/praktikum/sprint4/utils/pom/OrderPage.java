package edu.praktikum.sprint4.utils.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;


public class OrderPage {
    private final WebDriver webDriver;
    //URL
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private static String buttonOrderHeder = "В шапке"; //Кнопка заказ в "Шапке"
    private static String buttonOrderMiddle = "В центре"; //Кнопка заказ в "Центре"

    // Локатор кнопки заказать в шапке
    private final By buttonTopOrder = By.xpath(".//div[contains(@class,'Header')]/button[text()='Заказать']");
    // Локатор кнопки заказать в центре
    private final By buttonMiddleOrder = By.xpath(".//button[contains(@class,'Button_Middle') and text()='Заказать']");
    //Поле Имя
    private final By inputName = By.xpath(".//input[@placeholder='* Имя']");
    //Поле Фамилия
    private final By inputLastName = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле Адрес
    private final By inputAdress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле Станция метро
    private final By inputMetro = By.xpath(".//input[@placeholder='* Станция метро']");
    //Выбор из списка станций
    private final String buttonStation = ".//div[contains(@class,'Order_Text') and text()='%s']";
    //Поле Телефон
    private final By inputPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private final By buttonNext = By.xpath(".//button[text()='Далее']");
    //Поле ввода даты
    private final By inputDateOrder = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Поле срока аренды
    private final By termRent = By.xpath(".//div[text() ='* Срок аренды']");
    //Поле срока аренды с параметром
    private String randomTermRent =".//div[text()='%s']";
    //выбор значения аренды
    private final By headerRentText = By.xpath(".//div[contains(@class, 'Order_Header')]");
    // цвет самоката "черный"
    private final By colorBlack = By.xpath(".//input[@class='Checkbox_Input__14A2w' and @id='black']");
    // цвет самоката "серый"
    private final By colorGray = By.xpath(".//input[@class='Checkbox_Input__14A2w' and @id='grey']");
    //Поле комментария
    private final By inputComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать" после заполненой формы заказа
    private final By orderButton = By.xpath(".//button[text()='Заказать' and contains(@class,'Button_Mid')]");
    // Кнопка "Да" в окне подтверждения заказа
    private final By confirmOrderButton = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Да']");
    //Окно с заголовком "Заказ оформлен"
    private final By orderProcessed = By.xpath(".//div[contains(@class, 'Order_ModalHeader') and text()='Заказ оформлен']");
    private final By buttonCookie = By.id("rcc-confirm-button"); // Кнопка куки

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;

    }
    //Открытие главной страницы Я.Самоката и закрытие сообщения Cookie
    public void openUrlMain() {
        webDriver.get(URL);
        webDriver.findElement(buttonCookie).click();
    }
    //Выбор и нажатие кнопки "Заказать"
    public  void clickOrderButtonOnMainPage (String locationButtonOrder) {
        if (locationButtonOrder.equals(buttonOrderHeder)) {
            webDriver.findElement(buttonTopOrder).click();}
        if (locationButtonOrder.equals(buttonOrderMiddle)) {
            new WebDriverWait(webDriver,
                    Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(buttonMiddleOrder));
            webDriver.findElement(buttonMiddleOrder).click();

        }
    }
    // Ввод в поле формы имени
    public void setInputName (String name) {
        webDriver.findElement(inputName).sendKeys(name);
    }
    // Ввод в поле формы фамилии
    public void setInputLastName (String lastName) {
        webDriver.findElement(inputLastName).sendKeys(lastName);
    }
    //Ввод в поле адреса
    public void setInputAdress (String adress) {
        webDriver.findElement(inputAdress).sendKeys(adress);
    }
    //Выбор даты когда привезти самокат
    public void setDataOrder () {
        webDriver.findElement(inputDateOrder).click();
        Date today = new Date();
        today.setDate(today.getDate()+1);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.YYYY");
        webDriver.findElement(inputDateOrder).sendKeys(formatter.format(today));
        webDriver.findElement(headerRentText).click();
    }
    // Выбор станции метро
    public void setInputMetro (String metro) {
        By inputMetroFormat = By.xpath(String.format(buttonStation, metro));
        webDriver.findElement(inputMetro).click();
        webDriver.findElement(inputMetroFormat).click();

    }
    //Клик по кнопке для перехода на следующую форму заказа
    public void clickOnNextButton () {
         webDriver.findElement(buttonNext).click();
    }
    //Ввод в поле номера телефона
    public void setInputPhone (String phone) {
        webDriver.findElement(inputPhone).sendKeys(phone);
    }
    //Случайный выбор срока аренды из выпадающего списка
    public void setTermRent () {
        String [] rent = {"сутки", "двое суток", "трое суток", "четверо суток", "пятеро суток", "шестеро суток", "семеро суток"};
        Random rand = new Random();
        String randomItem = rent[rand.nextInt(rent.length)];
        webDriver.findElement(termRent).click();
        By locator = By.xpath(String.format(randomTermRent,randomItem));
        webDriver.findElement(locator).click();

    }
    //Выюор цвета самоката
    public void setColor () {
    webDriver.findElement(colorGray).click();
    webDriver.findElement(colorBlack).click();

    }
    //Ввод в поле комментария
    public void setComment (String comment) {
    webDriver.findElement(inputComment).sendKeys(comment);
    }
    //Клик по кнопке "Заказать"
    public void clickOrderButton () {
        webDriver.findElement(orderButton).click();
    }
    //Подтверждение заказа
    public void clickConfirmOrder () {
    webDriver.findElement(confirmOrderButton).click();
    }
    // Проверка появления окна, что заказ создан
    public boolean  chekOrderProcessed () {
       return webDriver.findElement(orderProcessed).isDisplayed();
    }
}
