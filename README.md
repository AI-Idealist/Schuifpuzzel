# Schuifpuzzel
Een schuifpuzzel die opgelost moet worden door de computer.   


De schuifpuzzel is ontwropen door Noyes Palmer Chapman in de 1870s. Het wordt op een bord gespeeld met 9 velden die in drie rijen en drie kolommen gegroepeerd zijn. Verder zijn er 8 blokjes genummerd 1 tot en met 8 die in een willekeurig opstelling op het bord geplaatst worden. Er blijft blijft dus altijd 1 leeg veld over. Het doel is om de blokjes naar de goede volgorde (juiste opstelling) te krijgen in zo min mogelijk zetten. Je kunt de blokjes horizontaal of verticaal verschuiven in het lege veld schuiven. Hieronder staat een een reeks verschillende zetten en hun opstellingen van het initiële opstelling tot en met de doel-opstelling (oplossing). (rechts).


        1  3        1     3        1  2  3        1  2  3        1  2  3
     4  2  5   =>   4  2  5   =>   4     5   =>   4  5      =>   4  5  6
     7  8  6        7  8  6        7  8  6        7  8  6        7  8 

     initieel        1 links          2 omhoog    5  links          doel


A*-algoritme
Het algoritme dat gebruikt wordt om van initiele opstelling naar doel opstelling te komen is het A* algoritme. A* (uitgesproken als A-star of A-ster) is een algoritme om in een graaf de kortste weg te vinden tussen twee knopen van die graaf. Het algoritme zoekt een pad van een startknoop naar een gevraagde knoop door middel van een "heuristische schatting", die elke knoop rangschikt volgens een schatting van de beste route door die knoop. Het algoritme bezoekt de knopen in de volgorde van deze heuristische schatting, het A*-algoritme is zo een voorbeeld van een "beste-eerst"-algoritme. Het werd in 1968 voor het eerst beschreven door Peter Hart, Nils Nilsson, en Bertram Raphael.

Werking A*
1. Het A*-algoritme begint in een geselecteerde knoop. Van deze knoop bepaalt men de "kosten" om de knoop te bezoeken, voor de initiële knoop is dit gewoonlijk nul. A* schat dan de afstand vanaf de huidige knoop tot de bestemming. Deze schatting en de kost worden opgeteld, en vormen de heuristiek die aan het pad naar deze knoop wordt toegekend. De knoop wordt vervolgens toegevoegd in een prioriteitswachtlijn, die soms "open" genoemd wordt.
2. Het algoritme haalt vervolgens een knoop uit de prioriteitswachtlijn; door de werking van deze wachtlijn zal dit de knoop met de laagste heuristiek zijn. Wanneer de wachtlijn leeg is, is er geen pad van de startknoop naar de doelknoop en stopt het algoritme. Is de knoop die uit de wachtrij gehaald wordt de doelknoop, dan reconstrueert A* het succesvolle pad, geeft het weer, en stopt. Deze reconstructie van het pad uit de opgeslagen gesloten knopen betekent dat het niet nodig is om het voorlopig pad op te slaan in elke knoop.
3. Wanneer de knoop die uit de wachtlijn gehaald wordt niet de doelknoop is, worden nieuwe knopen gecreëerd voor alle aanvaardbare aangrenzende knopen; de manier waarop dit precies gebeurt hangt af van het specifieke probleem. Voor elk van die knopen berekent A* de "kosten" om de knoop te bezoeken en slaat deze op in de knoop. Deze kosten worden berekend als de cumulatieve som van de kosten in de ouderknopen, plus de kosten van de bewerking om de nieuwe knoop te bereiken.
4. Het algoritme houdt ook een "gesloten" lijst bij met knopen die reeds gecontroleerd zijn. Wanneer een nieuw gegenereerde knoop al in de lijst staat met gelijke of lagere kosten, doet men verder niets met deze knoop of het pad dat ermee geassocieerd is. Wanneer de nieuwe knoop al in de gesloten lijst staat, maar nu lagere kosten heeft, wordt deze oude knoop uit de gesloten lijst verwijderd, en werkt men verder met de nieuwe knoop.
5 Vervolgens wordt een schatting van de afstand van de nieuwe knoop naar de bestemming gemaakt, en wordt deze opgeteld om de heuristiek voor de nieuwe knoop te vormen. Deze wordt vervolgens aan de "open" prioriteitswachtlijn toegevoegd, tenzij hier al een identieke knoop met lagere of gelijke heuristiek in zit.


We define a search node of the game to be a board (opstelling), the number of moves made to reach the board, and the previous search node.

The success of this approach hinges on the choice of priority function for a search node. We consider two priority functions:

1. Hamming priority function. The number of blocks in the wrong position, plus the number of moves made so far to get to the search node. Intuitively, a search node with a small number of blocks in the wrong position is close to the goal, and we prefer a search node that have been reached using a small number of moves.
2. Manhattan priority function. The sum of the Manhattan distances (sum of the vertical and horizontal distance) from the blocks to their goal positions, plus the number of moves made so far to get to the search node. 

For example, the Hamming and Manhattan priorities of the initial search node below are 5 and 10, respectively.


     8  1  3        1  2  3     1  2  3  4  5  6  7  8    1  2  3  4  5  6  7  8
     4     2        4  5  6     ----------------------    ----------------------
     7  6  5        7  8        1  1  0  0  1  1  0  1    1  2  0  0  2  2  0  3

     initial          goal         Hamming = 5 + 0          Manhattan = 10 + 0
