package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    private Kassapaate kassapaate;
    private Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(450);
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
    public void kassapaatteenRahamaaraAlussaOikein() {
        assertEquals(100000,kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassapaatteenMyytyjenEdullistenLounaidenMaaraAlussaOikein() {
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty());
    } 
    
    @Test
    public void kassapaatteenMyytyjenMaukkaidenLounaidenMaaraAlussaOiken() {
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKateisostoKasvattaaKassaaOikeinKunMaksuRiittaa() {
        kassapaate.syoEdullisesti(240);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }

    @Test
    public void edullisenLounaanKateisostossaVaihtorahaOikeinJosMaksuRiittaa() {       
        assertEquals(60,kassapaate.syoEdullisesti(300));
    }
    
    @Test
    public void edullisenLounaanKateisostossaMyydytLounaatKasvaaJosMaksuRiittaa() {
        kassapaate.syoEdullisesti(240);
        assertEquals(1,kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKateisostossaKassanRahamaaraEiKasvaKunRahaEiRiita() {
        kassapaate.syoEdullisesti(239);
        assertEquals(100000,kassapaate.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanKateisostossaKaikkiRahatPalautetaanKunRahaEiRiita() {
        assertEquals(239,kassapaate.syoEdullisesti(239));
    }
    
    @Test
    public void edullisenLounaanKateisostossaMyydytLounaatEiKasvaJosMaksuEiRiita() {
        kassapaate.syoEdullisesti(239);
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanKateisostoKasvattaaKassaaOikeinKunMaksuRiittaa() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanKateisostossaVaihtorahaOikeinJosMaksuRiittaa() {
        assertEquals(100,kassapaate.syoMaukkaasti(500));
    }
    
    @Test
    public void maukkaanLounaanKateisostossaMyydytLounaatKasvaaJosMaksuRiittaa() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(1,kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanKateisostossaKassanRahamaaraEiMuutuJosMaksuEiRiita() {
        kassapaate.syoMaukkaasti(399);
        assertEquals(100000, kassapaate.kassassaRahaa());        
    }
    
    @Test
    public void kaikkiRahatPalautetaanJosMaksuEiRiita() {
        assertEquals(399,kassapaate.syoMaukkaasti(399));
    }
   
    @Test 
    public void maukkaanLounaanKateisostossaMyydytLounaatEiKasvaKunMaksuEiRiita() {

        kassapaate.syoMaukkaasti(399);
        assertEquals(0,kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKorttiostossaVeloitetaanOikeinKunSaldoRiittaa() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(210,kortti.saldo());
    }
    
    @Test
    public void edullisenLounaanKorttiostossaPalautetaanTrueJosSaldoRiittaa() {
        assertTrue(kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullisenLounaanKorttiostossaMyydytLounaatKasvaaKunMaksuRiittaa() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1,kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKorttiOstossaEiVeloitetaJosSaldoEiRiita() {
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(210, kortti.saldo());
    }
    
    @Test
    public void edullisenLounaanKorttiostossaMyydytLounaatEiKasvaJosSaldoEiRiita() {
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKorttiostossaPalautetaanFalseJosSaldoEiRiita() {
        kassapaate.syoEdullisesti(kortti);
        assertFalse(kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullisenLounaanKorttiostossaKassanRahamaaraEiMuutu() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test   
    public void maukkaanLounaanKorttiostossaVeloitetaanOikeinJosSaldoRiittaa() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(50,kortti.saldo());
    }
    
    @Test
    public void maukkaanLounaanKorttiostossaPalautetaanTrueJosSaldoRiittaa() {
        assertTrue(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maukkaanLounaanKorttiostossaMyydytLounaatKasvaaJosSaldoRiittaa() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1,kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanKorttiostossaEiVeloitetaJosSaldoEiRiita() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(50,kortti.saldo());        
    }
    
    @Test
    public void maukkaanLounaanKorttiostossaMyydytLounaatEiKasvaJosSaldoEiRiita() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1,kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanKorttiostossaPalautetaanFalseJosSaldoEiRiita() {
        kassapaate.syoMaukkaasti(kortti);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maukkaanLounaanKorttiostossaKassanRahamaaraEiMuutu() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100000,kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassapaatteellaKortilleRahaaLadattaessaKortinSaldoMuuttuuOikein() {
        kassapaate.lataaRahaaKortille(kortti, 2000);
        assertEquals(2450,kortti.saldo());
    }
    
    @Test
    public void kassapaatteellaKortilleRahaaLadattaessaKassanRahamaaraKasvaaOikein() {
        kassapaate.lataaRahaaKortille(kortti, 2000);
        assertEquals(102000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortilleRahaaLadattaessaKortinSaldoEiMuutuJosSummaNegatiivinen() {
        kassapaate.lataaRahaaKortille(kortti, -2000);
        assertEquals(450,kortti.saldo());
    }
    
    @Test
    public void kortilleRahaaLadattaessaKassanRahamaaraEiMuutuJosSummaNegatiivinen() {
        kassapaate.lataaRahaaKortille(kortti, -2000);
        assertEquals(100000,kassapaate.kassassaRahaa());
    }
   
    
    
}

    

