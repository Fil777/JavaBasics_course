import java.util.Arrays;

public class AsciiCharSequence implements CharSequence {

    private byte[] arr;

    public AsciiCharSequence(byte[] arr){
        this.arr = Arrays.copyOf(arr, arr.length);
        // this.arr = arr;
    }

    @Override
    public int length() {
        return this.arr.length;
    }

    @Override
    public char charAt(int index) {
        return (char)this.arr[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(Arrays.copyOfRange(arr, start, end));
      }

    @Override
    public String toString() {
        return new String(arr);
    }
}