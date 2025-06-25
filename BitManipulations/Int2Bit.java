// Description: Convert Integer types to its internal bit representation
//
// FileName: Int2Bit.java
//
class Int2Bit{
    public static byte[] Int16ToByte(short n){
        return new byte[]{(byte)(n>>8), (byte) n};
    }

    public static byte[] Int32ToByte(int n){
        return new byte[]{(byte)(n>>24), (byte)(n>>16), (byte)(n>>8), (byte) n};
    }

    public static byte[] Int64ToByte(long n){
        return new byte[]{(byte)(n>>56), (byte)(n>>48), (byte)(n>>40), (byte)(n>>32), (byte)(n>>24), (byte)(n>>16), (byte)(n>>8), (byte) n};
    }

    
    public static String Bytes2String(byte[] b){
        byte[] bitmasks = new byte[]{
            (byte)0b1000_0000,
            (byte)0b0100_0000,
            (byte)0b0010_0000,
            (byte)0b0001_0000,
            (byte)0b0000_1000,
            (byte)0b0000_0100,
            (byte)0b0000_0010,
            (byte)0b0000_0001,
        };
        StringBuilder result = new StringBuilder(b.length*8*2);
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < 8; j++) {
                if ((b[i] & bitmasks[j]) != 0) {
                    result.append("1");
                }
                else{
                    result.append("0");
                }

                if (j == 3) {
                    result.append(" ");
                }
            }

            result.append("  ");
        }

        return result.toString();
    }

}