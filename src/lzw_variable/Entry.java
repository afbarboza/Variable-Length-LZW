package lzw_variable;

public class Entry {
    private String word;
    private byte[] code;
    
    public Entry(long code, String word) {
        this.word = word;
        this.code = BitManipulator.getBytetArray(code);
    }

    public Entry(byte[] code, String word) {
        this.word = word;
        this.code = code;
    }
    
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }
   
    /**
     * @param cmpCode: conjunto de bits a ser comparado.
     * @return: true, se cmpCode e this.code representam o mesmo valor binario
     *          false, caso contrario.
     * 
     * @author Alex Barboza
     */
   public boolean isTheSameCode(byte[] cmpCode) {
       if (this.code.length != cmpCode.length) {
           return false;
        } else {
           int lastByte = this.code.length;
           return (this.code[lastByte] == cmpCode[lastByte]);
       }
   }
}
