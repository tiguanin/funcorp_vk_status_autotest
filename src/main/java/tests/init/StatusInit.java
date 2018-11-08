package tests.init;

import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import tests.logic.Common;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class StatusInit {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusInit.class);

    @Test(description = "Базовая проверка работоспособности статуса")
    public static void baseStatusCheck() {
        SelenideElement status = $(byId("current_info")).shouldBe(exist);

        LOGGER.info("Очищаю поле \"Статус\"");
        status.click();
        Common.checkExistsStatusEditorPanel();


    }
}
