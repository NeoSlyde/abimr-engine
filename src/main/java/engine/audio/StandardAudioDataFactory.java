package engine.audio;

public class StandardAudioDataFactory implements AudioDataFactory {
    @Override
    public AudioData peacefulMusic() {
        return new AudioData.Builder().withPath("/sounds/music.wav").withVolume(-20.0f).withLoop(true).build();
    }

}
