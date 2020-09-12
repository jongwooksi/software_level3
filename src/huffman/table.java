package huffman;

public class table 
{
	public char datach; //문자의 아스키값
	public String data; // 문자값
	public int cnt = 0; // 빈도수
	public StringBuffer code = new StringBuffer(); // 허프만코드
	public int ascii; // 아스키코드값
	

	public table(){	}
	

	public void setter_asc(int asc)
	{
		data = String.valueOf(asc);
		ascii = asc;
	}
	
	public int getter_asc()
	{
		return ascii;
	}
}


