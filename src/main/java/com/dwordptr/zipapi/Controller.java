package com.dwordptr.zipapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

@RestController
public class Controller {
    @Resource
    private ZipService zipService;

    @PostMapping("/insert/{param}")
    public ResponseEntity<String> insert(@PathVariable("param") String param){
        boolean success = zipService.insert(param);
            return (success) ?
                    new ResponseEntity<String>(
                            String.format("Zip code %s inserted.",param),
                            HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{param}")
    public ResponseEntity<String> delete(@PathVariable("param") String param){
        boolean success = zipService.delete(param);
        String msg = success? String.format("Zip code %s deleted.", param) :
                "Issue removing %s from list.";
        return new ResponseEntity<>(msg, success ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/has/{param}")
    public ResponseEntity<Boolean> has(@PathVariable("param") String param){
        return new ResponseEntity<Boolean>(zipService.inList(param), HttpStatus.OK);
    }
    @GetMapping("/display")
    public ResponseEntity<String> display(){
        return new ResponseEntity<>(zipService.getZipCodeString(), HttpStatus.OK);
    }
}
