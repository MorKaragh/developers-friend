package ui.terminal;

import config.ApplicationProperties;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


public class TerminalView extends TextArea {

    public TerminalView() {
        setStyle("-fx-control-inner-background:#000000; -fx-text-fill: #ffffff;");
        setMinWidth(640D);
        try (InputStream inputStream = Objects.requireNonNull(TerminalView.class.getClassLoader()
                .getResource("UbuntuMono-R.ttf")).openStream() ){
            setFont(Font.loadFont(inputStream, 14));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTestText();
    }

    public void print(String toPrint) {
        Text text = new Text(toPrint);
        text.setFill(Color.WHITE);
        getChildren().add(text);
    }


    private void setTestText() {
        setText("total 6024\n" +
                "-rw-rw-r--   1 root              utmp              147840 мар 28  2020 wtmp\n" +
                "drwxrwxr-x  14 root              syslog              4096 мар 28  2020 .\n" +
                "-rw-r--r--   1 root              root               32597 мар 28  2020 Xorg.0.log\n" +
                "-rw-r--r--   1 root              adm                73389 мар 28  2020 dmesg\n" +
                "-rw-------   1 root              root             1014057 мар 28  2020 boot.log\n" +
                "-rw-r--r--   1 root              root                1189 мар 28  2020 gpu-manager.log\n" +
                "-rw-r-----   1 syslog            adm               680197 мар 28 21:50 syslog\n" +
                "-rw-r-----   1 syslog            adm                54897 мар 28 21:17 auth.log\n" +
                "-rw-r-----   1 syslog            adm               860494 мар 28 20:20 kern.log\n" +
                "-rw-r--r--   1 root              root               57627 мар 28 20:18 Xorg.1.log\n" +
                "-rw-r--r--   1 root              adm                73351 мар 28 03:41 dmesg.0\n" +
                "drwxr-xr-x   2 root              root                4096 мар 28 03:41 cups\n" +
                "-rw-r-----   1 syslog            adm               152430 мар 28 03:41 syslog.1\n" +
                "-rw-r--r--   1 root              root               38258 мар 28 01:02 Xorg.1.log.old\n" +
                "-rw-r--r--   1 root              root                1196 мар 28 01:02 gpu-manager-switch.log\n" +
                "-rw-r--r--   1 root              root               33657 мар 28 01:02 Xorg.0.log.old\n" +
                "-rw-r-----   1 syslog            adm                82899 мар 27 00:00 syslog.2.gz\n" +
                "-rw-r--r--   1 root              adm                17560 мар 26 23:13 dmesg.1.gz\n" +
                "-rw-r--r--   1 root              adm                17515 мар 26 01:26 dmesg.2.gz\n" +
                "-rw-r-----   1 syslog            adm                26175 мар 26 01:26 syslog.3.gz\n" +
                "-rw-r-----   1 syslog            adm               194509 мар 25 00:00 syslog.4.gz\n" +
                "-rw-r--r--   1 root              root              146579 мар 24 23:09 dpkg.log\n" +
                "drwxr-xr-x   2 root              root                4096 мар 24 23:09 apt\n" +
                "-rw-r--r--   1 root              adm                17513 мар 24 22:16 dmesg.3.gz\n" +
                "-rw-r--r--   1 root              adm                17610 мар 24 20:14 dmesg.4.gz\n" +
                "-rw-r-----   1 syslog            adm                83905 мар 24 00:00 syslog.5.gz\n" +
                "-rw-r--r--   1 root              root                8240 мар 23 23:38 alternatives.log\n" +
                "-rw-r--r--   1 root              root               10858 мар 23 23:38 fontconfig.log\n" +
                "-rw-r-----   1 syslog            adm               167418 мар 23 00:00 syslog.6.gz\n" +
                "-rw-r-----   1 syslog            adm                56012 мар 22 00:48 auth.log.1\n" +
                "-rw-r-----   1 syslog            adm              1125873 мар 22 00:48 kern.log.1\n" +
                "-rw-r-----   1 syslog            adm               146775 мар 22 00:48 syslog.7.gz\n" +
                "-rw-r-----   1 syslog            adm                 3629 мар 15 01:38 auth.log.2.gz\n" +
                "-rw-r-----   1 syslog            adm               157729 мар 15 01:38 kern.log.2.gz\n" +
                "drwxr-x---   2 root              adm                 4096 мар  9 02:13 unattended-upgrades\n" +
                "-rw-rw----   1 root              utmp                   0 мар  9 02:13 btmp\n" +
                "-rw-r-----   1 syslog            adm                 1407 мар  9 02:13 auth.log.3.gz\n" +
                "-rw-r-----   1 syslog            adm                36586 мар  9 02:13 kern.log.3.gz\n" +
                "-rw-r--r--   1 root              root              138320 фев 24 00:17 dpkg.log.1\n" +
                "-rw-r-----   1 syslog            adm                 1153 фев 23 04:46 auth.log.4.gz\n" +
                "-rw-r-----   1 syslog            adm                17707 фев 23 04:46 kern.log.4.gz\n" +
                "-rw-r--r--   1 root              root                2945 фев 16 22:34 alternatives.log.1\n" +
                "-rw-rw-r--   1 root              utmp            18704352 фев  6 22:13 lastlog\n" +
                "-rw-r--r--   1 root              root             2049792 фев  6 22:13 faillog\n" +
                "drwxr-xr-x   6 root              root                4096 фев  6 22:13 libvirt\n" +
                "-rw-rw----   1 root              utmp                   0 фев  6 02:08 btmp.1\n" +
                "-rw-r-----   1 root              adm                    0 фев  6 02:08 apport.log\n" +
                "-rw-r-----   1 root              adm                  618 янв 17 00:25 apport.log.1\n" +
                "-rw-r--r--   1 root              root                8416 янв 16 20:41 dpkg.log.2.gz\n" +
                "-rw-r--r--   1 root              root                 340 янв 16 20:29 alternatives.log.2.gz\n" +
                "-rw-r--r--   1 root              root               96260 дек 20 23:42 dpkg.log.3.gz\n" +
                "-rw-r--r--   1 root              root                5765 дек 20 23:42 alternatives.log.3.gz\n" +
                "-rw-r-----   1 root              adm                  407 дек 15 19:42 apport.log.2.gz\n" +
                "drwxr-sr-x+  3 root              systemd-journal     4096 дек 15 13:06 journal\n" +
                "drwxrwxr-x   2 root              root                4096 дек 15 13:02 installer\n" +
                "drwxr-xr-x  14 root              root                4096 окт 17 15:34 ..\n" +
                "drwxr-xr-x   3 root              root                4096 окт 17 15:27 hp\n" +
                "-rw-r--r--   1 root              root              101053 окт 17 15:25 bootstrap.log\n" +
                "-rw-------   1 root              root                   0 окт 17 15:25 ubuntu-advantage.log\n" +
                "drwx------   2 root              root                4096 окт 17 15:24 private\n" +
                "drwxr-xr-x   2 root              root                4096 окт 12 02:54 dist-upgrade\n" +
                "drwx--x--x   2 root              gdm                 4096 окт  7 19:23 gdm3\n" +
                "drwxr-xr-x   2 root              root                4096 сен  5  2019 openvpn\n" +
                "drwx------   2 speech-dispatcher root                4096 авг  5  2019 speech-dispatcher\n");
    }


}
