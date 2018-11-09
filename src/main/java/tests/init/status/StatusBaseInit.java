package tests.init.status;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import tests.logic.Common;
import tests.logic.StatusLogic;
import tests.model.Status;

public class StatusBaseInit {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusBaseInit.class);
    private static Status STATUS = new Status();

    @Test(description = "Проверка заполнения поля статуса стандартным значением")
    public static void checkDefaultStatus() {
        StatusLogic.goToMyPage();
        Common.checkDefaultStatusValue(STATUS);
    }

    @Test(description = "Проверка отображения всех основных элементов")
    public static void checkMainElements() {
        STATUS.open();
        Common.checkAllElementsOfStatusEditor(STATUS);
        STATUS.close();
    }

    @Test(description = "Проверка отображения панели смайлов")
    public static void checkEmojiPanel() {
        STATUS.open();
        StatusLogic.checkEmojiPanel(STATUS);
        STATUS.close();
    }

    @Test(description = "Проверка корректности ввода симоволов в разрешенных границах")
    public static void setCorrectStatus() {
        String text = RandomStringUtils.random(55, true, true);
        STATUS.open();
        STATUS.setStatus(text);
        STATUS.clearStatus();
        Common.returnToBaseUrl();
    }

}
