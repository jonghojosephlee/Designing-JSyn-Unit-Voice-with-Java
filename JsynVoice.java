package patches;

/**************
** WARNING - this code automatically generated by Syntona.
** The real source is probably a Syntona patch.
** Do NOT edit this file unless you copy it to another directory and change the name.
** Otherwise it is likely to get clobbered the next time you
** export Java source code from Syntona.
**
** Syntona is available from: http://www.softsynth.com/syntona/
*/

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitVoice;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.SineOscillatorPhaseModulated;
import com.softsynth.shared.time.TimeStamp;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.Circuit;

public class JsynVoice extends Circuit implements UnitVoice {
    // Declare units and ports.
    PassThrough mFrequencyPassThrough;
    public UnitInputPort frequency;
    PassThrough mAmplitudePassThrough;
    public UnitInputPort amplitude;
    PassThrough mOutputPassThrough;
    public UnitOutputPort output;
    EnvelopeDAHDSR mAmpEnv;
    SineOscillator mSineOsc;
    SineOscillatorPhaseModulated mSineOscPM;
    SineOscillator mSineOsc2;
    PassThrough mFrequency2PassThrough;
    public UnitInputPort frequency2;
    SawtoothOscillator mSawOsc;

    // Declare inner classes for any child circuits.

    public JsynVoice() {
        // Create unit generators.
        add(mFrequencyPassThrough = new PassThrough());
        addPort(frequency = mFrequencyPassThrough.input, "frequency");
        add(mAmplitudePassThrough = new PassThrough());
        addPort(amplitude = mAmplitudePassThrough.input, "amplitude");
        add(mOutputPassThrough = new PassThrough());
        addPort(output = mOutputPassThrough.output, "output");
        add(mAmpEnv = new EnvelopeDAHDSR());
        add(mSineOsc = new SineOscillator());
        add(mSineOscPM = new SineOscillatorPhaseModulated());
        add(mSineOsc2 = new SineOscillator());
        add(mFrequency2PassThrough = new PassThrough());
        addPort(frequency2 = mFrequency2PassThrough.input, "frequency2");
        add(mSawOsc = new SawtoothOscillator());
        // Connect units and ports.
        mFrequencyPassThrough.output.connect(mSineOscPM.frequency);
        mAmplitudePassThrough.output.connect(mSineOscPM.amplitude);
        mAmpEnv.output.connect(mOutputPassThrough.input);
        mSineOsc.output.connect(mSineOsc2.amplitude);
        mSineOscPM.output.connect(mAmpEnv.amplitude);
        mSineOsc2.output.connect(mSineOscPM.modulation);
        mFrequency2PassThrough.output.connect(mSineOscPM.amplitude);
        mSawOsc.output.connect(mSineOscPM.amplitude);
        // Setup
        frequency.setup(40.0, 646.008332, 8000.0);
        amplitude.setup(0.0, 1.0, 100.0);
        mAmpEnv.input.set(0.0);
        mAmpEnv.delay.set(0.0);
        mAmpEnv.attack.set(0.01);
        mAmpEnv.hold.set(0.0);
        mAmpEnv.decay.set(1.8956400000000002);
        mAmpEnv.sustain.set(0.5);
        mAmpEnv.release.set(6.68964);
        mSineOsc.frequency.set(20.0);
        mSineOsc.amplitude.set(0.9900690750456297);
        mSineOsc2.frequency.set(792.4574901886215);
        frequency2.setup(0.0, 0.999969482421875, 1.0);
        mSawOsc.frequency.set(2743.76706303948);
        mSawOsc.amplitude.set(0.6968737982737305);
    }

    public void noteOn(double frequency, double amplitude, TimeStamp timeStamp) {
        this.frequency.set(frequency, timeStamp);
        this.amplitude.set(amplitude, timeStamp);
        mAmpEnv.input.on(timeStamp);
    }

    public void noteOff(TimeStamp timeStamp) {
        mAmpEnv.input.off(timeStamp);
    }
    
    public UnitOutputPort getOutput() {
        return output;
    }
}
