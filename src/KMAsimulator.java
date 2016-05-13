
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
public class KMAsimulator {
    double [][]globalClusterCenters;
    int Kmax;
    int []countInCluster;
    File_Reader myfile_reader;
    double theta;

    public KMAsimulator(File_Reader myfile_reader,int Kmax,double theta)
    {
        this.myfile_reader=myfile_reader;
        this.Kmax=Kmax;
        this.theta=theta;
        performInitialCosineKMA();
    }

    void performInitialEuclidianKMA()
    {
       this.globalClusterCenters=new double[this.Kmax][this.myfile_reader.vocabulary.size()];
       double maxPBM=-1;
       for(int k=0; k<50; k++)
       {
               double [][]tempglobalClusterCenters=new double[this.Kmax][this.myfile_reader.vocabulary.size()];
               Random randomGenerator = new Random();
               ArrayList<Integer> randomIndexList=new  ArrayList<Integer>();
               for (int i=0; i< this.Kmax; i++)
               {
                   int randomInt;
                   do
                   {
                        randomInt = randomGenerator.nextInt(this.myfile_reader.wordCountArrayList.size());
                   }while(randomIndexList.contains(randomInt));
                   randomIndexList.add(randomInt);
                   //System.out.println(randomInt);
               }

               for (int i=0; i< this.Kmax; i++)
               {
                  for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                  {
                    //this.globalClusterCenters[i][j]=this.myfile_reader.TFIDF[randomIndexList.get(i)][j];
                      tempglobalClusterCenters[i][j]=this.myfile_reader.data[randomIndexList.get(i)][j];
                  }
               }


               this.countInCluster=new int[this.Kmax];


               for(int i=0; i<this.Kmax;i++)
                {
                    for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                    {
                        //System.out.print(this.ClusterCenters[i][j]+",");
                    }
                    //System.out.println();
                }
               DistanceFunction myDistanceFunction=new DistanceFunction();

               double [][]NewClusterCenters=new double[this.Kmax][this.myfile_reader.vocabulary.size()];
               int []centerIndex=new int[this.myfile_reader.wordCountArrayList.size()];

               int count=0;
               boolean flag=false;
               do
               {
                   count++;
                   flag=false;

                   for(int i=0; i<this.Kmax; i++)
                   {
                        this.countInCluster[i]=0;
                   }

                   for(int i=0; i<NewClusterCenters.length; i++)
                   {
                       for(int j=0; j<NewClusterCenters[i].length; j++)
                       {
                           NewClusterCenters[i][j]=0.0;
                       }
                   }

                   /*
                   if(count>1)
                   {
                       for(int i=0; i<myfile_reader.centerIndex.length; i++)
                       {
                            myfile_reader.prevCenterIndex[i]=myfile_reader.centerIndex[i];
                       }
                   }
                   */


                   for(int i=0; i<this.myfile_reader.wordCountArrayList.size(); i++)
                   {
                       double min=Double.POSITIVE_INFINITY;
                       int index=-1;
                       for(int j=0; j<this.Kmax ; j++)
                       {
                           if(min > myDistanceFunction.calculateEuclidianDistance(tempglobalClusterCenters[j],this.myfile_reader.data[i]))
                           {
                                //min=myDistanceFunction.calculateCosineSimilarity(this.globalClusterCenters[j],this.myfile_reader.TFIDF[i]);
                                min=myDistanceFunction.calculateEuclidianDistance(tempglobalClusterCenters[j],this.myfile_reader.data[i]);
                                index=j;
                           }
                       }
                       centerIndex[i]=index;
                       this.countInCluster[index]++;
                   }

                   /*
                   if(count>1)
                   {
                       for(int i=0; i<myfile_reader.centerIndex.length; i++)
                       {
                            if(myfile_reader.prevCenterIndex[i]!=myfile_reader.centerIndex[i])
                            {
                                System.out.println("count="+count+"; i="+i+"; prevC="+myfile_reader.prevCenterIndex[i]+"; newC="+myfile_reader.centerIndex[i]);
                            }
                       }
                   }*/

                    for(int i=0; i< this.myfile_reader.wordCountArrayList.size(); i++)
                    {
                        for(int j=0; j< this.myfile_reader.vocabulary.size(); j++)
                        {
                            NewClusterCenters[centerIndex[i]][j]+=this.myfile_reader.data[i][j];
                        }
                    }

                    for(int i=0; i<this.Kmax; i++)
                    {
                        for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                        {
                            NewClusterCenters[i][j]/=this.countInCluster[i];
                            if(Math.abs(NewClusterCenters[i][j]-tempglobalClusterCenters[i][j])>.0001)
                            {
                                flag=true;
                            }
                        }
                    }

                   for(int i=0; i<this.Kmax;i++)
                   {
                        for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                        {
                            tempglobalClusterCenters[i][j]=NewClusterCenters[i][j];
                        }
                   }

                    //System.out.println(count);

                    for(int i=0; i<this.myfile_reader.wordCountArrayList.size();i++)
                    {
                        //System.out.print(myfile_reader.centerIndex[i]+",");
                    }

                    //System.out.println();
                    for(int i=0; i<this.Kmax;i++)
                    {
                        for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                        {
                            //System.out.print(this.ClusterCenters[i][j]+",");
                        }
                        //System.out.println();
                    }
               }while(flag==true);




               for(int i=0; i<this.Kmax;i++)
               {
                    for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                    {
                        //System.out.print(this.globalClusterCenters[i][j]+",");
                    }
                    //System.out.println();
               }

               for(int i=0; i<this.myfile_reader.wordCountArrayList.size();i++)
               {
                    System.out.print(centerIndex[i]+",");
               }
               System.out.println();



               double E1=0;
               double Ek=0;
               double []singleClusterMean=new double[myfile_reader.vocabulary.size()];

               for(int i=0; i<singleClusterMean.length; i++)
               {
                   singleClusterMean[i]=0;
               }

               for(int i=0; i<myfile_reader.data.length; i++)
               {
                   for(int j=0; j<myfile_reader.data[i].length; j++)
                   {
                       singleClusterMean[j]+=myfile_reader.data[i][j];
                   }
               }

               for(int i=0; i<singleClusterMean.length; i++)
               {
                   singleClusterMean[i]/=myfile_reader.data.length;
               }



               for(int i=0; i<myfile_reader.data.length; i++)
               {
                  //E1+=myDistanceFunction.calculateCosineSimilarity(myfile_reader.TFIDF[i], singleClusterMean);
                  E1+=myDistanceFunction.calculateEuclidianDistance(singleClusterMean,myfile_reader.data[i]);
               }

               for(int i=0; i<myfile_reader.data.length; i++)
               {
                  //Ek+=myDistanceFunction.calculateCosineSimilarity(myfile_reader.TFIDF[i], ClusterCenters[centerIndex[i]]);
                   Ek+=myDistanceFunction.calculateEuclidianDistance(myfile_reader.data[i], tempglobalClusterCenters[centerIndex[i]]);
               }

               double Dm=-1;
               for(int i=0; i<tempglobalClusterCenters.length; i++)
               {
                   for(int j=i+1; j<tempglobalClusterCenters.length; j++)
                   {
                       //double d=myDistanceFunction.calculateCosineSimilarity(ClusterCenters[i],ClusterCenters[j]);
                       double d=myDistanceFunction.calculateEuclidianDistance(tempglobalClusterCenters[i],tempglobalClusterCenters[j]);
                       if (Dm < d)
                       {
                           Dm=d;
                       }
                   }
               }

               double PBM=Math.pow((Dm*E1)/(Math.log(tempglobalClusterCenters.length)*Ek),2);
               System.out.println("iteration: "+k+"  PBM="+PBM);
               if(maxPBM < PBM)
               {
                   for(int i=0; i<this.globalClusterCenters.length; i++)
                   {
                        for(int j=0; j<this.globalClusterCenters[i].length; j++)
                        {
                            this.globalClusterCenters[i][j]=tempglobalClusterCenters[i][j];
                        }
                   }
                   maxPBM=PBM;
               }
        }
        System.out.println("MaxPBM="+maxPBM);

    }
















    

