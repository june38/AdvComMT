package topic5;

//Example adapted from java.sun.com
//http://java.sun.com/docs/books/tutorial/collections/interfaces/list.html
import java.util.*;
public class Deal{
    public static void main(String[] args) {
        int numHands = Integer.parseInt(args[0]);
        int cardsPerHand = Integer.parseInt(args[1]);
    	
    	
        // Make a normal 52-card deck.
        String[] suit = {"spades", "hearts", "diamonds", "clubs"};
        String[] rank = {"ace","2","3","4","5","6","7","8",
             "9","10","jack","queen","king"};
        List<String> deck = new LinkedList<String>();
        for (int i = 0; i < suit.length; i++)
            for (int j = 0; j < rank.length; j++)
                deck.add(rank[j] + " of " + suit[i]);

        Collections.shuffle(deck);

        for (int i=0; i < numHands; i++)
            System.out.println(dealHand(deck, cardsPerHand));
    }
	public static <E> List<E> dealHand(List<E> deck, int n) {
		int deckSize = deck.size();
		List<E> handView = deck.subList(deckSize - n, deckSize);
		List<E> hand = new ArrayList<E>(handView);
		handView.clear();
		return hand;
	}
}