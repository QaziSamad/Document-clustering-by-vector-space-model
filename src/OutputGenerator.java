
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author santu
 */
public class OutputGenerator {
    int[] clusters;
    String inputFolder;
    String outputFolder;
    int K;

    public OutputGenerator(int[] clusters, String inputFolder,String outputFolder,int K)
    {
        this.clusters=clusters;
        this.inputFolder=inputFolder;
        this.outputFolder=outputFolder;
        this.K=K;
    }

    void GenerateOutput()
    {
        String parentDir="";
        String sourceDir="";
        String destDir="";
        try
        {
            parentDir = new File(".").getCanonicalPath();
            System.setProperty( "user.dir", parentDir);
            parentDir = new File(".").getCanonicalPath();
            sourceDir= parentDir+"\\"+this.inputFolder;
            destDir= parentDir+"\\"+this.outputFolder;
            System.out.println("parentDir:  "+parentDir);
            System.out.println("sourceDir:  "+sourceDir);
            System.out.println("destDir:  "+destDir);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
     

        File f1 = new File(destDir);
        if(f1.exists()==true)
        {
            deleteDir(f1);
        }
        f1.mkdir();

        try
        {
            for(int i=0; i<this.K; i++)
            {
                File f2 = new File(destDir+"/"+String.valueOf(i));
                f2.mkdir();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }




        f1 = new File(sourceDir);
        if (f1.isDirectory())
        {
            String s[] = f1.list();

            for (int i=0; i <s.length; i++)
            {
                File f = new File(sourceDir + "/" + s[i]);
                if(f.isFile())
                {
                    try
                    {
                        InputStream in =new FileInputStream(sourceDir + "/" + s[i]);
                        int n = in.available();
                        byte[] content=new byte[n];
                        in.read(content);
                        OutputStream out = new FileOutputStream(destDir+"/"+this.clusters[i]+"/"+s[i]);
                        out.write(content);
                        in.close();
                        out.close();
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

    }





    public static boolean deleteDir(File dir)
    {
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

}
