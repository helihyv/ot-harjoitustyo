# Käyttöohje

## Sovelluksen asentaminen

Sovellus asennetaan kopioimalla sen jar-tiedosto `Tetris.jar` haluttuun asennushakemistoon. 

## Sovelluksen konfigurointi

Sovellusta ei välttämättä tarvitse konfiguroida lainkaan. Jos halutaan määritellä parhaat tulokset sisältävän tietokannan sijainti, se tehdään asettamalla tiedostonnimi polkuineen ympäristömuuttujaan `TETRIS_DATABASE_FILENAME`
Sovellus lisää annettuun nimeen päätteen .mv.db . Lisäksi luodaan samaan hakemistoon toinen tiedosto, jonka nimeen lisätään vastaavasti .trace -pääte. Jos ympäristömuuttujaa ei määritellä, käytetään oletussijaintia `./tetris` 

## Sovelluksen käynnistäminen

Sovellus käynnistetään komennolla `java -jar Tetris.jar`

## Pelin aloittaminen

Sovelluksen käynnistyessä peli alkaa automaattisesti. Pelin voi käynnistää uudelleen (sekä pelin aikana että sen päätyttyä) painamalla `F1`-näppäintä.

## Palikan ohjaaminen

Palikkaa liikutetaan vasemmalla ja oikealle vastaavista nuolinäppäimistä. "Nuoli ylös" .-näppäimestä palikka kääntyy 90 astetta myötäpäivään. Välilyönnillä palikka pudotetaan nopeammin alas.

## Pelin säännöt

Ylhäältä putoavista erimuotoisista palikoista yritetään muodostaa yhtenäisiä rivejä. Kun rivi on saatu täyteen, se poistuu pelistä ja sen yläpuollella olevat rivit laskeutuvat alemmas. Peli päättyy, kun alas pudonneet palikat yltävät pelialueen yläosassa näkyvään viivaan asti.

## Pisteet

Pelin aikana saadut pisteet ("score") ovat näkyvissä sovelluksen vasemmassa laidan yläosassa. Pisteitä saa vähän jokaisesta palikasta, joka putoaa alas päättämättä peliä ja paljon jokaisesta rivistä, jonka saa poistettua.

## Parhaat tulokset

Sovelluksen vasemaassa laidassa on näkyvissä enintään kymmenen parasta tulosta. Kun pelaaja saa tuloksen, joka on kymmenen parhaan joukossa, häneltä kysytään nimeä lisättäväksi pisteiden kanssa parhaisiin tuloksiin. Jos pelaaja valitsee `OK`, tulos lisätään. Jos pelaaja valitsee `Cancel`, ei tulosta lisätä parhaisiin tuloksiin lainkaan.

## Sovelluksen sulkeminen

Sovellus suljetaan sulkemalla sen ikkuna. Tarvittaessa suljetaan ensin avoinna oleva dialogi.

