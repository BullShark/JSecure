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
public class MainGUI extends JPanel implements ActionListener {
    public MainGUI() {
        //setLayout(new MigLayout("hidemode 2, nogrid")); //todo
    }

    @Override public void actionPerformed(ActionEvent e) {
    }

    private static void createAndShowGUI() {
        // Create and set up the window.
        frame = new JFrame("JSecure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add content to the window.
        frame.add(new JSecure());

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //todo Let user choose L&F or use System's default
        if(args.length == 1 && (args[0].equals("-g") || args[0].equals("--debug"))) {
            debug = true;
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() { createAndShowGUI(); }
        });
    }
}