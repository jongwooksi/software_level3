package huffman;

public class table 
{
	public char datach; //������ �ƽ�Ű��
	public String data; // ���ڰ�
	public int cnt = 0; // �󵵼�
	public StringBuffer code = new StringBuffer(); // �������ڵ�
	public int ascii; // �ƽ�Ű�ڵ尪
	

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


