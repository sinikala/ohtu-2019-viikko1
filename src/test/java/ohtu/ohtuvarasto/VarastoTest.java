package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto saldollinenvarasto;
    Varasto molemmatNegatiiviset;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        saldollinenvarasto = new Varasto(5, 2);
        molemmatNegatiiviset = new Varasto(-2,-5);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

   @Test
   public void konstruktoriLuoSaldollisenVarastonOikeaTilavuus(){ assertEquals(5, saldollinenvarasto.getTilavuus(), vertailuTarkkuus);}

   @Test
   public void saldollinenKonstruktoriEiLuoNegatiivistaSaldoa(){ assertEquals(0, molemmatNegatiiviset.getSaldo(),vertailuTarkkuus);}

   @Test
   public void saldollinenKonstruktoriEiLuoNegatiivistaTilavuutta(){assertEquals(0, molemmatNegatiiviset.getTilavuus(), vertailuTarkkuus);}

    @Test
    public void konstruktoriLuoSaldollisenVarastonOikeaSaldo(){ assertEquals(2, saldollinenvarasto.getSaldo(), vertailuTarkkuus);}

    @Test
    public void saldollinenKonstruktorinSaldoEiYlitaTilavuutta(){
        Varasto liikaaSaldoa = new Varasto(2,10);
        assertEquals(-2, liikaaSaldoa.getSaldo(),vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriNollaaNegatiivisenAlkutilavuuden(){
        Varasto negTilavuus = new Varasto(-10);
        assertEquals(0, negTilavuus.getTilavuus(),vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiLisaaSaldoa(){
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldoYlittaaTilavuuden(){
        varasto.lisaaVarastoon(100);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenMaaraEiVaikutaSaldoon(){
        varasto.otaVarastosta(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaOttaaVainMitaVoi(){
        varasto.lisaaVarastoon(5);
        assertEquals(5, varasto.otaVarastosta(20), vertailuTarkkuus);
    }

    @Test
    public void toStringTulostuuOikein(){
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

}