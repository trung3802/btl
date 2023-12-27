import { Component, OnInit } from '@angular/core';
import { faBars, faHeart, faPhone, faShoppingBag } from '@fortawesome/free-solid-svg-icons';
import { MessageService } from 'primeng/api';
import { Order } from 'src/app/_class/order';
import { OrderDetail } from 'src/app/_class/order-detail';
import { UserService } from 'src/app/_service/user.service';
import { CartService } from 'src/app/_service/cart.service';
import { OrderService } from 'src/app/_service/order.service';
import { StorageService } from 'src/app/_service/storage.service';
import { Router } from '@angular/router'; // Import Router



@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css'],
  providers: [MessageService]

})
export class CheckoutComponent implements OnInit {
  heart = faHeart;
  bag = faShoppingBag;
  phone = faPhone;
  bars = faBars;
  showDepartment = false;
  order = new Order();
  listOrderDetail: any[] =[];
  username !: string;

  orderForm :any ={
    firstname: null,
    lastname : null,
    country : null,
    addrest : null,
    town : null,
    state : null,
    postCode: null,
    email: null,
    phone: null,
    note: null
  }

  constructor(private userService: UserService,
    public cartService: CartService,
    private orderService:OrderService,
    private storageService: StorageService,
    private router: Router
    ){

  }
  ngOnInit(): void {


    console.log(this.username);
    // Lấy tên người dùng từ dịch vụ lưu trữ
    this.username = this.storageService.getUser().username;

    // Lấy thông tin người dùng từ API và cập nhật orderForm
    this.userService.getUser(this.username).subscribe(
      (userDetails: any) => {
        this.orderForm.firstname = userDetails.firstname;
        this.orderForm.lastname = userDetails.lastname;
        this.orderForm.country = userDetails.country;
        this.orderForm.address = userDetails.address;
        this.orderForm.town = userDetails.town;
        this.orderForm.state = userDetails.state;
        this.orderForm.postCode = userDetails.postCode;
        this.orderForm.email = userDetails.email;
        this.orderForm.phone = userDetails.phone;
        // Gọi service để đặt hàng

      },
      (error) => {
        console.error('Lỗi khi lấy thông tin người dùng:', error);
      }
    );

    // Lấy danh sách sản phẩm từ giỏ hàng
    this.cartService.getItems();
  }

  showDepartmentClick(){
    this.showDepartment = !this.showDepartment;
  }


  placeOrder(){
    this.cartService.items.forEach(res =>{
      let orderDetail : OrderDetail = new OrderDetail;
      orderDetail.name = res.name;
      orderDetail.price = res.price;
      orderDetail.quantity = res.quantity;
      orderDetail.subTotal = res.subTotal;
      this.listOrderDetail.push(orderDetail);
    })
    const {firstname,lastname,country,address,town,state,postCcode,phone,email,note} = this.orderForm;
    this.orderService.placeOrder(firstname,lastname,country,address,town,state,postCcode,phone,email,note,this.listOrderDetail,this.username).subscribe({
      next: res =>{
        alert(" Đặt Hàng Thành công")
         // Xóa giỏ hàng sau khi đặt hàng thành công
         this.cartService.clearCart();
         // điều hướng về trang chủ
         this.router.navigateByUrl('/');
      },error: err=>{
        console.log(err);
      }
    })
  }


}
