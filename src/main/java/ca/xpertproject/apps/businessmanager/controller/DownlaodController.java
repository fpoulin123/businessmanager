package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.xpertproject.apps.businessmanager.exception.AuthenticationException;
import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
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
	
	@Autowired
	MemberRepository memberRepository;
	
	MemberUtils memberUtils = new MemberUtils(); 
	
	@GetMapping("/downloadCustomers")
    public void downloadCustomers(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,HttpSession session,HttpServletResponse response) throws Exception {
        try {
        	
        	if(!memberUtils.checkCookieMember(loggedMember, memberRepository)) {
        		throw new AuthenticationException("Accès non autorisé.");
        	}

            String fileName="clients.csv";
            
            File fileToDownload = new File("./" + fileName);
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
    public void downloadSubscriptions(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, HttpSession session,HttpServletResponse response) throws Exception {
        try {

        	if(!memberUtils.checkCookieMember(loggedMember, memberRepository)) {
        		throw new AuthenticationException("Accès non autorisé.");
        	}
        	
            String fileName="abonnements.csv";
            
            File fileToDownload = new File("./" + fileName);
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
    public void downloadPayments(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, HttpSession session,HttpServletResponse response) throws Exception {
        try {
        	
        	if(!memberUtils.checkCookieMember(loggedMember, memberRepository)) {
        		throw new AuthenticationException("Accès non autorisé.");
        	}

            String fileName="paiements.csv";
            
            File fileToDownload = new File("./" + fileName);
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

