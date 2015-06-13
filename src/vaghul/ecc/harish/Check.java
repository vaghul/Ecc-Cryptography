package vaghul.ecc.harish;

public class Check {

	public int check(double num,int mod)
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
				if(y*y==val)
					return (int)y;
			}
			return -1;

		}
	}
}
