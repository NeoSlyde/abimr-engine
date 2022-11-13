package audio;

// This class only *represents* audio.
// It does not deal with playing it
public class AudioData {
    String path;
    float volume;
    boolean loop;

    public AudioData(Builder builder) {
        this.path = builder.path;
        this.volume = builder.volume;
        this.loop = builder.loop;
    }

    public static class Builder {
        private String path = null;
        private float volume = 0f;
        private boolean loop = false;

        public Builder withPath(String path) {
            this.path = path;
            return this;
        }

        public Builder withVolume(float volume) {
            this.volume = volume;
            return this;
        }

        public Builder withLoop(boolean loop) {
            this.loop = loop;
            return this;
        }

        public AudioData build() {
            return new AudioData(this);
        }
    }
}
