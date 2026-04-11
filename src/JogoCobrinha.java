import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JogoCobrinha extends JPanel {




        private final int width;
        private final int height;


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
                   System.out.println("vc apertou espaço");
               }
            }
        }); // mapeeaia o teclado do jogador

        repaint(); // chama o Java swing q abstraem o window tool, biblioteca antiga
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // incia os objetos graficos do java
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(30F));
        g.drawString("COBRINHA",400,200); // escreve uma mensagem como elemento grafico
    }
}
