package tests.init.status;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import tests.logic.Common;
import tests.logic.StatusLogic;
import tests.model.Status;

public class StatusBoundaryValuesInit {

    private static Status STATUS = new Status();

    @Test(description = "Проверка стандартного значения статуса")
    public static void checkDefaultStatus() {
        StatusLogic.goToMyPage();
        STATUS.clearStatus();
        Common.checkDefaultStatusValue(STATUS);
    }

    @Test(description = "Проверка отображения всех основных элементов")
    public static void checkMainElements() {
        STATUS.open();
        Common.checkAllElementsOfStatusEditor(STATUS);
        STATUS.close();
    }

    @Test(description = "Проверка ввода одного символа")
    public static void setStatusWithOnceSymbol() {
        String text = RandomStringUtils.random(1, true, true);
        STATUS.setStatus(text);
        STATUS.clearStatus();
    }

    @Test(description = "Проверка корректности ввода максимального количества симоволов")
    public static void setCorrectStatus() {
        String text = RandomStringUtils.random(139, true, true);
        STATUS.open();
        STATUS.setStatus(text);
        STATUS.clearStatus();
    }

    @Test(description = "Проверка корректности ввода максимального количества симоволов")
    public static void setIncorrectStatus() {
        String text = RandomStringUtils.random(145, true, true);
        STATUS.open();
        STATUS.setStatus(text);
        STATUS.clearStatus();
        Common.returnToBaseUrl();
    }

}
