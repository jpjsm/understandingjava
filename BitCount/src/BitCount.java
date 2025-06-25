
public class BitCount {
	public static int CountBits(int n) {
		int r = 0;
		
		while(n != 0) {
			if((n & 1) != 0) {
				r++;
			}
			
			n >>>= 1;
		}
		
		return r;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (CountBits(0) != 0) {
			System.out.println("Error: CountBits(0) != 0 ");
			return;
		}

		if (CountBits(1) != 1) {
			System.out.println("Error: CountBits(1) != 1 ");
			return;
		}

		if (CountBits(2) != 1) {
			System.out.println("Error: CountBits(2) != 1 ");
			return;
		}

		if (CountBits(7) != 3) {
			System.out.println("Error: CountBits(7) != 3 ");
			return;
		}

		if (CountBits(Integer.MAX_VALUE) != 31) {
			System.out.println("Error: CountBits(Integer.MAX_VALUE) != 31 ");
			return;
		}

		if (CountBits(-1) != 32) {
			System.out.println("Error: CountBits(-1) != 32 ");
			return;
		}

		System.out.println("No Errors");
	}

}
