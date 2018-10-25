package views.helpers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

public class TimeBar extends JPanel {
	private JLabel clock;

	public TimeBar() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		clock = new JLabel();
		clock.setHorizontalAlignment(JLabel.RIGHT);
		clock.setFont(UIManager.getFont("Label.font").deriveFont(Font.BOLD, 12));
		tickTock();
		add(clock);
		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tickTock();
			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();
	}

	public void tickTock() {
		LocalTime clk = LocalTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
//        clock.setText(DateFormat.getDateTimeInstance().format(dtf2));
		clock.setText(clk.format(dtf));
	}
}

class TimerThread extends Thread {

	protected boolean isRunning;

//      protected JLabel dateLabel;
	protected JLabel timeLabel;

	protected SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
	protected SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");

	public TimerThread(JLabel timeLabel) {
//           this.dateLabel = dateLabel;
//           this.timeLabel = timeLabel;
		this.isRunning = true;
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		timeLabel = new JLabel(time.format(dtf2));
	}

	@Override
	public void run() {
		while (isRunning) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					LocalDateTime time = LocalDateTime.now();
					DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
//                     Calendar currentCalendar = Calendar.getInstance();
					// dateLabel.setText(dateFormat.format(currentTime));
					// timeLabel.setText(time.format(dtf2));
				}
			});

			try {
				Thread.sleep(5000L);
			} catch (InterruptedException e) {
			}
		}

	}
}
