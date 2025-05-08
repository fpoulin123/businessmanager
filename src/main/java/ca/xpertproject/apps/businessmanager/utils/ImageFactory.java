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
		String title = customer.getTitle();
		String customerNameL1 = (title!=null&&!title.isEmpty())?title + " " + customer.getFirstName():customer.getFirstName();
		
		Font font = new Font("Arial", Font.BOLD, 25);

		gr.setFont(font);
		gr.setColor(Color.BLACK);
		gr.drawString(customerNameL1, 15, 395);
		gr.drawString(customer.getLastName(), 15, 425);
		gr.drawString(customer.getLevel(),15,460);
		gr.drawImage(barcode, -10, 480, null);
		gr.drawString(customer.getBarcodeValue(), 90, 590);
		return resultImage;
	}
	
}
