# Tetris
Harjoitustyön aiheena on klassikkopeli tetris. Pelissä pelaaja kääntelee ja liikuttaa sivuittain erimuotoisia ylhäältä putoavia palikoita yrittäen muodostaa palikoista kokonaisia rivejä. Kun rivi on saatu täyteen, se katoaa ja pelaaja saa pisteitä. Jos alas pudonneiden palikoiden pino kasvaa liian korkeaksi peli päättyy.

## Dokumentaatio

[Käyttöhje](/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 5](https://github.com/helihyv/ot-harjoitustyo/releases/tag/Viikko5)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

`mvn test`

Testikattavuusraportti luodaan komennolla

`mvn test jacoco:report`

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedoston _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

`mvn package`

muodostaa hakemistoon target suoritettavan jar-tiedoston _Tetris-1.0-SNAPSHOT.jar_

### JavaDoc

Javadoc generoidaan komennolla

`mvn javadoc:javadoc`

Javadocia voi tarkastella avaamalla selaimella tiedoston _target/site/apidocs/index.html_

### CheckStyle

Tyylitarkastusraportti luodaan komennolla

`mvn jxr:jxr checkstyle:checkstyle`

Raporttia voi tarkastella avaamalla selaimella tiedoston _target/site/checkstyle.html_



