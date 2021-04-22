package com.technicaltest.business;

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
        if (sequences <= 1) {
            //diagonalsRightToLeftValidation(dna);
            diagonalsLeftToRightValidation(dna);
        }
        return sequences;
    }

    private void columnValidation(int column, char[][] dna) {
        int equalLetters  = 0;

        for (int i = 0; i < dna.length - 1; i++) {
            for (int j = i; j < dna.length; j++) {
                if (Character.toLowerCase(dna[i][column]) == Character.toLowerCase(dna[j][column])) {
                    equalLetters ++;
                } else {
                    equalLetters  = 0;
                    break;
                }
                if (equalLetters  >= MINIMUM_SIZE_MUTANT) {
                    this.sequences++;
                    equalLetters  = 0;
                }
            }
            equalLetters  = 0;
        }
    }

    private void rowValidation(int row, char[][] dna) {
        int equalLetters = 0;

        for (int i = 0; i < dna.length - 1; i++) {
            for (int j = i; j < dna.length; j++) {
                if (Character.toLowerCase(dna[row][i]) == Character.toLowerCase(dna[row][j])) {
                    equalLetters++;
                } else {
                    equalLetters = 0;
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

    private void diagonalsRightToLeftValidation(char[][] dna) {


/*		for (int i = 1 - dna.length; i <= dna.length; i++) {
			StringBuilder diagonal = new StringBuilder();
			for (int x = -Math.min(0, i), y = Math.max(0, i); x < dna.length && y < dna.length; x++, y++) {


				diagonal.append(dna[x][y]);
			}

			this.validateRow(diagonal.toString());

		}*/
        int k = 0;
        for (int j = dna.length - 2; j >= MINIMUM_SIZE_MUTANT - 1; j--) {
            StringBuilder diagonal = new StringBuilder();
            int l = j;
            for (int i = 0; i <= dna.length - 2 - k; i++) {
                diagonal.append(dna[i][l]);
                l--;
            }
            this.validateRow(diagonal.toString());
            k++;
        }

        int limitSize = dna.length - MINIMUM_SIZE_MUTANT;

        for (int i = 0; i <= limitSize; i++) {
            StringBuilder diagonal = new StringBuilder();
            int l = i;
            for (int j = dna.length - 1; j >= 0 + i; j--) {
                diagonal.append(dna[l][j]);
                l++;
            }
            this.validateRow(diagonal.toString());
        }

    }

    private void diagonalsLeftToRightValidation(char[][] dna) {

        for (int i = MINIMUM_SIZE_MUTANT - dna.length; i <= dna.length - MINIMUM_SIZE_MUTANT; i++) {
            StringBuilder diagonal = new StringBuilder();
            for (int y = -Math.min(0, i), x = Math.max(0, i); x < dna.length && y < dna.length; x++, y++) {
                diagonal.append(dna[x][y]);
            }
            this.validateRow(diagonal.toString());
        }
    }

    private void validateRow(String row) {
        int equalLetters = 0;

        for (int i = 0; i < row.length() - 1; i++) {
            for (int j = i; j < row.length(); j++) {
                if (Character.toLowerCase(row.charAt(i)) == Character.toLowerCase(row.charAt(j))) {
                    equalLetters++;
                } else {
                    equalLetters = 0;
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
