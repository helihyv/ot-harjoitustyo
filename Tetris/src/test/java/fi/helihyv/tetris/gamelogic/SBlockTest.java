/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.Tile;
import fi.helihyv.tetris.gamelogic.Area;
import fi.helihyv.tetris.gamelogic.TetrisTile;
import fi.helihyv.tetris.gamelogic.SBlock;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heli hyvättinen
 */
public class SBlockTest {
    
    SBlock block;
    
    public SBlockTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        block = new SBlock(100,20);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
        @Test
    public void sBlockRotatesCorrectlyFromZeroToNinetyDegrees() {
        
        block.rotate();
        
        Tile[] tiles = block.getTiles();
        
        assertEquals(120, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(140, tiles[2].getXCoordinate(), 0.1);
        assertEquals(140, tiles[3].getXCoordinate(), 0.1);
        
        assertEquals(0, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(20, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }
    
        @Test
    public void sBlockRotatesCorrectlyFromNinetyTo180Degrees() {
        
        block.rotate();
        block.rotate();
        
        Tile[] tiles = block.getTiles();
        
        assertEquals(100, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(120, tiles[2].getXCoordinate(), 0.1);
        assertEquals(140, tiles[3].getXCoordinate(), 0.1);
        
        assertEquals(20, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(0, tiles[2].getYCoordinate(), 0.1);
        assertEquals(0, tiles[3].getYCoordinate(), 0.1);
    }
    
        @Test
    public void sBlockRotatesCorrectlyFrom180To270Degrees() {
        
        block.rotate();
        block.rotate();
        block.rotate();
        
        Tile[] tiles = block.getTiles();
        
        assertEquals(120, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(140, tiles[2].getXCoordinate(), 0.1);
        assertEquals(140, tiles[3].getXCoordinate(), 0.1);
        
        assertEquals(0, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(20, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }
    
        @Test
    public void sBlockRotatesCorrectlyFrom270ToZeroDegrees() {
        
        block.rotate();
        block.rotate();
        block.rotate();
        block.rotate();
        
        Tile[] tiles = block.getTiles();
        
        assertEquals(100, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(120, tiles[2].getXCoordinate(), 0.1);
        assertEquals(140, tiles[3].getXCoordinate(), 0.1);
        
        assertEquals(20, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(0, tiles[2].getYCoordinate(), 0.1);
        assertEquals(0, tiles[3].getYCoordinate(), 0.1);
    }
    
        @Test
    public void sBlockFreeAreasNeededToRotateTo90DegreesIncludeAreasBlockIsMovingInto() {

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(140, 20, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(140, 40, 20))) {
                found2 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
    }

    @Test
    public void sBlockFreeAreasNeededToRotateTo180DegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(100, 20, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(120, 40, 20))) {
                found2 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
    }

    @Test
    public void sBlockFreeAreasNeededToRotateTo270DegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();
        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(140, 20, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(140, 40, 20))) {
                found2 = true;
            }

        }

        assertTrue(found1);
        assertTrue(found2);
    }

    @Test
    public void sBlockFreeAreasNeededToRotateToZeroDegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();
        block.rotate();
        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(100, 20, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(120, 40, 20))) {
                found2 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
    }
}
