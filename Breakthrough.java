import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Breakthrough extends JFrame implements ActionListener {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JButton [][] buttons = new JButton[8][8]; 
   int counter, p1, p2, playerTurn;
   String buttonPressed, buttonValue;
   JPanel north, center, south;
   JLabel player, p1Score, p2Score;
   JMenuBar menuBar;
   JMenu file, help;
   JMenuItem newGame, rules;
   
   public Breakthrough() { 
      counter = 0;
      p1 = 0;
      p2 = 0; 
		playerTurn = 1; 
		
		//JOptionPane.showMessageDialog(null, "Welcome! \nPlayer 1's units are unicorns. \nPlayer 2's units are dragons.");
		
      northPanel();
      centerPanel();
      southPanel();
      createMenu();
      
		setTitle("Best Game Ever");
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setResizable(false); 
      setLocationRelativeTo(null);
   
   }
   
   public void northPanel() {
      north = new JPanel();
      player = new JLabel("Player 1's Turn");
      north.add(player);
      add(north, BorderLayout.NORTH);
   }
   
   public void centerPanel()  {
      center = new JPanel(new GridLayout(8, 8));
      //setLayout(new GridLayout(8, 8));
      
      for(int col = 0; col < 8; col++)
      {
         for(int row = 0; row < 8; row++)
         {
            
            buttons[row][col] = new Button(row, col);
            center.add(buttons[row][col]);      

         }
      }
      
      add(center, BorderLayout.CENTER);
   }
   
   public void southPanel() {
      south = new JPanel();
		
      p1Score = new JLabel("Player 1: " + p1);
      p2Score = new JLabel("Player 2: " + p2);
      
      south.add(p1Score);
      south.add(p2Score);
      
      add(south, BorderLayout.SOUTH);
      
   }
   
   public void createMenu() {
      menuBar = new JMenuBar();
		
      file = new JMenu("File");
		help = new JMenu("Help");
		
      newGame = new JMenuItem("New Game");
		rules = new JMenuItem("Rules");
      
      file.add(newGame);
		help.add(rules);
		
      menuBar.add(file);
		menuBar.add(help);
      
      file.setMnemonic('F');
      newGame.setMnemonic('N');
      
      setJMenuBar(menuBar);
		
		newGame.addActionListener(this);
		rules.addActionListener(this);
   }
   
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == rules) {
			JOptionPane.showMessageDialog(null, "Rules: \n1. You are able to move diagonally forward by one unit space \n2. You are able to move horizontally forward by one unit space \n3. You may only capture an enemy unit if it is located diagonally forward \n4. You cannot move vertically or backwards \n5. Player with most captured units wins");
		}
		else if (ae.getSource() == newGame) {
		}
	}
	
   public static void main(String [] args) {
      new Breakthrough();
   }

   class Button extends JButton implements ActionListener {
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x, y;
   
      public Button(int row, int col) {
         x = row;
         y = col;
         assignButtonValue();
         setCoordinates();
         this.addActionListener(this);
      }
      
      public void assignButtonValue() {
         if (x == 0 || x == 1) {
            setText("O");        
         }
         else if (x == 7 || x == 6) {
            setText("X");
         }
      }
      
      public void setCoordinates()  {
         String message = "(" + x + ", " + y + ")";
         setToolTipText(message);
      }
      
      public String getCoordinates() {
         String coordinates = "(" + x + "," + y + ")";
         return coordinates;
      }
      
      public void makeMove(int x, int y) {
         //x and y  are old coordinates
         //this.x and this.y are the new coordinates
         if (isValidMove(x, y)) {								
					buttons[x][y].setText(""); 
					buttons[this.x][this.y].setText(buttonValue);				
         }		    
      }
      
      public boolean isValidMove(int x, int y) {
			if (buttonValue.equals("O")) {			
				if(this.x == (x + 1)) {
					if ((this.y == (y - 1)) || (this.y == (y + 1)) ) {
	            	if(buttons[this.x][this.y].getText().equals("")) {	
							playerTurn = 2;
							player.setText("Player 2's Turn");				
			            return true;	            
						}
						else if (buttons[this.x][this.y].getText().equals("X")) {
							p1++;
							p1Score.setText("Player 1: " + Integer.toString(p1));
							playerTurn = 2;
							player.setText("Player 2's Turn");
							return true;
						}
				      else {
				         JOptionPane.showMessageDialog(null, "That unit space is occupied");
							return false;
				      }
	            }
					else if (this.y == y) {
						if(buttons[this.x][this.y].getText().equals("")) {		
							playerTurn = 2;
							player.setText("Player 2's Turn");			
			            return true;	            
						}
				      else {
				         JOptionPane.showMessageDialog(null, "You are blocked. Cannot move forward");
							return false;
				      }
					}
					else {
	            	JOptionPane.showMessageDialog(null, "Not a valid move");
						return false;
	            }
				}
	         else {
	               JOptionPane.showMessageDialog(null, "Not a valid move");
						return false;
	         } 
			}  
			else if (buttonValue.equals("X")) {
				if(this.x == (x - 1)) {
					if ((this.y == (y - 1)) || (this.y == (y + 1)) ) {
	            	if(buttons[this.x][this.y].getText().equals("")) {	
							playerTurn = 1;	
							player.setText("Player 1's Turn");			
			            return true;	            
						}
						else if (buttons[this.x][this.y].getText().equals("O")) {
							p2++;
							p2Score.setText("Player 2: " + Integer.toString(p2));
							playerTurn = 1;
							player.setText("Player 1's Turn");
							return true;
						}
				      else {
				         JOptionPane.showMessageDialog(null, "That unit space is occupied");
							return false;
				      }
	            }
					else if (this.y == y) {
						if(buttons[this.x][this.y].getText().equals("")) {	
							playerTurn = 1;
							player.setText("Player 1's Turn");				
			            return true;	            
						}
				      else {
				         JOptionPane.showMessageDialog(null, "You are blocked. Cannot move forward");
							return false;
				      }
					}
					else {
	            	JOptionPane.showMessageDialog(null, "Not a valid move");
						return false;
	            }
				}
	         else {
	               JOptionPane.showMessageDialog(null, "Not a valid move");
						return false;
	         } 
			}  
			else {
				return false;
			}    
      }		
      		      
      public void actionPerformed(ActionEvent ae)  {
         if (ae.getActionCommand().equals("X") && counter == 0) {
				if (playerTurn == 2) {				
	            buttonValue = "X";
	            buttonPressed = getCoordinates();
	            counter++;
				}
				else {
					JOptionPane.showMessageDialog(null, "It is not your turn");
				}
         }
			else if (ae.getActionCommand().equalsIgnoreCase("O") && counter == 0) {
				if (playerTurn == 1) {
	            buttonValue = "O";   
	            buttonPressed = getCoordinates();
	            counter++;  
				} 
				else {
					JOptionPane.showMessageDialog(null, "It is not your turn");
				}
         }       
         else if (counter == 1) {
            int x = Integer.parseInt(buttonPressed.substring(1,2));
            int y = Integer.parseInt(buttonPressed.substring(3,4));
            makeMove(x,y);
            counter = 0;         
         }     
         else if(ae.getActionCommand().equals("") && counter == 0) {
            JOptionPane.showMessageDialog(null, "Cannot move an empty unit");
         }           
      }
   }
}