package org.itmo.secs;

import java.util.ArrayList;
import java.util.List;

import org.itmo.secs.model.dto.*;
import org.itmo.secs.model.entity.Data;
import org.itmo.secs.repository.DataRepository;
import org.itmo.secs.service.AuthenticationService;
import org.itmo.secs.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@AllArgsConstructor
public class AppController {
    private UserService userService;
    private AuthenticationService authService;
    private DataRepository dataRepository;

    @PostMapping("/auth/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody UserDTO userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signIn(userDto));
    }

    @PostMapping("/auth/registration")
    public ResponseEntity<JwtAuthResponse> registration(@RequestBody UserDTO userDto) {
        return ResponseEntity.ok().body(authService.signUp(userDto));
    }

    @GetMapping("/api/data")
    public ResponseEntity<List<DataDTO>> getData() {
        List<Data> data = dataRepository.findAllByUser(userService.getCurrentUser());
        List<DataDTO> dataDtos = new ArrayList<>();
        data.forEach((d) -> dataDtos.add(new DataDTO(d.getTitle(), d.getMagicNumber())));
        return ResponseEntity.ok().body(dataDtos);
    }

    @PutMapping("/api/data")
    public ResponseEntity<String> addElemToColl(@RequestBody DataDTO dataDto) {
        Data data = Data.builder()
            .user(userService.getCurrentUser())
            .title(dataDto.title())
            .magicNumber(dataDto.magicNumber())
            .build();
        dataRepository.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Done");
    }
}
