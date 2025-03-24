package ca.xpertproject.apps.businessmanager.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@RestController
public class DownlaodController {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
//	@GetMapping(value="/downloadCustomers",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//	public @ResponseBody Resource getFileViaByteArrayResource() throws IOException, URISyntaxException {
//		File fileToDwnld = new File("./customers.csv");
//		if(!fileToDwnld.exists())fileToDwnld.createNewFile();
//	    Path path = Paths.get(fileToDwnld.toURI());
//	    
//	    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//	    
//	    return resource;
//	}
	
	@GetMapping("/downloadCustomers")
    public void downloadCustomers(HttpSession session,HttpServletResponse response) throws Exception {
        try {

            String fileName="./customers.csv";
            
            File fileToDownload = new File(fileName);
            if(fileToDownload.exists())fileToDownload.delete();
            
            List<Customer> customerList = customerRepository.findAll();

            FileWriter fr = new FileWriter(fileToDownload);
            BufferedWriter bfr = new BufferedWriter(fr);
            
            bfr.append("\"Numéro\";\"Prénom\";\"Nom\";\"Adresse\";\"Ville\";\"Téléphone\";\"E-mail\"" + System.lineSeparator());
            customerList.stream().forEach(customer-> {
            	try {
					bfr.append(customer.toCsvString() + System.lineSeparator());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
            
            bfr.flush();
            
            bfr.close();
            
            InputStream inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName); 
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }
	
	@GetMapping("/downloadSubscriptions")
    public void downloadSubscriptions(HttpSession session,HttpServletResponse response) throws Exception {
        try {

            String fileName="./abonnements.csv";
            
            File fileToDownload = new File(fileName);
            if(fileToDownload.exists())fileToDownload.delete();
            
            List<Subscription> subscriptionList = subscriptionRepository.findAll();

            FileWriter fr = new FileWriter(fileToDownload);
            BufferedWriter bfr = new BufferedWriter(fr);
                        
            bfr.append("\"Numéro\";\"Prénom\";\"Nom\";\"Durée\";\"Sport 1\";\"Sport 2\";\"Sport 3\";\"Montant\";\"Date\"" + System.lineSeparator());
            subscriptionList.stream().forEach(subscription-> {
            	try {
					bfr.append(subscription.toCsvString() + System.lineSeparator());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
            
            bfr.flush();
            
            bfr.close();
            
            InputStream inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName); 
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        
	}   
        
    @GetMapping("/downloadPayments")
    public void downloadPayments(HttpSession session,HttpServletResponse response) throws Exception {
        try {

            String fileName="./paiments.csv";
            
            File fileToDownload = new File(fileName);
            if(fileToDownload.exists())fileToDownload.delete();
            
            List<Payment> paymentList = paymentRepository.findAll();

            FileWriter fr = new FileWriter(fileToDownload);
            BufferedWriter bfr = new BufferedWriter(fr);
            
            bfr.append("\"Numéro\";\"Num. Abonnement\";\"Prénom\";\"Nom\";\"Montant\";\"Date\"" + System.lineSeparator());
            
            paymentList.stream().forEach(payment-> {
            	try {
					bfr.append(payment.toCsvString() + System.lineSeparator());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
            
            bfr.flush();
            
            bfr.close();
            
            InputStream inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName); 
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
        } catch (Exception exception){
            System.out.println(exception.getMessage());			
        }
    }
}

