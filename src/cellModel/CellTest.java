package cellModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {
	private static Cell celltest=new Cell(100, 100);
	@Before
	public void setUp() throws Exception {
		celltest.randomCell();
	}

	@Test
	public void testCell() {
		Cell test2=new Cell(100,100);
		assertNotSame(celltest,test2);
	}

	@Test
	public void testRandomCell() {
		int cell[][];
    	cell=celltest.grid;
    	for (int i = 0; i <100; i++)
            for (int j = 0; j <100; j++)
            	assertEquals(0.5,cell[i][j],0.5);
	}

	@Test
	public void testDeleteAllCell() {
		celltest.deleteAllCell();
        for (int i = 0; i <100; i++)
            for (int j = 0; j <100; j++)
            	assertEquals(0,celltest.grid[i][j]);
	}
 
	@Test
	public void testUpdate() {
		int num1=celltest.nowGeneration;
                int[][] newGrid = new int[100 + 2][100 + 2];
                for (int i = 1; i <= 100; i++)
                    for (int j = 1; j <= 100; j++)
                        switch (celltest.getNeighborCount(i, j)) {
                            case 2:
                                newGrid[i][j] = celltest.grid[i][j]; //细胞状态保持不变
                                break;
                            case 3:
                                newGrid[i][j] = 1; // Cell is alive.
                                break;
                            default: 
                                newGrid[i][j] = 0; // Cell is dead.
                        }
				celltest.nowGeneration++;
				num1++;
				assertEquals(num1,celltest.nowGeneration);
				celltest.update();
				for (int i = 1; i <= 100; i++)
                    for (int j = 1; j <= 100; j++)
				assertEquals( newGrid[i][j], celltest.grid[i][j]);

	}

	@Test
	public void testGetNeighborCount() {
		int cell[][];
		cell=celltest.grid;
		int n;
		for (int i = 1; i <99; i++) {
            for (int j = 1; j <99; j++) {
				n=cell[i-1][j-1]+cell[i-1][j]+cell[i-1][j+1]+cell[i][j-1]+cell[i][j+1]+cell[i+1][j-1]+cell[i+1][j]+cell[i+1][j+1];
				assertEquals(n,celltest.getNeighborCount(i, j));
            }
	}

}
}
