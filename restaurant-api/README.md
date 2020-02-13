# Restaurant API
Backend အပိုင်းမှာ နေရာကျပါတယ်။ TypeScript Language နဲ့ Nest JS Framework ကို အသုံးပြုထားပါတယ်။ 

## Modular Approach

Nest JS ဟာ Type Script ကို အသုံးပြုထားတဲ့ အတွက် ရည်ရွယ်ချက်အပေါ်မူတည်ပြီး Module တွေကို ခွဲခြားရေးသားနိုင်ပါတယ်။
လက်ရှိ System မှာတော့ အောက်ပါအတိုင်း Module တွေကိို ခွဲခြားရေးသားထားပါတယ်။

1. Employee Module

```
import { EmployeeService } from './model/employee.service';
import { EmployeeController } from './controller/employee.controller';
import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm'
import { Employee } from './model/employee.entity'

@Module({
    imports: [
        TypeOrmModule.forFeature([Employee])
    ],
    controllers: [
        EmployeeController, ],
    providers: [
        EmployeeService, ],
})
export class EmployeeModule {}
```

2. Master Module

```
import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { CategoryController } from './controller/category.controller';
import { ProductController } from './controller/product.controller';
import { TablesController } from './controller/tables.controller';
import { Category } from './model/category.entity';
import { CategoryService } from './model/category.service';
import { Product } from './model/product.entity';
import { ProductService } from './model/product.service';
import { Tables } from './model/tables.entity';
import { TablesService } from './model/tables.service';

@Module({
    imports: [
        TypeOrmModule.forFeature([Category, Product, Tables])
    ],
    controllers: [
        CategoryController,
        ProductController,
        TablesController
    ],
    providers: [
        CategoryService,
        ProductService,
        TablesService
    ],
})
export class MasterModule {}
```

3. Sale Module

```
import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Product } from 'src/master/model/product.entity';
import { SaleController } from './controller/sale.controller';
import { SaleDetailsController } from './controller/saledetails.controller';
import { Sale } from './model/sale.entity';
import { SaleService } from './model/sale.service';
import { SaleDetails } from './model/saledetails.entity';
import { SaleDetailsService } from './model/saledetails.service';

@Module({
    imports: [
        TypeOrmModule.forFeature([
            Sale,
            SaleDetails,
            Product
        ])
    ],
    controllers: [
        SaleController, 
        SaleDetailsController],
    providers: [
        SaleService,
        SaleDetailsService
    ],
})
export class SalesModule {}
```


## Persistance Layer
Database အနေနဲ့ကတော့ Open Source Database ဖြစ်တဲ့ MariaDB ကို အသုံးပြုသွားပါမယ်။ Nest JS Framework မှာ Relational Database ကို အသုံးပြုဖို့အတွက် TypeORM Framework ကို အသုံးပြုသွားမှာဖြစ်ပါတယ်။

TypeORM ဆိုတာ Object Relation Mapping (ORM) ကို အခြေခံထားတဲ့ Framework တစ်ခုဖြစ်ပါတယ်။ Nest JS မှာ TypeORM ကို အသုံးပြုနိုင်အောင် ပြင်ဆင်ပေးထားပါတယ်။

ဒီီ System မှာ အသုံးပြုမည့် Database Structure (ERD) ကတော့ အောက်ပါအတိုင်းဖြစ်ပါတယ်။ 

![ERD](/images/RestaurantERD.png)

### Employee Relation

အသုံးပြုမည့် ဝန်ထမ်းတွေကို သိမ်းပေးထားနိုင်တဲ့ Table ဖြစ်ပါတယ်။ ဝန်ထမ်းတွေကို အသစ်ထပ်ပြီး ဖြည့်စွက်တာ၊ ပြင်တာ၊ ပြီးတော့ ရှိသမျှဝန်ထန်းတွေရဲ့ အချက်အလက်တွေကို ပြန်ကြည့်တာတို့ လုပ်နိုင်ဖို့ API တစ်ခုကိုတော့ ဒီမှာ ရေးရမှာ ဖြစ်ပါတယ်။
  
[Employee Class](https://github.com/minlwin/the-restaurant/blob/master/restaurant-api/src/employee/model/employee.entity.ts)
```
import { PrimaryGeneratedColumn, Entity, Column, CreateDateColumn, UpdateDateColumn } from "typeorm"
import { Transform } from 'class-transformer'
import moment = require("moment")
import { IdEnable } from "src/common/id.enable"

@Entity()
export class Employee implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number
    @Column()
    name:string
    @Column()
    email:string
    @Column()
    phone:string
    @Column()
    password:string
    @CreateDateColumn()
    @Transform(d => moment(d).format('YYYY-MM-DD HH:mm:ss'))
    creation:Date
    @UpdateDateColumn()
    @Transform(d => moment(d).format('YYYY-MM-DD HH:mm:ss'))
    modification:Date
}
```

### Tables Relation

### Category Relation

### Product Relation

### Sale Relation

### Sale Details Relation

## Controllers

## Service Layer
