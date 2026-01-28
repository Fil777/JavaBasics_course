import java.util.Objects;

public final class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        if (!(obj instanceof ComplexNumber)) return false;
        ComplexNumber that = (ComplexNumber) obj;
        return Double.compare(that.re, this.re) == 0 && Double.compare(that.im, this.im) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }
}