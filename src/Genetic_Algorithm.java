
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author santu
 */
public class Genetic_Algorithm
{
    Population myPopulation;
    int Kmin;
    KMAsimulator myKMAsimulator;

    public Genetic_Algorithm(Population myPopulation,KMAsimulator myKMAsimulator,int Kmin)
    {
        this.myPopulation=myPopulation;
        this.Kmin=Kmin;
        this.myKMAsimulator=myKMAsimulator;
    }

    Chromosome ExexuteGA()
    {
        Collections.sort(this.myPopulation.chromosomes, new Comparator(){

            public int compare(Object o1, Object o2) {
               Chromosome c1 = (Chromosome) o1;
               Chromosome c2 = (Chromosome) o2;
               if(c1.PBM<c2.PBM)
               {
                   return 1;
               }
               else if(c1.PBM>c2.PBM)
               {
                 return -1;
               }
               return 0;
            }

        });


        for(int i=0; i<myPopulation.chromosomes.size(); i++)
        {
            //System.out.println("Chromosome No: "+i+"\tNo of Clusters: "+myPopulation.chromosomes.get(i).NoOfOnes()+"\tPBM="+myPopulation.chromosomes.get(i).PBM);
        }
        

        int iteration=this.myPopulation.populationSize;
        int count=0;

        /*
        while(iteration>0)
        {
            count++;
            int firstChromosomeIndex=this.selection();
            int secondChromosomeIndex;
            do
            {
               secondChromosomeIndex=this.selection();
            }while(secondChromosomeIndex==firstChromosomeIndex);

            Chromosome chromosome1=this.myPopulation.chromosomes.get(firstChromosomeIndex);
            Chromosome chromosome2=this.myPopulation.chromosomes.get(secondChromosomeIndex);


            Chromosome crossChromosome1=new Chromosome(this.Kmin,chromosome1.genes.length);
            Chromosome crossChromosome2=new Chromosome(this.Kmin,chromosome1.genes.length);

            for(int i=0; i<chromosome1.genes.length; i++)
            {
                crossChromosome1.genes[i]=chromosome1.genes[i];
                crossChromosome2.genes[i]=chromosome2.genes[i];
            }

            this.crossOver(crossChromosome1, crossChromosome2);

            if(Math.random()<.2)
            {
                //System.out.println("Mutation Applied");
                this.mutation(crossChromosome1);
            }
            if(Math.random()<.2)
            {
                //System.out.println("Mutation Applied");
                this.mutation(crossChromosome2);
            }

            crossChromosome1.PBM=myKMAsimulator.performCosineKMA(crossChromosome1);
            crossChromosome2.PBM=myKMAsimulator.performCosineKMA(crossChromosome2);

            if(crossChromosome2.PBM > crossChromosome1.PBM)
            {
                Chromosome temp=crossChromosome1;
                crossChromosome1=crossChromosome2;
                crossChromosome2=temp;
            }

            if(crossChromosome1.PBM > this.myPopulation.chromosomes.get(0).PBM)
            {
                for(int i=0; i<100; i++)
                {
                    System.out.print("done");
                }
                System.out.println("done");
                iteration=this.myPopulation.populationSize;
            }

            if(crossChromosome1.PBM > this.myPopulation.chromosomes.get(this.myPopulation.populationSize-2).PBM  &&  crossChromosome2.PBM > this.myPopulation.chromosomes.get(this.myPopulation.populationSize-1).PBM)
            {
                System.out.println("Replacig 2");
                for(int i=0; i<chromosome1.genes.length; i++)
                {
                    this.myPopulation.chromosomes.get(this.myPopulation.populationSize-2).genes[i]=crossChromosome1.genes[i];
                    this.myPopulation.chromosomes.get(this.myPopulation.populationSize-1).genes[i]=crossChromosome2.genes[i];
                }
                this.myPopulation.chromosomes.get(this.myPopulation.populationSize-2).PBM=crossChromosome1.PBM;
                this.myPopulation.chromosomes.get(this.myPopulation.populationSize-1).PBM=crossChromosome2.PBM;              
            }
            else if(crossChromosome1.PBM > this.myPopulation.chromosomes.get(this.myPopulation.populationSize-1).PBM)
            {
                System.out.println("Replacig 1");
                for(int i=0; i<chromosome1.genes.length; i++)
                {
                    this.myPopulation.chromosomes.get(this.myPopulation.populationSize-1).genes[i]=crossChromosome1.genes[i];
                }
                this.myPopulation.chromosomes.get(this.myPopulation.populationSize-1).PBM=crossChromosome1.PBM;
            }

            
            Collections.sort(this.myPopulation.chromosomes, new Comparator(){

                public int compare(Object o1, Object o2) {
                   Chromosome c1 = (Chromosome) o1;
                   Chromosome c2 = (Chromosome) o2;
                   if(c1.PBM<c2.PBM)
                   {
                       return 1;
                   }
                   else if(c1.PBM>c2.PBM)
                   {
                     return -1;
                   }
                   return 0;
                }

            });

            
            iteration--;
            System.out.println("iteration: "+count+"\tNofClusers:"+this.myPopulation.chromosomes.get(0).NoOfOnes()+"\tPBM:"+this.myPopulation.chromosomes.get(0).PBM);
        }*/

        return this.myPopulation.chromosomes.get(0);
        
    }















