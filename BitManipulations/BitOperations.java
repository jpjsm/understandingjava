public class BitOperations {
    public static int BitCount(int x){
        int bitcount = 0;
        if (x < 0) {
            bitcount++;
            x = x & 0x7FFFFFFF;
        }
        while (x != 0) {
            bitcount++;
            x = x & (x-1);
        }

        return bitcount;
    }

    public static int BitCount(long x){
        int bitcount = 0;
        if (x < 0) {
            bitcount++;
            x = (x & 0x7FFFFFFF_FFFFFFFFL);
        }
        while (x != 0) {
            bitcount++;
            x =x & (x-1);
        }

        return bitcount;
    }    

    public static int BitCount(short x){
        int bitcount = 0;
        if (x < 0) {
            bitcount++;
            x =(short) (x & 0x7FFF);
        }
        while (x != 0) {
            bitcount++;
            x =(short) (x & (x-1));
        }

        return bitcount;
    }    

    public static int BitCount(byte x){
        int bitcount = 0;
        if (x < 0) {
            bitcount++;
            x =(byte) (x & 0x7F);
        }
        while (x != 0) {
            bitcount++;
            x =(byte) (x & (x-1));
        }

        return bitcount;
    }    
}
