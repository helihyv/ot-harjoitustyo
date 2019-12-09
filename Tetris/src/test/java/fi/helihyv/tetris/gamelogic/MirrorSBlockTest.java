/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.Tile;
import fi.helihyv.tetris.gamelogic.Area;
import fi.helihyv.tetris.gamelogic.TetrisTile;
import fi.helihyv.tetris.gamelogic.MirrorSBlock;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heli Hyvättinen
 */
public class MirrorSBlockTest {
    
    MirrorSBlock block;
    
    public MirrorSBlockTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        block = new MirrorSBlock(100,20);
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
    public void mirrorSBlockRotatesCorrectlyFromZeroToNinetyDegrees() {
        
        block.rotate();
        
        Tile[] tiles = block.getTiles();
        
        assertEquals(120, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(100, tiles[2].getXCoordinate(), 0.1);
        assertEquals(100, tiles[3].getXCoordinate(), 0.1);
        
        assertEquals(0, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(20, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }
    
        @Test
    public void mirroSBlockRotatesCorrectlyFromNinetyTo180Degrees() {
        
        block.rotate();
        block.rotate();
        
        Tile[] tiles = block.getTiles();
        
        assertEquals(100, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(120, tiles[2].getXCoordinate(), 0.1);
        assertEquals(140, tiles[3].getXCoordinate(), 0.1);
        
        assertEquals(20, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(40, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }
    
        @Test
    public void mirrorSBlockRotatesCorrectlyFrom180To270Degrees() {
        
        block.rotate();
        block.rotate();
        block.rotate();
        
        Tile[] tiles = block.getTiles();
        
        assertEquals(120, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(100, tiles[2].getXCoordinate(), 0.1);
        assertEquals(100, tiles[3].getXCoordinate(), 0.1);
        
        assertEquals(0, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(20, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }
    
        @Test
    public void mirrorSBlockRotatesCorrectlyFrom270ToZeroDegrees() {
        
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
        assertEquals(40, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }
    
        @Test
    public void mirrorSBlockFreeAreasNeededToRotateTo90DegreesIncludeAreasBlockIsMovingInto() {

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(120, 0, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(100, 40, 20))) {
                found2 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
    }

    @Test
    public void mirrorSBlockFreeAreasNeededToRotateTo180DegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;


        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(120, 40, 20))) {
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
    public void mirrorSBlockFreeAreasNeededToRotateTo270DegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();
        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;
 

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(120, 0, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(100, 40, 20))) {
                found2 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);

    }

    @Test
    public void mirrorSBlockFreeAreasNeededToRotateToZeroDegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();
        block.rotate();
        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(120, 40, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(140, 40, 20))) {
                found2 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
    }
}
