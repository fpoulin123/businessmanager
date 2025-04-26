package ca.xpertproject.apps.businessmanager.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.constant.ApplicationConstants;
import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.utils.ImageFactory;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

@Controller
public class CardController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("membercard")
	public String getMemberCard(@RequestParam(required = true) Long id, Model model) throws Exception {
		
		Customer customer = customerRepository.findById(id);
		
		String barcodeValue = customer.barcodeValue;
		
		ImageFactory imageFactory = new ImageFactory();
		
		BufferedImage barcode = ImageFactory.generateBarcodeImage(barcodeValue);
		
		imageFactory.saveMemberCardImg(imageFactory.agregateImage(barcode, customer), barcodeValue);
		
		model.addAttribute("cardPath", "img/customerPictures/" + barcodeValue + ".jpg");
		
		return "membercard";
	}

	
}
