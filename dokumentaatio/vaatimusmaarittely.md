# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksella voi pelata tetristä. Pelissä pelaaja kääntelee ja liikuttaa sivuittain erimuotoisia ylhäältä putoavia palikoita yrittäen muodostaa palikoista kokonaisia rivejä. Kun rivi on saatu täyteen, se katoaa ja pelaaja saa pisteitä. Jos alas pudonneiden palikoiden pino kasvaa liian korkeaksi peli päättyy.

## Käyttäjät

Sovelluksella on vain yhdenlaisia käyttäjiä, pelaajia.

## Perusversion toiminnallisuus

* Pelaaja voi aloittaa pelin (myös kesken edellisen pelin) (tehty)
* Pelin alussa ja kun edellinen palikka on pysähtynyt, uusi palikka ilmestyy pelialueen ylälaitaan ja alkaa pudota alas. (tehty)
* pelissä on erimuotoisia palikoita (tehty)
* uusi palikka valitaan satunnaisesti eri palikkamuodoista (tehty)
* Pelaaja voi siirtää putoavaa palikkaa vasemmalle, kunnes pelialueen reuna tai aiemmin pudonnut palikka tulee vastaan (tehty)
* Pelaaja voi siirtää putovaa palikkaa oikealle, kunnes pelialueen reuna tai aiemmin pudonnut palikka tulee vastaan (tehty)
* Pelaaja voi kääntää palikkaa 90 astetta myötäpäivään, jos se mahtuu kääntymään (tehty)
* Pelaaja voi nopeuttaa palikan putoamista (tehty)
* Kun palikka saavuttaa pohjan tai aikaisemmin pudonneen palikan se pysähtyy (tehty)
* Kun palikka pyshähtyy eikä peli pääty, pelaaja saa pienen määrän pisteitä (tehty)
* Kun pysähtyneet palikat muodostavat täyden vaakarivin, rivi poistuu (tehty)
* kun rivi poistuu ylemmät rivit laskeutuvat alaspäin (tehty)
* kun rivi poistuu pelaaja saa pisteitä (tehty)
* Pelaaja näkee pelin aikana kertyneet pisteet (tehty)
* Pisteiden kertyessä palikoiden putoamisnopeus kasvaa portaittain
* Kun pysähtyneet palikat ulottuvat tietyn korkeuden yläpuolelle peli päättyy (tehty)
* Pelin päättyessä, jos pelaajan pistemäärä on suurempi kuin kymmenenneksi paras tallennettu tulos, pelaaja voi kirjoittaa nimensä ja nimi ja tulos lisätään parhaiden tulosten luetteloon (tehty, uusi)
* Pelaaja voi katsella parhaiden tulosten luetteloa (näytetään 10 parasta järjestyksessä paras ensin) (tehty, uusi)
* Parhaat tulokset muistetaan pelikerrasta toiseen (tiedot tallennetaan tietokantaan) (tehty, uusi)

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


