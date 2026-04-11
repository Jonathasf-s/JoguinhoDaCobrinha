import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class JogoCobrinha extends JPanel implements ActionListener {


        private final int width;
        private final int height;
        private static final int FRAME_RATE = 20;
        private boolean inicioDoJogo = false;
        //Fazendo a cobra
        private final List<GamePoints> cobra = new ArrayList<>();

        private final int TamanhoDaCelula;


    public JogoCobrinha (final int width, final int height){
            super(); // esta herdando do Jpanel
            this.width = width;
            this.height = height;
            this.TamanhoDaCelula = width / (FRAME_RATE * 2);
            setPreferredSize(new Dimension(width,height));
            setBackground(Color.BLACK);
    }

    public void inciarJogo(){
        resetGameData();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocusInWindow();


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

               if (e.getKeyCode() == KeyEvent.VK_SPACE){
                   inicioDoJogo = true;
               }
            }
        }); // mapeeaia o teclado do jogador

        new Timer(1000/ FRAME_RATE, this).start();
        //repaint(); // chama o Java swing q abstraem o window tool, biblioteca antiga
    }

    private void resetGameData(){
        cobra.clear();
        cobra.add(new GamePoints(width/2, height/2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);// incia os objetos graficos do java

        if (!inicioDoJogo) {


            g.setColor(Color.WHITE);
            g.setFont(g.getFont().deriveFont(30F));
            g.drawString("COBRINHA", 400, 200); // escreve uma mensagem como elemento grafico
        } else {
            g.setColor(Color.GREEN);
            for (final var point : cobra){
                g.fillRect(point.x, point.y, TamanhoDaCelula, TamanhoDaCelula );
            }
        }
    }

    @Override
    public void actionPerformed(final ActionEvent e) {


        repaint();
    }


    private record GamePoints(int x, int y){}
}
