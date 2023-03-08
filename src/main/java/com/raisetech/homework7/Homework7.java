package com.raisetech.homework7;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Validated
@RestController
public class Homework7 {

  @GetMapping("/users")
  public Map<String, String> user(@RequestParam("name") @NotBlank(message = "名前を入力してください")
                                  @Size(max = 20, message = "20文字以内で入力してください") String name,
                                  @RequestParam("birthday")
                                  @Pattern(regexp = "^(19[0-9]{2}|20[0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$")
                                  String birthday,
                                  @RequestParam("address")
                                  String address) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDate birthdayToLocalDate = LocalDate.parse(birthday, formatter);
    formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    String formatBirthday = birthdayToLocalDate.format(formatter);
    return Map.of("name", name, "birthday", formatBirthday, "address", address);
  }


  @PostMapping("/users")
  public ResponseEntity<Map<String, String>> createUser(@RequestBody @Validated CreateForm form, UriComponentsBuilder uriBuilder) {
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



