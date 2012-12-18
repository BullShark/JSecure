/*
 * Copyright (C) 2012 Christopher Lemire <christopher.lemire@gmail.com>
 * Copyright (C) 2012 Gabriel <zer0.cam0@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
import java.util.Random;

public class SecurePassword implements BackendReqs {
    private String pass;
    private boolean isAlpha, isNumeric, isPunc;
    private int passLen;
    private Random generator;

    public SecurePassword() throws InvalidPasswordException, ZeroPasswordLengthException {
        this(true, true, true, 16);
    }

    public SecurePassword(boolean isA, boolean isN, boolean isP, int len) throws InvalidPasswordException, ZeroPasswordLengthException {
        if(isA == false && isN == false && isP == false) {
            throw new InvalidPasswordException("Password must contain one or more types");
        } else if(len == 0) {
            throw new ZeroPasswordLengthException("Password cannot be zero in length");
        }
        pass = "";
        isAlpha = isA;
        isNumeric = isN;
        isPunc = isP;
        passLen = len;
        generator = new Random();
    }

    /**************************************************************************
     * Getters
     */
    @Override
    public boolean getAlpha() {
        return isAlpha;
    }

    @Override
    public boolean getNumeric() {
        return isNumeric;
    }

    @Override
    public boolean getPunctuation() {
        return isPunc;
    }

    @Override
    public int getLength() {
        return passLen;
    }

    /**************************************************************************
     * Setters
     */
    @Override
    public void setAlpha(boolean isAlpha) {
        this.isAlpha = isAlpha;
    }

    @Override
    public void setNumeric(boolean isNumeric) {
        this.isNumeric = isNumeric;
    }

    @Override
    public void setPunctuation(boolean isPunc) {
        this.isPunc = isPunc;
    }

    @Override
    public void setLength(int len) throws ZeroPasswordLengthException {
        if(len == 0) {
            throw new ZeroPasswordLengthException("Password cannot be zero in length");
        }
        passLen = len;
    }

    /**************************************************************************
     * Miscellaneous
     */
    public String generateNew() throws InvalidPasswordException {
        if(isAlpha == false && isNumeric == false && isPunc == false) {
            throw new InvalidPasswordException("Password must contain one or more types");
        }

        String ch; int ascii;
        for(int x=0; x<passLen; x++) {
            ascii = generator.nextInt(128);
            ch = String.valueOf(Character.toChars(ascii));
            /*
             * Randomize whether alpha, numeric, or punctuation comes next
             */
            switch(generator.nextInt(3)) {
                case 0:
                    if(isAlpha) {
                        if(ch.matches("\\p{Alpha}{1}")) {
                            pass += ch;
                            break;
                        }
                    }
                case 1:
                    if(isNumeric) {
                        if(ch.matches("\\p{Digit}{1}")) {
                            pass += ch;
                            break;
                        }
                    }
                case 2:
                    if(isPunc) {
                        if(ch.matches("\\p{Punct}{1}")) {
                            pass += ch;
                            break;
                        }
                    }
                x--; // A new character wasn't added
            }
        }
        return pass;
    }

    @Override
    public String toString() {
        return pass;
    }
}
