package wds;

import net.sf.classifier4J.bayesian.IWordsDataSource;
import net.sf.classifier4J.bayesian.SimpleWordsDataSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AverageProbabilityClassifier {
    private IWordsDataSource wds = new SimpleWordsDataSource();


   public void trainMatch(File file) throws Exception{
       BufferedReader reader = new BufferedReader(new FileReader(file));
       String l="";
       while((l=reader.readLine())!=null){
           trainMatch(l);
       }
       reader.close();
   }

    public void trainNonMatch(File file) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String l="";
        while((l=reader.readLine())!=null){
            trainNonMatch(l);
        }
        reader.close();
    }

   public void trainMatch(String in) throws Exception {
       String[] sp = getStrings(in);
       for(String s:sp){wds.addMatch(s);}
   }

    public void trainNonMatch(String in) throws Exception {
        String[] sp = getStrings(in);
        for(String s:sp){wds.addNonMatch(s);}
    }

    private String[] getStrings(String in) {
        in = in.toLowerCase();
        in = leaveLetters(in);
        return in.split(" ");
    }


    public double classify(File file) throws Exception{
       double ret = 0;
       int cnt = 0;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String l="";
        while((l=reader.readLine())!=null){
            double d = classify(l);
            if(!Double.isNaN(d)) {
                ret = ret + classify(l);
                cnt++;
            }
        }

        reader.close();
       return ret/(double) cnt;

    }


    public double classify(String in) throws Exception {
        String[] sp = getStrings(in);

        double sum = 0;
       for(String s:sp){
           try {
               sum = sum + wds.getWordProbability(s).getProbability();
           }catch (Exception e){
               sum = sum +0.5;
           }
       }

       return sum/(double)sp.length;
   }


   private String leaveLetters(String in){
       String ret = in.replaceAll("[^a-z ]", "");
       return  ret;
   }


}
