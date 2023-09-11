package com.moaaz.modernhome.Auth;


import com.moaaz.modernhome.Employee.Employee;
import com.moaaz.modernhome.Employee.EmployeeRepository;
import com.moaaz.modernhome.Mail.MailService;
import com.moaaz.modernhome.User.Person;
import com.moaaz.modernhome.User.User;
import com.moaaz.modernhome.User.UserRepository;
import com.moaaz.modernhome.User.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    @Autowired
    private UserRepository userService;

    @Autowired
    private EmployeeRepository employeeService;

    @Autowired
    private MailService mailService;


    public ResponseEntity<?> login(String email, String password) {

        if (email.equals("modernhomeinegypt@gmail.com") && password.equals("ModernHome"))
            return new ResponseEntity<>(Person.admin(), HttpStatus.ACCEPTED);

        User user = userService.findByEmailAndPassword(email, password).orElse(null);
        if (user != null)
            return new ResponseEntity<>(UserResponse.convertUserToUserResponse(user), HttpStatus.ACCEPTED);
        Employee employee = employeeService.findByEmailAndPassword(email, password).orElse(null);
        if (employee != null)
            return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);

        return new ResponseEntity<>("Wrong In Email Or Password ", HttpStatus.UNAUTHORIZED);

    }

    public ResponseEntity<?> forgetPassword(String email) {

        if (email.equals("modernhomeinegypt@gmail.com")) {
            mailService.sendPasswordToEmail("modernhomeinegypt@gmail.com", "ModernHome");
            return sentPasswordSuccessfully();
        }
        User user = userService.findByEmail(email).orElse(null);
        if (user != null) {
            mailService.sendPasswordToEmail(user.getEmail(), user.getPassword());
            return sentPasswordSuccessfully();
        }
        Employee employee = employeeService.findByEmail(email).orElse(null);
        if (employee != null) {
            mailService.sendPasswordToEmail(employee.getEmail(), employee.getPassword());
            return sentPasswordSuccessfully();
        }
        return new ResponseEntity<>("This Email Aren't In Our Database!", HttpStatus.UNAUTHORIZED);

    }

    public ResponseEntity<?> sentPasswordSuccessfully() {
        return new ResponseEntity<>("The Password Send To Gmail Successfully", HttpStatus.ACCEPTED);
    }

}
