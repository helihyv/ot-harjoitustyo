# Testausdokumentti

## Yksikkö- ja integraatiotestaus

Yksikkö- ja integraatiotestaus on tehty automaattisilla JUnit-testeillä.

### Pelilogiikka

Pelilogiikkaa on testattu JUnit:illa totetutetuilla yksikkö- ja integraatiotesteillä. Peliin liittyvän satunnaisuuden (palikan muodon arpominen) takia koko pelilogiikan läpi kulkevaa integraatiotestausta on käytetty vain vähän. Valtaosa testeistä testaa joko kahta luokkaa yhdessä tai vain yhtä luokkaa. 


### HighScore-logiikka

HighScoreService-luokka on yksikkötestattu JUnit-testeillä käyttäen apuna HighScoreDAO-rajapinnan toteuttavaa mock-luokkaa FakeHighScoreDAO.

### DAO

Tietokantan tallentamisesta vastaava DAO-luokka on yksikkötestattu JUnit-testeillä käyttäen muistiin sijoitettuja tietokantoja, kullekin testille omaansa.

### Testauskattavuus

![Testauskattavuus](/dokumentaatio/testauskattavuus.png)

Koko peliloggikan läpi tehdyt integraatiotestit yhdistettynä siihen, ettei samaa toiminnallisuutta testata yksikkötesteillä tuo testauskattavuuteen hieman satunnaisuutta. Kun käyttöliittymää ei huomioida, tyypillinen rivikattavuus on noin 73 % ja tyypillinen haaraumakattavuus noin 71 %. 

## Järjestelmätestaus

Järjestelmätestaus on tehty manuaalisestti Linux-ympäristössä. Sovelluksen asentamista, kofigurointia ja vaatimusmäärittelyssä mainittuja toimintoja on testattu. 

## Sovellukseen jääneet laatuongelmat

Pelissä pitäisi olla mahdollista sujauttaa yhden tiilen korkuinen palikka muiden palikoiden välissä olevaan yhden tiilen kokoiseen pystyrakoon, mutta tämä ei onnistu.

Erittäin harvoin esiintyvänä virheenä palikka saattaa jatkaa matkkansa alaspäin, kun sen pitäisi pysähtyä toisen palikan päälle.

Jos käyttäjällä ei ole oikeuksia tietokantatiedostoon, sovellus tulostaa stack trace:n, vaikka sovellus nappaakin kaikki tietokantatoiminnoista tulevat poikkeukset.

Sovellus saattaa joskus kaatua highscore-tuloksen onnistuneen tallentamisen jälkeen.

Sovelluksen tai sen avamman dialogin ikkuna saattaa satunnaisesti jäädä minimaalisen pieneksi. Tätä ongelma esiintyy ilmeisesti vain KDE-ympäristössä. 