    void performInitialCosineKMA()
    {
       this.globalClusterCenters=new double[this.Kmax][this.myfile_reader.vocabulary.size()];
       double maxPBM=-1;
       for(int k=0; k<1; k++)
       {
               double [][]tempglobalClusterCenters=new double[this.Kmax][this.myfile_reader.vocabulary.size()];
               Random randomGenerator = new Random();
               ArrayList<Integer> randomIndexList=new  ArrayList<Integer>();
               for (int i=0; i< this.Kmax; i++)
               {
                   int randomInt;
                   do
                   {
                        randomInt = randomGenerator.nextInt(this.myfile_reader.wordCountArrayList.size());
                   }while(randomIndexList.contains(randomInt));
                   randomIndexList.add(randomInt);
                   //System.out.println(randomInt);
               }

               for (int i=0; i< this.Kmax; i++)
               {
                  for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                  {
                    //this.globalClusterCenters[i][j]=this.myfile_reader.TFIDF[randomIndexList.get(i)][j];
                      tempglobalClusterCenters[i][j]=this.myfile_reader.TFIDF[randomIndexList.get(i)][j];
                  }
               }


               this.countInCluster=new int[this.Kmax];


               for(int i=0; i<this.Kmax;i++)
                {
                    for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                    {
                        //System.out.print(this.ClusterCenters[i][j]+",");
                    }
                    //System.out.println();
                }
               DistanceFunction myDistanceFunction=new DistanceFunction();

               double [][]NewClusterCenters=new double[this.Kmax][this.myfile_reader.vocabulary.size()];
               int []centerIndex=new int[this.myfile_reader.wordCountArrayList.size()];

               int count=0;
               boolean flag=false;
               do
               {
                   count++;
                   flag=false;

                   for(int i=0; i<this.Kmax; i++)
                   {
                        this.countInCluster[i]=0;
                   }

                   for(int i=0; i<NewClusterCenters.length; i++)
                   {
                       for(int j=0; j<NewClusterCenters[i].length; j++)
                       {
                           NewClusterCenters[i][j]=0.0;
                       }
                   }

                   /*
                   if(count>1)
                   {
                       for(int i=0; i<myfile_reader.centerIndex.length; i++)
                       {
                            myfile_reader.prevCenterIndex[i]=myfile_reader.centerIndex[i];
                       }
                   }
                   */


                   for(int i=0; i<this.myfile_reader.wordCountArrayList.size(); i++)
                   {
                       double min=Double.POSITIVE_INFINITY;
                       int index=-1;
                       for(int j=0; j<this.Kmax ; j++)
                       {
                           if(min > myDistanceFunction.calculateCosineSimilarity(tempglobalClusterCenters[j],this.myfile_reader.TFIDF[i],this.theta))
                           {
                                min=myDistanceFunction.calculateCosineSimilarity(tempglobalClusterCenters[j],this.myfile_reader.TFIDF[i],this.theta);
                                index=j;
                           }
                       }
                       centerIndex[i]=index;
                       this.countInCluster[index]++;
                   }

                   /*
                   if(count>1)
                   {
                       for(int i=0; i<myfile_reader.centerIndex.length; i++)
                       {
                            if(myfile_reader.prevCenterIndex[i]!=myfile_reader.centerIndex[i])
                            {
                                System.out.println("count="+count+"; i="+i+"; prevC="+myfile_reader.prevCenterIndex[i]+"; newC="+myfile_reader.centerIndex[i]);
                            }
                       }
                   }*/

                    for(int i=0; i< this.myfile_reader.wordCountArrayList.size(); i++)
                    {
                        for(int j=0; j< this.myfile_reader.vocabulary.size(); j++)
                        {
                            NewClusterCenters[centerIndex[i]][j]+=this.myfile_reader.TFIDF[i][j];
                        }
                    }

                    for(int i=0; i<this.Kmax; i++)
                    {
                        for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                        {
                            NewClusterCenters[i][j]/=this.countInCluster[i];
                            if(Math.abs(NewClusterCenters[i][j]-tempglobalClusterCenters[i][j])>.0001)
                            {
                                flag=true;
                            }
                        }
                    }

                   for(int i=0; i<this.Kmax;i++)
                   {
                        for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                        {
                            tempglobalClusterCenters[i][j]=NewClusterCenters[i][j];
                        }
                   }

                    //System.out.println(count);

                    for(int i=0; i<this.myfile_reader.wordCountArrayList.size();i++)
                    {
                        //System.out.print(myfile_reader.centerIndex[i]+",");
                    }

                    //System.out.println();
                    for(int i=0; i<this.Kmax;i++)
                    {
                        for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                        {
                            //System.out.print(this.ClusterCenters[i][j]+",");
                        }
                        //System.out.println();
                    }
               }while(flag==true);




               for(int i=0; i<this.Kmax;i++)
               {
                    for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                    {
                        //System.out.print(this.globalClusterCenters[i][j]+",");
                    }
                    //System.out.println();
               }

               for(int i=0; i<this.myfile_reader.wordCountArrayList.size();i++)
               {
                    //System.out.print(centerIndex[i]+",");
               }
               //System.out.println();



               double E1=0;
               double Ek=0;
               double []singleClusterMean=new double[myfile_reader.vocabulary.size()];

               for(int i=0; i<singleClusterMean.length; i++)
               {
                   singleClusterMean[i]=0;
               }

               for(int i=0; i<myfile_reader.TFIDF.length; i++)
               {
                   for(int j=0; j<myfile_reader.TFIDF[i].length; j++)
                   {
                       singleClusterMean[j]+=myfile_reader.TFIDF[i][j];
                   }
               }

               for(int i=0; i<singleClusterMean.length; i++)
               {
                   singleClusterMean[i]/=myfile_reader.TFIDF.length;
               }



               for(int i=0; i<myfile_reader.TFIDF.length; i++)
               {
                  E1+=myDistanceFunction.calculateCosineSimilarity(singleClusterMean,myfile_reader.TFIDF[i],this.theta);
               }

               for(int i=0; i<myfile_reader.data.length; i++)
               {
                  Ek+=myDistanceFunction.calculateCosineSimilarity(myfile_reader.TFIDF[i], tempglobalClusterCenters[centerIndex[i]],this.theta);
               }

               double Dm=-1;
               for(int i=0; i<tempglobalClusterCenters.length; i++)
               {
                   for(int j=i+1; j<tempglobalClusterCenters.length; j++)
                   {
                       //double d=myDistanceFunction.calculateCosineSimilarity(ClusterCenters[i],ClusterCenters[j]);
                       double d=myDistanceFunction.calculateEuclidianDistance(tempglobalClusterCenters[i],tempglobalClusterCenters[j]);
                       if (Dm < d)
                       {
                           Dm=d;
                       }
                   }
               }

               double PBM=Math.pow((Dm*E1/Ek)/(tempglobalClusterCenters.length),2);
               //System.out.println("iteration: "+k+"  PBM="+PBM);
               if(maxPBM < PBM)
               {
                   for(int i=0; i<this.globalClusterCenters.length; i++)
                   {
                        for(int j=0; j<this.globalClusterCenters[i].length; j++)
                        {
                            this.globalClusterCenters[i][j]=tempglobalClusterCenters[i][j];
                        }
                   }
                   maxPBM=PBM;
               }
        }
        //System.out.println("MaxPBM="+maxPBM);

    }




















