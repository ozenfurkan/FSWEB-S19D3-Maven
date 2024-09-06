package com.workintech.s19d2.controller;

import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/account")
@RestController

    public class AccountController {

        private AccountService accountService;

        @GetMapping
        public List<Account> findAll(){
            return accountService.findAll();
        }

        @PostMapping
        public Account save(@RequestBody Account account){
            return accountService.save(account);
        }

    }
//[GET]/workintech/accounts/ => tüm account listini dönmeli.
//[GET]/workintech/accounts/{id} => İlgili id deki account objesini dönmeli.
//[POST]/workintech/accounts => Bir adet account objesini veritabanına kaydeder.
//[PUT]/workintech/accounts/{id} => İlgili id deki account objesinin değerlerini yeni gelen data ile değiştirir.
//[DELETE]/workintech/accounts/{id} => İlgili id değerindeki account objesini veritabanından siler.

