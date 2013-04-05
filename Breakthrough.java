/**
*	@author	Aaron Levy, Jenny Liu
*	@date		4/5/2013
*	Breakthrough Mini Project
*	
*	Use GUI components to create a board game called Breakthrough. Made for two players
**/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

public class Breakthrough extends JFrame implements ActionListener {
	
	//Class Attributes
	JButton [][] buttons = new JButton[8][8]; 
   int counter, p1, p2, playerTurn;
   String buttonPressed, buttonValue;
   JPanel north, center, south;
   JLabel player, p1Score, p2Score;
   JMenuBar menuBar;
   JMenu file, help;
   JMenuItem newGame, rules;
   
	/**
	*	Contructor for Breakthrough. 
	*	Initialize some class attributes and create the GUI
	**/
   public Breakthrough() { 
		//Initialize some attributes
      counter = 0;
      p1 = 0;
      p2 = 0; 
		playerTurn = 1; 
		
		//Create GUI
      northPanel();
      centerPanel();
      southPanel();
      createMenu();
      
		//JFrame settings
		setTitle("Best Game Ever");
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setResizable(false); 
      setLocationRelativeTo(null);
   
   }
   	
	/**
	*	Create a northern panel which displays whose turn it is
	**/
   public void northPanel() {
      north = new JPanel();
      player = new JLabel("Pikachu's Turn");
      north.add(player);
      add(north, BorderLayout.NORTH);
		player.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
   }
   
	/**
	*	Create a center panel with an 8x8 board.
	**/
   public void centerPanel() {
      center = new JPanel(new GridLayout(8, 8));
      
		//Create the 2D array by using two for loops
      for(int row = 0; row < 8; row++) {
         for(int col = 0; col < 8; col++) { 
			
				//create an object of the inner Button class and add to 2D array list
				buttons[row][col] = new Button(row, col);
           	center.add(buttons[row][col]);   
				buttons[row][col].setPreferredSize(new Dimension(75,80)); 
				setColor(row, col);
         }
      }
      add(center, BorderLayout.CENTER);
   }
   
	/**
	*	Set board colors to make a pixel-type art
	**/
	public void setColor(int row, int col) {
		if ((row == 3 || row == 4) && (col == 0 || col == 7)) {
			buttons[row][col].setBackground(Color.BLACK);
		}
		else if ((row == 2 || row == 5) && (col == 1 || col == 6)) {
			buttons[row][col].setBackground(Color.BLACK);
		}
		else if ((row == 1 || row == 6) && (col == 2 || col == 5)) {
			buttons[row][col].setBackground(Color.BLACK);
		}
		else if ((col == 3 || col == 4) && (row == 0 || row == 7)) {
			buttons[row][col].setBackground(Color.BLACK);
		}
		else if ((row == 4 || row == 5 || row == 6) && (col == 1 || col == 2 || col == 3 || col == 4 || col == 5 || col == 6) || (row == 3 && (col == 3 || col == 4))) {
			buttons[row][col].setBackground(Color.WHITE);
		}
		else if ((row == 1 && (col == 3 || col == 4)) || (row == 2 && (col == 2 || col == 3 || col == 4 || col == 5)) || (row == 3 && (col == 1 || col == 2 || col == 5 || col == 6))) {
			buttons[row][col].setBackground(Color.RED);
		}
	}	
	
	/**
	*	Create a southern panel which the number of captures by each player
	**/
   public void southPanel() {
      south = new JPanel();
		south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
		
		//Instantiate JLabels
      p1Score = new JLabel("Pikachu's captures: " + p1);
      p2Score = new JLabel("Ash Ketchum's Captures: " + p2);
		
		p1Score.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		p2Score.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
      
		//Center the components
		p1Score.setAlignmentX(Component.CENTER_ALIGNMENT);
		p2Score.setAlignmentX(Component.CENTER_ALIGNMENT);
		
      south.add(p1Score);
      south.add(p2Score);
      
      add(south, BorderLayout.SOUTH);      
   }
   
	/**
	*	Create menu bar, menu, and menu items
	**/
   public void createMenu() {
      menuBar = new JMenuBar();
		
      file = new JMenu("File");
		help = new JMenu("Help");
		
      newGame = new JMenuItem("New Game");
		rules = new JMenuItem("Rules");
      
		//add menu items to menus
      file.add(newGame);
		help.add(rules);
		
		//add menus to menu bar
      menuBar.add(file);
		menuBar.add(help);
      
		//Mnemonics
      file.setMnemonic('F');
      newGame.setMnemonic('N');
		help.setMnemonic('H');
		rules.setMnemonic('R');
      
      setJMenuBar(menuBar);
		
		//Action listeners for menu items
		newGame.addActionListener(this);
		rules.addActionListener(this);
   }
   
