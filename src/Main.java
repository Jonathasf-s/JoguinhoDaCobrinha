import javax.swing.*;



public class Main {

   private static final int WIDTH = 800;
   private static final int HEIGHT = 600;

    public static void main(String[] args) {


        JFrame frame = new JFrame("JogoDaCobrinha");

        frame.setSize(WIDTH, HEIGHT); // tamanho da janela

        JogoCobrinha jogo = new JogoCobrinha(WIDTH, HEIGHT);
        frame.add(jogo);

        frame.setLocationRelativeTo(null); // aparece no centro da tela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // para o que quando a janla feche o programa tbm para de rodar
        frame.setResizable(false); // nao pode mudar o tamanho
        frame.setVisible(true); // deixa vc ver a tela
        frame.pack(); // vai juntar os frames no painel

        jogo.inciarJogo();





    }
}
