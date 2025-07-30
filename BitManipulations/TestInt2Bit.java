public class TestInt2Bit {
    public static void main(String[] args){
        for (int index = -128; index < 128; index++) {
            System.out.printf("byte : %22d --> %80s (%2d)\n", index, Int2Bit.Bytes2String(new byte[] {(byte)index}), BitOperations.BitCount((byte)index));
        }

        short[] shortTests = new short[]{
            (short)0x8000, (short)0x8001, 
            (short)0xB000, (short)0xB001, 
            (short)0xE000, (short)0xE001, 
            (short)0xF000, (short)0xF001, 
            (short)-2, (short)-1, 
            (short)0, 
            (short)1, (short)2, 
            (short)0x1FFE, (short)0x1FFF,
            (short)0x2000, (short)0x2001,
            (short)0x3FFE, (short)0x3FFF,
            (short)0x4000, (short)0x4001,
            (short)0x7FFE, (short)0x7FFF
        };
        for (short test : shortTests) {
            System.out.printf("short: %22d --> %80s (%2d)\n", test, Int2Bit.Bytes2String(Int2Bit.Int16ToByte(test)), BitOperations.BitCount(test));
        }

        int[] intTests = new int[]{
            0x8000_0000, 0x8000_0001, 
            0xB000_0000, 0xB000_0001, 
            0xE000_0000, 0xE000_0001, 
            0xF000_0000, 0xF000_0001, 
            -2, -1, 
            0, 
            1, 2, 
            0x1FFF_FFFE, 0x1FFF_FFFF,
            0x2000_0000, 0x2000_0001,
            0x3FFF_FFFE, 0x3FFF_FFFF,
            0x4000_0000, 0x4000_0001,
            0x7FFF_FFFE, 0x7FFF_FFFF
        };
        for (int test : intTests) {
            System.out.printf("int  : %22d --> %80s (%2d)\n", test, Int2Bit.Bytes2String(Int2Bit.Int32ToByte(test)), BitOperations.BitCount(test));
        }

        long[] longTests = new long[]{
            0x8000_0000_0000_0000L, 0x8000_0000_0000_0001L, 
            0xB000_0000_0000_0000L, 0xB000_0000_0000_0001L, 
            0xE000_0000_0000_0000L, 0xE000_0000_0000_0001L, 
            0xF000_0000_0000_0000L, 0xF000_0000_0000_0001L, 
            -2L, -1L, 
            0L, 
            1L, 2L, 
            0x1FFF_FFFF_FFFF_FFFEL, 0x1FFF_FFFF_FFFF_FFFFL,
            0x2000_0000_0000_0000L, 0x2000_0000_0000_0001L,
            0x3FFF_FFFF_FFFF_FFFEL, 0x3FFF_FFFF_FFFF_FFFFL,
            0x4000_0000_0000_0000L, 0x4000_0000_0000_0001L,
            0x7FFF_FFFF_FFFF_FFFEL, 0x7FFF_FFFF_FFFF_FFFFL
        };
        for (long test : longTests) {
            System.out.printf("long : %22d --> %80s (%2d)\n", test, Int2Bit.Bytes2String(Int2Bit.Int64ToByte(test)), BitOperations.BitCount(test));
        }
    }
}
