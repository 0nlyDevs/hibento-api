package org.onlydevs.hibento.unit;

import org.junit.jupiter.api.Test;
import org.onlydevs.hibento.endpoint.rest.security.AdminChecker;
import org.springframework.test.util.ReflectionTestUtils;

class AdminCheckerTest {

  @Test
  void admin_email_returns_true_ok() {
    var checker = new AdminChecker();
    ReflectionTestUtils.setField(checker, "admins", "admin@gmail.com, coco@gmail.com");
    assert checker.isAdmin("admin@gmail.com");
  }

  @Test
  void admin_email_case_insensitive_ok() {
    var checker = new AdminChecker();
    ReflectionTestUtils.setField(checker, "admins", "admin@gmail.com,coco@gmail.com");
    assert checker.isAdmin("coco@Gmail.Com");
  }

  @Test
  void non_admin_email_returns_false_ko() {
    var checker = new AdminChecker();
    ReflectionTestUtils.setField(checker, "admins", "admin@gmail.com,coco@gmail.com");
    assert !checker.isAdmin("other@gmail.com");
  }

  @Test
  void null_email_returns_false_ko() {
    var checker = new AdminChecker();
    ReflectionTestUtils.setField(checker, "admins", "admin@gmail.com, another@gmail.com");
    assert !checker.isAdmin(null);
  }

  @Test
  void second_admin_also_matches_ok() {
    var checker = new AdminChecker();
    ReflectionTestUtils.setField(checker, "admins", "admin@gmail.com,another@gmail.com");
    assert checker.isAdmin("another@gmail.com");
  }

  @Test
  void empty_admin_list_returns_false_ko() {
    var checker = new AdminChecker();
    ReflectionTestUtils.setField(checker, "admins", "");
    assert !checker.isAdmin("admin@gmail.com");
  }

  @Test
  void null_admin_list_returns_false_ok() {
    var checker = new AdminChecker();
    ReflectionTestUtils.setField(checker, "admins", null);
    assert !checker.isAdmin("admin@gmail.com");
  }

  @Test
  void handles_whitespace_in_admin_list_ok() {
    var checker = new AdminChecker();
    ReflectionTestUtils.setField(checker, "admins", " admin@gmail.com , coco@gmail.com ");
    assert checker.isAdmin("admin@gmail.com");
  }
}
