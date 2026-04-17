package com.santhosh.java.Restcontroller;

import com.santhosh.java.model.UserInfo;
import com.santhosh.java.service.UserInfoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class UserController
{

    @Autowired
    UserInfoInterface userInfoInterface ;
      @PostMapping
      public ResponseEntity<UserInfo> adduser(@RequestBody UserInfo  newuser)
      {
         UserInfo savenewuser = userInfoInterface.addUser(newuser);

         return ResponseEntity.ok(savenewuser) ;

      }
}
