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
        private boolean gameOver = false;

        private Direction direction = Direction.RIGHT;
        private Direction newDirection = Direction.RIGHT;



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

                handleKey(e.getKeyCode());
            }
        }); // mapeeaia o teclado do jogador

        new Timer(1000/ FRAME_RATE, this).start();
        //repaint(); // chama o Java swing q abstraem o window tool, biblioteca antiga
    }

    private void handleKey(final int keyCode) {

        if (!inicioDoJogo) {
            if (keyCode == KeyEvent.VK_SPACE) {
                inicioDoJogo = true;
            }
        } else if (!gameOver) {
            switch (keyCode){
                case KeyEvent.VK_W:
                    newDirection = Direction.UP;
                    break;

                case KeyEvent.VK_S:
                    newDirection = Direction.DOWN;
                    break;

                case KeyEvent.VK_D:
                    newDirection = Direction.RIGHT;
                    break;

                case KeyEvent.VK_A:
                    newDirection = Direction.LEFT;
                    break;
            }

        }
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


    private void move(){
        final GamePoints posiçaoDaCabeçaI = cobra.getFirst();
        direction = newDirection;

        final GamePoints novaCabeca = switch (direction){

            case UP -> new GamePoints(posiçaoDaCabeçaI.x,posiçaoDaCabeçaI.y - TamanhoDaCelula);
            case DOWN -> new GamePoints(posiçaoDaCabeçaI.x,posiçaoDaCabeçaI.y +TamanhoDaCelula);
            case LEFT -> new GamePoints(posiçaoDaCabeçaI.x - TamanhoDaCelula,posiçaoDaCabeçaI.y );
            case RIGHT -> new GamePoints(posiçaoDaCabeçaI.x + TamanhoDaCelula,posiçaoDaCabeçaI.y );
        };
        cobra.addFirst(novaCabeca);


        if (isCollision()){
            gameOver = true;
            cobra.removeFirst();
        }else {
            cobra.removeLast();
        }

        direction = newDirection;
    }

    private boolean isCollision(){
        final GamePoints cabeca = cobra.getFirst();
        final var invalidWidth = (cabeca.x <0)||(cabeca.x >= width );
        final var invalidHeight = (cabeca.y < 0)||(cabeca.y>=height);
        if (invalidWidth || invalidHeight){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        if (inicioDoJogo && !gameOver){
            move();
        }
        repaint();
    }


    private record GamePoints(int x, int y){}

    private enum Direction{
        UP, DOWN, RIGHT,LEFT
    }
}
