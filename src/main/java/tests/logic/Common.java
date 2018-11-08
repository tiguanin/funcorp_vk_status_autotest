package tests.logic;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.text;
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
    public static void checkExistsStatusEditorPanel() {
        if ($(byId("currinfo_editor")).exists()) {
            LOGGER.info("Панель редактирования статуса корректно отображается при клике.");
        } else {
            Assert.fail("Панель изменения статуса не отобразилась!");
        }
    }
}
