/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.Area;
import fi.helihyv.tetris.gamelogic.TetrisTile;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heli
 */
public class AreaUnitTest {
    
    Area area;
    
    public AreaUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        area = new Area(100,200,300,400);
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
    public void areaOverlapsReturnsTrueIfTileIsInsideArea() {
        
        TetrisTile tile = new TetrisTile(320,160,20);
        
        assertTrue(area.overlaps(tile));
    }
    
    @Test
    public void areaOverLapsReturnsFalseIfTileIsOutsideArea() {
        TetrisTile tile = new TetrisTile(1000,1000,20);
        
        assertFalse(area.overlaps(tile));
    }
}
