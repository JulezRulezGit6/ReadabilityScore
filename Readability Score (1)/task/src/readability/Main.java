package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int wordsTotal = 0;
        int sentencesTotal = 0;
        int charactersTotal = 0;
        int syllablesTotal = 0;
        int polySyllablesTotal = 0;

        File file = new File(args[0]);
        System.out.println("The text is : ");
        String mainString = generateMainString(file);

        String[] sentences = mainString.split("[?!.]");
        sentencesTotal = sentences.length;

        String[] words = mainString.split("[\\s]");
        for (String word : words) {
            charactersTotal += word.length();
        }
        wordsTotal = words.length;

        for (String word : words) {
            syllablesTotal += countSyllables(word);
            if (countSyllables(word) > 2) {
                polySyllablesTotal += 1;
            }
        }

        System.out.println();
        System.out.println("Words: " + wordsTotal);
        System.out.println("Sentences: " + sentencesTotal);
        System.out.println("Characters: " + charactersTotal);
        System.out.println("Syllables: " + syllablesTotal);
        System.out.println("Polysyllables: " + polySyllablesTotal);

        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        switch (command) {
            case "ARI":
                calculateARI(charactersTotal, wordsTotal, sentencesTotal);
                break;
            case "FK":
                calculateFK(wordsTotal, sentencesTotal, syllablesTotal);
                break;
            case "SMOG":
                calculateSMOG(polySyllablesTotal, sentencesTotal);
                break;
            case "CL":
                calculateCL(charactersTotal, wordsTotal, sentencesTotal);
                break;
            case "all":
                int scoreARI = calculateARI(charactersTotal, wordsTotal, sentencesTotal);
                System.out.println();
                int scoreFK = calculateFK(wordsTotal, sentencesTotal, syllablesTotal);
                System.out.println();
                int scoreSMOG = calculateSMOG(polySyllablesTotal, sentencesTotal);
                System.out.println();
                int scoreCL = calculateCL(charactersTotal, wordsTotal, sentencesTotal);
                System.out.println();
                System.out.println();
                double averageScore = (double) (scoreARI + scoreCL + scoreFK + scoreSMOG) / 4;
                System.out.printf("This text should be understood in average by %.2f year olds.", averageScore);
                break;
        }

    }

    private static int calculateCL(int charactersTotal, int wordsTotal, int sentencesTotal) {
        double L = (double) charactersTotal / wordsTotal * 100;
        double S = (double) sentencesTotal / wordsTotal * 100;
        double score = 0.0588 * L - 0.296 * S - 15.8;
        int age = getAge(score);
        System.out.printf("Coleman–Liau index: %.2f (about %d year olds).", score, age);
        return age;

    }

    private static int calculateSMOG(int polySyllablesTotal, int sentencesTotal) {
        double score = 1.043 * Math.sqrt((double) polySyllablesTotal * 30 / sentencesTotal) + 3.1291;
        int age = getAge(score);
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d year olds).", score, age);
        return age;
    }

    private static int calculateFK(int wordsTotal, int sentencesTotal, int syllablesTotal) {
        double score = 0.39 * (double) wordsTotal / (double) sentencesTotal + 11.8 * syllablesTotal / wordsTotal - 15.59;
        int age = getAge(score);
        System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d year olds).", score, age);
        return age;
    }

    public static int calculateARI(int charactersTotal, int wordsTotal, int sentencesTotal) {
        double score = 4.71 * (double) charactersTotal / (double) wordsTotal + 0.5 * (double) wordsTotal / (double) sentencesTotal - 21.43;
        int age = getAge(score);
        System.out.printf("Automated Readability Index: %.2f (about %d year olds).", score, age);
        return age;
    }

    private static int getAge(double score) {
        int age = 0;

        int roundedScore = (int) Math.round(score);

        switch (roundedScore) {
            case 1:
            case 2:
                age = roundedScore + 5;
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                age = roundedScore + 6;
                break;
            case 13:
                age = roundedScore + 11;
                break;
            case 14:
                age = roundedScore + 10;
                break;
        }
        return age;
    }

    public static int countSyllables(String word) {
        boolean previousIsSyllable = false;
        int count = 0;
        for (int i = 0; i < word.length(); i++) {

            if (isSyllable(word.charAt(i))) {
                if (!previousIsSyllable) {
                    count++;
                }
                previousIsSyllable = true;
            } else {
                previousIsSyllable = false;
            }
        }

        if ((word.endsWith(".") || word.endsWith("!") || word.endsWith("?")) && word.charAt(word.length() - 2) == 'e' ||
                word.endsWith("e")) {
            count--;
        }
        return Math.max(1, count);

    }

    private static boolean isSyllable(char character) {
        character = Character.toLowerCase(character);
        return character == 'a' ||
                character == 'e' ||
                character == 'i' ||
                character == 'o' ||
                character == 'u' ||
                character == 'y';
    }

    private static String generateMainString(File file) {
        try (Scanner input = new Scanner(file)) {
            String text = input.nextLine();
            System.out.println(text);
            return text;


        } catch (FileNotFoundException e) {
            return "Can't find file. Sorry!";
        }
    }
}
