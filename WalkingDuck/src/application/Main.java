package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	
	private Image still;
	private Image walk1;
	private Image walk2;
	
	private Rectangle duck;
	
	private boolean isRunning = false;
	
	private boolean[] flags = new boolean[9];
	
	public Thread thread;
	
	private boolean buttonPressed = false;
	
	private BorderPane spielfeld;
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			still = new Image(getClass().getResource("Still.png").toExternalForm());
//			walk1 = new Image(getClass().getResource("Walk1.png").toExternalForm());
//			walk2 = new Image(getClass().getResource("Walk2.png").toExternalForm());
//			
			spielfeld = new BorderPane();
			Scene scene = new Scene(spielfeld,400,400);
			duck.setX(100);
			duck.setY(100);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void animateDuck(boolean isMoving) {
		if (isMoving) {
			
		}
	}
	
	
	
	public void buttonNotPressed(KeyEvent event){
        if(event.getCode()==KeyCode.W)flags[0]=false;
        if(event.getCode()==KeyCode.D)flags[1]=false;
        if(event.getCode()==KeyCode.S)flags[2]=false;
        if(event.getCode()==KeyCode.A)flags[3]=false;
    }
	
	public void buttonPressed(KeyEvent event){
		 
        if(event.getCode()==KeyCode.W)flags[0]=true;
        if(event.getCode()==KeyCode.D)flags[1]=true;
        if(event.getCode()==KeyCode.S)flags[2]=true;
        if(event.getCode()==KeyCode.A)flags[3]=true;
       
        if(event.getCode()==KeyCode.ESCAPE){
            Stage stage = (Stage)duck.getScene().getWindow();
       
            if(buttonPressed){
                stage.setFullScreen(true);
                buttonPressed=false;
            }else if(!buttonPressed){
                stage.setFullScreen(false);
                buttonPressed =  true;
            }
        }
        
        if(isRunning)return;
           
            thread = new Thread(new Runnable() {
 
 
                @Override
                public void run() {
                    isRunning=true;
                    steuerung();
                    isRunning=false;
                }
            });
            thread.start();
       
    }
	
	public void steuerung(){
		 
		 
	       while(flags[0]||flags[1]||flags[2]||flags[3]){
	        if(flags[0]){
	            duck.setLayoutY(duck.getLayoutY()-1);
	            if(duck.getLayoutY()<spielfeld.getLayoutY()){
	                duck.setLayoutY(0);
	 
	 
	            }  
	        }if(flags[1]){          
	            duck.setLayoutX(duck.getLayoutX()+1);            
	            if(duck.getLayoutX()+duck.getWidth()>spielfeld.getPrefWidth()){
	                duck.setLayoutX(spielfeld.getPrefWidth()-duck.getWidth());
	            }
	 
	        }if(flags[2]){
	            duck.setLayoutY(duck.getLayoutY()+1);
	            if(duck.getLayoutY()+duck.getHeight()>spielfeld.getPrefHeight()){
	                duck.setLayoutY(spielfeld.getPrefHeight()-duck.getHeight());
	            }
	 
	 
	        }if(flags[3]){
	            duck.setLayoutX(duck.getLayoutX()-1);
	            if(duck.getLayoutX()<spielfeld.getLayoutX()){
	                duck.setLayoutX(0);
	            }
	        }      
	        try {
	                Thread.sleep(2);
	            } catch (InterruptedException ex) {
	 
	            }
	 
	        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
