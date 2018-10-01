// TimerLabel.java
package views.helpers;

import java.util.Calendar;
import javax.swing.*;
import java.awt.event.*;

public class TimerLabel extends JLabel {
	private static Calendar calendar = Calendar.getInstance();
	private static Timer time;
	public TimerLabel() {
		super(formatTimeString(), SwingConstants.RIGHT);

		handleHandler();
	}

	private void handleHandler()
	{
		// creating timer functionality
        time = new Timer(1000,
        		new ActionListener()
        		{
        			@Override
        			public void actionPerformed(ActionEvent event)
        			{
        				setText(formatTimeString());
        			}
        		}
        	);
        time.start();
        
	}

	private static String formatTimeString()
	{
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		return formatTwoCharTimeString(hour) + ":" + formatTwoCharTimeString(minute) + ":" + formatTwoCharTimeString(second);
	}

	private static String formatTwoCharTimeString(int time)
	{
		return (time >= 10) ? time + "" : 0 + "" + time;
	}
}