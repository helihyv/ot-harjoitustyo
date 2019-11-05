# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksella voi pelata tetristä. Pelissä pelaaja kääntelee ja liikuttaa sivuittain erimuotoisia ylhäältä putoavia palikoita yrittäen muodostaa palikoista kokonaisia rivejä. Kun rivi on saatu täyteen, se katoaa ja pelaaja saa pisteitä. Jos alas pudonneiden palikoiden pino kasvaa liian korkeaksi peli päättyy.

## Käyttäjät

Sovelluksella on vain yhdenlaisia käyttäjiä, pelaajia.

## Perusversion toiminnallisuus

* Pelaaja voi aloittaa pelin (myös kesken edellisen pelin)
* Pelin alussa ja kun edellinen palikka on pysähtynyt, uusi palikka ilmestyy pelialueen ylälaitaan ja alkaa pudota alas.
* Pelaaja voi siirtää putoavaa palikkaa vasemmalle, kunnes pelialueen reuna tulee vastaan
* Pelaaja voi siirtää putovaa palikkaa oikealle, kunnes pelialueen reuna tulee vastaan
* Pelaaja voi kääntää palikkaa 90 astetta myötäpäivään, jos se mahtuu kääntymään
* Pelaaja voi nopeuttaa palikan putoamista
* Kun palikka saavuttaa pohjan tai aikaisemmin pudonneen palikan se pysähtyy
* Kun pysähtyneet palikat muodostavat täyden vaakarivin, rivi poistuu, ylemmät rivit laskeutuvat alaspäin ja pelaaja saa pisteitä
* Pelaaja näkee pelin aikana kertyneet pisteet
* Pisteiden kertyessä palikoiden putoamisnopeus kasvaa portaittain
* Kun pysähtyneet palikat ulottuvat tietyn korkeuden yläpuolelle peli päättyy
* Pelin päättyessä, jos pelaajan pistemäärä on suurempi kuin kymmenenneksi paras tallennettu tulos, pelaaja voi kirjoittaa nimensä ja nimi ja tulos lisätään parhaiden tulosten luetteloon
* Pelaaja voi katsella parhaiden tulosten luetteloa (näytetään 10 parasta järjestyksessä paras ensin)
* Parhaat tulokset muistetaan pelikerrasta toiseen (tiedot tallennetaan tietokantaan)

## Jatkokehitysideat

Perusversion jälkeen peliin ajan salliessa lisättäviä toiminnallisuuksia:
* Pelaajalle näytetään seuraavaksi tulossa oleva palikka
* Pelaaja voi valita näytetäänkö seuraavaksi tulossa oleva palikka
* Pelaaja saa enemmän pistetitä, jos seuraavaa palikkaa ei näytetä
* Peli muistaa pelikerrasta toiseen näytetäänkö seuraavaksi tulevaa palikkaa (valinta tallennetaan tietokantaan)
* Kun pisteitä on kertynyt riittävästi, pysähtyneiden palikoiden pinon korkeus, jossa peli päättyy, laskee portaittain.
* Näytetään prässi kuvaamaan sitä palikkapinon korkeutta, jolla peli päättyy.
* Erimuotoiset palikat ovat erivärisiä
* Pelaaja voi valita palikoiden värit
* Valitut värit muistetaan (tallennetaan tietokantaan)


