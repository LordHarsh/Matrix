import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;
class MatrixCalcInverse 
{
    double Mat[][];
    double Adj[][];
    double Inv[][];
    int size;
    MatrixCalcInverse(int t)
    {
        size=t;
        Mat =new double[size][size];
        Adj =new double[size][size];
        Inv =new double[size][size];
    }

    void input()        // to take input
    {
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
            {
                Scanner sc=new Scanner(System.in);
                System.out.println("Enter the element");
                Mat[i][j]=sc.nextDouble();
            }
    }

    void display(double a[][])      // to display matrices of double data type
    {
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
                System.out.print(a[i][j]+"\t\t");
            System.out.println();
        }
    }

    double findDeterminant(double a[][])      //to find determinant of passed matrix
    {
        int len=a.length;
        if (len==1)
        {
            return a[0][0];
        }
        if(len==2)     // returns the value for 
        {
            return(a[0][0]*a[1][1]-a[0][1]*a[1][0]);
        }
        double anew[][]=new double[len-1][len-1];     // to save new matrix after delearing values
        double value=0;
        for(int k=0; k<len; k++)
        {
            for(int i=1; i<len; i++)
            {
                for(int j=0; j<len-1; j++)
                {
                    int temp=j;
                    if(j>=k)
                    {
                        temp+=1;
                    }
                    anew[i-1][j]=a[i][temp];
                }
            } 
            value =value+(double) (Math.pow(-1,k) *(a[0][k]) * findDeterminant(anew) );
        }
        return value;
    }

    void findAdjointMat()       // to find adjount of inputed matrix
    {
        double temp[][]=new double[size][size];
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                double newMat[][]=new double[size-1][size-1];
                int t1=1;
                int t2=0;
                for(int x=0; x<size-1; x++)
                {
                    t1=x;
                    if (x>=i) 
                        t1++;
                    for(int y=0; y<size-1; y++)
                    {
                        t2=y;
                        if(y>=j) 
                            t2++;
                        newMat[x][y]=Mat[t1][t2];
                    }
                }
                temp[i][j]=Math.pow(-1,(i+j))*findDeterminant(newMat);
            }
        }
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
                Adj[i][j]=temp[j][i];
    }

    void findInverse()      // to find inverse of inputted matrix
    {
        double det=findDeterminant(Mat);
        System.out.println("Determinant of orignal matrix is"+det);
        if(det==0)
            return;
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                Inv[i][j]=((double)Adj[i][j]/det);       
            }
        }
    }

    public static void main (String agrs[]) throws ArrayIndexOutOfBoundsException
    {
        try
        {
            Scanner sc=new Scanner (System.in);
            System.out.print("Enter the size of square matrix-\t");
            int temp=sc.nextInt();
            MatrixCalcInverse obj=new MatrixCalcInverse(temp);
            obj.input();
            System.out.println("This  is your Original Matrix-");
            obj.display(obj.Mat);
            obj.findAdjointMat();
            System.out.println("This  is your Adjoint Matrix-");
            obj.display(obj.Adj);
            obj.findInverse();
            System.out.println("This  is your Inverse Matrix-");
            obj.display(obj.Inv);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
