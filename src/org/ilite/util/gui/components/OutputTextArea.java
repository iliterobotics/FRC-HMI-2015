package org.ilite.util.gui.components;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultCaret;


/**
 * @author Gaelan Dean
 * This a thread safe scrollable text area that is not editable.
 */
public class OutputTextArea {
	private JTextArea mTextArea;
	private JScrollPane mScrollPane;
	private SimpleDateFormat mDateFormat = new SimpleDateFormat("kk:mm:ss:SSS ");
	private final Date mDateForFormatting = new Date();
  private static final String NEWLINE = "\n";
	
	public OutputTextArea()
	{
		mTextArea = new JTextArea();
		mTextArea.setWrapStyleWord(true);
		mTextArea.setEditable(false);
		mScrollPane = new JScrollPane(mTextArea);
		mScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		DefaultCaret caret = (DefaultCaret)mTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}
	
	public void clear()
	{
    SwingUtilities.invokeLater(new Runnable() {     
      @Override
      public void run() {
        mTextArea.setText("");
      }
    });
	}
	
	public void append(long pDate, String pInput)
	{
	  mDateForFormatting.setTime(pDate);
		append(mDateFormat.format(mDateForFormatting) + pInput);
	}
	
	public void append(final String input)
	{
    SwingUtilities.invokeLater(new Runnable() {     
      @Override
      public void run() {
        mTextArea.append(input);
        mTextArea.append(NEWLINE);
      }
    });
	}
	
	public JComponent getTextArea() {
		return mScrollPane;
	}
}
