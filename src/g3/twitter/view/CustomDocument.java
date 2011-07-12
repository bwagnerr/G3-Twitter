package g3.twitter.view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

class CustomDocument extends PlainDocument {

	private static final long serialVersionUID = 1L;
	int maxLength;

    public CustomDocument(int maxLength ) {
        this.maxLength = maxLength;
    }

    public void insertString( int offset, String str, AttributeSet a )
            throws BadLocationException {
        int length = str.length();

        if (offset + length > maxLength )
            length = maxLength - offset;

        super.insertString( offset, str.substring(0, length), a );
    }
}