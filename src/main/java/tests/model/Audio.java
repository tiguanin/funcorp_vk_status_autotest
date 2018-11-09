package tests.model;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$$;

public class Audio {

    private ElementsCollection playlist;

    public Audio() {
        playlist = $$(byClassName("audio_row__inner"));
    }

    public String getFirstAudioName() {
        SelenideElement audio = playlist.stream().findFirst().get();
        String artist = audio.$(byClassName("artist_link")).getText();
        String title = audio.$(byClassName("audio_row__title_inner")).getText();
        return artist + " – " + title;
    }

    public String getAudioNameByIndex(int index) {
        SelenideElement audio = playlist.get(index);
        String artist = audio.$(byClassName("artist_link")).getText();
        String title = audio.$(byClassName("audio_row__title_inner")).getText();
        return artist + " – " + title;
    }

    public void playFirstAudio() {
        playlist.stream().findFirst().get().$(byClassName("audio_row__title_inner")).click();
    }

    public void playAudioByIndex(int index) {
        playlist.get(index).$(byClassName("audio_row__title_inner")).click();
    }



    public ElementsCollection getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ElementsCollection playlist) {
        this.playlist = playlist;
    }
}
