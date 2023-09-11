package com.sherwin.demo2.rest;

import com.sherwin.demo2.Automobile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("automobile")
@RequiredArgsConstructor
public class AutomobileController {

    @PostMapping("")
    public Automobile insertAutomobile(@Valid @RequestBody Automobile automobile)
    {
        return automobile;
    }
}
