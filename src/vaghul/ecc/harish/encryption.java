package vaghul.ecc.harish;

public class encryption {

	public  String pointadd(int lam,int xr,int yr,int g1,int g2,int p,int a){
		lam= multiply(xr,yr,g1,g2, p, a);
		//System.out.println(lam);
		int temp=calxr(lam, xr, g1, p);
		//System.out.println(temp);
		yr=calyr(lam, xr, temp,yr,p);
		xr=temp;
		//System.out.println(yr);
		return xr+","+yr;
		
	}
	
	
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
		//System.out.println(lam1+" "+lam2);
		if((lam1%lam2==0))  //common module check
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
	
	public  String decode(int cmx2,int cmy2, int nb,int cmx1,int cmy1,int p,int a){
		int lam=0,xr=cmx1,yr=cmy1,temp1,temp2;
		String t=loopadd(nb, lam, xr, yr, cmx1, cmy1, p, a);
		String t1[]=t.split(",");     // if any problem exists then use t2,t3,t4
		temp1=Integer.parseInt(t1[0]);
		temp2=Integer.parseInt(t1[1]);
		
		temp2=temp2-(2*temp2);
		while(temp2<0)
			temp2=temp2+p;
	
		//System.out.println(temp2);
		xr=cmx2;yr=cmy2;
		
		t=pointadd( lam, xr, yr, temp1, temp2, p, a);
		t1=t.split(",");     // if any problem exists then use t2,t3,t4
		temp1=Integer.parseInt(t1[0]);
		temp2=Integer.parseInt(t1[1]);
		return temp1+","+temp2;
	}
	
	public  String encode(int k, int lam,  int g1, int g2, int p, int a, int pmx,int pmy,int bx, int by){
		int xr=g1,yr=g2,cmx1,cmy1;
		String t=loopadd(k, lam, xr, yr, g1, g2, p, a);
		String t1[]=t.split(",");     // if any problem exists then use t2,t3,t4
		cmx1=Integer.parseInt(t1[0]);
		cmy1=Integer.parseInt(t1[1]);
		//System.out.println(t1);
		//System.out.println("cmx1 "+cmx1+"cmy1 "+cmy1);
		xr=bx;yr=by;
		int temp1,temp2;
		t=loopadd(k, lam, xr, yr, bx, by, p, a);
		t1=t.split(",");     // if any problem exists then use t2,t3,t4
		temp1=Integer.parseInt(t1[0]);
		temp2=Integer.parseInt(t1[1]);
		
		xr=pmx;yr=pmy;
		t=pointadd( lam, xr, yr, temp1, temp2, p, a);
		t1=t.split(",");     // if any problem exists then use t2,t3,t4
		int cmx2=Integer.parseInt(t1[0]);
		int cmy2=Integer.parseInt(t1[1]);
		//System.out.println(t1);
		//System.out.println("cmx1 "+cmx2+"cmy1 "+cmy2);
		return cmx1+","+cmy1+","+cmx2+","+cmy2;
		
	}

}
