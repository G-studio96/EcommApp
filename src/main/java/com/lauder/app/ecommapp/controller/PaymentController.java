package com.lauder.app.ecommapp.controller;



import com.lauder.app.ecommapp.dto.request.payments.PaymentRequest;
import com.lauder.app.ecommapp.dto.response.payments.PaymentResponse;
import com.lauder.app.ecommapp.service.PaymentService;
import com.lauder.app.ecommapp.service.paymentsservice.PaymentProcessorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/payments")
public class PaymentController {

    final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    private final PaymentProcessorService paymentProcessorService;


    @Autowired
    public PaymentController(PaymentService paymentService, PaymentProcessorService paymentProcessorService) {
        this.paymentService = paymentService;
        this.paymentProcessorService = paymentProcessorService;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@Valid  @RequestBody PaymentRequest paymentRequest) {
        logger.info("Payment request received for order: {}", paymentRequest.getOrderId());
        PaymentResponse createdPayment = paymentService.createPayment(paymentRequest);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPayment.getPaymentID())
                .toUri();

        return ResponseEntity.created(location).body(createdPayment);
        }

        @GetMapping("/{id}")
        public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long id) {
            logger.info("Fetching payment with id: {}", id);

            try {
                PaymentResponse payment = paymentService.getPayment(id);
                return ResponseEntity.ok(payment);
            } catch (ResourceNotFoundException e) {
                logger.error("Payment not found with id: {} ", id);
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("/process")
        public ResponseEntity<PaymentResponse> processPayment(@RequestParam String paymentMethod, @Valid @RequestBody PaymentRequest paymentRequest) {
            logger.info("Processing payment via {} for order: {}", paymentMethod, paymentRequest.getOrderId());

            try {
                PaymentResponse response = paymentProcessorService.paymentResponse(paymentMethod, paymentRequest);
                return ResponseEntity.ok(response);
            } catch (UnsupportedOperationException e) {
                logger.info("Unsupported payment method: {}", paymentMethod);
                return ResponseEntity.badRequest().build();
            } catch (Exception e) {
                logger.error("Error processing payment", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }


      @GetMapping("/methods")
    public ResponseEntity<List<String>> getPaymentMethods() {
        logger.info("Fetching available payment methods");

        List<String> methods = paymentProcessorService.getAvailablePaymentsMethods();
        return ResponseEntity.ok(methods);
      }



}
