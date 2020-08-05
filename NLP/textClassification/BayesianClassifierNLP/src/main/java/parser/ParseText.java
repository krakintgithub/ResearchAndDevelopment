package parser;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParseText {

    private File modelIn = new File("bin/en-sent.bin");
    private SentenceModel model = new SentenceModel(modelIn);
    private SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);

    public ParseText() throws Exception {
    }


    public String[] parse(String path) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        String sentences[] = sentenceDetector.sentDetect(content);
        return sentences;
    }


}
