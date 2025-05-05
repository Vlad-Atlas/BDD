package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement heading1 = $("[data-test-id=dashboard]");
    private final SelenideElement heading2 = $("h1");
    private final SelenideElement amountField = $("[data-test-id=amount] .input__control").shouldBe(visible);
    private final SelenideElement cardNumberField = $("[data-test-id=from] .input__control").shouldBe(visible);
    private final SelenideElement buttonReplenish = $(".button");

    public TransferPage() {
        heading1.shouldBe(visible);
        heading2.shouldBe(visible);
    }



    public DashboardPage enterTransferAmount(DataHelper.TransferNumber amount100, DataHelper.CardInfo cardSecondInfo) {
        amountField.setValue(String.valueOf(amount100.getAmount()));
        cardNumberField.setValue(cardSecondInfo.getNumber());
        buttonReplenish.click();
        return new DashboardPage();
    }

}