	/**
	*	Action listener method
	**/
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == rules) {
			JOptionPane.showMessageDialog(null, "Rules: \n1. You are able to move diagonally forward by one unit space \n2. You are able to move horizontally forward by one unit space \n3. You may only capture an enemy unit if it is located diagonally forward \n4. You cannot move vertically or backwards \n5. Player who reaches other side first wins");
		}
		else if (ae.getSource() == newGame) {
			this.setVisible(false);
			new Breakthrough();			//create a new game
		}
	}
	
   public static void main(String [] args) {
      new Breakthrough();
   }
	
	/**
	*	Button class is a button which allows the user to make game movements
	**/
   class Button extends JButton implements ActionListener {

		//class attributes
		int row, col;
		ImageIcon pikachu, ash;
   
		/**
		*	Constructor for Button
		* 	@param	int row is the button's row place value
		*	@param	int col is the button's column place value
		**/
      public Button(int row, int col) {
			//Innitialize the image icons which are the player pieces
			pikachu = new ImageIcon("pikachu.png");
			ash = new ImageIcon("ash.png");

			//initalize class variables
         this.row = row;
         this.col = col;
			
         assignButtonValue();
         setCoordinates();
         this.addActionListener(this);
      }
      
		/**
		*	Assign the image icons to their initial places on each side
		**/
      public void assignButtonValue() {
         if (col == 0 || col == 1) {				
        		this.setIcon(pikachu);
         }
         else if (col == 6 || col == 7) {
            this.setIcon(ash);
         }
      }
      
		/**
		*	This method makes a message appear with the button's coordinate place when hovered over
		**/
      public void setCoordinates()  {
         String message = "(" + row + ", " + col + ")";
         setToolTipText(message);
      }
      
		/**
		*	Get the coordinates of the pressed button
		*	@return String		the coordinates
		**/
      public String getCoordinates() {
         String coordinates = "(" + row + "," + col + ")";
         return coordinates;
      }
      
		/**
		*	Moves the player piece
		**/
      public void makeMove(int row, int col) {
         //row and col  are old coordinates
         //this.row and this.col are the new coordinates
			
			//Only move the piece if isValidMove() returns true
			//Replaces the previously empty space with the current player's piece
         if (isValidMove(row, col)) {								
					buttons[row][col].setIcon(null); 
					if (buttonValue.equals("ash")) {
						buttons[this.row][this.col].setIcon(ash);
					}			
					else if (buttonValue.equals("pikachu")) {
						buttons[this.row][this.col].setIcon(pikachu);
					}	
         }		    
      }
      
		/**
		*	Checks if the player's attempted move is a valid move
		*	@return Boolean	true if valid, false otherwise. Show message if false
		**/
      public boolean isValidMove(int row, int col) {
		
			//First check if button pressed is player 1
			if (buttonValue.equals("pikachu")) {
																
				//Checks if column value increases by one. If it doesn't then return false
				if(this.col == (col + 1)) {
																		
					//This conditional statement is if the player attempts to move diagonally
					//Row value may only increase or decrease by one in order to proceed to further checks.
					if ((this.row == (row - 1)) || (this.row == (row + 1)) ) {
	            	if(buttons[this.row][this.col].getIcon() == null) {
						
							//Return true and alternate player turn if diagonal space is empty.
							playerTurn = 2;
							player.setText("Ash Ketchum's Turn");				
			            return true;	            
						}
						
						//If diagonal space has an enemy unit then add one to capture points
						//Return true and alternate player turn after capture
						else if (buttons[this.row][this.col].getIcon() == ash) {
							p1++;
							p1Score.setText("Pikachu's captures: " + Integer.toString(p1));
							playerTurn = 2;
							player.setText("Ash Ketchum's Turn");
							return true;
						}					
							
						//Return false if the diagonal space has the current player's piece
				      else {
				         JOptionPane.showMessageDialog(null, "That unit space is occupied");
							return false;
				      }
	            }
					
					//This conditional statement checks if the player is attemping to move forward
					//Row must stay the same in order to proceed to next check. Else return false
					else if (this.row == row) {
					
						//Check if the space selected is empty
						if(buttons[this.row][this.col].getIcon() == null) {		
							playerTurn = 2;
							player.setText("Ash Ketchum's Turn");			
			            return true;	            
						}
						
						//If not empty then return false
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
			//First check if button pressed is player 1
			else if (buttonValue.equals("ash")) {
			
				//Checks if column value decreases by one. If it doesn't then return false
				if(this.col == (col - 1)) {
				
					//This conditional statement is if the player attempts to move diagonally
					//Row value may only increase or decrease by one in order to proceed to further checks.
					if ((this.row == (row - 1)) || (this.row == (row + 1)) ) {
	            	if(buttons[this.row][this.col].getIcon() == null) {
						
							//Return true and alternate player turn if diagonal space is empty.	
							playerTurn = 1;	
							player.setText("Pikachu's Turn");			
			            return true;	            
						}
						
						//If diagonal space has an enemy unit then add one to capture points
						//Return true and alternate player turn after capture
						else if (buttons[this.row][this.col].getIcon() == pikachu) {
							p2++;
							p2Score.setText("Ash Ketchum's captures: " + Integer.toString(p2));
							playerTurn = 1;
							player.setText("Pikachu's Turn");
							return true;
						}
						
						//Return false if the diagonal space has the current player's piece
				      else {
				         JOptionPane.showMessageDialog(null, "That unit space is occupied");
							return false;
				      }
	            }
					
					//This conditional statement checks if the player is attemping to move forward
					//Row must stay the same in order to proceed to next check. Else return false
					else if (this.row == row) {
					
						//Check if the space selected is empty
						if(buttons[this.row][this.col].getIcon() == null) {	
							playerTurn = 1;
							player.setText("Pikachu's Turn");				
			            return true;	            
						}
						
						//If not empty then return false
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
      } //End of isValidMove()
				
		/**
		*	Checks if a player has won.
		*	If a winner is found, the entire board is disabled
		**/
		public void isWinner() {
			//Checks if player 1 has reached the end of the board on the other side
			if (buttonValue.equals("pikachu") && this.col == 7) {
				JOptionPane.showMessageDialog(null, "Pikachu wins");
				
				//Iterate through entire 2D array and disable each button
				for(int col = 0; col < 8; col++)
		     	{
		         for(int row = 0; row < 8; row++)
		    	   {			            
				      buttons[row][col].setEnabled(false);   			
			      }
		    	}				
			}
			//Checks if player 2 has reached the end of the board on the other side
			else if (buttonValue.equals("ash") && this.col == 0) {
				JOptionPane.showMessageDialog(null, "Ash Ketchum wins");
				
				//Iterate through entire 2D array and disable each button
				for(int col = 0; col < 8; col++)
		     	{
		         for(int row = 0; row < 8; row++)
		    	   {			            
				      buttons[row][col].setEnabled(false);   			
			      }
		    	}
			}
		}
		
		/**
		*	Do these action(s) when a button is pressed	
		**/
      public void actionPerformed(ActionEvent ae)  {
		
			//Check if button pressed is player 2 and if the counter is 0
			//Counter is always zero on the first click on a player's turn
			//A player must click two times
		   if (getIcon() == ash && counter == 0) {
				//Only allow player 2 to click if the player turn is 2
				if (playerTurn == 2) {				
	            buttonValue = "ash";
	            buttonPressed = getCoordinates();	//get the clicked button's coordinates
	            counter++;									//increase the counter for the second click on same turn
				}
				else {
					JOptionPane.showMessageDialog(null, "It is not your turn");
				}
         }
			
			//Check if button pressed is player 1 and if the counter is 0
			else if (getIcon() == pikachu && counter == 0) {
				//Only allow player 1 to click if the player turn is 1
				if (playerTurn == 1) {
	            buttonValue = "pikachu";   
	            buttonPressed = getCoordinates();	//get the clicked button's coordinates
	            counter++;  								//increase the counter for the second click on same turn
				} 
				else {
					JOptionPane.showMessageDialog(null, "It is not your turn");
				}
         }    
			   
			//If counter was incremented, player is on second button selection
         else if (counter == 1) {
			
				//get second selection's coordinates	
            int row = Integer.parseInt(buttonPressed.substring(1,2));	
            int col = Integer.parseInt(buttonPressed.substring(3,4));
				
				//Use these coordinates to call makeMove() which will check if the attempted move is a valid move
            makeMove(row,col);
				isWinner();			//check for winner
            counter = 0;      //reset counter
         }    
			 
			//if an empty space is selected on the player's first click then show message that it is not possible
         else if(getIcon() == null && counter == 0) {
            JOptionPane.showMessageDialog(null, "Cannot move an empty unit");
         }           
      }
   }
}