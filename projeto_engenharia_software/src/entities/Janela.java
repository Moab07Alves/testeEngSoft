package entities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

//https://www.guj.com.br/t/quero-aprender-sobre-como-trabalhar-com-imagens-em-java/364450/24?page=2
public class Janela extends JFrame {

    InputStream stream;
    Image image;
    ImageIcon imageIcon;
    JLabel imageLabel;

    public Janela(String caminhoFoto) throws IOException {
        this.stream = getClass().getResourceAsStream(caminhoFoto);
        this.image = ImageIO.read(stream);
        this.imageIcon = new ImageIcon(image);
        this.imageLabel = new JLabel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        this.getContentPane().add(this.imageLabel);

        this.imageLabel.setLayout(null);
        this.imageLabel.setIcon(this.imageIcon);

        moverLabel(100, 100);
    }

    public void moverLabel(int x, int y) {
        this.imageLabel.setLocation(x, y);
    }

}
