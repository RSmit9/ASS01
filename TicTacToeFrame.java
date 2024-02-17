    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
public class TicTacToeFrame extends JFrame implements ActionListener
{
    private JButton[][] buttons;
    private String currentPlayer;
    private int moveCount;
    public TicTacToeFrame()
    {
            setTitle("Tic Tac Toe");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(700, 700);
            setLocationRelativeTo(null);
            JPanel panel = new JPanel(new GridLayout(3, 3));
            buttons = new JButton[3][3];
            currentPlayer = "X";
            moveCount = 0;
            for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
                {
                    buttons[row][col] = new JButton();
                    buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                    buttons[row][col].addActionListener(this);
                    panel.add(buttons[row][col]);
                }
        }
        add(panel);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton clickedButton = (JButton) e.getSource();
        if (!clickedButton.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Wrong move Buster! Choose an empty square.");
                return;
            }
        clickedButton.setText(currentPlayer);
        moveCount++;

        if (checkWin() || moveCount == 9)
        {
            String message;
            if (checkWin())
            {
                message = "Winner winner Chicken Dinner! Player " + currentPlayer + " wins!";
            } else
                {
                    message = "Womp Womp It's a tie :(";
                }
            int option = JOptionPane.showConfirmDialog(this, message + " Wanna play again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION)
                    {
                        resetGame();
                    } else
                {
                    System.exit(0);
                }
        } else
            {
                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
            }
    }
    private boolean checkWin()
    {
        for (int i = 0; i < 3; i++)
            {
                if (!buttons[i][0].getText().isEmpty() &&
                        buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                        buttons[i][0].getText().equals(buttons[i][2].getText()))
                    {
                        return true;
                    }
            }
        for (int i = 0; i < 3; i++)
            {
                if (!buttons[0][i].getText().isEmpty() &&
                        buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                            buttons[0][i].getText().equals(buttons[2][i].getText()))
                    {
                        return true;
                    }
            }
        if (!buttons[0][0].getText().isEmpty() &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()))
            {
                return true;
            }
        if (!buttons[0][2].getText().isEmpty() &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                    buttons[0][2].getText().equals(buttons[2][0].getText()))
            {
                return true;
            }

        return false;
    }
    private void resetGame()
    {
        for (int row = 0; row < 3; row++)
            {
                for (int col = 0; col < 3; col++)
                    {
                        buttons[row][col].setText("");
                    }
            }
        currentPlayer = "X";
        moveCount = 0;
    }
    public static void main(String[] args)
        {
            SwingUtilities.invokeLater(TicTacToeFrame::new);
        }
}
