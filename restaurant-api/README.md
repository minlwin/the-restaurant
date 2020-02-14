# Restaurant API
Backend အပိုင်းမှာ နေရာကျပါတယ်။ TypeScript Language နဲ့ Nest JS Framework ကို အသုံးပြုထားပါတယ်။ 

## Modular Approach

Nest JS ဟာ Type Script ကို အသုံးပြုထားတဲ့ အတွက် ရည်ရွယ်ချက်အပေါ်မူတည်ပြီး Module တွေကို ခွဲခြားရေးသားနိုင်ပါတယ်။
လက်ရှိ System မှာတော့ အောက်ပါအတိုင်း Module တွေကိို ခွဲခြားရေးသားထားပါတယ်။

1. Employee Module

```typescript
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

```typescript
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

```typescript
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

### Employee 

အသုံးပြုမည့် ဝန်ထမ်းတွေကို သိမ်းပေးထားနိုင်တဲ့ Table ဖြစ်ပါတယ်။ ဝန်ထမ်းတွေကို အသစ်ထပ်ပြီး ဖြည့်စွက်တာ၊ ပြင်တာ၊ ပြီးတော့ ရှိသမျှဝန်ထန်းတွေရဲ့ အချက်အလက်တွေကို ပြန်ကြည့်တာတို့ လုပ်နိုင်ဖို့ API တစ်ခုကိုတော့ ဒီမှာ ရေးရမှာ ဖြစ်ပါတယ်။ Employees Module ထဲဲမှာပါဝင်တဲ့ Entity တစ်ခုဖြစ်ပါတယ်။
  
[Employee Class](https://github.com/minlwin/the-restaurant/blob/master/restaurant-api/src/employee/model/employee.entity.ts)
```typescript
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

### Tables 

Restaurant မှာရှိတဲ့ စားပွဲတွေကို ကိုယ်စားပြုတဲ့ Entity ဖြစ်ပါတယ်။ အရောင်းစာရင်းတွေမှတ်တဲ့ နေရာ၊ အော်ဒါမှာတဲ့အခါတွေမှာ ဘယ်စာပွဲကလဲ ဆိုတာကို ဖေါ်ပြတဲ့ နေရာမှာ အသုံးပြပါမယ်။ Master Data အမျိုးအစားဖြစ်တဲ့ အတွက် အပလီကေးရှင်းစတဲ့ အချိန်မှာ ကြိုပြီး ပြင်ဆင်ထားဖို့လိုအပ်ပါတယ်။ တဖန် လိုအပ်သလို ထပ်ပြီး ဖြည့်စွက်နိုင်သလို ပြင်လို့လဲရရပါမယ်။

```typescript
@Entity()
export class Tables implements IdEnable{
    @PrimaryGeneratedColumn()
    id:number
    @Index({unique : true})
    @Column()
    tableNumber:string
    @Column()
    seats:number
}
```

### Category Relation
ဟင်းပွဲအမျိုးအစားတွေကို ကိုယ်စားပြုတဲ့ Entity တစ်ခုဖြစ်ပါတယ်။ အမျိုးအစားအလိုက် ဟင်းပွဲတွေကို ရှာဖွေတဲ့ နေရာမှာ အထောက်အကူပြုနိုင်ပါတယ်။ တဖန် အမျိုးအစားအလိုက် ရောင်းအားတွေကို ခန့်မှန်းတဲ့ နေရာမှာလဲ အသုံးပြုနိုင်ပါတယ်။ Master Data အမျိုးအစားဖြစ်ပြီး MasterModule အောက်မှာ ပါဝင်ပါတယ်။

```typescript
@Entity()
export class Category  implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number

    @Index({unique : true})
    @Column()
    name:string
    @Column()
    color:number
}
```

### Product 
ဟင်းပွဲ Menu တွေကို ကိုယ်စားပြုပါတယ်။ ဈေးနူန်းတွေ၊ အရွယ်အစားတွေ ကို ဖေါ်ပြပေးနိုင်ပါတယ်။ Master Data အမျိုးအစားဖြစ်ပြီး MasterModule အောက်မှာ ပါဝင်ပါတယ်။

```typescript
@Entity()
export class Product implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number
    @Column()
    name:string

    @ManyToOne(type => Category, {
        nullable : false,
        eager: true
    })
    category:Category
    @Column()
    price:number
    @Column()
    size:String
}
```

### Sale 

### Sale Details 

## Controllers

## Service Layer
