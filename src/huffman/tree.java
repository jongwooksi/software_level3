package huffman;


public class tree {
	
	private tree LNode; // ���ʳ��
	private tree RNode; // �����ʳ��
	private int data; 
	private char ascii;
	private StringBuffer hcode=new StringBuffer();
	
	public tree(){}
	  
	public tree(int treedata)
	{		
		
		data = treedata;
	}
	
	public tree(int treedata, tree LNodedata, tree RNodedata)
	{
		LNode = LNodedata;
		RNode = RNodedata;
		data = treedata;
		
	}
	
	// �Ʒ��� ���� set, get�Լ�
	public void setter_LNode(tree LNodedata)
	{
		LNode = LNodedata;
	}
	
	public void setter_RNode(tree RNodedata)
	{
		RNode = RNodedata;
	}
	public tree getter_LNode()
	{
		return LNode;
	}
	
	public tree getter_RNode()
	{
		return RNode;
	}
	
	
	
	
	
	
	
	public void setter_data(int treedata)
	{
		data = treedata;
	}
	public int getter_data()
	{
		return data;
	}
	
	
	public void setter_asc(char asc)
	{
		ascii = asc;
	}

	public char getter_asc()
	{
		return ascii;
	}
	
	
	public void setter_code(String treecd)
	{
		hcode.append(treecd);
	}
	
	public StringBuffer getter_code()
	{
		return hcode;
	}
	
	
	
	
	
	
}
