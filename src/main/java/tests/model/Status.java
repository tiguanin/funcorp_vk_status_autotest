package tests.model;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import tests.logic.Common;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Status {

    private final static Logger LOGGER = LoggerFactory.getLogger(Status.class);

    private SelenideElement statusView;
    private SelenideElement editor;
    private SelenideElement inputField;
    private SelenideElement audioCheckbox;
    private SelenideElement emojiIcon;
    private SelenideElement emojiPanel;
    private SelenideElement sharingPopup;
    private SelenideElement saveBtn;

    public Status() {
        this.statusView = $(byId("current_info"));
        this.editor = $(byClassName("editor"));
        this.inputField = $(byClassName("page_status_input"));
        this.audioCheckbox = $(byClassName("page_status_audio"));
        this.emojiIcon = $(byClassName("emoji_smile_icon"));
        this.emojiPanel = $$(byClassName("emoji_block_cont")).findBy(visible);
        this.sharingPopup = $$(byClassName("checkbox")).findBy(text("Рассказать друзьям"));
        this.saveBtn = $$(byClassName("page_status_btn_save")).findBy(text("Сохранить"));

    }


    /**
     * Нажать на кнопку "Сохранить" в окне редактирования статуса.
     */
    public void save() {
        Selenide.sleep(1000);
        saveBtn.click();
    }


    /**
     * Открыть окно редактирования статуса.
     */
    public void open() {
        Selenide.sleep(1000);
        if (!editor.is(visible)) {
            statusView.click();
        }
    }

    /**
     * Закрыть окно редактирования статуса.
     */
    public void close() {
        Selenide.sleep(1000);
        if (!editor.is(visible)) {
            statusView.click();
        }
    }

    /**
     * Ввести с сохранить новый статус.
     * @param text - любые симовлы от 1 до 140.
     */
    public void setStatus(String text) {
        inputField.setValue(text);
        Selenide.sleep(1000);
        save();
        if (Common.checkSavedStatusText(text, this)) {
            LOGGER.info("Введенный статус: <" + text + "> корректно отобразился.");
        } else {
            Assert.fail("Введенный статус: <" + text + "> не соответствует сохранившемуся: <"
                    + inputField.getText() + ">");

        }
    }

    /**
     * Очистить статус.
     */
    public void clearStatus() {
        Selenide.sleep(1000);
        statusView.click();
        Selenide.sleep(1000);
        inputField.clear();
        Selenide.sleep(1000);
        save();
    }

    /**
     * Включение чекбокса трансляции музыки.
     */
    public void enableAudioSharing() {
        if (!this.isAudioSharingOn()) {
            LOGGER.info("Активирую чекбокс \"Транслировать музыку в статус\"");
            audioCheckbox.click();
        }
    }

    /**
     * Отключение чекбокса трансляции музыки.
     */
    public void disableAudioSharing() {
        if (this.isAudioSharingOn()) {
            LOGGER.info("Снимаю чекбокс \"Транслировать музыку в статус\"");
            audioCheckbox.click();
        }
    }

    /**
     * Активирован ли чек-бокс трансляции музыки?
     */
    public boolean isAudioSharingOn() {
        String val = audioCheckbox.getAttribute("aria-checked");
        if (val != null && !val.isEmpty()) {
            return Boolean.parseBoolean(val);
        } else {
            SoftAssert softAssert = new SoftAssert();
            softAssert.fail("При проверке состояния чекбокса трансляции музыки в статус возникла проблема" +
                    "возвращаю false!");
            softAssert.assertAll();
            return false;
        }
    }

    /**
     * Получить акутальный текст статуса.
     */
    public String getStatusText() {
        return statusView.getText();
    }

    /**
     * Дублирование статуса на стену - рассказать друзьям (репост).
     */
    public void shareCurrentStatusToFriends() {
        statusView.hover();
        sharingPopup.click();
    }

    /**
     * Проверка расшаренного статуса на стене.
     */
    public boolean checkSharedStatusToFriends() {
        String statusText = this.getStatusText();
        String postText = $$(byClassName("post")).stream()
                .findFirst().get().$(byClassName("wall_post_text")).getText();
        if (statusText.equals(postText)) {
            LOGGER.info("Статус пользователя: <" + statusText + "> и разшаренный статус " +
                    "друзьям на стене - совпадают: <" + postText + ">");
            return true;
        } else {
            Assert.fail("Статус пользователя: <" + statusText + "> и разшаренный статус " +
                    "друзьям на стене - НЕ совпадают!: <" + postText + ">");
            return false;
        }
    }

    //getters-n-setters

    public SelenideElement getStatusView() {
        return statusView;
    }

    public void setStatusView(SelenideElement statusView) {
        this.statusView = statusView;
    }

    public SelenideElement getEditor() {
        return editor;
    }

    public void setEditor(SelenideElement editor) {
        this.editor = editor;
    }

    public SelenideElement getInputField() {
        return inputField;
    }

    public void setInputField(SelenideElement inputField) {
        this.inputField = inputField;
    }

    public SelenideElement getAudioCheckbox() {
        return audioCheckbox;
    }

    public void setAudioCheckbox(SelenideElement audioCheckbox) {
        this.audioCheckbox = audioCheckbox;
    }

    public SelenideElement getEmojiIcon() {
        return emojiIcon;
    }

    public void setEmojiIcon(SelenideElement emojiIcon) {
        this.emojiIcon = emojiIcon;
    }

    public SelenideElement getEmojiPanel() {
        return emojiPanel;
    }

    public void setEmojiPanel(SelenideElement emojiPanel) {
        this.emojiPanel = emojiPanel;
    }

    public SelenideElement getSaveBtn() {
        return saveBtn;
    }

    public void setSaveBtn(SelenideElement saveBtn) {
        this.saveBtn = saveBtn;
    }
}
