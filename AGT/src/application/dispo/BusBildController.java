package application.dispo;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class BusBildController {

	@FXML ImageView bild;
	
    @FXML
    void initialize() {
    	if(Busse.getB().getBild()==null){
    		
    	}else{
    		bild.setImage(Busse.getB().getBild().getImage());
    	}
    	

    }

}
