package tests.init.status;

import org.testng.annotations.Test;
import tests.logic.Common;
import tests.logic.StatusLogic;
import tests.model.Audio;
import tests.model.Status;

public class StatusAudiosInit {

    private static Status STATUS = new Status();
    private static Audio AUDIO = new Audio();

    @Test(description = "Проверка стандартного статуса")
    public static void checkDefaultStatus() {
        StatusLogic.goToMyPage();
        STATUS.clearStatus();
        Common.checkDefaultStatusValue(STATUS);
    }

    @Test(description = "Проверка отображения основных элементов в окне статуса")
    public static void checkMainElements() {
        STATUS.open();
        Common.checkAllElementsOfStatusEditor(STATUS);
        STATUS.close();
    }

    @Test(description = "Проверка отображения чекбокса трансляции музыки в статус")
    public static void playFirstAudioFromPlaylist() {
        StatusLogic.goToPlaylist();
        String name = AUDIO.getFirstAudioName();
        AUDIO.playFirstAudio();
        StatusLogic.goToMyPage();
        STATUS.open();
        STATUS.enableAudioSharing();
        Common.checkSavedStatusText(name, STATUS);
        STATUS.close();
    }

    @Test(description = "Проверка статуса при смене транслируемой музыки")
    public static void playSecondAudioFromPlayList() {
        StatusLogic.goToPlaylist();
        String name = AUDIO.getAudioNameByIndex(1);
        AUDIO.playAudioByIndex(1);
        StatusLogic.goToMyPage();
        STATUS.open();
        Common.checkSavedStatusText(name, STATUS);
        STATUS.close();
    }

    @Test(description = "Проверка стандартного статуса после отключения трансляции музыки")
    public static void checkStatusAfterDisableAudioSharing() {
        STATUS.open();
        STATUS.disableAudioSharing();
        STATUS.save();
        Common.checkDefaultStatusValue(STATUS);
        Common.returnToBaseUrl();
    }





}
