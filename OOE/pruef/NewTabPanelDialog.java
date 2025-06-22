import javax.swing.*;
import java.awt.Component;
import java.awt.GridLayout;

public class NewTabPanelDialog {
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

    public static class NewTabData {
        public final String category;
        public final boolean isPlainText;

        public NewTabData(String category, boolean isPlainText) {
            this.category = category;
            this.isPlainText = isPlainText;
        }
    }
}
