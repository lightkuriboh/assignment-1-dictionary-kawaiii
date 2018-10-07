package sample;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class Speaker {

    private Synthesizer synthesizer;

    public Speaker() {
        try {
            System.setProperty("freetts.voices",
                    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

            Central.registerEngineCentral
                    ("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

            this.synthesizer =
                    Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));

            this.synthesizer.allocate();

            this.synthesizer.resume();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }



    public void speak(final String text) {
        try
        {

            this.synthesizer.speakPlainText(text, null);
            this.synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

        }

        catch (Exception e)
        {
            e.getMessage();
        }
    }
}