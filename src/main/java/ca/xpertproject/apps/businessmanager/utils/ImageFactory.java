package ca.xpertproject.apps.businessmanager.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.xpertproject.apps.businessmanager.constant.ApplicationConstants;
import ca.xpertproject.apps.businessmanager.model.Customer;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class ImageFactory {
	
	public static BufferedImage generateBarcodeImage(String barcodeText) throws Exception {
	    Barcode barcode = BarcodeFactory.createCode128(barcodeText);
	    barcode.setFont(null);
	    
	    barcode.setBarWidth(3);
	    barcode.setBarHeight(80);
	    return BarcodeImageHandler.getImage(barcode);
	}

	public void saveMemberCardImg(BufferedImage bufferedImage, String filePartName) throws IOException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        byte[] imageBytes = baos.toByteArray();
        FileOutputStream fos = new FileOutputStream(ApplicationConstants.PICTURES_DIR_PATH +  "/" + filePartName + ".jpg");
        fos.write(imageBytes);
        fos.close();
       
	}
	
	public BufferedImage agregateImage(BufferedImage barcode, Customer customer) throws IOException {
		int cardWidth = 189 * 2;
		int cardHeight = 303 *2 ;
		BufferedImage resultImage = new BufferedImage(cardWidth, cardHeight, BufferedImage.TYPE_INT_BGR);
		
		BufferedImage logoImg = ImageIO.read(new File(ApplicationConstants.PICTURES_DIR_PATH + "/logo.jpg"));
				
		Graphics gr = resultImage.createGraphics();
		gr.setColor(Color.WHITE);
		//gr.drawRect(0, 0, 500, 800);
		gr.fillRect(0, 0, cardWidth, cardHeight);
		gr.drawImage(logoImg,10, 0, null);

		String customerName = customer.getFirstName() + " " + customer.getLastName();
		
		Font font = new Font("Arial", Font.BOLD, 30);

		gr.setFont(font);
		gr.setColor(Color.BLACK);
		gr.drawString(customer.getFirstName(), 15, 410);
		gr.drawString(customer.getLastName(), 15, 440);
		//gr.drawRect(0, 0, cardWidth-2, cardHeight-2);
		gr.drawImage(barcode, -10, 460, null);
		gr.drawString(customer.getBarcodeValue(), 90, 580);
		return resultImage;
	}
	
}
