package ui;

import engine.ApplicationEngine;
import javafx.scene.layout.BorderPane;
import model.Host;
import model.UserOnHost;
import ui.terminal.TerminalView;
import ui.tree.MainTreeView;

public class MainView extends BorderPane {

    public MainView(ApplicationEngine engine) {
        MainTreeView tree = new MainTreeView();
        tree.setListener(new MainTreeView.Listener() {
            @Override
            public void saveHost(Host host) {
                engine.saveHost(host);
                tree.refresh();
            }

            @Override
            public void onSelect(Host host, UserOnHost userOnHost) {
                if (host != null) {System.out.println(host.toString());}
                if (userOnHost != null) {System.out.println(userOnHost.toString());}
            }
        });
        setLeft(tree);
    }
}
