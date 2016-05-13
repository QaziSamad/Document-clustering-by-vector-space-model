
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author santu
 */
public class Population {
    ArrayList<Chromosome> chromosomes;
    int populationSize;

    public Population(int populationSize,int Kmin, int Kmax)
    {
        this.populationSize=populationSize;
        this.chromosomes=new  ArrayList<Chromosome>();
        for(int i=0; i<this.populationSize; i++)
        {
            this.chromosomes.add(new Chromosome(Kmin,Kmax));
        }
    }

    void printPopulation()
    {
        for(int i=0; i<this.chromosomes.size();i++)
        {
            Chromosome aChromosome=this.chromosomes.get(i);
            for(int j=0; j<aChromosome.genes.length ; j++)
            {
                System.out.print(aChromosome.genes[j]+" ");
            }
            System.out.println();
        }
    }

}
