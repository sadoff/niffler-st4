package guru.qa.niffler.objects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class NifflerLoginPage {

    private final SelenideElement loginButton = $("a[href*='redirect']");
    private final SelenideElement userNameInput = $("input[name='username']");
    private final SelenideElement passwordInput = $("input[name='password']");
    private final SelenideElement submitButton = $("button[type='submit']");

    @Step("Переход на страницу логина")
    public NifflerLoginPage goToLoginPage(){
        loginButton
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Вввод и отправка данных данных пользователя на странице логина")
    public NifflerLoginPage sendUserData(String name, String password){
        userNameInput
                .shouldBe(visible)
                .setValue(name);
        passwordInput
                .shouldBe(visible)
                .setValue(password);
        submitButton
                .shouldBe(visible)
                .click();
        return this;
    }

}
