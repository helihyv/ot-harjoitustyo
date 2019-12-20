# Arkkitehtuurikuvaus

## Rakenne

Pakkauksessa fi.helihyv.tetris on ohjelman pääluokka, joka ainostaan käynnistää käyttöliittymän. Pakkaus fi.helihyv.tetris.ui sisältää sovelluksen JavaFX:llä toteutetun käyttöliittymän. 
Pakkaus fi.helihyv.tetris.gamelogic sisältää itse pelin sovelluslogiikan. Pakkaus fi.helihyv.tetris.highscores sisältää highscore-toiminnallisuuksien sovelluslogiikan. Pakkaus fi.helihyv.tetris.dao sisältää tietojen pysyväistallennukseen tarvittavat luokat.

### Luokkakaavio

![Luokkakaavio](/dokumentaatio/luokkakaavio.png)

## Käyttöliittymä

Käyttöliittymässä on yksi näkymä, joka jakautuu vasemmassa laidassa olevaan sivupalkkkiin ja itse peliin. Käyttöliittymän runkona on TetrisUI-olio. Käyttöliittymä on yhteydessä pelilogiikkaan Game- ja Tile -rajapintojen kautta, HighScore-logiikkaan se on yhteydessä suoraan HighScoreService-luokkaan. 

Pelialue (GameArea-olio) on toteutettu JavaFX:n Canvas-oliolla, johon piirretään säännöllisin aikavälein (JavaFX:n AnimationTimer) sovelluslogiikalta haetut "tiilet" (Tile-oliot). 

Sivupalkissa (SideBar-olio) näytetään aloitusohje ja pistetilanne (toteutettu suoraan  JavaFX:n Label-olioina) sekä highscore-listaus, joka on oma HighScoreView-olionsa. Se näyttää otsikon, mahdollisen virheilmoituksen ja tulokset Label-olioina.

Pelin päätyttyä käyttäjälle näytetään ilmoitus pelin päättymisestä dialogina. Jos pelaaja on pääsemässä highscore-listalle näytetään dialogi, johon käyttäjä voi kirjoittaa nimensä (JavaFX:n TextInputDialogista periytetty HighScoreNameInputDialog). Dialogi luo uuden HighScore-olion ja lisää sen HighScoreServiceen, minkä kälkeen se pyytää HighScoreView:iä päivittämään itsenbsä. Jos pelaaja ei ole pääsemässä highscore-listalle näytetään pelin päättyessä ilmoitusdialogi (JavaFX:n Alertista periytetty GameOverDialog).

## Pelin logiikka

Peln logiikan ytimenä on Game-rajapinnan toteuttava TetrisGame-luokan olio, joka vastaa pelin ajastetuista toiminnoista ja käyttäjän komentojen vastaanottamisesta käyttöliittymältä. Sovelluslogiikka ei käytä JavaFX:ää ja on pyritty tekemään niin, että käyttöliittymä olisi tarvittaessa vaihdettavissa muuttamatta sovelluslogiikka. Sovelluslogiikalla on oma ajastimensa, joka tahdistaa palikoiden putomaista ja siitä seuraavia tapahtumia. 

Kutakin erilaista palikan muotoa varten on oma luokkansa. Nämä luokat perivät kaikki abstraktin luokan Block, jonka kautta TetrisGame käsittelee niitä. Palikat muodostuvat kukin neljästä neliömäisestä "tiilestä", joiden keskinäistä sijaintia järjestellään uudelleen palikan kääntyessä. 

Ennen palikan liikkumista ja kääntymistä TetrisGame varmistaa, että tilaa on riittävästi. Apuna käytetään Area-luokkaa, joka kuvaa neliskulmaista aluetta pelialueen sisällä. 

Palikan pudottua sen tiilet siirretään (sijainti säilyttäen) tiilipinolle (TileStack-olio). Tiilipino
huolehtii täysien rivien poistamisesta ja niiden yläpuolella olevien rivien laskemisesta alaspäin. Lisäksi se pystyy kertomaan korkeutensa tietyllä kohdalla sen ja meneekö jokin sen tiilistä päällekkäin Area-alueen kanssa 

## Highscore-logiikka

Hihgscore-listan ylläpito on erillään muusta sovelluslogiikasta. HighScoreService huolehtii annetun tuloksen lisäämisestä listalle ja oikeassa järjestyksessä olevan listan antamisesta käyttöliittymälle.

## Pysyväistallennus

Highscore-tulokset tallennetaan H2-tietokantaan. Sovellus luo itse tietokannan tauluineen, jos niitä ei ennestään ole. Tallennuksesta huolehtii fi.heliyv.tetris.dao pakkauksessa oleva HighScoreH2DAO-luokka, johon HighScoreService on yhteydessä HighScoreDAO-rajapinnan kautta. Luokka noudattaa Data Access Object suunnittelumallia. Tallennusmuoto olisi vaihdettavissa luomalla toinen HighScoreDAO-rajapinnan toteuttava luokka.

