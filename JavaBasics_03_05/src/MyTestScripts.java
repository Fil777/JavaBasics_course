/**
 * Chapter 3.5 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 3.5 tests
 */

public class MyTestScripts {

    public static void main(String[] args) {
        final String welcome = "\n" + "Hello and welcome to Chapter 3.5";

        MyTestScripts testObject = new MyTestScripts();

        if (args.length == 0) {
            System.out.println("\n" + welcome + " !\n");
        } else {
            System.out.println("\n" + welcome + args[0] + " " + args[1] + " !\n");
        }

        System.out.println("-------------------------------------------------------");

        // инициализация анализаторов для проверки в порядке данного набора анализаторов
        String[] spamKeywords = {"spam", "bad"};
        int commentMaxLength = 40;
        TextAnalyzer[] textAnalyzers1 = {
                new SpamAnalyzer(spamKeywords),
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(commentMaxLength)
        };
        TextAnalyzer[] textAnalyzers2 = {
                new SpamAnalyzer(spamKeywords),
                new TooLongTextAnalyzer(commentMaxLength),
                new NegativeTextAnalyzer()
        };
        TextAnalyzer[] textAnalyzers3 = {
                new TooLongTextAnalyzer(commentMaxLength),
                new SpamAnalyzer(spamKeywords),
                new NegativeTextAnalyzer()
        };
        TextAnalyzer[] textAnalyzers4 = {
                new TooLongTextAnalyzer(commentMaxLength),
                new NegativeTextAnalyzer(),
                new SpamAnalyzer(spamKeywords)
        };
        TextAnalyzer[] textAnalyzers5 = {
                new NegativeTextAnalyzer(),
                new SpamAnalyzer(spamKeywords),
                new TooLongTextAnalyzer(commentMaxLength)
        };
        TextAnalyzer[] textAnalyzers6 = {
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(commentMaxLength),
                new SpamAnalyzer(spamKeywords)
        };

        // тестовые комментарии
        String[] tests = {
                "This comment is so good.",                            // OK
                "This comment is so Loooooooooooooooooooooooooooong.", // TOO_LONG
                "Very negative comment !!!!=(!!!!;",                   // NEGATIVE_TEXT
                "Very BAAAAAAAAAAAAAAAAAAAAAAAAD comment with :|;",    // NEGATIVE_TEXT or TOO_LONG
                "This comment is so bad....",                          // SPAM
                "The comment is a spam, maybeeeeeeeeeeeeeeeeeeeeee!",  // SPAM or TOO_LONG
                "Negative bad :( spam.",                               // SPAM or NEGATIVE_TEXT
                "Very bad, very neg =(, very .................."      // SPAM or NEGATIVE_TEXT or TOO_LONG
        };

        TextAnalyzer[][] textAnalyzers = {
                textAnalyzers1, textAnalyzers2, textAnalyzers3,
                textAnalyzers4, textAnalyzers5, textAnalyzers6
        };

        int numberOfAnalyzer;   // номер анализатора, указанный в идентификаторе textAnalyzers{№}
        int numberOfTest = 1;       // номер теста, который соответствует индексу тестовых комментариев
        for (String test : tests) {
            numberOfAnalyzer = 1;
            System.out.print("test #" + numberOfTest + ": ");
            System.out.println(test);
            for (TextAnalyzer[] analyzers : textAnalyzers) {
                System.out.print(numberOfAnalyzer + ": ");
                System.out.println(testObject.checkLabels(analyzers, test));
                numberOfAnalyzer++;
            }
            numberOfTest++;
        }

        System.out.println("-------------------------------------------------------");

    }

    public Label checkLabels (TextAnalyzer[] analyzers, String text){
        Label lab;
        for (TextAnalyzer ta : analyzers) {
            lab = ta.processText(text);
            if (lab != Label.OK) return lab;
        }
        return Label.OK;
    }
}