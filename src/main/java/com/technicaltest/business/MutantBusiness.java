package com.technicaltest.business;

/**
 * Class Business
 *
 * @author luismiguelrodriguez
 */
public class MutantBusiness {

    private int sequences = 0;
    public static final int MINIMUM_SIZE_MUTANT = 4;

    public int validateDNA(char[][] dna) {
        if (sequences <= 1) {
            for (int k = 0; k < dna.length; k++) {
                columnValidation(k, dna);
            }
        }
        if (sequences <= 1) {
            for (int k = 0; k < dna.length; k++) {
                rowValidation(k, dna);
            }
        }
        if (sequences <= 1){
            diagonalsAbove(dna);
        }
        if (sequences <= 1){
            diagonalsUnder(dna);
        }
        if (sequences <= 1) {
            diagonalsLeftToRightValidation(dna);
        }
        return sequences;
    }

    /**
     * Search sequence in column
     *
     * @param column
     * @param dna
     */
    private void columnValidation(int column, char[][] dna) {
        int equalLetters = 0;

        for (int i = 0; i < dna.length - 1; i++) {
            for (int j = i; j < dna.length; j++) {
                if (Character.toLowerCase(dna[i][column]) == Character.toLowerCase(dna[j][column])) {
                    equalLetters++;
                } else {
                    break;
                }
                if (equalLetters >= MINIMUM_SIZE_MUTANT) {
                    this.sequences++;
                    equalLetters = 0;
                }
            }
            equalLetters = 0;
        }
    }

    /**
     * Search sequence in row
     *
     * @param row
     * @param dna
     */
    private void rowValidation(int row, char[][] dna) {
        int equalLetters = 0;

        for (int i = 0; i < dna.length - 1; i++) {
            for (int j = i; j < dna.length; j++) {
                if (Character.toLowerCase(dna[row][i]) == Character.toLowerCase(dna[row][j])) {
                    equalLetters++;
                } else {
                    break;
                }
                if (equalLetters >= MINIMUM_SIZE_MUTANT) {
                    this.sequences++;
                    equalLetters = 0;
                }
            }
            equalLetters = 0;
        }
    }

    private void diagonalsAbove(char[][] dna) {
        int k = 0;
        for (int j = dna.length - 2; j >= MINIMUM_SIZE_MUTANT - 1; j--) {
            StringBuilder diagonalAbove = new StringBuilder();
            int l = j;
            for (int i = 0; i <= dna.length - 2 - k; i++) {
                diagonalAbove.append(dna[i][l]);
                l--;
            }
            validateRow(diagonalAbove.toString());
            k++;
        }
    }

    private void diagonalsUnder(char[][] dna) {
        int size = dna.length - MINIMUM_SIZE_MUTANT;

        for (int i = 0; i <= size; i++) {
            StringBuilder diagonalUnder = new StringBuilder();
            int l = i;
            for (int j = dna.length - 1; j >= 0 + i; j--) {
                diagonalUnder.append(dna[l][j]);
                l++;
            }
            validateRow(diagonalUnder.toString());
        }
    }

    /**
     * Search sequence in diagonals
     *
     * @param dna
     */
    private void diagonalsLeftToRightValidation(char[][] dna) {

        for (int i = MINIMUM_SIZE_MUTANT - dna.length; i <= dna.length - MINIMUM_SIZE_MUTANT; i++) {
            StringBuilder diagonal = new StringBuilder();
            for (int y = -Math.min(0, i), x = Math.max(0, i); x < dna.length && y < dna.length; x++, y++) {
                diagonal.append(dna[x][y]);
            }
            validateRow(diagonal.toString());
        }
    }

    /**
     * Validation diagonals
     *
     * @param row
     */
    private void validateRow(String row) {
        int equalLetters = 0;

        for (int i = 0; i < row.length() - 1; i++) {
            for (int j = i; j < row.length(); j++) {
                if (Character.toLowerCase(row.charAt(i)) == Character.toLowerCase(row.charAt(j))) {
                    equalLetters++;
                } else {
                    break;
                }
                if (equalLetters >= MINIMUM_SIZE_MUTANT) {
                    this.sequences++;
                    equalLetters = 0;
                }
            }
            equalLetters = 0;
        }
    }
}
