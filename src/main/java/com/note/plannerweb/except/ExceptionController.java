package com.note.plannerweb.except;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exception")
public class ExceptionController {
    @GetMapping("/entrypoint")
    public Exception entryPointException(){
        throw new IllegalArgumentException();
    }

    @GetMapping("/accessDenied")
    public Exception accessDeniedException(){
        throw new AccessDeniedException("");
    }

}
