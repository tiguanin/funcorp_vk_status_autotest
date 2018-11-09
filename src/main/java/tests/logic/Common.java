package tests.logic;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import tests.config.StaticParameters;
import tests.model.Status;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Common {
    private static final Logger LOGGER = LoggerFactory.getLogger(Common.class);

    /**
     * Метод логина на сайт vk.com.
     */
    public static void loginAtVk(String login, String password) {
        SelenideElement loginForm = $(byId("index_login"));

        if (loginForm.exists() && loginForm.is(Condition.visible)) {
            LOGGER.info("Ввод данных в поле \"Телефон или email\" <" + login + ">");
            $(byId("index_email")).setValue(login);
            LOGGER.info("Ввод данных в поле \"Пароль\" <" + password + ">");
            $(byId("index_pass")).setValue(password);
            LOGGER.info("Нажимаю кнопку \"Войти\"");
            $$(byId("index_login_button")).findBy(text("Войти")).click();
        }
    }

    /**
     * Проверка наличия окна редактирования статуса.
     */
    public static void checkExistsStatusEditorPanel(Status status) {
        if (status.getEditor().exists()) {
            LOGGER.info("Панель редактирования статуса корректно отображается при клике.");
        } else {
            Assert.fail("Панель изменения статуса не отобразилась!");
        }
    }

    /**
     * Проверка наличия иконки emoji в окне редактирования статуса.
     */
    public static void checkExistsEmojiIcon(Status status) {
        if (status.getEmojiIcon().exists()) {
            LOGGER.info("Иконка emoji корректно отображается в окне редактирования статуса.");
        } else {
            Assert.fail("Нет отображения иконки emoji в окне редактирования статуса!");
        }

    }

    /**
     * Проверка наличия чекбокса трансляции музыки в окне редактирования статуса.
     */
    public static void checkAudioCheckbox(Status status) {
        if (status.getAudioCheckbox().exists()) {
            LOGGER.info("Чекбокс трансляции музыки корректно отображается в окне редактирования статуса.");
        } else {
            Assert.fail("Нет отображения чекбокса трансляции музыки в окне редактирования статуса!");
        }

    }

    /**
     * Полная проверка наличия всех основных элементов в окне редактирования статуса.
     */
    public static void checkAllElementsOfStatusEditor(Status status) {
        checkExistsStatusEditorPanel(status);
        checkExistsEmojiIcon(status);
        checkAudioCheckbox(status);
    }

    public static String generateRandomString(int length) {
        return RandomStringUtils.random(length, true, true);
    }

    /**
     * Проверка того, что введенный в статус текст соответствует сохранившимуся значению.
     * @param srcText - исходный вводимый текст
     * @param status - обёртка статуса
     */
    public static boolean checkSavedStatusText(String srcText, Status status) {
        status.open();
        boolean result = srcText.equals(status.getInputField().getText());
        LOGGER.info("Текст сохраненного статуса: <" + status.getInputField().getText() + ">");
        status.save();
        return result;
    }

    /**
     * Проверка на отображение дефолтного статуса "изменить статус".
     * @param status
     */
    public static void checkDefaultStatusValue(Status status) {
        status.clearStatus();
        if ("изменить статус".equals($(byClassName("no_current_info")).getText())) {
            LOGGER.info("Стандартный текст \"Изменить статус\" корректно отобразился после очистки статуса");
        } else {
            Assert.fail("Стандартный текст \"Изменить статус\" не отобразился после очистки статуса!");
        }
    }

    public static void returnToBaseUrl() {
        Selenide.open(StaticParameters.BASE_URL);
    }


}
