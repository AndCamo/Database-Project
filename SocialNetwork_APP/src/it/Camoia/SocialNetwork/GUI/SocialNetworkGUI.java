package it.Camoia.SocialNetwork.GUI;
import it.Camoia.SocialNetwork.SocialNetworkManager;

import javax.swing.*;

public class SocialNetworkGUI {

    public static SocialNetworkManager socialManager = new SocialNetworkManager();

    public static void main(String[] args) {
        SocialNetworkFrame frame = new SocialNetworkFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
