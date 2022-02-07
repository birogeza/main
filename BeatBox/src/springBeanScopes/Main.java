package springBeanScopes;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.midi.*;
import java.io.*;
import java.util.*;

public class Main {

    JPanel mainPanel;
    //a jelölőnégyzeteket tömblistában tároljuk.
    ArrayList<JCheckBox> checkBoxList;
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    JFrame theFrame;

    //Ezek a hangszerek nevei String tömbként, a grafikus felületen (egy-egy sorban) megjelenő szövegcimkék számára.
    String[] instrumentNames = {"Bass Drum","Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
            "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "CowBell", "Vibraslap",
            "Low-mid Tom", "High Agogo", "Open High Conga"};

    //a dob tényleges "billentyűi":
    int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

    public static void main(String[] args) {
        new Main().buildGUI();
    }

    public void buildGUI(){

        theFrame = new JFrame("Cyber BeatBox");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        //üres szegély (empty border) margót biztosít a panel széle és az összetevők között, látványosabb
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        checkBoxList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton upTempo = new JButton("Tempo up");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("Tempo down");
        downTempo.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new MySaveActionListener());
        buttonBox.add(saveButton);

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new MyLoadActionListener());
        buttonBox.add(loadButton);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for(int i = 0; i < 16; i++){
            nameBox.add(new Label(instrumentNames[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST,nameBox);

        theFrame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16,16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);

        /*Létrehozunk jelölőnégyzeteket, "false" értékre állítjuk őket és hozzáadjuk a tömblistához és a GUI-hoz*/
        for(int i = 0; i < 256; i++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkBoxList.add(c);
            mainPanel.add(c);
        }
        setUpMidi();

        theFrame.setBounds(50,50,30,30);
        theFrame.pack();
        theFrame.setVisible(true);
    }

    /*Ez a tagfüggvény: a szokásos midi beállítás, a sorozat feldolgozó, az utasítássorozat a sáv számára:*/
    public void setUpMidi(){
        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ,4);
            track =sequence.createTrack();
            sequencer.setTempoInBPM(120);
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex);
        }
    }

    /*A jelölőnégyzetek állapotát Midi-eseményekké alakítjuk és hozzáadjuk őket a sávhoz:*/
    public void buildTrackAndStart(){
        /*Létrehozunk egy 16 elemű tömböt, amelyben egy hangszer értékeit tároljuk a 16 ütemre.
         * Ha egy hangszernek meg kell szólalnia egy adott ütemben, a tömb adott értéke lesz a kulcs.
         * Ha a hangszernek nem kell megszólalnia, az említett ütemben nullát szúrunk be.*/
        int[] trackList = null;

        /*Megszabadulunk a régi sávoktól, és létrehozunk egy újat.*/
        sequence.deleteTrack(track);
        track = sequence.createTrack();

        /*Ezt mind a 16 sorra (hangszerre) elvégezzük:*/
        for(int i = 0; i < 16; ++i){
            trackList = new int[16];

            /*Beállítjuk a kulcsot, ami a "hangszert" jelképezi. Az egyes hangszerek MIDI-számazonosítóit az instruments
             * - hangszerek - tömb tárolja.*/
            int key = instruments[i];
            for(int j = 0; j < 16; j++){
                JCheckBox jc = (JCheckBox) checkBoxList.get(j + (16 * i));
                /*Be van kapcsolva a jelölőnégyzet erre az ütemre? Ha igen, a tömb megfelelő rekeszébe (az adott ütemet
                 * jelképező rekeszbe) beszúrjuk a kulcs értéket. Ha viszont nem, akkor a hangszernek nem kell meg-
                 * szólalni ebben az ütemben, ezért nullát szúrunk be.*/
                if(jc.isSelected()) {
                    trackList[j] = key;
                }else{
                    trackList[j] = 0;
                }
            }
            /*Ehhez a hangszerhez, és mind a 16 ütemhez eseményeket hozunk létre és hozzáadjuk azokat a sávhoz.*/
            makeTracks(trackList);
            track.add(makeEvent(176,1,127,0,16));
        }

        /*Mindig gondoskodni kell róla, hogy legyen esemény a 16. ütemhez (a számolás 0-15-ig tart).
         * Különben a BeatBox esetleg nem megy végig mind a 16 ütemen, mielőtt újrakezdené a ciklust.*/
        track.add(makeEvent(192,9,1,0,15));
        try{
            sequencer.setSequence(sequence);
            /*hogy itt adhatod meg a ciklus ismétléseinek számát - ebben az esetben folyamatos ismétlést állítunk be.*/
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            /*Lejátszás:*/
            sequencer.start();
            sequencer.setTempoInBPM(120);
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex);
        }
    }

    /*Az első a belső osztályok közül - figyelők a gombok számára: start*/
    public class MyStartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            buildTrackAndStart();
        }
    }

    /*A stop gomb belső esemény figyelő osztálya:*/
    public class MyStopListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            sequencer.stop();
        }
    }

    /*A tempo up belső esemény figyelő-osztálya:*/
    public class MyUpTempoListener implements ActionListener{

        public float tempoFactor;

        @Override
        public void actionPerformed(ActionEvent e) {
            /*A TempoFactor szabályozza a sorozatfeldolgozó tempóját, megadott tényezővel felszorozva.
             * Az alapértemlezett 1.0, tehát ütésenként +/-3%-kal igazítunk a tempón.*/
            this.tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(tempoFactor * 1.03));
        }
    }
    /*a tempo down belső esemény figyelő osztálya:*/
    public class MyDownTempoListener implements ActionListener{

        public float tempoFactor;

        @Override
        public void actionPerformed(ActionEvent e) {
            this.tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(tempoFactor * .97));
        }
    }

    public class MySaveActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean[] checkBoxState = new boolean[256];

            for (int i = 0; i < 256; i++){
                JCheckBox check = (JCheckBox) checkBoxList.get(i);
                if(check.isSelected()){
                    checkBoxState[i]=true;
                }
            }
            try{
                FileOutputStream fos = new FileOutputStream(new File("BeatBox.ser"));
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(checkBoxState);
            }catch (IOException ioex){
                ioex.printStackTrace();
            }
        }
    }

    public class MyLoadActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean[] checkBoxState = null;
            try{
                FileInputStream fis = new FileInputStream(new File("BeatBox.ser"));
                ObjectInputStream ois = new ObjectInputStream(fis);
                checkBoxState = (boolean[]) ois.readObject();

            }catch(IOException ioex){
                ioex.printStackTrace();
            }
            catch(ClassNotFoundException cnfe){
                cnfe.printStackTrace();
            }
            for (int i = 0; i < 256; ++i){
                JCheckBox check = (JCheckBox) checkBoxList.get(i);
                if(checkBoxState[i]){
                    check.setSelected(true);
                }else{
                    check.setSelected(false);
                }
            }
            sequencer.stop();
            buildTrackAndStart();
        }
    }

    /*Egyszerre egy hangszer számára hoz létre eseményeket, mint a 16 ütemre. Vagyis lehet egy int[] a basszusdobhoz,
     * és a tömb egyes rekeszeiben vagy a hangszer kulcsa vagy nulla áll majd. Ha nulla a hangszernek nem kell megszólalnia
     * az adott ütemben, más esetben viszont létrehozunk egy eseményt és hozzáadjuk a sávhoz.*/
    public void makeTracks(int[] list){
        for(int i = 0; i < 16; ++i){
            int key = list[i];

            if(key != 0){
                track.add(makeEvent(144,9,key,100,i));
                track.add(makeEvent(128,9,key,100,i+1));
            }
        }
    }

    public MidiEvent makeEvent(int cmd, int chan, int one, int two, int tick){
        MidiEvent event = null;
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(cmd, chan, one, two);
            event = new MidiEvent(a, tick);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return event;
    }

}


