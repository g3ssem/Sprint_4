import edu.praktikum.sprint4.utils.pom.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SamokatTest {
    private WebDriver webDriver;
    private final String correctText;
    private int index;

    public SamokatTest (String correctText, int index) {
        this.correctText = correctText;
        this.index = index;
    }
    @Parameterized.Parameters
    public static Object [][] getText () {
        return new Object[][] {
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7}
        };
    }
    @Before
    public void setup () {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.of(3, ChronoUnit.SECONDS));
    }
   //https://qa-scooter.praktikum-services.ru/
    @Test
    public void checkAllQuestions() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.open();
        mainPage.srollMainPage();
        mainPage.cookieClose();
        String message = String.format("В тексте сообщения № %s ошибка",index+1);
        assertEquals(message,correctText,mainPage.getTextAccordionOnElement(index) );
                     }

    @After
    public void tearDown () {
        webDriver.quit();
    }
}
    