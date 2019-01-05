import java.util.*;
public class RouletteWheel {
	//Reverse roulette lottery with a 'black or red: double previous bet after loss, 
	//minimum bet after win' strategy with $10,000,000 bank.
	
	Map<Integer, Integer> table;
	int winNum;
	Random gen;
	int wallet, bet, minBet, maxBet;
	private boolean bust;
	
	public RouletteWheel(int bank, int minimumBet){
		table = new HashMap<Integer, Integer>();
		setMap();
		gen = new Random();
		wallet = bank;
		maxBet = wallet;
		minBet = minimumBet;
		bust = false;
	}
	
	public void setMap() {
		table.put(0, 0);
		table.put(1, 3);
		table.put(2, 2);
		table.put(3, 3);
		table.put(4, 2);
		table.put(5, 3);
		table.put(6, 2);
		table.put(7, 3);
		table.put(8, 2);
		table.put(9, 3);
		table.put(10, 2);
		table.put(11, 2);
		table.put(12, 3);
		table.put(13, 2);
		table.put(14, 3);
		table.put(15, 2);
		table.put(16, 3);
		table.put(17, 2);
		table.put(18, 3);
		table.put(19, 3);
		table.put(20, 2);
		table.put(21, 3);
		table.put(22, 2);
		table.put(23, 3);
		table.put(24, 2);
		table.put(25, 3);
		table.put(26, 2);
		table.put(27, 3);
		table.put(28, 2);
		table.put(29, 2);
		table.put(30, 3);
		table.put(31, 2);
		table.put(32, 3);
		table.put(33, 2);
		table.put(34, 3);
		table.put(35, 2);
		table.put(36, 3);
		table.put(37, 1);
	}
	
	public void betBlack(int x) {
		spin();
		boolean win = isBlack(winNum);
		if(win) {
			System.out.println("win, plus: "+x);
			wallet+=x;
		}
		else
		{
			System.out.println("lose, minus: "+x);
			wallet-=x;
		}
		System.out.println();
	}
	
	public int lossDoubleBet(int n) {
		bet = minBet;
		int lastWallet;
		for(int i=0; i<n; i++) {
			lastWallet = wallet;
			if(wallet<=0||minBet>wallet) {
				System.out.println("Busted out.");
				bust = true;
			}
			else {
			betBlack(bet);
			if(wallet<lastWallet)
				if(bet*2<maxBet&&bet*2<=wallet) {
					bet*=2;
				}
				else {
					System.out.println("Bet doubling exceeds wallet");
					if(bet>wallet)
						bet=wallet;
				}
			else
				bet = minBet;
			}
			System.out.print("Wallet: ");
			printWallet();
		}
		System.out.println();
		System.out.print("End Wallet: ");
		printWallet();
		System.out.println("Busted out: "+bust);
		System.out.println("--End Round--");
		return wallet;
	}
	
	public boolean isBlack(int x) {
		if(table.get(x)==2)
			return true;
		return false;
	}
	
	public void spin() {
		winNum = gen.nextInt(38);
		System.out.println("Winner: "+winNum);
	}
	
	public void printWinner() {
		System.out.println(winNum);
	}
	
	public void printWallet() {
		System.out.println(wallet);
	}
	
	public int getWallet() {
		return wallet;
	}
	
	public void testgenerator() {
		int count;
		for(int i=0; i<40; i++)
		{
			count = 0;
			for(int j=0; j<3800; j++) {
				if(gen.nextInt(38)==i)
					count++;
			}
			System.out.println(i+": "+count);
		}
	}
	
	public static void main(String[] args) {
		int wallet = 10000000;
		int spins = 100000;
		int minimumBet = 5;
		RouletteWheel foo = new RouletteWheel(wallet, minimumBet);
		foo.lossDoubleBet(spins);
		foo.printWallet();
		System.out.println("Profit: "+(foo.getWallet()-wallet));
		System.out.println("$ per spin: "+((foo.getWallet()-wallet)/spins));
		System.out.println("Hours: "+spins/33);
		System.out.println("Pay rate: "+((foo.getWallet()-wallet)/(spins/33)));
	}
}
