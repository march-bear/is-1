package org.itmo.secs;

import org.itmo.secs.data.dto.DataDTO;
import org.itmo.secs.data.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class AppController {
    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDto) {
        return ResponseEntity.ok().body("Hello, " + userDto.username());
    }

    @GetMapping("/api/data")
    public ResponseEntity<String> getData() {
        return ResponseEntity.ok().body("Nothing yet");
    }

    @PutMapping("/api/data")
    public ResponseEntity<String> addElemToColl(@RequestBody DataDTO dataDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Nothing yet");
    }
}
