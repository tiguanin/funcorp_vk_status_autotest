package tests.logic;

import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import tests.model.Status;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StatusLogic {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusLogic.class);

    public static void baseCheckStatus() {
        SelenideElement status = $(byId("current_info")).shouldBe(exist);
        SelenideElement statusInputField = status.$(byClassName("page_status_input"));
        SelenideElement emojiIcon = status.$(byClassName("emoji_smile_icon"));

        LOGGER.info("Нажимаю на кнопку \"Моя страница\"");
        $$(byClassName("left_label")).findBy(text("Моя страница")).click();


//        Common.checkExistsStatusEditorPanel();
//
//        statusInputField.clear();
//        Common.checkAllElementsOfStatusEditor();


    }


    public static void checkEmojiPanel(Status status) {
        status.getEmojiIcon().hover();
        status.getEmojiPanel().waitUntil(appear, 1000);
        if (status.getEmojiPanel().is(visible)) {
            LOGGER.info("Панель со смайлами отобразилась");
        } else {
            Assert.fail("Панель со смайлами не отобразилась!");
        }
    }


    public static void goToMyPage() {
        LOGGER.info("Нажимаю на кнопку \"Моя страница\"");
        $$(byClassName("left_label")).findBy(text("Моя страница")).click();
    }

    public static void goToPlaylist() {
        LOGGER.info("Нажимаю на кнопку \"Музыка\"");
        $$(byClassName("left_label")).findBy(text("Музыка")).click();
    }




}
