
import java.util.HashMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author santu
 */
public class DistanceFunction {


    public DistanceFunction()
    {
        
    }

    double calculateCosineSimilarity(double []a,double []b,double theta)
    {
        double sum=0;
        for(int i=0; i<a.length; i++)
        {
            sum+=a[i]*b[i];
        }
        return Math.pow(1.0-sum,theta);
    }

    double calculateEuclidianDistance(double[] a,double[] b)
    {
        double sum=0;
        for(int i=0; i<a.length; i++)
        {
            sum+=Math.pow(a[i]-b[i],2);
        }
        return Math.sqrt(sum);
    }

}
