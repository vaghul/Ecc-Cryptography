package vaghul.ecc.harish;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class eccmain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File pointFile = new File("points.txt");
		PrintWriter writer;
		try {
			writer = new PrintWriter(pointFile);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			boolean bool= pointFile.createNewFile();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner in =new Scanner(System.in);
		int p,a,b,y2,y,g1,g2,na,nb,k;
		System.out.println("enter the prime number");
		p=in.nextInt();
		System.out.println("enter the a");
		a=in.nextInt();
		System.out.println("enter the b");
		b=in.nextInt();
		List< Integer > xlist = new ArrayList< Integer >();
		List< Integer > ylist = new ArrayList< Integer >();
		List< Integer > y_list = new ArrayList< Integer >();
		
		Check step1=new Check();
		for(int x=0;x<p;x++)
		{
			y2=((x*x*x)+(a*x)+b)%p;
			//System.out.println(y2);
			y=step1.check(y2,p);
//			System.out.println(y2);
			if(y!=-1)
			{
				xlist.add(x);
				ylist.add(y);
				y_list.add(p-y);
			}
		}// end of if is prime
		int i=0;
		for ( int x : xlist )
		{

			System.out.printf( "X:%d  %d", x, i );
			i++;
		}
		System.out.println("");
		i=0;
		for ( int ylis : ylist )
		{
			System.out.printf( "y:%d %d", ylis, i );
			i++;
		}
		System.out.println("");
		for ( int y1 : y_list )
	System.out.printf( "y':%d ", y1);
		
		privateKey step2 = new privateKey();
		System.out.println("");
		System.out.println("enter the G1");
		g1=in.nextInt();
		System.out.println("enter the G2");
		g2=in.nextInt();
		System.out.println("enter nA's Key");
		na=in.nextInt();
		System.out.println("enter nB's Key");
		nb=in.nextInt();
		System.out.println("Enter k");
		k=in.nextInt();
		
		int xr=g1,yr=g2,lam=0;
		int ax,ay,bx,by;
		
		String t=step2.loopadd(na, lam, xr, yr, g1, g2, p, a);
		String t1[]=t.split(",");     // if any problem exists then use t2,t3,t4
		ax=Integer.parseInt(t1[0]);
		ay=Integer.parseInt(t1[1]);
		//System.out.println(t1);
		System.out.println("point 1: ("+ax+","+ay+")");
		
		
		xr=g1;yr=g2;
		t=step2.loopadd(nb, lam, xr, yr, g1, g2, p, a);
		t1=t.split(",");
		bx=Integer.parseInt(t1[0]);
		by=Integer.parseInt(t1[1]);
		//System.out.println(t1);
		System.out.println("point 2: ("+bx+","+by+")");
		System.out.println("Enter the choice of key");
		int ch=in.nextInt();
		
		
		//System.out.println("shared key of A");
		xr=bx;yr=by;
		int kax,kay;
		t=step2.loopadd(na, lam, xr, yr, bx, by, p, a);
		t1=t.split(",");
		kax=Integer.parseInt(t1[0]);
		kay=Integer.parseInt(t1[1]);
		//System.out.println(t1);
		//System.out.println("kax"+kax+"kay"+kay);
				
		//System.out.println("shared key of B");
    	xr=ax;yr=ay;
    	int kbx,kby;
		t=step2.loopadd(nb, lam, xr, yr, ax, ay, p, a);
	    t1=t.split(",");
		kbx=Integer.parseInt(t1[0]);
		kby=Integer.parseInt(t1[1]);
		//System.out.println(t1);
		//System.out.println("kbx"+kbx+"kby"+kby);
		if((kax==kbx)&&(kay==kby))
		System.out.println("shared key succes");
		else
		System.out.println("shared key failed");	
		encoding step3=new encoding();
		
		String Str1="";
		
		//System.out.println("enter the string");
		Charset encoding = Charset.defaultCharset();
		String filename="test.txt";
       	readFile rfile= new readFile();
       	File file = new File(filename);
        
 	     
		Map<Character, Integer> m1 = new HashMap<Character, Integer>(); 
	    step3.mapString(m1); 
        Map<Integer, Character > m2 = new HashMap<Integer, Character>(); 
	    step3.mapInt(m2); 
	    int px1=0,py1=0,px11=0;
        try {
			Str1=rfile.handleFile(file, encoding);
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        char[] Str2 = new char[Str1.length()];
		 Str1.getChars(0, Str1.length(), Str2, 0);
		     for(char cha : Str2)
		     {
			    if(cha !=' ')
			    {
			    for(int j=1;j<=k-1;j++)  // put for loop out of cal 
	            {
			
	            	String points=step3.getPoints(m1,cha,p,a,b,k,j);
	            	if(points!=null)
	            	{
				    	String pa[]=points.split(",");     // if any problem exists then use t2,t3,t4
				    	px1=Integer.parseInt(pa[0]);
						py1=Integer.parseInt(pa[1]);
						px11=Integer.parseInt(pa[2]);
						System.out.println("Point for "+cha+":"+px1+", "+py1);
						try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("points.txt", true)))) {
						    out.println("Point for "+cha+":( "+px1+", "+py1+" )");
						    
						    
						    int p1=0;
						    if(px11==0)
						    p1=px1;
						    else
						    p1=px11;// two points for a character
						    String l=step3.getChar(m2,p1,p,a,b,k);
						   int pmx=px1,pmy=py1;
						   
						   encryption step4=new encryption();
						   if(ch==1)
						   {
							   t=step4.encode(k,lam,g1,g2,p,a,pmx,pmy,ax,ay);
								 t1=t.split(",");     // if any problem exists then use t2,t3,t4
								int cmx1=Integer.parseInt(t1[0]);
								int cmy1=Integer.parseInt(t1[1]);
								int cmx2=Integer.parseInt(t1[2]);
								int cmy2=Integer.parseInt(t1[3]);
									
								System.out.println("Point are { ("+cmx1+","+cmy1+"),("+cmx2+","+cmy2+") }");
								try(PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter("points.txt", true)))) {
								    out2.println("Encrypted Point for "+cha+"{ ("+cmx1+","+cmy1+"),("+cmx2+","+cmy2+") }");
								}
								catch (IOException e) {
							    //exception handling left as an exercise for the reader
								}
								t=step4.decode(cmx2,cmy2,na,cmx1,cmy1,p,a);
									t1=t.split(",");     // if any problem exists then use t2,t3,t4
										int dec1=Integer.parseInt(t1[0]);
										int dec2=Integer.parseInt(t1[1]);
										
										if((dec1==pmx)&&(dec2==pmy))
											System.out.println("Encryption sucessful");
										else
											System.out.println("Encryption not sucessful");
								
						   }
						   else
						   {
							    t=step4.encode(k,lam,g1,g2,p,a,pmx,pmy,bx,by);
										t1=t.split(",");     // if any problem exists then use t2,t3,t4
										int cmx1=Integer.parseInt(t1[0]);
										int cmy1=Integer.parseInt(t1[1]);
										int cmx2=Integer.parseInt(t1[2]);
										int cmy2=Integer.parseInt(t1[3]);
											
										System.out.println("Point are { ("+cmx1+","+cmy1+"),("+cmx2+","+cmy2+") }");
										
										t=step4.decode(cmx2,cmy2,nb,cmx1,cmy1,p,a);
											t1=t.split(",");     // if any problem exists then use t2,t3,t4
												int dec1=Integer.parseInt(t1[0]);
												int dec2=Integer.parseInt(t1[1]);
												
												if((dec1==pmx)&&(dec2==pmy))
													System.out.println("Encryption sucessful");
												else
													System.out.println("Encryption not sucessful");
							   
						   }
						
						    
						    
						    
						}catch (IOException e) {
						    //exception handling left as an exercise for the reader
						}
						break;
			  	  	}
	            }
			    }
			    else
			    {
			    	try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("points.txt", true)))) {
					    out.println("Space here");
					}catch (IOException e) {
					    //exception handling left as an exercise for the reader
					}
			    }
		      }
	        
		
     
    /*    int p1=0;
	    if(px11==0)
	    p1=px1;
	    else
	    p1=px11;// two points for a character
	    String l=step3.getChar(m2,p1,p,a,b,k);
	   int pmx=px1,pmy=py1;
	   
	   encryption step4=new encryption();
	   if(ch==1)
	   {
		   t=step4.encode(k,lam,g1,g2,p,a,pmx,pmy,ax,ay);
			 t1=t.split(",");     // if any problem exists then use t2,t3,t4
			int cmx1=Integer.parseInt(t1[0]);
			int cmy1=Integer.parseInt(t1[1]);
			int cmx2=Integer.parseInt(t1[2]);
			int cmy2=Integer.parseInt(t1[3]);
				
			System.out.println("Point are { ("+cmx1+","+cmy1+"),("+cmx2+","+cmy2+") }");
			
			t=step4.decode(cmx2,cmy2,na,cmx1,cmy1,p,a);
				t1=t.split(",");     // if any problem exists then use t2,t3,t4
					int dec1=Integer.parseInt(t1[0]);
					int dec2=Integer.parseInt(t1[1]);
					
					if((dec1==pmx)&&(dec2==pmy))
						System.out.println("Encryption sucessful");
					else
						System.out.println("Encryption not sucessful");
			
	   }
	   else
	   {
		    t=step4.encode(k,lam,g1,g2,p,a,pmx,pmy,bx,by);
					t1=t.split(",");     // if any problem exists then use t2,t3,t4
					int cmx1=Integer.parseInt(t1[0]);
					int cmy1=Integer.parseInt(t1[1]);
					int cmx2=Integer.parseInt(t1[2]);
					int cmy2=Integer.parseInt(t1[3]);
						
					System.out.println("Point are { ("+cmx1+","+cmy1+"),("+cmx2+","+cmy2+") }");
					
					t=step4.decode(cmx2,cmy2,nb,cmx1,cmy1,p,a);
						t1=t.split(",");     // if any problem exists then use t2,t3,t4
							int dec1=Integer.parseInt(t1[0]);
							int dec2=Integer.parseInt(t1[1]);
							
							if((dec1==pmx)&&(dec2==pmy))
								System.out.println("Encryption sucessful");
							else
								System.out.println("Encryption not sucessful");
		   
	   }
	*/
	}

}
