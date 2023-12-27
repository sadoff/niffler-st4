package guru.qa.niffler.objects.pages;

import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.model.SpendJson;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class NifflerMainPage {

    private final SelenideElement mainTable = $(".spendings-table tbody");
    private final SelenideElement deleteButton = $(byText("Delete selected"));

    @Step("Удалить первый элемент в таблице")
    public NifflerMainPage deleteFirstElementInTable(SpendJson spend){
        mainTable
                .shouldBe(visible)
                .$$("tr")
                .find(text(spend.description()))
                .$$("td")
                .first()
                .click();
        return this;
    }

    @Step("Размер таблицы должен быть равен {size}")
    public NifflerMainPage checkSizeOfTheTable(int size){
        mainTable
                .shouldBe(visible)
                .$$("tr")
                .shouldHave(size(size));
        return this;
    }

    @Step("Нажать на кнопку Удалить")
    public NifflerMainPage clickDeleteFromTable(){
        deleteButton
                .shouldBe(visible)
                .click();
        return this;
    }
}
