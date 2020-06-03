package ru.netology.domain;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryAppTest {

    @Test
    void shouldSubmitRequestWithDateAsString() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Ханты-Мансийск");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 10); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String dateForTest = format1.format(c.getTime());
        for (int i=0; i<10; i++){
        $("input[placeholder=\"Дата встречи\"]"). sendKeys(Keys.BACK_SPACE);}
        $("input[placeholder=\"Дата встречи\"]"). val(dateForTest);
        $("[data-test-id=name] input").setValue("Херуимова Елизавета");
        $("[data-test-id=phone] input").setValue("+79119686113");
        $("[data-test-id=agreement]").click();
        $$("button[type=\"button\"]").find(exactText("Запланировать")).click();
//        $(withText("Успешно!")).waitUntil(visible, 15000);
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);

    }

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Ханты-Мансийск");

        $$("button[type=\"button\"]").first().click();
        $$(".calendar-input__calendar-wrapper").first().sendKeys(Keys.ARROW_DOWN);
        $$(".calendar-input__calendar-wrapper").first().sendKeys(Keys.ARROW_DOWN);
        $$(".calendar-input__calendar-wrapper").first().sendKeys(Keys.ENTER);


        $("[data-test-id=name] input").setValue("Херуимова Елизавета");
        $("[data-test-id=phone] input").setValue("+79119686113");
        $("[data-test-id=agreement]").click();
        $$("button[type=\"button\"]").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }
}
