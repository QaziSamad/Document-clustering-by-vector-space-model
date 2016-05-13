/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author santu
 */

import java.io.*;
import java.util.*;



public class File_Reader {
    String dirName;
    HashMap vocabularyCount;
    HashMap vocabulary;
    ArrayList<HashMap> wordCountArrayList;
    double [][]TFIDF;
    int []prevCenterIndex;
    HashMap IDF;
    double [][]data;

    public File_Reader(String dirName)
    {
        this.dirName=dirName;
        this.wordCountArrayList=new ArrayList<HashMap>();
        this.vocabulary=new HashMap();
        this.vocabularyCount=new HashMap();
        this.IDF= new HashMap();
        
    }

    public void ReadFiles()
    {
        String dirname = this.dirName;
        String currentDir="";
        try
        {
            currentDir = new File(".").getCanonicalPath();
            System.out.println(currentDir);
            System.setProperty( "user.dir", currentDir+"\\"+dirname );
            System.out.println("entered diredtory: "+new File(".").getCanonicalPath());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        File f1 = new File(dirname);
        if (f1.isDirectory())
        {
            //System.out.println("Directory of " + dirname);
            String s[] = f1.list();

            for (int i=0; i <s.length; i++)
            {
                File f = new File(dirname + "/" + s[i]);
                if(f.isFile())
                {
                    try
                    {
                        FileReader fr= new FileReader(f);
                        char buffer[]=new char[(int)f.length()];
                        fr.read(buffer);
                        String article=new String(buffer);
                        article=article.replaceAll("\n", "");
                        article=article.replaceAll("[0-9]+", "NUMBER");
                        article=article.replaceAll("[^A-Za-z]", " ");
                        article=article.replaceAll("[ ]+", " ");
                        article=article.toLowerCase();
                        //System.out.println(article);
                        StringTokenizer tokens = new StringTokenizer(article," ");
                        //System.out.println(tokens.countTokens());
                        HashMap wordCount= new HashMap();


                        while (tokens.hasMoreTokens())
                        {
                            String key=tokens.nextToken();

                            if(this.vocabularyCount.containsKey(key))
                            {
                                this.vocabularyCount.put(key, Integer.parseInt(this.vocabularyCount.get(key).toString())+1);
                            }
                            else
                            {
                                this.vocabularyCount.put(key,1);
                            }

                        }
                        //System.out.println("\n");
                        fr.close();
                    }
                    catch(Exception e)
                    {
                        System.out.println("Exception:"+e.toString());
                    }
                }
            }


            Set aset= this.vocabularyCount.entrySet();
            // Get an iterator
            Iterator aitr = aset.iterator();
            // Display elements
            while(aitr.hasNext())
            {
                Map.Entry me = (Map.Entry)aitr.next();
                if(Integer.parseInt(me.getValue().toString())< 3)
                {
                    aitr.remove();
                }
            }



            s= f1.list();
            for (int i=0; i <s.length; i++)
            {
                File f = new File(dirname + "/" + s[i]);
                if(f.isFile())
                {
                    try
                    {
                        FileReader fr= new FileReader(f);
                        char buffer[]=new char[(int)f.length()];
                        fr.read(buffer);
                        String article=new String(buffer);
                        article=article.replaceAll("\n", "");
                        article=article.replaceAll("[0-9]+", "NUMBER");
                        article=article.replaceAll("[^A-Za-z]", " ");
                        article=article.replaceAll("[ ]+", " ");
                        article=article.toLowerCase();
                        //System.out.println(article);
                        StringTokenizer tokens = new StringTokenizer(article," ");
                        //System.out.println(tokens.countTokens());
                        HashMap wordCount= new HashMap();
                        
                        
                        while (tokens.hasMoreTokens())
                        {
                            String key=tokens.nextToken();
                            if(this.vocabularyCount.containsKey(key))
                            {
                                if(vocabulary.containsKey(key))
                                {
                                    if(wordCount.containsKey(vocabulary.get(key)))
                                    {
                                        int x=(Integer)wordCount.get(vocabulary.get(key));
                                        x++;
                                        wordCount.put(vocabulary.get(key),x);
                                    }
                                    else
                                    {
                                        wordCount.put(vocabulary.get(key),1);
                                    }
                                }
                                else
                                {
                                    vocabulary.put(key,(Integer)vocabulary.size());
                                    wordCount.put(vocabulary.get(key),1);
                                }
                            }
                        }
                        this.wordCountArrayList.add(wordCount);
                      
                        //System.out.println("\n");
                        fr.close();
                    }
                    catch(Exception e)
                    {
                        System.out.println("Exception:"+e.toString());
                    }
                }
                
            }

            
            try
            {
                FileOutputStream fos =new FileOutputStream("output2.csv");
                DataOutputStream dos=new DataOutputStream(fos);

                Set set= vocabulary.entrySet();
                // Get an iterator
                Iterator itr = set.iterator();
                // Display elements
                while(itr.hasNext())
                {
                    Map.Entry me = (Map.Entry)itr.next();
                    dos.writeChars(me.getKey().toString());
                    dos.writeChar(',');
                }
                dos.writeChar('\n');

                set= vocabulary.entrySet();
                // Get an iterator
                itr = set.iterator();
                // Display elements
                while(itr.hasNext())
                {
                    Map.Entry me = (Map.Entry)itr.next();
                    dos.writeChars(me.getValue().toString());
                    dos.writeChar(',');
                }
                dos.writeChar('\n');

                
                
                for(int i=0; i<wordCountArrayList.size(); i++)
                {
                    set= vocabulary.entrySet();
                    // Get an iterator
                    itr = set.iterator();
                    // Display elements
                    while(itr.hasNext())
                    {
                        Map.Entry me = (Map.Entry)itr.next();
                        if(wordCountArrayList.get(i).containsKey(me.getValue()))
                        {
                            dos.writeChars(wordCountArrayList.get(i).get(me.getValue()).toString());
                        }
                        else
                        {
                            dos.writeChar('0');
                        }
                        dos.writeChar(',');
                    }
                    dos.writeChar('\n');
                }
                
            }catch(Exception e)
            {
                System.out.println(e.toString());
            }

            


            Set set= vocabulary.entrySet();
            // Get an iterator
            Iterator itr = set.iterator();
            // Display elements
            while(itr.hasNext())
            {
                Map.Entry me = (Map.Entry)itr.next();
                for(int i=0; i<wordCountArrayList.size(); i++)
                {
                    if(wordCountArrayList.get(i).containsKey(me.getValue()))
                    {
                        if(this.IDF.containsKey(me.getValue()))
                        {
                            this.IDF.put(me.getValue(),Integer.parseInt(this.IDF.get(me.getValue()).toString())+1);
                        }
                        else
                        {
                           this.IDF.put(me.getValue(),1);
                        }
                    }
                }
                this.IDF.put(me.getValue(),Math.log(wordCountArrayList.size()/Double.parseDouble(this.IDF.get(me.getValue()).toString())));
            }




/*
            set= vocabulary.entrySet();
            // Get an iterator
            itr = set.iterator();
            // Display elements
            while(itr.hasNext())
            {
                Map.Entry me = (Map.Entry)itr.next();
                System.out.println(me.getKey()+":\t"+this.IDF.get(me.getValue()));

            }*/



            
            this.data=new double[s.length][vocabulary.size()];
            this.TFIDF=new double[s.length][vocabulary.size()];
            
            this.prevCenterIndex=new int[wordCountArrayList.size()];

            for(int i=0; i<this.data.length; i++)
            {
                for(int j=0; j < this.data[i].length; j++)
                {
                    Integer x=new Integer(j);
                    if(this.wordCountArrayList.get(i).containsKey(j))
                    {
                        String str=this.wordCountArrayList.get(i).get(j).toString();
                        this.data[i][j]=Integer.parseInt(str,10)*Double.parseDouble(this.IDF.get(j).toString());
                        this.TFIDF[i][j]=(Double.parseDouble(str)*Double.parseDouble(this.IDF.get(j).toString()))/wordCountArrayList.get(i).size();
                    }
                    else
                    {
                        this.data[i][j]=0;
                    }

                }
            }

            for(int i=0; i<this.data.length; i++)
            {
                double sum=0;
                for(int j=0; j < this.data[i].length; j++)
                {
                    sum+=this.TFIDF[i][j]*this.TFIDF[i][j];
                }
                sum=Math.sqrt(sum);
                for(int j=0; j < this.data[i].length; j++)
                {
                    sum+=this.TFIDF[i][j]*this.TFIDF[i][j];
                }
                for(int j=0; j < this.data[i].length; j++)
                {
                    this.TFIDF[i][j]=this.TFIDF[i][j]/sum;
                }
            }
                
            try
            {
                FileOutputStream fos =new FileOutputStream("output.csv");
                DataOutputStream dos=new DataOutputStream(fos);

                for(int i=0; i<this.data.length; i++)
                {
                    for(int j=0; j < this.data[i].length; j++)
                    {
                        dos.writeChars(String.valueOf(this.data[i][j]));
                        if(j < this.data[i].length-1)
                        {
                            dos.writeChar(',');
                        }
                    }
                    dos.writeChar('\n');
                }
                dos.close();
                fos.close();
            }catch(Exception e)
            {
                System.out.println(e.toString());
            }
            /*
            DistanceFunction myDistanceFunction=new DistanceFunction();
            for(int l=0; l<this.wordCountArrayList.size(); l++ )
            {
                System.out.println("file no:"+l+"\tdistance:"+myDistanceFunction.calculateEuclidianDistance(data[11],data[l]));
            }*/
            

        }
        
        System.setProperty( "user.dir", currentDir);
    }
}
