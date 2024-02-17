    package com.example.fxdelas.rest;

    import com.example.fxdelas.entity.Deal;
    import com.example.fxdelas.exceptions.DealNotFoundException;
    import com.example.fxdelas.exceptions.DealValidationException;
    import com.example.fxdelas.service.DealService;
    import org.springframework.http.HttpEntity;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api")
    public class DealController {

        private final DealService dealService;

        public DealController(DealService dealService) {
            this.dealService = dealService;
        }

        @PostMapping("/deals")
        public ResponseEntity<String> addDeal(@RequestBody Deal deal) {
            String message = "";
            try {
                message = dealService.addDeal(deal);
            } catch (Exception ex) {
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok(message);
        }
        @GetMapping("/deals/{dealId}")
        public HttpEntity<? extends Object> getDealById(@PathVariable Long dealId){
            Deal deal = dealService.findById(dealId);

            return ResponseEntity.ok(deal);
        }

    }
