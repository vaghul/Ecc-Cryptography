package vaghul.ecc.harish;

import java.util.Map;

public class encoding {
	
	public  void mapString(Map m1)
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

	public  void mapInt(Map m1)
	{
    for(int l1=0,l=48;l<58;l1++,l++)
      {
      m1.put(l1,(char)l);
      }
      for(int l1=10,l=97;l<123;l1++,l++)
      {
      m1.put(l1,(char)l);
      }
	}

	public  String getPoints(Map m1,char ch,int p,int a,int b,int k,int j){
		int y2,y,x1=0;
        String point = null;
        
       		 int val=(Integer) m1.get(ch);
       		 int x;
       		 //System.out.println(val);
       		 		x=(val*k)+j;
       		 		if(x>p)
       		 		{
       		 			x1=x;
       		 		x=x%p;
       		 		}
					y2= (x*x*x)+(a*x)+b;
					
					y2=y2%p;
					//System.out.println(y2);
					y2= check(y2,p);
					//System.out.println(y2);
					
			      if(y2==-1)		
					return null;
			      else
			      {
			    	//  System.out.println(x+","+y2+","+x1);
			    	return x+","+y2+","+x1;  
			      }
	}
	
	
	public  int check(double num,int mod)
	{
		double y,val;
		y=(int)Math.sqrt(num);
		if((y*y==num)&&(y!=0))
		{
			//System.out.println("return" +y);
			return (int)y;
		}
		else
		{
			for( int i=1;i<=50;i++)
			{
				val=num+(mod*i);
				

				y=(int)Math.sqrt(val);
				//System.out.println("val="+val+"y="+y);
				//System.out.println("y val "+y+" "+val);
				if(y*y==val)
				{
					//System.out.println("y "+y);
					return (int)y;
				}
			}
			return -1;

		}
	}

	public  String getChar(Map m1,int p1,int p,int a,int b,int k){
		//System.out.println(m1);
		//System.out.println(p1);
		//System.out.println(k);
		
		double m=Math.floor((p1-1)/k);
		int n= (int)m;
		System.out.println(m);
		char val= (Character) m1.get(n);
		System.out.println("The point depicts the character is "+ val);
		return "a";
 	}

}
