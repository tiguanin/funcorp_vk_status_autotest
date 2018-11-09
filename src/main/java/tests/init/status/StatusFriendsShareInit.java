package tests.init.status;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import tests.logic.Common;
import tests.logic.StatusLogic;
import tests.model.Status;

public class StatusFriendsShareInit {

    private static Status STATUS = new Status();

    @Test(description = "Проверка стандартного статуса")
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

    @Test(description = "Проверка корректности ввода симоволов в разрешенных границах")
    public static void setCorrectStatus() {
        String text = RandomStringUtils.random(40, true, true);
        STATUS.open();
        STATUS.setStatus(text);
    }

    @Test(description = "Проверка шеринга статуса на стену")
    public static void shareStatusToFriends() {
        STATUS.shareCurrentStatusToFriends();
        STATUS.checkSharedStatusToFriends();
        Common.returnToBaseUrl();
    }
}
