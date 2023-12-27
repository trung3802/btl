import { Component } from '@angular/core';
import { EmailService } from 'src/app/_service/email.service';
import { HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-email-verification',
  templateUrl: './email-verification.component.html',
  styleUrls: ['./email-verification.component.css']
})
export class EmailVerificationComponent {
  email: string = '';
  otp: string = '';

  constructor(private emailService: EmailService,private router:Router) {}

  submitForm() {
    const params = new HttpParams()
      .set('email', this.email)
      .set('otp', this.otp);
  
    // // Gửi yêu cầu đến backend
    // this.emailService.verifyEmailAndOTP(params).subscribe(
    //   response => {
    //     // Xác minh thành công, hiển thị thông báo và chuyển hướng
    //     console.log('Xác minh thành công:', response);
    //     // Hiển thị thông báo (có thể sử dụng toastr hoặc cách hiển thị thông báo tùy chọn)
    //     alert('Xác minh thành công!');
    //     // Chuyển hướng đến trang đăng nhập
    //     this.router.navigate(['/login']);
    //   },
    //   error => {
    //     // Xử lý lỗi nếu có
    //     console.error('Lỗi xác minh:', error);
    //   }
    // );
    this.emailService.verifyEmailAndOTP(params).subscribe({
      next: res => {
          console.log('Xác minh thành công:', res);
          // Hiển thị thông báo (có thể sử dụng toastr hoặc cách hiển thị thông báo tùy chọn)
          alert('Xác minh thành công!');
          // Chuyển hướng đến trang đăng nhập
          this.router.navigate(['/login']);
      },
      error: error => {
          console.error('Lỗi xác minh:', error);

      }
  })
  }
}
