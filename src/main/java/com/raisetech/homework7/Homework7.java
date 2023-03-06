package com.raisetech.homework7;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Validated
@RestController
public class Homework7 {

  @GetMapping("/users")
  public Map<String, String> user(@RequestParam("name") @NotBlank(message = "名前を入力してください")
                                  @Size(max = 20, message = "20文字以内で入力してください") String name, BindingResult result,
                                  @RequestParam("birthday")
                                  @Pattern(regexp = "^(19|20[0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$")
                                  @DateTimeFormat(pattern = "yyyyMMdd")
                                  @JsonFormat(pattern = "yyyy年MM月dd日")
                                  String birthday,
                                  @RequestParam("address")
                                  String address) {
    if (result.hasErrors()) {
      return Map.of("message", "名前を入力してください、20文字以内で入力してください");
    } else {
      return Map.of("name", name, "birthday", birthday, "address", address);
    }
  }

  @PostMapping("/users")
  public ResponseEntity<Map<String, String>> createUser(@RequestBody CreateForm form, UriComponentsBuilder uriBuilder) {
    // 登録処理は省略
    URI url = uriBuilder.path("/users/id") // id部分は実際に登録された際に発⾏したidを設定する
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
    return ResponseEntity.noContent().build();
  }
}



