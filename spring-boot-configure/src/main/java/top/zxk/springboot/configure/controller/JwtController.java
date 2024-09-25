package top.zxk.springboot.configure.controller;



import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zxk.springboot.configure.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/jwt")
@AllArgsConstructor
public class JwtController {
    private final JwtUtil jwtUtil;

    /**
     *
     *
     * @param username
     * @return
     */
    @PostMapping("/generate")
    public Map<String, String> generateToken(@RequestParam String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        String token = jwtUtil.generateToken(claims);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
