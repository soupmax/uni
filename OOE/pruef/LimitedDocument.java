import java.util.regex.Pattern;
import javax.swing.text.*;

/**
 * {@code LimitedDocument} ist eine spezialisierte Implementierung von
 * {@link PlainDocument}, die eine maximale Zeichenanzahl sowie eine Liste
 * unerlaubter Zeichen für Benutzereingaben in einem {@link JTextField}
 * oder {@link JTextArea} durchsetzt.
 * 
 * <p>
 * Diese Klasse verhindert die Eingabe bestimmter Zeichen (z. B. für Dateinamen
 * ungeeignete Zeichen wie {@code / \ : * ? " < > |}) und beschränkt zusätzlich
 * die Anzahl der Zeichen auf eine festgelegte Obergrenze.
 * </p>
 * 
 * <p>
 * Sowohl Tastatureingaben als auch eingefügte Texte (z. B. via Copy & Paste)
 * werden automatisch gefiltert.
 * </p>
 * 
 * <p>
 * Beispielhafte Anwendung:
 * 
 * <pre>{@code
 * JTextField field = new JTextField();
 * field.setDocument(new LimitedDocument(50));
 * }</pre>
 * </p>
 * 
 * @author Max
 */
public class LimitedDocument extends PlainDocument {
    /** Maximale erlaubte Zeichenanzahl im Dokument. */
    private final int maxLength;

    /** Liste der verbotenen Zeichen, die nicht eingefügt werden dürfen. */
    private final String forbiddenChars = "/\\:*?\"<>|";

    /**
     * Erstellt ein {@code LimitedDocument} mit einer angegebenen maximalen
     * Zeichenanzahl.
     *
     * @param maxLength Die maximale Anzahl an Zeichen, die eingegeben werden
     *                  dürfen.
     */
    public LimitedDocument(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Überschreibt {@link PlainDocument#insertString}, um unerlaubte Zeichen zu
     * filtern und die Eingabe auf eine maximale Länge zu begrenzen.
     *
     * @param offset Die Einfügeposition.
     * @param str    Der einzufügende Text.
     * @param attr   Attributsatz für den Text (z. B. Formatierungen).
     * @throws BadLocationException falls der Offset ungültig ist.
     */
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;

        // Verbotene Zeichen entfernen
        str = str.replaceAll("[" + Pattern.quote(forbiddenChars) + "]", "");

        // Eingabe auf maximale Länge beschränken
        if (getLength() + str.length() > maxLength) {
            str = str.substring(0, maxLength - getLength());
        }

        super.insertString(offset, str, attr); // Nur erlaubte Zeichen einfügen
    }
}
