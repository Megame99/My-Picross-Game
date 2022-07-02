//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
///**
// * 
// */
//
///**
// * @author megan
// *
// */
//public class PicrossController implements ActionListener{
//	
//
//	private PicrossModel Model;
//	private PicrossView View;
//	
//	public PicrossController(PicrossModel theModel, PicrossView theView) {
//	
//		this.View = theView;
//		this.Model = theModel;
//		
//	}
//	
//	public PicrossController() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public void startGame() {
////		View.launchSplashScreen();
//		Model.setRandomGame();
//		View.createGUI();
//	}
//	public void createController() {
//		
//	}
//	
//
//	
//		ActionListener exit = new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		        System.exit(0);
//		    }
//		};
//		
//		ActionListener aboutDialog = new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		    	View.displayAboutDialog();
//		    }
//		};
//		
//		ActionListener newGame = new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		    	View.newGame();
//		    }
//		};
//		
//		ActionListener reset = new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		        View.resetBoard();
//		    }
//		};
//		
//		ActionListener mark = new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		        View.markMode(e);
//		    }
//		};
//		
//		ActionListener markCorrectSquare = new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		        View.markCorrectSquare(e);
//		    }
//		};
//		
//		ActionListener markIncorrectSquare = new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		        View.markIncorrectSquare(e);
//		    }
//		};
//		
//		ActionListener closeWindow = new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		        View.closeWindow();
//		    }
//		};
//		
//		ActionListener displaySolution = new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		        View.displaySolution();
//		    }
//		}; 
//		
//		ActionListener updateScore = new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		        Model.updateScore();
//		        
//		    }
//		}; 
//		ActionListener correctSquare = new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		    	View.correctSquare(e);
//		    }
//		};
//		
//		ActionListener incorrectSquare = new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		    	View.incorrectSquare(e);
//		    }
//		};
//		
//		ActionListener Scenario1 = new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		    	Model.setScenario1();
//		    }
//		};
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		
//		
//		
//	
//	
//}
