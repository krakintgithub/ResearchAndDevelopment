package wds;

import net.sf.classifier4J.bayesian.IWordsDataSource;
import net.sf.classifier4J.bayesian.SimpleWordsDataSource;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import parser.ParseText;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Shredder {

    private AverageProbabilityClassifier apc = new AverageProbabilityClassifier();
    private List<String> accepted = new ArrayList<>();
    private List<String> rejected = new ArrayList<>();
    private List<String> neither = new ArrayList<>();
    private double lowThreshold = 0.45;
    private double highThreshold = 0.55;

    private ParseText parseText = new ParseText();

    public Shredder() throws Exception {
    }

    public void shred(File file) throws Exception {
        String[] arr = parseText.parse(file.getAbsolutePath());
        for(String s:arr){
            double v = apc.classify(s);
            if(v>=highThreshold){
                accepted.add(s);
            }
            else if(v<lowThreshold){
                rejected.add(s);
            }
            else {
                neither.add(s);
            }
        }
    }

    public List<String> getAccepted() {
        return accepted;
    }

    public List<String> getRejected() {
        return rejected;
    }

    public List<String> getNeither() {
        return neither;
    }

    public double getLowThreshold() {
        return lowThreshold;
    }

    public double getHighThreshold() {
        return highThreshold;
    }

    public void setApc(AverageProbabilityClassifier apc) {
        this.apc = apc;
    }
    public void setAccepted(List<String> accepted) {
        this.accepted = accepted;
    }

    public void setRejected(List<String> rejected) {
        this.rejected = rejected;
    }

    public void setNeither(List<String> neither) {
        this.neither = neither;
    }

    public void setLowThreshold(double lowThreshold) {
        this.lowThreshold = lowThreshold;
    }

    public void setHighThreshold(double highThreshold) {
        this.highThreshold = highThreshold;
    }
}
