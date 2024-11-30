package INTERVIEW_JAVA;

public class Test {
	
	public static void main(String args[])
	{
		RemotWebDriver1 c=new Chrome();
		
		//Takescreenshot v=(Takescreenshot)c;
		c.screenshot();
 		
		
		
		
		
		
	
	
	}

}
interface WebDriver1
{
	void get();
	void find();
}
interface Takescreenshot
{
	void screenshot();
}
class RemotWebDriver1 implements WebDriver1,Takescreenshot
{

	@Override
	public void get() {
		System.out.println("get");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
		System.out.println("find");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void screenshot() {
		
		System.out.println("screenshot");
		// TODO Auto-generated method stub
		
	}
	
	
	
}
class Chrome extends RemotWebDriver1
{
	public void get()
	{
		System.out.println("get1");
	}
	public void hai()
	{
		System.out.println("hai1");
	}
}
class Edge extends RemotWebDriver1
{
	public void get12()
	{
		System.out.println("get12");
	}
	public void hai12()
	{
		System.out.println("hai12");
	}
}

