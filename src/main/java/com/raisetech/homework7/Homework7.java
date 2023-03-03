package com.raisetech.homework7;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
public class Homework7 {

  @GetMapping("/users")
  public String user(@RequestParam("name") @NotBlank(message = "名前を入力してください")
                     @Size(max = 20, message = "20文字以内で入力してください") String name,
                     @RequestParam("birthday") @DateTimeFormat(pattern = "yyyy年MM月dd日") String birthday,
                     @RequestParam("address") String address) {
    return "名前：" + name + " 生年月日：" + birthday + " 住所：" + address + "で登録完了しました。";
  }

  @PostMapping("/users")
  public ResponseEntity<Map<String, String>> createUser(@RequestBody CreateForm form) {
    // 登録処理は省略
    URI url = UriComponentsBuilder.fromUriString("http://localhost:8080")
        .path("/users/id") // id部分は実際に登録された際に発⾏したidを設定する
        .build()
        .toUri();
    return ResponseEntity.created(url).body(Map.of("massage", "name successfully created"));
  }

  @PatchMapping("/users/{id}")
  public ResponseEntity<Map<String, String>> updateUser(@PathVariable("id") int id, @RequestBody UpdateForm form) {
    // 更新処理は省略
    return ResponseEntity.ok(Map.of("message", "name successfully updated"));
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("id") int id) {
    // 削除処理は省略
    return ResponseEntity.ok(Map.of("message", "name successfully deleted"));
  }
}