    int selection()
    {
	double sum=0;
        for(int i=0; i<this.myPopulation.populationSize; i++)
	{
            sum=sum+this.myPopulation.chromosomes.get(i).PBM;
	}

	for(int i=0; i<this.myPopulation.populationSize;i++)
	{
            this.myPopulation.chromosomes.get(i).relativeFintess=this.myPopulation.chromosomes.get(i).PBM/sum;
	}

        
	double p=Math.random();
	sum=0;

	for (int i=0; i<this.myPopulation.populationSize; i++)
	{
            sum+=this.myPopulation.chromosomes.get(i).relativeFintess;
            if(sum>p)
            {
                //System.out.println("Selected: "+i);
                return i;
            }
	}
        //System.out.println("Selected failed 0");
	return 0;
    }



    void crossOver(Chromosome chromosome1,Chromosome chromosome2)
    {
            Random randomGenerator = new Random();
            int firstCrossoverPoint=randomGenerator.nextInt(chromosome1.genes.length);
            int secondCrossoverPoint;

            do
            {
               secondCrossoverPoint=randomGenerator.nextInt(chromosome1.genes.length);;
            }while(firstCrossoverPoint==secondCrossoverPoint);


            if (firstCrossoverPoint > secondCrossoverPoint)
            {
                    int temp=firstCrossoverPoint;
                    firstCrossoverPoint=secondCrossoverPoint;
                    secondCrossoverPoint=temp;
            }

            //System.out.println("firstCrossoverPoint:"+firstCrossoverPoint);
            //System.out.println("secondCrossoverPoint:"+secondCrossoverPoint);

            for(int i=firstCrossoverPoint; i<=secondCrossoverPoint; i++)
            {
                int temp=chromosome1.genes[i];
                chromosome1.genes[i]=chromosome2.genes[i];
                chromosome2.genes[i]=temp;
            }


            if(chromosome1.NoOfOnes()< this.Kmin)
            {
                int x=this.Kmin-chromosome1.NoOfOnes();
                for(int i=0; i<x; i++)
                {
                    int index=-1;
                    do
                    {
                       index=randomGenerator.nextInt(chromosome1.genes.length);;
                    }while(chromosome1.genes[index]==1);
                    chromosome1.genes[index]=1;
                }
            }

            if(chromosome2.NoOfOnes()< this.Kmin)
            {
                int x=this.Kmin-chromosome2.NoOfOnes();
                for(int i=0; i<x; i++)
                {
                    int index=-1;
                    do
                    {
                       index=randomGenerator.nextInt(chromosome2.genes.length);;
                    }while(chromosome2.genes[index]==1);
                    chromosome2.genes[index]=1;
                }
            }

    }


    void mutation(Chromosome chromosome1)
    {
        double min=Double.POSITIVE_INFINITY;
        for(int i=0; i<this.myPopulation.chromosomes.size(); i++)
        {
            if(min > this.myPopulation.chromosomes.get(i).PBM)
            {
                min=this.myPopulation.chromosomes.get(i).PBM;
            }
        }
        Random randomGenerator = new Random();

        //System.out.println("Min="+min+"\tGaussian="+randomGenerator.nextGaussian());
        int N=(int)((randomGenerator.nextGaussian()*min/chromosome1.PBM)*Math.sqrt(chromosome1.genes.length-this.Kmin));
        
        
        if(N+chromosome1.NoOfOnes() < this.Kmin)
        {
            N=this.Kmin-chromosome1.NoOfOnes();
        }

        else if(N+chromosome1.NoOfOnes() > chromosome1.genes.length)
        {
            for(int i=0; i<chromosome1.genes.length; i++)
            {
                chromosome1.genes[i]=1;
            }
            return;
        }

        //System.out.println("N="+N);
        if(N<0)
        {

            N=-N;
            for(int i=0; i<N; i++)
            {
                int index=-1;
                do
                {
                   index=randomGenerator.nextInt(chromosome1.genes.length);;
                }while(chromosome1.genes[index]==0);
                //System.out.println("index="+index);
                chromosome1.genes[index]=0;
            }
        }
        else
        {
            for(int i=0; i<N; i++)
            {
                int index=-1;
                do
                {
                   index=randomGenerator.nextInt(chromosome1.genes.length);;
                }while(chromosome1.genes[index]==1);
                //System.out.println("index="+index);
                chromosome1.genes[index]=1;
            }
        }

    }

}
