import java.util.Random;

public class RareCandy {
    public void rareCandyOdds(){
        int trials = 10000;//number of trials
        int handSize = 7;//cards in hand
        int pokemonCount = 20;//pokemon in deck
        int rareCandyLimit = 4;//limited to 4 rare candies max
        int prizePileSize = 6;//prize pile has 6 cards to start out
        int rareCandyCards = 1;//amount of rare candies in deck
        int rareCandiesInPile = 0;//amount of rare candies in the prize pile
        //assigns cards in deck
        Card[] pokemonDeck = new Card[60];
        for(int i = 0; i < pokemonDeck.length; i++){
            pokemonDeck[i] = new Card();
        }
        //puts 20 pokemon cards in deck. Also puts 39 energies in deck and one rare candy.
        pokemonDeck[0].setPokemonCard("rare candy");
        for(int i = 1; i < 21; i++){
            pokemonDeck[i].setPokemonCard("pokemon");
        }
        for(int f = 21; f < pokemonDeck.length; f++){
            pokemonDeck[f].setPokemonCard("energy");
        }


        Random rand = new Random();
        for(int trial = 0; trial < trials; trial++){
            //shuffles Deck
            for(int i = pokemonDeck.length - 1; i > 0; i--){
                int j = rand.nextInt(i + 1);
                Card temp = pokemonDeck[i];
                pokemonDeck[i] = pokemonDeck[j];
                pokemonDeck[j] = temp;
            }
            //checks your hand for a pokemon card
            boolean isPokemon = false;
            for(int card = 0; card < handSize; card++){
                if("pokemon".equals(pokemonDeck[card].getPokemonCard())){
                    isPokemon = true;

                }
            }

            //if you drew a pokemon card it checks if the prize pile will have a rare candy in it
            int rareCandiesFound = 0;
            if(isPokemon){
                //loops through prize pile
                for(int t = handSize ; t < handSize + prizePileSize; t++){
                    if("rare candy".equals(pokemonDeck[t].getPokemonCard())){
                        rareCandiesFound++;
                    }
                }
                if(rareCandyCards == rareCandiesFound){
                    rareCandiesInPile++;//updates rare candies found in prize pile
                }
            }
            //gives you the odds of all the rare candies being in the prize pile
            if((trial + 1) % trials == 0){
                double odds = ((double) rareCandiesInPile / trials) * 100;
                System.out.println("The pokemon cards in deck are:" + pokemonCount);
                System.out.println("The number of rare candy cards in the deck are:" + rareCandyCards);
                System.out.println("The odds of all the rare candies being in the prize pile are:" + odds);

            }
            // checks if the rare candy limit has been hit 10000 trials
            if((trial + 1) % trials == 0 && rareCandyCards < rareCandyLimit){
                //this updates the number of rare candies in the deck as long as they do not go over the limit
                    for(int j = 0; j < pokemonDeck.length; j++){
                        if("energy".equals(pokemonDeck[j].getPokemonCard())){
                            pokemonDeck[j].setPokemonCard("rare candy");
                            rareCandyCards++;
                            break;
                        }
                    }
                //resets variables for next 10000 trials
                trial = 0;
                rareCandiesInPile = 0;
            }
        }
    }
}
