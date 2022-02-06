import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField; 
import java.awt.event.*; 
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.*;
//import java.applet.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MemoryGame extends JFrame implements ActionListener{

    
    private int numPairs;
 private int columns;
 private int rows;
 private int buttonsSelected = 0;
 private int num1;
 private int num2; 
 private int correct = 0;
 private String first;
 private String second;
 JLabel chooseLayout = new JLabel("CHOOSE YOUR LAYOUT"); 
 JLabel enterData = new JLabel("ENTER YOUR Pairs to Match");
JPanel layoutBtnPnl = new JPanel();
JPanel layoutHdrPnl = new JPanel();
JPanel dataTxtPnl = new JPanel();
JPanel dataHdrPnl = new JPanel();
JPanel memoryPnl = new JPanel();
JButton[] layouts = new JButton[6];
JButton[] memoryBtns;
JButton submit = new JButton("SUBMIT");

String[] dimensions = {"3x4","4x4","4x5","4x6","5x6","6x6"};
String[][] pairs;

JTextField[][] dataFields;

public MemoryGame()
{
    super("Memory Matching Game");
    setSize(1200, 1000);
    setLocation(300, 10);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(2,1));
    layoutBtnPnl.setLayout(new GridLayout(2,3,15,15));
    layoutHdrPnl.setLayout(new GridBagLayout());


    for(int i=0; i<layouts.length; i++){
        layouts[i] = new JButton(dimensions[i]);
        layouts[i].addActionListener(this);
        layouts[i].setFont(new Font("Arial", Font.BOLD, 50));
        layouts[i].setBackground(new Color(218,105,0));
        
        
        layoutBtnPnl.add(layouts[i]);
    }

    submit.addActionListener(this);
    submit.setFont(new Font("Arial", Font.BOLD, 30));
    submit.setBackground(new Color(100,150,206,205));
    chooseLayout.setFont(new Font("Arial", Font.BOLD, 70));
    layoutHdrPnl.add(chooseLayout);
    layoutHdrPnl.setBackground(new Color(135,206,250));
    layoutBtnPnl.setBackground(new Color(135,206,250));
    add(layoutHdrPnl);
    add(layoutBtnPnl);

   
    setBounds(0,0,1000,700);
    setVisible(true);
}
public void getPairs()
{
    dataTxtPnl.setLayout(new GridLayout(numPairs,2,15,5));
    dataHdrPnl.setLayout(new GridBagLayout());
    this.setLayout(new BorderLayout());

    dataFields = new JTextField[numPairs][2];
 
    pairs = new String[numPairs][2];
    for(int i=0; i<numPairs; i++){
        for(int j=0; j<2; j++){
            dataFields[i][j] = new JTextField();
            dataFields[i][j].setEditable(true);
            dataFields[i][j].setBounds(50,30,50 , 70);
            dataTxtPnl.add(dataFields[i][j]);
        }
    }
    enterData.setFont(new Font("Arial", Font.BOLD, 50));
    dataHdrPnl.add(enterData);
    this.remove(layoutHdrPnl);
    this.remove(layoutBtnPnl);
    this.repaint();
    this.add(dataHdrPnl, BorderLayout.NORTH);
    this.add(dataTxtPnl, BorderLayout.CENTER); 
    this.add(submit, BorderLayout.SOUTH);
    this.validate();
} // end getPairs

