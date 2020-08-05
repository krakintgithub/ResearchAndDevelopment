import wds.AverageProbabilityClassifier;
import wds.Shredder;

import java.io.File;
import java.util.List;

public class mainRun {

    public static void main(String[] args) throws Exception {

        //train the classifier
        File[] desired = new File("data/DesiredData").listFiles();
        File[] notdesired = new File("data/NotDesiredData").listFiles();

        AverageProbabilityClassifier apc = new AverageProbabilityClassifier();

        for(File f:desired){
            apc.trainMatch(f);
        }

        for(File f:notdesired){
            apc.trainNonMatch(f);
        }

        Shredder shredder = new Shredder();
        shredder.setApc(apc);
        shredder.setHighThreshold(0.5);
        shredder.setLowThreshold(0.1);

        shredder.shred(new File("data/toShred/tobeshredded.txt"));

        List<String> ok = shredder.getAccepted();
        for(String s:ok){
            System.out.println(s);
        }


    }


}
