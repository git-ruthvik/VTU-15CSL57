import java.util.Scanner;
class BellmanFord{
	private int distances[];
	private int nov;
	public static final int MAX_VALUE=999;
	public BellmanFord(int nov)
	{
		this.nov=nov;
		distances=new int[nov + 1];
	}
	public void BellmanFordEvaluation(int source,int destination,int am[][]){
		for(int node=1;node<=nov;node++)
			distances[node]=MAX_VALUE;
		distances[source]=0;
		for(int node=1;node<=nov-1;node++)
		{
			for(int sn=1;sn<=nov;sn++)
			{
				for(int dn=1;dn<=nov;dn++)
				{
					if(am[sn][dn]!=MAX_VALUE)
					{
						if(distances[dn]>distances[sn]+am[sn][dn])
							distances[dn]=distances[sn]+am[sn][dn];
					}
				}
			}
		}
		for(int sn=1;sn<=nov;sn++)
		{
			for(int dn=1;dn<=nov;dn++)
			{
				if(am[sn][dn]!=MAX_VALUE)
				{
					if(distances[dn]>distances[sn]+am[sn][dn])
						System.out.println("The Graph Contains Negative Edge Cycle");
				}
			}
		}
		for(int vertex=1;vertex<=nov;vertex++)
		{
			if(vertex==destination)
				System.out.println("Distance of source "+source+" to "+vertex+" is "+distances[vertex]);
		}
	}
}
public class p8{
        public static final int MAX_VALUE=999;
	public static void main(String[] args)
	{
                
		int nov=0;
		int source,destination;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of vertices : ");
		nov=sc.nextInt();
		int am[][]= new int[nov+1][nov+1];
		System.out.println("Enter the adjacency matrix : ");
		for(int sn=1;sn<=nov;sn++)
		{
			for(int dn=1;dn<=nov;dn++)
			{
				am[sn][dn]=sc.nextInt();
				if(sn==dn)
				{
					am[sn][dn]=0;
					continue;
				}
				if(am[sn][dn]==0)
					am[sn][dn]=MAX_VALUE;
			}
		}
		System.out.print("Enter source vertex : ");
		source=sc.nextInt();
		System.out.print("Enter destination vertex : ");
		destination=sc.nextInt();
		BellmanFord bf=new BellmanFord(nov);
		bf.BellmanFordEvaluation(source,destination,am);
		sc.close();
	}
}

