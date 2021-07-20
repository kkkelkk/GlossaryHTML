import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class GlossaryHTMLTest {

    /*
     * Test outputHeader
     */
    /*
     * routine case: test with given file's ("terms.txt") header
     */
    @Test
    public void testOutputHeader_Given_File() {
        SimpleWriter out = new SimpleWriter1L(
                "testSample/sampleFile1ForHeader.txt");
        String name = "GlossaryList";

        SimpleReader ExpectedIn = new SimpleReader1L(
                "testSample/testOutputHeaderFromGivenFile.txt");
        Glossary.outputHeader(name, out);
        SimpleReader in = new SimpleReader1L(
                "testSample/sampleFile1ForHeader.txt");
        while (!in.atEOS() && ExpectedIn.atEOS()) {
            assertEquals(ExpectedIn.nextLine(), in.nextLine());
        }
        out.close();
        in.close();
        ExpectedIn.close();
    }

    /*
     * challenge case: test with a new file's ("newFileForOutputHeader.txt")
     * header
     */
    @Test
    public void testOutputHeader_Different_File() {
        SimpleWriter out = new SimpleWriter1L(
                "testSample/sampleFile2ForHeader.txt");
        String name = "Kelvin's page";

        SimpleReader ExpectedIn = new SimpleReader1L(
                "testSample/newFileForOutputHeader.txt");
        Glossary.outputHeader(name, out);
        SimpleReader in = new SimpleReader1L(
                "testSample/sampleFile2ForHeader.txt");
        while (!in.atEOS() && ExpectedIn.atEOS()) {
            assertEquals(ExpectedIn.nextLine(), in.nextLine());
        }
        out.close();
        in.close();
        ExpectedIn.close();
    }

    /*
     * Test outputWordsAndFooter
     */
    /*
     * routine case: test with given file's list and footer
     */
    @Test
    public void testOutputWordsAndFooter_Given_File() {
        Queue<String> qi = new Queue1L<>();
        qi.enqueue("book");
        qi.enqueue("definition");
        qi.enqueue("glossary");
        qi.enqueue("language");
        qi.enqueue("meaning");
        qi.enqueue("term");
        qi.enqueue("word");

        SimpleWriter output = new SimpleWriter1L(
                "testSample/sampleFile1ForWords.txt");
        SimpleReader expectedIn = new SimpleReader1L(
                "testSample/testOutputWordsAndFooter.txt");
        Glossary.outputWordsAndFooter(qi, output);
        SimpleReader actualIn = new SimpleReader1L(
                "testSample/sampleFile1ForWords.txt");
        while (!actualIn.atEOS() && expectedIn.atEOS()) {
            assertEquals(expectedIn.nextLine(), actualIn.nextLine());
        }
        output.close();
        actualIn.close();
        expectedIn.close();
    }

    /*
     * challenge case: test with a new file
     */
    @Test
    public void testOutputWordsAndFooter_Different_File() {
        Queue<String> qi = new Queue1L<>();
        qi.enqueue("Go");
        qi.enqueue("Bucks");
        qi.enqueue("Beat");
        qi.enqueue("Michigan");
        qi.enqueue("Kelvin");
        qi.enqueue("Baicen");
        qi.enqueue("Wu");

        SimpleWriter output = new SimpleWriter1L(
                "testSample/sampleFile2ForWords.txt");
        SimpleReader expectedIn = new SimpleReader1L(
                "testSample/newFileForOutputWordsAndFooter.txt");
        Glossary.outputWordsAndFooter(qi, output);
        SimpleReader actualIn = new SimpleReader1L(
                "testSample/sampleFile2ForWords.txt");
        while (!actualIn.atEOS() && expectedIn.atEOS()) {
            assertEquals(expectedIn.nextLine(), actualIn.nextLine());
        }
        output.close();
        actualIn.close();
        expectedIn.close();
    }

    /*
     * Test processEachWord(Map<String, String> list, Set<Character>
     * separatorSet, String word, String webName, SimpleWriter output)
     */
    /*
     * routine case: test with a given file which contains no other words in
     * definition
     */
    @Test
    public void testProcessEachWord_Given_File() {
        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);

        Map<String, String> expectedList = new Map1L<>();
        expectedList.add("book", "a printed or written literary work");

        SimpleWriter output = new SimpleWriter1L(
                "testSample/sampleFile1ForEachWord.txt");
        SimpleReader expectedIn = new SimpleReader1L("outputHTML/book.html");
        Glossary.processEachWord(expectedList, separatorSet, "book",
                "GlossaryList", output);
        SimpleReader actualIn = new SimpleReader1L(
                "testSample/sampleFile1ForEachWord.txt");
        while (!actualIn.atEOS() && expectedIn.atEOS()) {
            assertEquals(expectedIn.nextLine(), actualIn.nextLine());
        }
        output.close();
        actualIn.close();
        expectedIn.close();
    }

    /*
     * challenge case: test with a given file which contains two other words in
     * definition
     */
    @Test
    public void testProcessEachWord_Different_File() {
        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);

        Map<String, String> expectedList = new Map1L<>();
        expectedList.add("definition",
                "a sequence of words that gives meaning to a term");

        SimpleWriter output = new SimpleWriter1L(
                "testSample/sampleFile2ForEachWord.txt");
        SimpleReader expectedIn = new SimpleReader1L(
                "outputHTML/definition.html");
        Glossary.processEachWord(expectedList, separatorSet, "definition",
                "GlossaryList", output);
        SimpleReader actualIn = new SimpleReader1L(
                "testSample/sampleFile2ForEachWord.txt");
        while (!actualIn.atEOS() && expectedIn.atEOS()) {
            assertEquals(expectedIn.nextLine(), actualIn.nextLine());
        }
        output.close();
        actualIn.close();
        expectedIn.close();
    }
    /*
     * Test GenerateListAndGetWords
     */

    /*
     * routine case : test with given file
     */
    @Test
    public void testGenerateListAndGetWords_Given_File() {
        Map<String, String> expectedList = new Map1L<>();
        expectedList.add("meaning",
                "something that one wishes to convey, especially by language");
        expectedList.add("term", "a word whose definition is in a glossary");
        expectedList.add("word",
                "a string of characters in a language, which has at least one character");
        expectedList.add("definition",
                "a sequence of words that gives meaning to a term");
        expectedList.add("glossary",
                "a list of difficult or specialized terms, with their definitions, usually near the end of a book");
        expectedList.add("language",
                "a set of strings of characters, each of which has meaning");
        expectedList.add("book", "a printed or written literary work");
        Queue<String> expectedQi = new Queue1L<>();
        expectedQi.enqueue("meaning");
        expectedQi.enqueue("term");
        expectedQi.enqueue("word");
        expectedQi.enqueue("definition");
        expectedQi.enqueue("glossary");
        expectedQi.enqueue("language");
        expectedQi.enqueue("book");
        Map<String, String> actualList = new Map1L<>();
        Queue<String> actualQi = new Queue1L<>();
        SimpleReader in = new SimpleReader1L("terms.txt");
        Glossary.generateListAndGetWords(actualList, actualQi, in);
        assertEquals(expectedList.toString(), actualList.toString());
        assertEquals(expectedQi.toString(), actualQi.toString());
        in.close();
    }

    /*
     * challenge test: test with a new file
     */
    @Test
    public void testGenerateListAndGetWords_Different_File() {
        Map<String, String> expectedList = new Map1L<>();
        expectedList.add("Kelvin", "This is my English name");
        expectedList.add("BaicenWu", "This is my real name");
        expectedList.add("China",
                "Where I came from, Where I was born, Where my parents live.");
        Queue<String> expectedQi = new Queue1L<>();
        expectedQi.enqueue("Kelvin");
        expectedQi.enqueue("BaicenWu");
        expectedQi.enqueue("China");
        Map<String, String> actualList = new Map1L<>();
        Queue<String> actualQi = new Queue1L<>();
        SimpleReader in = new SimpleReader1L(
                "testSample/newFileForGenerateListAndGetWords.txt");
        Glossary.generateListAndGetWords(actualList, actualQi, in);
        assertEquals(expectedList.toString(), actualList.toString());
        assertEquals(expectedQi.toString(), actualQi.toString());
        in.close();
    }

    /*
     * Test generateElements
     */
    /*
     * challenge test: test one whitespace
     */
    @Test
    public void testGenerateElements_whitespace() {
        /*
         * there is one whitespace in str
         */
        String str = " ";
        /*
         * manually create the expected separator by adding character one by one
         */
        Set<Character> expectedSeparator = new Set1L<>();
        expectedSeparator.add(' ');
        /*
         * call the method to generate actual separator
         */
        Set<Character> actualSeparator = new Set1L<>();
        Glossary.generateElements(str, actualSeparator);
        assertEquals(expectedSeparator.toString(), actualSeparator.toString());
    }

    /*
     * routine test: test some normal characters
     */
    @Test
    public void testGenerateElements_normal_characters() {
        /*
         * there are some normal characters in str
         */
        String str = "gshrwe";
        /*
         * manually create the expected separator by adding character one by one
         */
        Set<Character> expectedSeparator = new Set1L<>();
        expectedSeparator.add('g');
        expectedSeparator.add('s');
        expectedSeparator.add('h');
        expectedSeparator.add('r');
        expectedSeparator.add('w');
        expectedSeparator.add('e');
        /*
         * call the method to generate actual separator
         */
        Set<Character> actualSeparator = new Set1L<>();
        Glossary.generateElements(str, actualSeparator);
        assertEquals(expectedSeparator.toString(), actualSeparator.toString());
    }

    /*
     * boundary test: test one character
     */
    @Test
    public void testGenerateElements_single_character() {
        /*
         * there is only one character in str
         */
        String str = "g";
        /*
         * manually create the expected separator by adding character one by one
         */
        Set<Character> expectedSeparator = new Set1L<>();
        expectedSeparator.add('g');
        /*
         * call the method to generate actual separator
         */
        Set<Character> actualSeparator = new Set1L<>();
        Glossary.generateElements(str, actualSeparator);
        assertEquals(expectedSeparator.toString(), actualSeparator.toString());
    }

    /*
     * String nextWordOrSeparator(String text, int position, Set<Character>
     * separators)
     */
    /*
     * Test nextWordOrSeparator
     */
    /*
     * boundary case: single character that is not in separator
     */
    @Test
    public void testNextWordOrSeparator_Single_Character_Not_In_Separator() {
        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);
        String text = "g";
        int position = 0;
        String actualOutput = Glossary.nextWordOrSeparator(text, position,
                separatorSet);
        assertEquals(text, actualOutput);
    }

    /*
     * routine case: a sentence
     */
    @Test
    public void testNextWordOrSeparator_Sentence() {
        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);
        String text = "My name is Kelvin";
        String result = "My";
        int position = 0;
        String actualOutput = Glossary.nextWordOrSeparator(text, position,
                separatorSet);
        assertEquals(result, actualOutput);
    }
}
