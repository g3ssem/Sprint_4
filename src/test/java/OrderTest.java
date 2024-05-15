import edu.praktikum.sprint4.utils.pom.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver webDriver;
    private String locationButtonOrder,name, lastName, adress, metro, phone, comment;
    public OrderTest (String locationButtonOrder, String name,  String lastName, String adress, String metro, String phone, String comment) {
        this.locationButtonOrder = locationButtonOrder;
        this.name = name;
        this.lastName = lastName;
        this.adress = adress;
        this.metro = metro;
        this.phone = phone;
        this.comment = comment;
    }
    @Parameterized.Parameters
    public static Object [][] getText () {
        return new Object[][] {
                {"В шапке", "Валера", "Иванов", "Вавилова 12", "Динамо", "89991112233", "Стучать в дверь 3 раза"},
                {"В центре", "Иван", "Гудков", "Панфилова 12", "Речной вокзал", "89998887766", ""}

        };
    }
    @Before
    public void setup () {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.of(3, ChronoUnit.SECONDS));
    }
        @Test
    public void checkCreateOrderOnTopButton() {

        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.openUrlMain();
        orderPage.clickOrderButtonOnMainPage(locationButtonOrder);
        orderPage.setInputName(name);
        orderPage.setInputLastName(lastName);
        orderPage.setInputAdress(adress);
        orderPage.setInputMetro(metro);
        orderPage.setInputPhone(phone);
        orderPage.clickOnNextButton();
        orderPage.setDataOrder();
        orderPage.setTermRent();
        orderPage.setColor();
        orderPage.setComment(comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmOrder();
        assertTrue(orderPage.chekOrderProcessed());

       // String message = String.format("В тексте сообщения № %s ошибка",index+1);
       // assertEquals(message,correctText,mainPage.getTextAccordionOnElement(index) );
    }

    @After
    public void tearDown () {
        webDriver.quit();
    }


}
