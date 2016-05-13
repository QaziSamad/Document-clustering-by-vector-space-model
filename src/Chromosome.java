
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author santu
 */
public class Chromosome {
    int []genes;
    double PBM;
    double relativeFintess;

    public Chromosome(int Kmin,int Kmax)
    {
        this.genes=new int[Kmax];
        int NoOfOnes=Kmin + (int)(Math.random() * ((Kmax - Kmin) + 1));
        //System.out.println("NoOfOnes: "+NoOfOnes);
        ArrayList<Integer> randomIndexList=new  ArrayList<Integer>();
        Random randomGenerator = new Random();

        for(int i=0; i<this.genes.length;i++)
        {
            this.genes[i]=0;
        }

        for(int i=0; i<NoOfOnes;i++)
        {
            int randomInt;
            do
            {
                randomInt = randomGenerator.nextInt(this.genes.length);
            }while(randomIndexList.contains(randomInt));
            randomIndexList.add(randomInt);
            this.genes[randomInt]=1;
        }
    }

    int NoOfOnes()
    {
        int count=0;
        for(int i=0; i<this.genes.length; i++)
        {
            if(this.genes[i]==1)
            {
                count++;
            }
        }
        return count;
    }
}
