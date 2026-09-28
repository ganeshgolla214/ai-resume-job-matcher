package com.ganesh.matcher;
import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api") @CrossOrigin(origins="*")
public class MatchController{
 private static final List<String> SKILLS=List.of("java","spring boot","react","typescript","javascript","python","sql","postgresql","mysql","docker","kubernetes","aws","azure","git","github","rest","microservices","html","css","tailwind","linux","mongodb","redis","jenkins");
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","UP");}
 @PostMapping("/match") public Map<String,Object> match(@RequestBody Map<String,String> b){
  String resume=b.getOrDefault("resume","").toLowerCase(),job=b.getOrDefault("job","").toLowerCase();
  List<String> required=SKILLS.stream().filter(job::contains).toList();
  List<String> matched=required.stream().filter(resume::contains).toList();
  List<String> missing=required.stream().filter(x->!matched.contains(x)).toList();
  int score=required.isEmpty()?0:(int)Math.round(matched.size()*100.0/required.size());
  return Map.of("score",score,"matchedSkills",matched,"missingSkills",missing,"resumeSkills",SKILLS.stream().filter(resume::contains).toList());
 }
}