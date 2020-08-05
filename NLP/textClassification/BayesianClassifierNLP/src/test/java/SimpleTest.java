import wds.AverageProbabilityClassifier;

public class SimpleTest {

    public static void main(String[] args) throws Exception {

        AverageProbabilityClassifier averageProbabilityClassifier = new AverageProbabilityClassifier();
        averageProbabilityClassifier.trainMatch("Hello, doctor, name, yesterday, tomorrow.");
        averageProbabilityClassifier.trainNonMatch("Today is a very nice day with sunshine");

        System.out.println( averageProbabilityClassifier.classify("Hi everybody! -Hello doctor Nick!"));
        System.out.println( averageProbabilityClassifier.classify("You are my sunshine, my only sunshine."));
    }

}