    double performEuclidianKMA(Chromosome aChromsome)
    {
       System.out.println("No of Clusters: "+aChromsome.NoOfOnes());

       double [][]ClusterCenters=new double[aChromsome.NoOfOnes()][this.myfile_reader.vocabulary.size()];

       int indexOfChromosome=0;
       
       for (int i=0; i< aChromsome.NoOfOnes(); i++)
       {
          while(aChromsome.genes[indexOfChromosome]==0)
          {
              indexOfChromosome++;
          }
          
          for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
          {
            ClusterCenters[i][j]=this.globalClusterCenters[indexOfChromosome][j];
          }
          indexOfChromosome++;
       }


       this.countInCluster=new int[aChromsome.NoOfOnes()];


       for(int i=0; i<aChromsome.NoOfOnes();i++)
        {
            for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
            {
                //System.out.print(ClusterCenters[i][j]+",");
            }
            //System.out.println();
        }
       DistanceFunction myDistanceFunction=new DistanceFunction();

       double [][]NewClusterCenters=new double[aChromsome.NoOfOnes()][this.myfile_reader.vocabulary.size()];
       int []centerIndex=new int[this.myfile_reader.wordCountArrayList.size()];

       int count=0;
       boolean flag=false;
       do
       {
           count++;
           flag=false;

           for(int i=0; i<aChromsome.NoOfOnes(); i++)
           {
                this.countInCluster[i]=0;
           }

           for(int i=0; i<NewClusterCenters.length; i++)
           {
               for(int j=0; j<NewClusterCenters[i].length; j++)
               {
                   NewClusterCenters[i][j]=0.0;
               }
           }

           /*
           if(count>1)
           {
               for(int i=0; i<myfile_reader.centerIndex.length; i++)
               {
                    myfile_reader.prevCenterIndex[i]=myfile_reader.centerIndex[i];
               }
           }
           */


           for(int i=0; i<this.myfile_reader.wordCountArrayList.size(); i++)
           {
               double min=Double.POSITIVE_INFINITY;
               int index=-1;
               for(int j=0; j<aChromsome.NoOfOnes() ; j++)
               {
                   if(min > myDistanceFunction.calculateEuclidianDistance(ClusterCenters[j],this.myfile_reader.data[i]))
                   {
                        //min=myDistanceFunction.calculateCosineSimilarity(ClusterCenters[j],this.myfile_reader.TFIDF[i]);
                        min=myDistanceFunction.calculateEuclidianDistance(ClusterCenters[j],this.myfile_reader.data[i]);
                        index=j;
                   }
               }
               centerIndex[i]=index;
               this.countInCluster[index]++;
           }

           /*
           if(count>1)
           {
               for(int i=0; i<myfile_reader.centerIndex.length; i++)
               {
                    if(myfile_reader.prevCenterIndex[i]!=myfile_reader.centerIndex[i])
                    {
                        System.out.println("count="+count+"; i="+i+"; prevC="+myfile_reader.prevCenterIndex[i]+"; newC="+myfile_reader.centerIndex[i]);
                    }
               }
           }*/

            for(int i=0; i< this.myfile_reader.wordCountArrayList.size(); i++)
            {
                for(int j=0; j< this.myfile_reader.vocabulary.size(); j++)
                {
                    NewClusterCenters[centerIndex[i]][j]+=this.myfile_reader.data[i][j];
                }
            }

            for(int i=0; i<aChromsome.NoOfOnes(); i++)
            {
                for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                {
                    NewClusterCenters[i][j]/=this.countInCluster[i];
                    if(Math.abs(NewClusterCenters[i][j]-ClusterCenters[i][j])>.0001)
                    {
                        flag=true;
                    }
                }
            }

           for(int i=0; i<aChromsome.NoOfOnes(); i++)
           {
                for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                {
                    ClusterCenters[i][j]=NewClusterCenters[i][j];
                }
           }

            //System.out.println(count);

            for(int i=0; i<this.myfile_reader.wordCountArrayList.size();i++)
            {
                //System.out.print(myfile_reader.centerIndex[i]+",");
            }

            //System.out.println();
            for(int i=0; i<aChromsome.NoOfOnes();i++)
            {
                for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                {
                    //System.out.print(this.ClusterCenters[i][j]+",");
                }
                //System.out.println();
            }
       }while(flag==true);




       for(int i=0; i<aChromsome.NoOfOnes();i++)
       {
            for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
            {
                //System.out.print(this.globalClusterCenters[i][j]+",");
            }
            //System.out.println();
       }

       for(int i=0; i<this.myfile_reader.wordCountArrayList.size();i++)
       {
            System.out.print(centerIndex[i]+",");
       }





       
       System.out.println();

       double E1=0;
       double Ek=0;
       double []singleClusterMean=new double[myfile_reader.vocabulary.size()];

       for(int i=0; i<singleClusterMean.length; i++)
       {
           singleClusterMean[i]=0;
       }

       for(int i=0; i<myfile_reader.data.length; i++)
       {
           for(int j=0; j<myfile_reader.data[i].length; j++)
           {
               singleClusterMean[j]+=myfile_reader.data[i][j];
           }
       }

       for(int i=0; i<singleClusterMean.length; i++)
       {
           singleClusterMean[i]/=myfile_reader.data.length;
       }

       
       
       for(int i=0; i<myfile_reader.data.length; i++)
       {
          //E1+=myDistanceFunction.calculateCosineSimilarity(myfile_reader.TFIDF[i], singleClusterMean);
          E1+=myDistanceFunction.calculateEuclidianDistance(singleClusterMean,myfile_reader.data[i]);
       }

       for(int i=0; i<myfile_reader.data.length; i++)
       {
          //Ek+=myDistanceFunction.calculateCosineSimilarity(myfile_reader.TFIDF[i], ClusterCenters[centerIndex[i]]);
           Ek+=myDistanceFunction.calculateEuclidianDistance(myfile_reader.data[i], ClusterCenters[centerIndex[i]]);
       }

       double Dm=-1;
       for(int i=0; i<ClusterCenters.length; i++)
       {
           for(int j=i+1; j<ClusterCenters.length; j++)
           {
               //double d=myDistanceFunction.calculateCosineSimilarity(ClusterCenters[i],ClusterCenters[j]);
               double d=myDistanceFunction.calculateEuclidianDistance(ClusterCenters[i],ClusterCenters[j]);
               if (Dm < d)
               {
                   Dm=d;
               }
           }
       }

       double PBM=Math.pow((Dm*E1)/(Math.log(ClusterCenters.length)*Ek),2);


       //System.out.println("Dm="+Dm);
       //System.out.println("K="+ClusterCenters.length);
       //System.out.println("E1="+E1);
       //System.out.println("Ek="+Ek);
       System.out.println("PBM="+PBM);
       return PBM;

    }

























