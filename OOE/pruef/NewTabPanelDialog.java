import javax.swing.*;
import java.awt.Component;
import java.awt.GridLayout;

/**
 * Die Klasse {@code NewTabPanelDialog} zeigt einen Dialog an,
 * in dem der Benutzer eine neue Kategorie (Tab) benennen und
 * optional als Fließtext-Liste markieren kann.
 *
 * <p>
 * Die Auswahl wird als {@link NewTabData} zurückgegeben.
 * </p>
 *
 * <p>
 * Wird der Dialog abgebrochen oder bleibt das Eingabefeld leer,
 * wird {@code null} zurückgegeben.
 * </p>
 * 
 * @author Max
 */
public class NewTabPanelDialog {

    /**
     * Zeigt einen Dialog zur Eingabe eines Kategorienamens und
     * zur Auswahl, ob es sich um eine Fließtext-Liste handelt.
     *
     * @param parent Das übergeordnete GUI-Element (für Zentrierung)
     * @return {@link NewTabData}-Objekt mit Nutzereingaben oder {@code null} bei
     *         Abbruch
     */
    public static NewTabData showDialog(Component parent) {
        JTextField categoryField = new JTextField(20);
        JCheckBox isPlainTextCheckBox = new JCheckBox("Fließtext-Liste");

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Neue Kategorie eingeben:"));
        panel.add(categoryField);
        panel.add(isPlainTextCheckBox);

        int result = JOptionPane.showConfirmDialog(
                parent,
                panel,
                "Neuer Tab",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String category = categoryField.getText().trim();
            boolean isPlainText = isPlainTextCheckBox.isSelected();

            if (!category.isEmpty()) {
                return new NewTabData(category, isPlainText);
            }
        }
        return null;
    }

    /**
     * Datenklasse zur Rückgabe der vom Benutzer eingegebenen
     * Kategorieinformationen aus dem Dialog.
     */
    public static class NewTabData {
        /** Der eingegebene Kategoriename */
        public final String category;

        /** Gibt an, ob die Kategorie als Fließtext dargestellt werden soll */
        public final boolean isPlainText;

        /**
         * Erstellt ein neues {@code NewTabData}-Objekt mit Name und Typ.
         *
         * @param category    Der Kategoriename
         * @param isPlainText {@code true}, wenn Fließtext verwendet werden soll
         */
        public NewTabData(String category, boolean isPlainText) {
            this.category = category;
            this.isPlainText = isPlainText;
        }
    }
}
