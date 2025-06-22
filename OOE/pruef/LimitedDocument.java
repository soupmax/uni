import java.util.regex.Pattern;
import javax.swing.text.*;

/**
 * {@code LimitedDocument} ist eine spezialisierte Implementierung von
 * {@link PlainDocument}, die sowohl die maximale Zeichenanzahl als auch eine
 * Liste unerlaubter Zeichen für Benutzereingaben in einem {@link JTextField}
 * oder {@link JTextArea} beschränkt.
 *
 * <p>
 * Diese Klasse verhindert die Eingabe bestimmter Zeichen – insbesondere
 * solcher, die in Dateinamen ungültig sind (z.&nbsp;B.
 * {@code / \ : * ? " < > |}) – und begrenzt gleichzeitig die Länge des
 * eingegebenen Texts.
 * </p>
 *
 * <p>
 * Sowohl Tastatureingaben als auch Einfügungen über die Zwischenablage
 * (Copy & Paste) werden automatisch geprüft und entsprechend gefiltert.
 * </p>
 *
 * <p>
 * <b>Beispiel:</b>
 * </p>
 * 
 * <pre>{@code
 * JTextField field = new JTextField();
 * field.setDocument(new LimitedDocument(50));
 * }</pre>
 *
 * @author Max
 */
public class LimitedDocument extends PlainDocument {
    /** Maximale Anzahl an Zeichen, die das Dokument aufnehmen darf. */
    private final int maxLength;

    /** Zeichen, die nicht erlaubt sind und herausgefiltert werden. */
    private final String forbiddenChars = "/\\:*?\"<>|";

    /**
     * Erstellt ein neues {@code LimitedDocument} mit der angegebenen
     * maximalen Zeichenanzahl.
     *
     * @param maxLength maximale Anzahl an Zeichen, die erlaubt ist
     */
    public LimitedDocument(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Fügt einen String ins Dokument ein – unter Beachtung der maximalen
     * Länge und ohne unerlaubte Zeichen.
     *
     * @param offset Position, an der der Text eingefügt werden soll
     * @param str    einzufügender Text
     * @param attr   Attribute für den Text (z. B. Stilinformationen)
     * @throws BadLocationException falls der Offset ungültig ist
     */
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;

        // Entferne alle verbotenen Zeichen
        str = str.replaceAll("[" + Pattern.quote(forbiddenChars) + "]", "");

        // Wenn durch das Einfügen die maximale Länge überschritten wird, kürzen
        if (getLength() + str.length() > maxLength) {
            str = str.substring(0, maxLength - getLength());
        }

        super.insertString(offset, str, attr); // Einfügen des bereinigten Texts
    }
}
