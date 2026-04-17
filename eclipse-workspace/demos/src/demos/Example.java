package demos;

public class Example
{
	public static void main(String[] args)
	{
		Stringfunction exclaim = (s) -> s + "!";
		Stringfunction ask = (s) -> s + "?";
    printFormatted("Hello", exclaim);
    printFormatted("Hello", ask);
  }
  public static void printFormatted(String str, Stringfunction format) {
    String result = format.run(str);
    System.out.println(result);
  }
}

