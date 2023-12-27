package guru.qa.niffler.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.niffler.jupiter.GenerateCategory;
import guru.qa.niffler.jupiter.GenerateSpend;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.objects.pages.NifflerLoginPage;
import guru.qa.niffler.objects.pages.NifflerMainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SpendingTest {

    static {
        Configuration.browserSize = "1980x1024";
    }

    private NifflerLoginPage nifflerLoginPage;
    private NifflerMainPage nifflerMainPage;

    @BeforeEach
    void doLogin() {
        nifflerMainPage = new NifflerMainPage();
        nifflerLoginPage = new NifflerLoginPage();
    }

    @GenerateSpend(
            username = "duck",
            description = "QA.GURU Advanced 4",
            amount = 72500.00,
            category = "Обучение",
            currency = CurrencyValues.RUB
    )

    @GenerateCategory(
            username = "duck",
            description = "Обучение"
    )

    @Test
    void spendingShouldBeDeletedByButtonDeleteSpending(SpendJson spend, CategoryJson category) {
        Selenide.open("http://frontend.niffler.dc/main");
        nifflerLoginPage.goToLoginPage();
        nifflerLoginPage.sendUserData("duck", "12345");
        nifflerMainPage.deleteFirstElementInTable(spend);
        nifflerMainPage.clickDeleteFromTable();
        nifflerMainPage.checkSizeOfTheTable(0);
    }
}
