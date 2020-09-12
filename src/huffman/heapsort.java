//Heap.java
package huffman;



public class heapsort {

	
	private int heapsize;
	private tree heaptree[];
	private tree temp;
	private tree temptree;
	private int index;
	
	public heapsort()
	{	//heap 초기화
		
		heapsize = 0;
		heaptree = new tree[300]; // 힙트리를 300으로 세팅
			
	}
	
	
	
	
	public tree heapdelete()
	{	
		
		temp = heaptree[1]; // 젤 위값
		temptree = heaptree[heapsize--]; // 젤 아래값
		
		int PNode=1; // 부모노드
		int CNode=2; // 자식노드
		
		while(CNode <= heapsize)
		{
			if( heaptree[CNode].getter_data() >= temptree.getter_data() ) 
				break;
			
			if((CNode<heapsize) && (heaptree[CNode]).getter_data() > heaptree[CNode+1].getter_data())
				CNode++;
			
				
			heaptree[PNode] = heaptree[CNode];
			PNode = CNode;
			CNode *= 2; // 삭제하고 값을 바꾸는 과정
		}
		
		heaptree[PNode] = temptree;
		return temp;
	}
	

	public void heapinsert(tree tree)	
	{
		index = ++heapsize;
		
		while((index!=1) && (heaptree[index/2].getter_data()>tree.getter_data())) // 부모가 더 크면
		{
			heaptree[index] = heaptree[index/2];
			index = index/2;
		}
		heaptree[index] = tree;
	}
	
	
	public tree Min()
	{
		if(heapsize == 0)	//0이면 널
			return null;
		else // 아니면 루트값
			return heaptree[1];
	}
	
	public int getter_hsize()
	{
		return heapsize;
	}
	
}



