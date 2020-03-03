package com.jdc.restaurant.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import com.jdc.image.crop.ImageCropFactory;
import com.jdc.image.crop.ImageCropper;
import com.jdc.image.crop.Shape;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.model.MenuModel;
import com.jdc.restaurant.utils.ImageUtils;
import com.jdc.restaurant.utils.Page;
import com.jdc.restaurant.utils.StringUtils;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ImageEditView {
	
	@FXML
	private Label menuName;
	private Group group;
	
	@FXML
	private VBox container;

	private Menu menu;
	
	private ImageCropper imageCropper;
	
	@FXML
	private void initialize() {
		group = new Group();
		container.getChildren().add(group);
	}

	public static void show(Menu menu) {

		try {
			
			FXMLLoader loader = new FXMLLoader(ImageEditView.class.getResource("ImageEditView.fxml"));
			Parent view = loader.load();
			
			ImageEditView controller = loader.getController();
			controller.init(menu);
			
			MainFrame.getPageLoader().loadView(view);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init(Menu menu) {
		this.menu = menu;
		menuName.setText(menu.getName());
		
		if(!StringUtils.isEmpty(menu.getImage())) {
			Image image  = new Image(ImageUtils.getImageUrl(menu.getImage()), true);
			loadCropper(image);
		}
	}
	
	@FXML
	private void loadImage() {
		try {
			
			this.imageCropper = null;
			
			FileChooser fc = new FileChooser();
			fc.setTitle("Image Upload");
			fc.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
			
			File file = fc.showOpenDialog(menuName.getScene().getWindow());
			
			if(null != file) {
				Image image = new Image(new FileInputStream(file));
				loadCropper(image);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void save() {
		try {
			
			Image image = imageCropper.getImage();
			
			File imageFolder = new File("temp");
			imageFolder.mkdir();
			File imageFile = new File(imageFolder, String.format("%s.png", menu.getCode()));
			
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", imageFile);
			
			MenuModel.getModel().uploadPhoto(menu, imageFile);
			
			MainFrame.getPageLoader().loadView(Page.Menus);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@FXML
	private void crop() {
		Image image = imageCropper.getImage();
		loadCropper(image);
	}
	
	private void loadCropper(Image image) {
		ImageView imageView = new ImageView(image);				
		imageView.setPreserveRatio(true);

		group.getChildren().clear();
		group.getChildren().add(imageView);
		
		container.applyCss();
		
		imageView.setFitHeight(container.prefHeight(-1));
		imageView.setFitWidth(container.prefHeight(-1));
		
		this.imageCropper = ImageCropFactory.getCropper(Shape.Square, imageView, group);
	}

}
