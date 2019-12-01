/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.domain;

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
public class IBlockTest {
    
    IBlock block;
    
    public IBlockTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        block = new IBlock(100,20);
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
    public void iBlockRotatesCorrectlyFromHorizontalToVertical() {
        
        block.rotate();
        
        for (int i = 0; i < 4; i++) {
            assertEquals(120,block.getTiles()[i].getXCoordinate(),0.1);
            assertEquals(-20 + i * 20, block.getTiles()[i].getYCoordinate(), 0.1);
        }
    
    }
    
    @Test 
    public void iBlockRotatesCorrectlyFromVerticalToHorizontal() {
        
        block.rotate();
        block.rotate();
        
        for (int i = 0; i < 4; i++) {
            assertEquals(0,block.getTiles()[i].getYCoordinate(),0.1);
            assertEquals(100 + i * 20, block.getTiles()[i].getXCoordinate(), 0.1);
        }
    }
    

    
}

