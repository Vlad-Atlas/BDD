package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.web.data.DataHelper.getAuthInfo;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        var info = getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(info);
        var cardFirstInfo = DataHelper.getFerstCardInfo();
        var cardSecondInfo = DataHelper.getSecondCardInfo();
        var amount100 = DataHelper.genTransferNumber();

        var loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        var verificationPage = loginPage.validLogin(info);
        var dashboardPage = verificationPage.validVerification(verificationCode);
        var transferCard = dashboardPage.selectCard(cardFirstInfo);
        transferCard.enterTransferAmount(amount100, cardSecondInfo);
        dashboardPage.updateCardBalance(cardFirstInfo, amount100);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        DataHelper.AuthInfo info = getAuthInfo();
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCodeFor(info);
        DataHelper.CardInfo cardFirstInfo = DataHelper.getFerstCardInfo();
        DataHelper.getSecondCardInfo();
        DataHelper.genTransferNumber();

        var loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        var verificationPage = loginPage.validLogin(info);
        var dashboardPage = verificationPage.validVerification(verificationCode);
        var transferCard = dashboardPage.selectCard(cardFirstInfo);
        $("[data-test-id='dashboard']");
        $("[class=App_appContainer__3jRx1]");
        $("[data-test-id=amount] .input__control").shouldBe(visible).setValue("100");
        $("[data-test-id=from] .input__control").shouldBe(visible).setValue("5559 0000 0000 0002");
        $(".button").click();
        $("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0] .list__item").shouldHave(Condition.text("10100")).shouldBe(visible);
    }
}