package engine.audio;

public class StandardAudioDataFactory implements AudioDataFactory {
    @Override
    public AudioData peacefulMusic() {
        return new AudioData.Builder().withPath("music.wav").withVolume(-20.0f).withLoop(true).build();
    }

    @Override
    public AudioData jumpSound() {
        return new AudioData.Builder().withPath("jump.wav").withVolume(-10.0f).build();
    }

}
