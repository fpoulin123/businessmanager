package ca.xpertproject.apps.businessmanager.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class PaymentController {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@GetMapping("/allPayments")
	public String getAllPayments(Model model) {
		
		List<Payment> payments = paymentRepository.findAll();
		
		model.addAttribute("payments", payments);
		
		return "payments";
	}
	
	@GetMapping("/addPayment")
	public String getAddPayment() {
		
		return "createPaymentform";
	}
	
	@PostMapping("/addPayment")
	public String addPayment(@RequestParam Map<String, String> body, HttpServletResponse response) throws ParseException {
		
		body.forEach((k,v)-> System.out.println(k + ":" + v));
		
		boolean taekwondo = "on".equals(body.get("taekwondo"))?true:false;
		boolean kickboxing = "on".equals(body.get("kickboxing"))?true:false;
		boolean taekibodo = "on".equals(body.get("taekibodo"))?true:false;
		
		String paymentDateValue = body.get("paymentDate");
		Date paymentDate = null;
		
		if(paymentDateValue!=null &&  !(paymentDateValue.isEmpty())) {
			paymentDate = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("paymentDate"));
		}
		
		Payment payment = Payment.builder()
				.customerId(Long.parseLong(body.get("customerid")))
				.product(Integer.parseInt(body.get("product")))
				.taekwondo(taekwondo)
				.kickboxing(kickboxing)
				.taekibodo(taekibodo)
				.amount(Double.parseDouble(body.get("amount")))
				.payedAmount(Double.parseDouble(body.get("payedAmount")))
				.paymentDate(paymentDate)
				.build();
		
		payment = paymentRepository.save(payment);
		
		return "redirect:./payment?id=" + payment.id;
	}
	
	@GetMapping("/modifyPayment")
	public String modifyPayment(@RequestParam(required=true) Long id, Model model) {
		
		
		return "modifyPayment";
	}

}