### Tietokanta

Tietokanta luodaan oletusarvoisesti tiedostoon _./tetris.mv.db_ . Tietokannan sijainnin voi konfguroida asettamalla ympäristömuuttujaan TETRIS_DATABASE_FILENAME haluamansa tiedostonnimen polkuineen. Ympäristömuuttujasta otettuun nimeen lisätään pääte .mv.db .

Tietokannassa on taulu HighScores, jossa on sarakkeet name ja score pelaajan nimen ja pistemäärän tallentamiseen. 

## Päätoiminnallisuudet

### Palikan liikuttaminen  vasemmalle ja oikealle

Käyttäjän painaessa "nuoli oikealle" näppäintä tapahtumankäsittelejä kutsuu Game-olion moveBlockRight()-funktiota, joka varmistaa ensin kutsumalla tarvittavia funktioita, että siirtyminen ei vie palikkaa pois pelialueelta ja etteivät tiilipinon tiilet tuki siirtymiseen tarvittavaa tilaa. Jos palikkaa mahtuu liikkumaan, kutsutaan palikan siirtymisfunktiota, joka puolestaan kutsuu yksittäisten tiilten siisrtymisfunktioita. Palikan liikuttaminen vasemalle toimii vastaavasti kuin palikan liikuttaminen oikealle

Alla oleva sekvenssikaavio kuvaa ohjelman toimintaa, kun pelaaja painaa "nuoli oikealle" -näppäintä tilanteessa, jossa pudonneena on ainoastaan neliömäinen palikka pelialueen vasemmassa alakulmassa ja L-muotoinen palikka on juuri ilmestynyt pelialueen ylälaitaan.

![Sekvenssikaavio](/dokumentaatio/sekvenssikaavio_nuoli_oikealle.png)

### Palikan kääntäminen

Käyttäjän painettua "nuoli ylös" -näppäintä käyttöliittymäluokan TetrisUI tapahtumankäsittelijä kutsuu Game-rajapinnan toteuttavan olion rotateBlock-funktiota. Tämä funktio varmistaa ensin, että tilaa kääntymiseen on riittävästi. tämä tehdään ensinnäkin kutsumalla pailkan  sen reunan sijainnin kääntymisen jälkeen kertovia funktiota ja vertaamalla niiden paluuarvoja pelialueen reunan sijaintiin. Toiseksi kutsutaan palikan funktiota, joka kertoo kääntymiseen vapaaksi tarvittavat alueet listana Area-olioita. Tämä lista on parametrina kutsuttaessa tiilipinon funktioita, joka kertoo ovatka kaikki listan alueet vapaita. Jos tilaa on, kutsutaan palikan (Block-rajapinta) rotate()-funktiota, joka asettaa palikan uuden asennon ja kutsuu tiilet uuden asennon mukaiseksi järjestävää funktiota. 

### Palikan liikuttaminen ajastetusti alaspäin seurauksineen

Ajastimena toimii javan Timer-luokan olio. Sen käynnistämät toiminnot on määritelty Javan TimerTask-luokalle toteutetussa run()-funktiossa, joka vain kutsuu TetrisGame-luokan olion moveBlockDown()-funktiota. Tämä puolestaan kutsuu saman olion moveBlockDownByOne()-funktiota kerran, jos palikka on nputomassa normaalinopeudella. Jos palikka on putoamassa pikapudotuspeudella, kutsutaan tätä funktiota viidesti peräkkäin. 

Funkto moveBlockDownByOne() kutsuu palikan moveDown()-funktiota, joka puolestaan kutsuu kunkin tiilensä moveDown()-funktiota. Tämän jälkeen moveBlockDoenByOne()-funktio jatkaa tarkistamalla palikan ja tiilipinon funktioita käyttäen, onko palikka jo saavuttanut tiilipinon yläreunan tai pelialueen alalaidan jossain kohdassa. Jos on, palikan tiilet lisätään tiilipinoon. Tämän jälkeen kutsutaan tiilipinon removeFullRows()-funktiota, jossa (saman olion apufunktioita hyödyntäen) tarkistetaan onko tiilipinossa täysiä rivejä, poistetaan löydetyt täydet rivit ja siirretään niiden yläpuolella olleita rivejä alaspäin. TetrisGame-oliolle palautetaan poistettujen rivien määrä. 

Poistetuista täysistä riveistä annetaan pelaajalle pisteitä. Pisteet lisäävä funktio tarkistaa samalla, nostetaanko palikan putoamisnopeutta. Lopuksi TetrisGame-olion funktiolla isGameOver() tarkistetaan tiilipinon topEdge()-funktiota hyödyntäen ulottuuko tiilipino pelin päättymisrajaan asti. Jos ulottuu, kutsutaan stopGame()-funktiota, joka pysäyttää ajastimen ja merkitsee pelin loppuneeksi. Jos peli ei päättynyt, annetaan pelaajalle hieman pisteitä ja luodaan uusi palikka generateNewBlock()-funktiolla.

