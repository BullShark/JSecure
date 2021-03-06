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
public interface BackendReqs {
    void setAlpha(boolean isAlpha);
    void setNumeric(boolean isNumeric);
    void setPunctuation(boolean isPunc);
    void setLength(int len) throws ZeroPasswordLengthException;
    boolean getAlpha();
    boolean getNumeric();
    boolean getPunctuation();
    int getLength();
    String generateNew() throws InvalidPasswordException;
}
