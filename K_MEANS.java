/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k_means;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Steven
 */
class means
{
    double x;
    double y;

    public means(double a,double b) {
   
        x=a;
        y=b;
    }
    
    public double calcdistance(double x2,double y2)
    {
        double sqr1,sqr2;
        sqr1=(x-x2)*(x-x2);
        sqr2=(y-y2)*(y-y2);
        double adds=sqr1+sqr2;
        return  Math.sqrt(adds);
        
    }
   
    public void reset(double x2,double y2)
    {
    x=x2;
    y=y2;
    }   
}


public class K_MEANS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int k,lines=0;


        
        //1.get the no of clusters from user
        Scanner scn = new Scanner(System.in);
        k=scn.nextInt();
        
       //2.count the no of lines in file
       File file = new File("ip1.txt");
       try (BufferedReader br = new BufferedReader(new FileReader(file))) 
       {
        while((br.readLine())!=null)
        {
           lines++;
        }
       }
       catch(Exception e)
       {}

       //3.declare input array
       double[][] input = new double[lines][2];  
       
       //4.take the input from the file         
       int colc=0;
       File f = new File("ip1.txt");
       try (BufferedReader b = new BufferedReader(new FileReader(f))) {
       String line;
       while ((line = b.readLine()) != null) { 
            String[] split = line.split(" ");
            for(int i=0;i<split.length;i++)
            {
                input[colc][i]=Double.parseDouble(split[i]);            
            }
            colc+=1; //increment j index of array
       }}  
       catch(Exception e)
       {System.out.print(e);}
       

       //5.innitialize the cluster medians
       double[][] kmeans=new double[lines][k+1];
         for(int i=0;i<lines;i++)
           {    for(int j=0;j<k+1;j++)
                   kmeans[i][j]=-2;
           }
       means[] cluster_mean=new means[k];       
       
       for(int i=0;i<k;i++)
       {
        cluster_mean[i]=new means(input[i][0],input[i][1]);
       }
      
       
  
       
       
       ArrayList change = new ArrayList();
       change.add(-1);
       
       while(!change.isEmpty())
       {
           change.clear();
           
           //calculate distance between pts and cluster centres
           for(int i=0;i<lines;i++)
               for(int j=0;j<k;j++)
               {
                   kmeans[i][j]=cluster_mean[j].calcdistance(input[i][0],input[i][1]);
               }
       
           //find the the cluster of min distance for each point
           for(int i=0;i<lines;i++)
           {
               double min=kmeans[i][0];
               int minpos=0;
              for(int j=0;j<k;j++)
              {
                  if(kmeans[i][j]<min)
                  {
                      min=kmeans[i][j];
                      minpos=j;
                  }   
              }
             
              if(kmeans[i][k]!=minpos)
                  change.add(i);
              kmeans[i][k]=minpos;
           }
           
           //find the new cluster centre
           double sumx=0,sumy=0,num=0; 
           for(int i=0;i<k;i++)
           {   sumx=0;sumy=0;num=0;
               for(int j=0;j<lines;j++)
               {
                   if(kmeans[j][k]==i)
                   {
                       sumx+=input[j][0];
                       sumy+=input[j][1];
                       num++;
                   }
               }
               cluster_mean[i].reset(sumx/num,sumy/num);
               
           }   
               
          
       
       }
      
       
     
           for(int i=0;i<lines;i++)
           {    
                System.out.println(kmeans[i][k]+" ");
                System.out.print("\n");
           } 
       
       
  
      // System.out.print(lines);
       
  
       /*
       for(int i=0;i<lines;i++)
           for(int j=0;j<k;j++)
               System.out.print(input[i][j]);
       
       */
       
       
        
        
        
        
    }
    
}
