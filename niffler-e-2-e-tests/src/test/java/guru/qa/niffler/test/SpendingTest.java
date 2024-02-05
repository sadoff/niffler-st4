package guru.qa.niffler.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.niffler.jupiter.GenerateCategory;
import guru.qa.niffler.jupiter.GenerateSpend;
import guru.qa.niffler.jupiter.SpendExtension;
import guru.qa.niffler.jupiter.SpendResolverExtension;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.objects.pages.NifflerLoginPage;
import guru.qa.niffler.objects.pages.NifflerMainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
@ExtendWith({SpendExtension.class, SpendResolverExtension.class})

public class SpendingTest {

    private static final String USERNAME = "duck";
    private static final String PASSWORD = "12345";

    static {
        Configuration.browserSize = "1980x1024";
    }

    private NifflerLoginPage nifflerLoginPage = new NifflerLoginPage();;
    private NifflerMainPage nifflerMainPage = new NifflerMainPage();;

    @GenerateSpend(
            username = "duck",
            description = "QA.GURU Advanced 4",
            amount = 72500.00,
            category = "Обучение",
            currency = CurrencyValues.RUB
    )
    @GenerateCategory(
            username = "duck",
            category = "Обучение"
    )
    @Test
    void spendingShouldBeDeletedByButtonDeleteSpending(SpendJson spend) {
        Selenide.open("http://127.0.0.1:3000");
        nifflerLoginPage.goToLoginPage();
        nifflerLoginPage.sendUserData(USERNAME, PASSWORD);
        nifflerMainPage.deleteFirstElementInTable(spend.description());
        nifflerMainPage.clickDeleteFromTable();
        nifflerMainPage.checkSizeOfTheTable(0);
    }
}
