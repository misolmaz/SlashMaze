package maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    static int[][] mp= new int[350][350];
    static int n,m;
    int [][] dis={{1,0},{-1,0},{0,1},{0,-1}};
    static int tmp;
    static int flag;

    static void dfs(int x,int y)
    {
        mp[x][y]=1;
        tmp++;
        if(x<=0||x>=(3*n-1)||y<=0||y>=(3*m-1))
            flag=1;
        if(x>0&&mp[x-1][y]==0)
            dfs(x-1,y);
        if(y>0&&mp[x][y-1]==0)
            dfs(x,y-1);
        if(x<(3*n-1)&&mp[x+1][y]==0)
            dfs(x+1,y);
        if(y<(3*m-1)&&mp[x][y+1]==0)
            dfs(x,y+1);
    }

    public static void main(String[] args) throws IOException {

        String line = Files.readAllLines(Paths.get("sampleinput.txt")).get(0);
        String[] arrs = line.split(" ");
        n=Integer.parseInt(arrs[1]);
        m=Integer.parseInt(arrs[0]);

        Scanner sc = new Scanner(System.in);
        int t=0;

        //	for(int y = 0; sc.hasNextLine(); y++)

        {

            String Ss;
            char s[];

            for(int i=0;i<n;i++)
            {
                Ss = Files.readAllLines(Paths.get("sampleinput.txt")).get(i+1);
                //  Ss=sc.nextLine();
                // int foo=sc.nextInt();

                s=Ss.toCharArray();
                int l=Ss.length();
                for(int j=0;j<l;j++)
                {
                    if(s[j]=='\\') {
                        mp[3*i][3*j] = 1;
                        mp[3*i+1][3*j+1]=1;
                        mp[3*i+2][3*j+2]=1;
                    }
                    if(s[j]=='/') {
                        mp[3*i][3*j+2]=1;
                        mp[3*i+1][3*j+1]=1;
                        mp[3*i+2][3*j]=1;
                    }
                }
            }

            int ans=0;
            int sum=0;
            flag=0;
            tmp=0;

            for(int i=0;i<3*n;i++)
            {
                for(int j=0;j<3*m;j++)
                {
                    if(mp[i][j]==0)
                    {
                        dfs(i,j);
                        if(flag==0)
                        {
                            sum++;
                            ans=Math.max(ans,tmp);
                        }
                        tmp=0;
                        flag=0;
                    }
                }
            }


            t++;
            System.out.println("Maze  "+ t);

            if(sum==0)
            {
                System.out.println("There are no cycles.");
            }
            else
            {
                System.out.println(sum+ " Cycles; the longest has length "+ ans/3);
            }
            System.out.println();


        }


    }

}
