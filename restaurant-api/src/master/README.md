# 2. Master Module

Application ကို အလုပ်လုပ်စေဖို့ ကြိုပြီးပြင်ထားပေးဖို့လိုအပ်တဲ့ Master Data တွေကို Manage လုပ်ဖို့အတွက် လိုအပ်တဲ့ Module ဖြစ်ပါတယ်။ အသုံးပြုမည့် Master Data တွေကတော့ Tables, Category နဲ့ Product တို့ပဲ ဖြစ်ပါတယ်။

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

MasterModule ထဲမှာတော့ TypeOrmModule ကနေ Entity တွေအတွက် Repository တွေကို Inject လုပ်ဖို့အတွက် TypeOrmModule.forFeature([Category, Product, Tables]) ဆိုပြီး Import လုပ်ထားပါတယ်။ 

Module ထဲမှာ အသုံးပြုမည့် Controller တွေဖြစ်ကြတဲ့ CategoryController, ProductController, TablesController တွေကို Controller အနေနဲ့ Define လုပ်ထားပါတယ်။

CategoryService, ProductService, TablesService တွေကို Controller တွေထဲမှာ Inject လုပ်ပြီး အသုံးပြုနိုင်အောင် Provider အနေနဲ့ Define လုပ်ထားပါတယ်။

## Entities

Master Module ထဲမှာတော့ Master Data တွေဖြစ်ကြတဲ့ Category, Product, Tables Entity တွေပါဝင်ကြပါတယ်။

### Category Entity

ဟင်းအမျိုးအစားတွေကို ကိုယ်စားပြုတဲ့ Entity တစ်ခုဖြစ်ပါတယ်။ ဟင်းပွဲတွေကို ရှာဖွေရတာလွယ်ကူအောင် အကူအညီပေးနိုင်ပါတယ်။ တဖန် ဘယ်လိုဟင်းအမျိုးအစားတွေဟာ ဘယ်လို အခြေအနေမျိုးမှာ ရောင်းအားကောင်းလဲ ဆိုတာကို Analyse လုပ်လိုတဲ့ အခါမှာလဲ အသုံးဝင်ပါတယ်။

[Category](model/category.entity.ts)
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

### Product Entity

ဟင်းပွဲ Menu တွေကို ကိုယ်စားပြုတဲ့ Entity ဖြစ်ပါတယ်။ Category အလိုက် ဟင်းပွဲတွေကို သတ်မှတ်ထားလိုတဲ့ အတဝက် Category ကို ManyToOne နဲ့ Reference လုပ််ထားပါတယ်။

[Product](model/product.entity.ts)
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

### Tables Entity

Customer Table စားပွဲခုံတွေကို ကိုယ်စားပြုတဲ့ Entity Class ဖြစ်ပါတယ်။ ဘယ်စားပွဲကမှာတဲ့ ဟင်းလဲ၊ ဘယ်စားပွဲက ငွေရှင်းမှာလဲ ဆိုတဲ့နေရာမှာ အသုံးပြုမှာ ဖြစ်တယ်။

[Tables](model/tables.entity.ts)
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

## Services



## Controllers