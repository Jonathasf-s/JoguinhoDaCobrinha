import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JogoCobrinha extends JPanel implements ActionListener {


        private final int width;
        private final int height;
        private static final int FRAME_RATE = 20;
        private boolean inicioDoJogo = false;

    public JogoCobrinha (final int width, final int height){
            super(); // esta herdando do Jpanel
            this.width = width;
            this.height = height;
            setPreferredSize(new Dimension(width,height));
            setBackground(Color.BLACK);
    }

    public void inciarJogo(){
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);// incia os objetos graficos do java

        if (!inicioDoJogo) {


            g.setColor(Color.WHITE);
            g.setFont(g.getFont().deriveFont(30F));
            g.drawString("COBRINHA", 400, 200); // escreve uma mensagem como elemento grafico
        }
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        repaint();
    }
}
