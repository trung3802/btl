import { Component } from '@angular/core';
import { ProductService } from 'src/app/_service/product.service';
import { OrderService } from 'src/app/_service/order.service';
import { BlogService } from 'src/app/_service/blog.service';
import { faCalculator, faDollarSign, faComment, faClipboard } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})
export class ChartsComponent {
  totalProducts: number = 0;
  totalRevenue: number = 0;
  totalOrder: number=0;
  totalblog: number=0;
  cacli=faCalculator;
  clip=faClipboard;
  dolo=faDollarSign;
  cmt=faComment;
  constructor(private productService: ProductService,private orderService: OrderService,private blogService: BlogService) {}

  ngOnInit(): void {
    //gọi ra api tổng sản phẩm
    this.productService.getTotalProducts().subscribe(total => {
      this.totalProducts = total;
    });
    // gọi tổng doanh thu
    this.orderService.getTotalRevenue().subscribe(revenue => {
      this.totalRevenue = revenue;
    });
      // gọi tổng doanh thu
      this.orderService.getOders().subscribe(odrder => {
        this.totalOrder = odrder;
      });
      // gọi tổng blog
      this.blogService.getBlogtt().subscribe(blogss => {
        this.totalblog = blogss;
      });
  }






}