    double performCosineKMA(Chromosome aChromsome)
    {
       //System.out.println("No of Clusters: "+aChromsome.NoOfOnes());

       double [][]ClusterCenters=new double[aChromsome.NoOfOnes()][this.myfile_reader.vocabulary.size()];

       int indexOfChromosome=0;

       for (int i=0; i< aChromsome.NoOfOnes(); i++)
       {
          while(aChromsome.genes[indexOfChromosome]==0)
          {
              indexOfChromosome++;
          }

          for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
          {
            ClusterCenters[i][j]=this.globalClusterCenters[indexOfChromosome][j];
          }
          indexOfChromosome++;
       }


       this.countInCluster=new int[aChromsome.NoOfOnes()];


       for(int i=0; i<aChromsome.NoOfOnes();i++)
        {
            for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
            {
                //System.out.print(ClusterCenters[i][j]+",");
            }
            //System.out.println();
        }
       DistanceFunction myDistanceFunction=new DistanceFunction();

       double [][]NewClusterCenters=new double[aChromsome.NoOfOnes()][this.myfile_reader.vocabulary.size()];
       int []centerIndex=new int[this.myfile_reader.wordCountArrayList.size()];

       int count=0;
       boolean flag=false;
       do
       {
           count++;
           flag=false;

           for(int i=0; i<aChromsome.NoOfOnes(); i++)
           {
                this.countInCluster[i]=0;
           }

           for(int i=0; i<NewClusterCenters.length; i++)
           {
               for(int j=0; j<NewClusterCenters[i].length; j++)
               {
                   NewClusterCenters[i][j]=0.0;
               }
           }

           /*
           if(count>1)
           {
               for(int i=0; i<myfile_reader.centerIndex.length; i++)
               {
                    myfile_reader.prevCenterIndex[i]=myfile_reader.centerIndex[i];
               }
           }
           */


           for(int i=0; i<this.myfile_reader.wordCountArrayList.size(); i++)
           {
               double min=Double.POSITIVE_INFINITY;
               int index=-1;
               for(int j=0; j<aChromsome.NoOfOnes() ; j++)
               {
                   if(min > myDistanceFunction.calculateCosineSimilarity(ClusterCenters[j],this.myfile_reader.TFIDF[i],this.theta))
                   {
                        min=myDistanceFunction.calculateCosineSimilarity(ClusterCenters[j],this.myfile_reader.TFIDF[i],this.theta);
                        index=j;
                   }
               }
               centerIndex[i]=index;
               this.countInCluster[index]++;
           }

           /*
           if(count>1)
           {
               for(int i=0; i<myfile_reader.centerIndex.length; i++)
               {
                    if(myfile_reader.prevCenterIndex[i]!=myfile_reader.centerIndex[i])
                    {
                        System.out.println("count="+count+"; i="+i+"; prevC="+myfile_reader.prevCenterIndex[i]+"; newC="+myfile_reader.centerIndex[i]);
                    }
               }
           }*/

            for(int i=0; i< this.myfile_reader.wordCountArrayList.size(); i++)
            {
                for(int j=0; j< this.myfile_reader.vocabulary.size(); j++)
                {
                    NewClusterCenters[centerIndex[i]][j]+=this.myfile_reader.TFIDF[i][j];
                }
            }

            for(int i=0; i<aChromsome.NoOfOnes(); i++)
            {
                for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                {
                    NewClusterCenters[i][j]/=this.countInCluster[i];
                    if(Math.abs(NewClusterCenters[i][j]-ClusterCenters[i][j])>.0001)
                    {
                        flag=true;
                    }
                }
            }

           for(int i=0; i<aChromsome.NoOfOnes(); i++)
           {
                for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                {
                    ClusterCenters[i][j]=NewClusterCenters[i][j];
                }
           }

            //System.out.println(count);

            for(int i=0; i<this.myfile_reader.wordCountArrayList.size();i++)
            {
                //System.out.print(myfile_reader.centerIndex[i]+",");
            }

            //System.out.println();
            for(int i=0; i<aChromsome.NoOfOnes();i++)
            {
                for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                {
                    //System.out.print(this.ClusterCenters[i][j]+",");
                }
                //System.out.println();
            }
       }while(flag==true);




       for(int i=0; i<aChromsome.NoOfOnes();i++)
       {
            for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
            {
                //System.out.print(this.globalClusterCenters[i][j]+",");
            }
            //System.out.println();
       }

       for(int i=0; i<this.myfile_reader.wordCountArrayList.size();i++)
       {
            //System.out.print(centerIndex[i]+",");
       }
       //System.out.println();







       double E1=0;
       double Ek=0;
       double []singleClusterMean=new double[myfile_reader.vocabulary.size()];

       for(int i=0; i<singleClusterMean.length; i++)
       {
           singleClusterMean[i]=0;
       }

       for(int i=0; i<myfile_reader.TFIDF.length; i++)
       {
           for(int j=0; j<myfile_reader.TFIDF[i].length; j++)
           {
               singleClusterMean[j]+=myfile_reader.TFIDF[i][j];
           }
       }

       for(int i=0; i<singleClusterMean.length; i++)
       {
           singleClusterMean[i]/=myfile_reader.TFIDF.length;
       }



       for(int i=0; i<myfile_reader.TFIDF.length; i++)
       {
          //E1+=myDistanceFunction.calculateCosineSimilarity(myfile_reader.TFIDF[i], singleClusterMean);
          E1+=myDistanceFunction.calculateCosineSimilarity(singleClusterMean,myfile_reader.TFIDF[i],this.theta);
       }

       for(int i=0; i<myfile_reader.data.length; i++)
       {
          //Ek+=myDistanceFunction.calculateCosineSimilarity(myfile_reader.TFIDF[i], ClusterCenters[centerIndex[i]]);
           Ek+=myDistanceFunction.calculateCosineSimilarity(myfile_reader.TFIDF[i], ClusterCenters[centerIndex[i]],this.theta);
       }

       double Dm=-1;
       for(int i=0; i<ClusterCenters.length; i++)
       {
           for(int j=i+1; j<ClusterCenters.length; j++)
           {
               //double d=myDistanceFunction.calculateCosineSimilarity(ClusterCenters[i],ClusterCenters[j]);
               double d=myDistanceFunction.calculateCosineSimilarity(ClusterCenters[i],ClusterCenters[j],this.theta);
               if (Dm < d)
               {
                   Dm=d;
               }
           }
       }

       double PBM=(Dm*E1/Ek)/ClusterCenters.length;
       //double PBM=.3*(Dm*E1/Ek)+.7*(1/ClusterCenters.length);


       //System.out.println("Dm="+Dm);
       //System.out.println("K="+ClusterCenters.length);
       //System.out.println("E1="+E1);
       //System.out.println("Ek="+Ek);
       //System.out.println("PBM="+PBM);
       return PBM;

    }



