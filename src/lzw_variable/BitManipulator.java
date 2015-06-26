package lzw_variable;

public final class BitManipulator {
    /**
     * decToBin: retorna o valor binario de um inteiro do tipo long.
     *           Long, em Java, são inteiros na escala: -2^(63) ateh 2^63. 
     * @param val o valor a ser convertido em binario.
     * @return: uma string, armazenando a representacao binaria do valor 
     */
    public static String decToBin(long val) {
        long k;
        String strBinNumber = "";
        
        for (int i = 63; i >= 0; i--) {
            k = val >> i; 
            strBinNumber += (k & 1);
        }
        
        /*String manipulation - at most 64 chars*/
        int i = 0;
        while (i < strBinNumber.length() &&  strBinNumber.charAt(i) == '0') {
            i++;
        }
       
        String strBinMSB = "";
        while (i < strBinNumber.length()) {
            strBinMSB += strBinNumber.charAt(i); 
            i++;
        }
        
       /*returns only the most signifcant bits*/
       return strBinMSB;
    }
    
    /**
    * nBits - calcula a quantidade de bits necessario para representar um valor
    *         inteiro em binario.
    * @param buffer: string contendo a representacao binaria do inteiro
    * @return int, informando o numero de bits necessario para representar
    *         um dado valor.
    * @deprecated: funcao nao mais utilizada - useless
    */
    public static int nBits(String buffer) {
        int nbits = buffer.length();
        double log2n = (Math.log(nbits)/Math.log(2)) + 1; // log(2^n)
        double integerPart = Math.ceil(log2n);
        
        if ((log2n-integerPart) == 0.0f) {
            return (int) log2n;
        } else {
            return (int) Math.floor(log2n);
        }
    }
    
    /**
     * nBytes - calcula a quantidade de bytes necessarios para representar um
     *          valor inteiro em binario.
     * @param buffer: representação textual do valor binario
     * @author Alex Barboza
     */
    public static int nBytes (String buffer) {
        int nbits = buffer.length();
        while (nbits % 8 != 0) nbits++;
        return (nbits/8);
    }
    
    /**
     * getBytetArray - converte um long em um array de bytes.
     * @param val: valor a ser conertido em um array de bytes.
     * @return: o array de bytes que representam o valor passado.
     * @see: o tipo primitivo byte em java representa um inteiro com sinal
     *       que varia de -128 a 128.
     */
    public static byte[] getBytetArray(long val) {
        /* Aplicacao do Algoritmo de Euclides */
        int n = 0;
        while ((128*n) <= val) n++;
        n--;
        
        byte r = 0;
        while (((127*n) + r) != val) r++;
        
        byte[] b = new byte[n+1];
        for (int i = 0; i < n; i++) b[i] = 127;
        b[n] = r;
        return b;
    }
}
