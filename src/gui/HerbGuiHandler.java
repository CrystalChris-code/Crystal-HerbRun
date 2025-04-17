package gui;

import data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HerbGuiHandler {
    private HerbTypes selectedHerb;
    private CompostTypes selectedCompost;
    private LogTypes selectedLogs;
    private BirdhouseSeedTypes selectedSeed;

    public HerbGuiHandler() {
        createGui();
    }

    private void createGui() {
        JFrame frame = new JFrame("Select Herb Type");
        frame.setSize(275, 200);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Choose a seed:");
        JComboBox<HerbTypes> herbDropdown = new JComboBox<>(HerbTypes.values());

        JLabel compostlabel = new JLabel("Choose compost");
        JComboBox<CompostTypes> compostDropdown = new JComboBox<>(CompostTypes.values());

        JLabel loglabel = new JLabel("Choose birdhouse");
        JComboBox<LogTypes> logDropdown = new JComboBox<>(LogTypes.values());

        JLabel seedlabel = new JLabel("Choose a birdhouse seed");
        JComboBox<BirdhouseSeedTypes> seedDropdown = new JComboBox<>(BirdhouseSeedTypes.values());

        JButton startButton = new JButton("Start Script");

        panel.add(label);
        panel.add(herbDropdown);
        panel.add(compostlabel);
        panel.add(compostDropdown);
        panel.add(loglabel);
        panel.add(logDropdown);
        panel.add(seedlabel);
        panel.add(seedDropdown);
        panel.add(startButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Button Action
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedHerb = (HerbTypes) herbDropdown.getSelectedItem();
                selectedCompost = (CompostTypes) compostDropdown.getSelectedItem();
                selectedLogs = (LogTypes) logDropdown.getSelectedItem();
                selectedSeed = (BirdhouseSeedTypes) seedDropdown.getSelectedItem();
                frame.dispose(); // Close the GUI after selecting
            }
        });

        // Wait until the user selects a seed
        while (selectedHerb == null || selectedCompost == null || selectedLogs == null || selectedSeed == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public HerbTypes getSelectedHerb() {
        return selectedHerb;
    }

    public CompostTypes getSelectedCompost() {
        return selectedCompost;
    }

    public LogTypes getSelectedLogs() {
        return selectedLogs;
    }

    public BirdhouseSeedTypes getSelectedSeed() {
        return selectedSeed;
    }

}