public void shuffleArray(JButton[] ar)
{
    Random rnd = new Random();
    for (int i = ar.length - 1; i > 0; i--)
    {
        int index = rnd.nextInt(i + 1);

        JButton a = ar[index];
        ar[index] = ar[i];
        ar[i] = a;
    }
}
public void setMemoryBoard()
{
    memoryPnl.setLayout(new GridLayout(rows,columns,5,5));
    memoryBtns = new JButton[numPairs*2];

    for(int i=0; i<numPairs; i++){
        for(int j=0; j<2; j++){
            pairs[i][j] = dataFields[i][j].getText();
        }
    }

    for(int x=0; x<numPairs*2; x++){
        memoryBtns[x] = new JButton();
        memoryBtns[x].addActionListener(this);
        memoryBtns[x].setBackground(new Color(170,57,57));

        memoryBtns[x].setFont(new Font("Arial", Font.BOLD, 40));
    }

    shuffleArray(memoryBtns);

    for(int x=0; x<numPairs*2; x++){
        memoryPnl.add(memoryBtns[x]);
    }
    this.remove(dataHdrPnl);
    this.remove(dataTxtPnl);
    this.remove(submit);
    this.repaint();
    this.add(memoryPnl);
    this.validate();
} // end setMemoryBoard
public void actionPerformed(ActionEvent e)
{
    Object source = e.getSource();

    if(source == layouts[0]){
        numPairs = 6;
        columns = 4;
        rows = 3;
        getPairs();
    }
    if(source == layouts[1]){
        numPairs = 8;
        columns = 4;
        rows = 4;
        getPairs();
    }
    if(source == layouts[2]){
        numPairs = 10;
        columns = 5;
        rows = 4;
        getPairs();
    }
    if(source == layouts[3]){
        numPairs = 12;
        columns = 6;
        rows = 4;
        getPairs();
    }
    if(source == layouts[4]){
        numPairs = 15;
        columns = 6;
        rows = 5;
        getPairs();
    }
    if(source == layouts[5]){
        numPairs = 18;
        columns = 6;
        rows = 6;
        getPairs();
    }

    if(source == submit){
        setMemoryBoard();
    }

    for(int i=0; i<memoryBtns.length; i++){
        if(source==memoryBtns[i]){
            if(buttonsSelected==0){
                memoryBtns[i].setEnabled(false);
                first = pairs[i%numPairs][i/numPairs];
                memoryBtns[i].setText(first);
                memoryBtns[i].setBackground(new Color(85,170,85));
                buttonsSelected++;
                num1=i;
            }else if(buttonsSelected==1){
                memoryBtns[i].setEnabled(false);
                second = pairs[i%numPairs][i/numPairs];
                memoryBtns[i].setText(second);
                memoryBtns[i].setBackground(new Color(85,170,85));
                buttonsSelected++;
                num2=i;
                if(correct==numPairs-1){
                    terminator();
                }
            }else{
                for(int a=0; a<numPairs; a++){
                    if((first.equals(pairs[a][0]) && second.equals(pairs[a][1])) || (second.equals(pairs[a][0]) && first.equals(pairs[a][1]))){
                        memoryBtns[num1].setEnabled(false);
                        memoryBtns[num2].setEnabled(false);
                        memoryBtns[num1].setBackground(new Color(81,95,144));
                        memoryBtns[num2].setBackground(new Color(81,95,144));
                        a=numPairs;
                        correct++;

                        memoryBtns[i].setEnabled(false);
                        first = pairs[i%numPairs][i/numPairs];
                        memoryBtns[i].setText(first);
                        memoryBtns[i].setBackground(new Color(85,170,85));
                        num1=i;
                        buttonsSelected=1;
                    }else if(a==numPairs-1){
                        memoryBtns[num1].setEnabled(true);
                        memoryBtns[num2].setEnabled(true);
                        memoryBtns[num1].setText("");
                        memoryBtns[num2].setText("");
                        memoryBtns[num1].setBackground(new Color(170,57,57));
                        memoryBtns[num2].setBackground(new Color(170,57,57));

                        memoryBtns[i].setEnabled(false);
                        first = pairs[i%numPairs][i/numPairs];
                        memoryBtns[i].setText(first);
                        memoryBtns[i].setBackground(new Color(85,170,85));
                        num1=i;
                        buttonsSelected=1;
                    }
                }
            }//else else
        }//end if
    }//end for
} // end actionPerformed
public void terminator()
{
    this.remove(memoryPnl);
    this.repaint();
    this.validate();
    JLabel end = new JLabel("GOOD JOB YOU WON!!!");
    end.setFont(new Font("Times New Roman", Font.ITALIC, 90));
    this.setLayout(new GridBagLayout());
    this.add(end);
    this.setVisible(true);
}


public static void main(String[] args)
{
    MemoryGame harambe = new MemoryGame();
}
}
