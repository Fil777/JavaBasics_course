import java.util.Arrays;

public class SpamAnalyzer extends KeywordAnalyzer {
    private String[] keywords;

    SpamAnalyzer(String[] inputStringArr) {
        this.keywords = Arrays.copyOf(inputStringArr, inputStringArr.length);
    }

    @Override
    protected String[] getKeywords() {
        return this.keywords;
    }

    @Override
    protected Label getLabel() {
        return Label.SPAM;
    }
}
