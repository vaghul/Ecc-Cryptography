package vaghul.ecc.harish;

import java.util.Map;
import java.util.Scanner;

public class privateKey {


			// TODO Auto-generated method stub
	public  String loopadd(int na,int lam,int xr,int yr,int g1,int g2,int p,int a)
	{
		if(na!=1)                                                                                       
		{
			
			for(int i=1;i<na;i++)
			{
				
			 lam=multiply(xr,yr,g1,g2, p, a);
				//System.out.println(lam);
				//System.out.println("lam"+lam+"xr"+xr+"g1"+g1+"p"+p);
				int temp=calxr(lam, xr, g1, p);
				yr=calyr(lam, xr, temp,yr,p);
				xr=temp;
			}
		}
	return xr+","+yr;
	}
	
	public  int calxr(int lam,int xp,int xq,int p)
	{
		int xr,i=1;
		xr= ((lam*lam)-xp-xq);
		while(xr<0)
			{
			xr=xr+(p*i);
			i++;
			}
		return xr%p;
			}
	public  int calyr(int lam,int xp,int xr,int yp,int p)
	{
		int yr,i=1;
		yr= (lam*(xp-xr)-yp);
		while(yr<0)
		{
		yr=yr+(p*i);
		i++;
		}
		return yr%p;
	
	}
	public  int multiply(int x1,int y1,int x2,int y2,int p,int a)
	{
		int lam1,lam2,lam;
		if((x1==x2)&&(y1==y2))
		{
		lam1=(3*(x1*x1))+a;
		lam2=2*y1;
		}
		else
		{
		lam1=y2-y1;
		lam2=x2-x1;
		}	
		if(lam1%lam2==0)  //common module check
		{
			lam=(lam1/lam2)%p;
			return lam;
		}
		else
		{
			lam=makemodul(lam1,lam2,p);
			return lam;
		}
	}
	
	
	public  int makemodul(int l1,int l2,int p) 
	{
		int result;
		for(int i=1;;i++)
		{
			result=(l1+(p*i));
			if(result%l2==0)
			{
				result/=l2;
				return result%p;
			}
		}
	}
	
	public  void mapValues(Map m1)
	{
    for(int l1=0,l=48;l<58;l1++,l++)
      {
      m1.put((char)l,l1);
      }
      for(int l1=10,l=97;l<123;l1++,l++)
      {
      m1.put((char)l,l1);
      }
	}

	
	public  String getPoints(Map m1,char ch,int p,int a,int b,int k){
		int y2,y;
        String point = null;
        
       		 int val=(Integer) m1.get(ch);
       		 int x;
       		
       		 for(int j=1;j<=k;j++)
       		 {
					x=(val*k)+j;
					x=x%p;
					y2= (x*x*x)+(a*x)+b;
					y2=y2%p;
					y= (int)Math.sqrt(y2);
					point= x+","+y;
					/*if(y*y==y2)
					{	
						//Perfect Square(Quad Residue)
						//		System.out.println("Point for "+ch+" :("+x+","+y+")");
						point= x+","+y;
					}	*/
			}
		return point;
 	}				
	

}