    int[] performCosineKMAwithResults(Chromosome aChromsome)
    {
       //System.out.println("No of Clusters: "+aChromsome.NoOfOnes());

       double [][]ClusterCenters=new double[aChromsome.NoOfOnes()][this.myfile_reader.vocabulary.size()];

       int indexOfChromosome=0;

       for (int i=0; i< aChromsome.NoOfOnes(); i++)
       {
          while(aChromsome.genes[indexOfChromosome]==0)
          {
              indexOfChromosome++;
          }

          for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
          {
            ClusterCenters[i][j]=this.globalClusterCenters[indexOfChromosome][j];
          }
          indexOfChromosome++;
       }


       this.countInCluster=new int[aChromsome.NoOfOnes()];


       for(int i=0; i<aChromsome.NoOfOnes();i++)
        {
            for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
            {
                //System.out.print(ClusterCenters[i][j]+",");
            }
            //System.out.println();
        }
       DistanceFunction myDistanceFunction=new DistanceFunction();

       double [][]NewClusterCenters=new double[aChromsome.NoOfOnes()][this.myfile_reader.vocabulary.size()];
       int []centerIndex=new int[this.myfile_reader.wordCountArrayList.size()];

       int count=0;
       boolean flag=false;
       do
       {
           count++;
           flag=false;

           for(int i=0; i<aChromsome.NoOfOnes(); i++)
           {
                this.countInCluster[i]=0;
           }

           for(int i=0; i<NewClusterCenters.length; i++)
           {
               for(int j=0; j<NewClusterCenters[i].length; j++)
               {
                   NewClusterCenters[i][j]=0.0;
               }
           }

           /*
           if(count>1)
           {
               for(int i=0; i<myfile_reader.centerIndex.length; i++)
               {
                    myfile_reader.prevCenterIndex[i]=myfile_reader.centerIndex[i];
               }
           }
           */


           for(int i=0; i<this.myfile_reader.wordCountArrayList.size(); i++)
           {
               double min=Double.POSITIVE_INFINITY;
               int index=-1;
               for(int j=0; j<aChromsome.NoOfOnes() ; j++)
               {
                   if(min > myDistanceFunction.calculateCosineSimilarity(ClusterCenters[j],this.myfile_reader.TFIDF[i],this.theta))
                   {
                        min=myDistanceFunction.calculateCosineSimilarity(ClusterCenters[j],this.myfile_reader.TFIDF[i],this.theta);
                        index=j;
                   }
               }
               centerIndex[i]=index;
               this.countInCluster[index]++;
           }

           /*
           if(count>1)
           {
               for(int i=0; i<myfile_reader.centerIndex.length; i++)
               {
                    if(myfile_reader.prevCenterIndex[i]!=myfile_reader.centerIndex[i])
                    {
                        System.out.println("count="+count+"; i="+i+"; prevC="+myfile_reader.prevCenterIndex[i]+"; newC="+myfile_reader.centerIndex[i]);
                    }
               }
           }*/

            for(int i=0; i< this.myfile_reader.wordCountArrayList.size(); i++)
            {
                for(int j=0; j< this.myfile_reader.vocabulary.size(); j++)
                {
                    NewClusterCenters[centerIndex[i]][j]+=this.myfile_reader.TFIDF[i][j];
                }
            }

            for(int i=0; i<aChromsome.NoOfOnes(); i++)
            {
                for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                {
                    NewClusterCenters[i][j]/=this.countInCluster[i];
                    if(Math.abs(NewClusterCenters[i][j]-ClusterCenters[i][j])>.0001)
                    {
                        flag=true;
                    }
                }
            }

           for(int i=0; i<aChromsome.NoOfOnes(); i++)
           {
                for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                {
                    ClusterCenters[i][j]=NewClusterCenters[i][j];
                }
           }

            //System.out.println(count);

            for(int i=0; i<this.myfile_reader.wordCountArrayList.size();i++)
            {
                //System.out.print(myfile_reader.centerIndex[i]+",");
            }

            //System.out.println();
            for(int i=0; i<aChromsome.NoOfOnes();i++)
            {
                for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
                {
                    //System.out.print(this.ClusterCenters[i][j]+",");
                }
                //System.out.println();
            }
       }while(flag==true);




       for(int i=0; i<aChromsome.NoOfOnes();i++)
       {
            for(int j=0; j<this.myfile_reader.vocabulary.size(); j++)
            {
                //System.out.print(this.globalClusterCenters[i][j]+",");
            }
            //System.out.println();
       }

       for(int i=0; i<this.myfile_reader.wordCountArrayList.size();i++)
       {
            //System.out.print(centerIndex[i]+",");
       }
       //System.out.println();







       double E1=0;
       double Ek=0;
       double []singleClusterMean=new double[myfile_reader.vocabulary.size()];

       for(int i=0; i<singleClusterMean.length; i++)
       {
           singleClusterMean[i]=0;
       }

       for(int i=0; i<myfile_reader.TFIDF.length; i++)
       {
           for(int j=0; j<myfile_reader.TFIDF[i].length; j++)
           {
               singleClusterMean[j]+=myfile_reader.TFIDF[i][j];
           }
       }

       for(int i=0; i<singleClusterMean.length; i++)
       {
           singleClusterMean[i]/=myfile_reader.TFIDF.length;
       }



       for(int i=0; i<myfile_reader.TFIDF.length; i++)
       {
          //E1+=myDistanceFunction.calculateCosineSimilarity(myfile_reader.TFIDF[i], singleClusterMean);
          E1+=myDistanceFunction.calculateCosineSimilarity(singleClusterMean,myfile_reader.TFIDF[i],this.theta);
       }

       for(int i=0; i<myfile_reader.data.length; i++)
       {
          //Ek+=myDistanceFunction.calculateCosineSimilarity(myfile_reader.TFIDF[i], ClusterCenters[centerIndex[i]]);
           Ek+=myDistanceFunction.calculateCosineSimilarity(myfile_reader.TFIDF[i], ClusterCenters[centerIndex[i]],this.theta);
       }

       double Dm=-1;
       for(int i=0; i<ClusterCenters.length; i++)
       {
           for(int j=i+1; j<ClusterCenters.length; j++)
           {
               //double d=myDistanceFunction.calculateCosineSimilarity(ClusterCenters[i],ClusterCenters[j]);
               double d=myDistanceFunction.calculateCosineSimilarity(ClusterCenters[i],ClusterCenters[j],this.theta);
               if (Dm < d)
               {
                   Dm=d;
               }
           }
       }

       double PBM=(Dm*E1/Ek)/ClusterCenters.length;
       //double PBM=.3*(Dm*E1/Ek)+.7*(1/ClusterCenters.length);


       //System.out.println("Dm="+Dm);
       //System.out.println("K="+ClusterCenters.length);
       //System.out.println("E1="+E1);
       //System.out.println("Ek="+Ek);
       //System.out.println("PBM="+PBM);
       return centerIndex;

    }





    
}
