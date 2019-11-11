package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(10,kortti.saldo());
    }
    
    @Test
    public void kortinLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(25);
        assertEquals(35,kortti.saldo());
    }
    
    @Test
    public void rahaaOtettaessaSaldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(5);
        assertEquals(5,kortti.saldo());
    }
    
    @Test
    public void rahaaOtettaessaSaldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(15);
        assertEquals(10,kortti.saldo());
    }
    
    @Test
    public void rahaaOtettaessaPalautetaanTrueJosRahaaTarpeeksi() {
        assertTrue(kortti.otaRahaa(5));
    }
    
    @Test
    public void rahaaOtettaessaPalautetaanFalseJosRahaaEiTarpeeksi() {
        assertFalse(kortti.otaRahaa(15));
    }
    
    @Test
    public void kortinToStringPalauttaaOikeanMerkkijonon() {
        System.out.println(kortti);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
}
