package br.com.brn.brnapi.controller;
/*
 * @author BRUN
 */

import br.com.brn.brnapi.dto.BrnUserDto;
import br.com.brn.brnapi.model.BrnUser;
import br.com.brn.brnapi.repository.BrnUserRepository;
import br.com.brn.brnapi.utils.EncodingPassword;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.brn.brnapi.utils.SystemConstants.SECRET_KEY;

@RestController
public class BrnUserController {
    @Autowired
    private BrnUserRepository brnUserRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Iterable<BrnUser> Get() {
        return brnUserRepository.findAll();
    }


    @PostMapping("login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String pwd) {
        BrnUserDto user = new BrnUserDto();
        try {
            String encoding = EncodingPassword.encoding(pwd);
            System.out.println(encoding);
            Optional<BrnUser> brnUser = brnUserRepository.findBrnUserByUsernameAndPassword(username, encoding);
            if (brnUser.isPresent() && brnUser.get().isActive()) {
                String token = getJWTToken(username);
                user.setUser(username);
                user.setToken(token);
                return user.getToken();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId(SECRET_KEY)
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512,
                        SECRET_KEY.getBytes()).compact();

        return "Bearer " + token;
    }
}
