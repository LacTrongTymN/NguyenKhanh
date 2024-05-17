import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

class Clock extends JPanel {
    private JLabel timeLabel;
    private int timezoneOffset = 7;

    public Clock() {
        setBounds(0, 0, 200, 100);
        setLayout(new FlowLayout());

        timeLabel = new JLabel();
        add(timeLabel);
        updateTime();
        Start();
    }

    public void Start() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    updateTime();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void updateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        TimeZone timezone = TimeZone.getTimeZone("GMT");
        timezone.setRawOffset(timezoneOffset * 3600 * 1000);
        dateFormat.setTimeZone(timezone);
        timeLabel.setText(dateFormat.format(new Date()));
    }

    public void setTimezoneOffset(int newtimez) {
        timezoneOffset = newtimez;
        updateTime();
    }
}