package com.example.frequencychar;

import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class CharCounterController {

    private final CharCounterService charCounterService;

    public CharCounterController(CharCounterService charCounterService) {
        this.charCounterService = charCounterService;
    }

    @PostMapping("/frequency_char")
    public ResponseEntity<?> frequency(@RequestParam("input") @Size(max = 10_000) String input) {
        return charCounterService.charCounter(input);
    }
}
