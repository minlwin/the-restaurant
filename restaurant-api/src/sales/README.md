# 3. Sale Module

Restaurant ရဲ့ အရောင်းစာရင်းကို Manage လုပ်ပေးမည့် Module ဖြစ်ပါတယ်။ Restaurant Counter ပေါ်မှာရှိတဲ့ ဘောင်ချာတစ်စောင်ကို Sale Entity က ကိုယ်စားပြုပါတယ်။ ဘောင်ချာထဲမှာပါတဲ့ အော်ဒါတစ်ခုချင်းစီကို SaleDetails Entity က ကိုယ်စားပြုပါတယ်။

```typescript
@Module({
    imports: [
        TypeOrmModule.forFeature([
            Sale,
            SaleDetails
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

Entity အနေနဲ့ Sale နဲ့ SaleDetails သာပါဝင်ပါမယ်။ Service အနေနဲ့လဲ SaleService နဲ့ SaleDetailsService ကိုအသုံးပြုမယ်လို့ သတ်မှတ်ထားပါတယ်။ တဖန် Controller တွေကလဲ SaleController နဲ့ SaleDetailsController တို့သာ ပါဝင်မှာ ဖြစ်ပါတယ်။


## Entities

### Sale Entity

Restaurant Counter ပေါ်မှာရှိတဲ့ ဘောင်ချာ တစ်စောင်ကို ကိုယ်စားပြုပါမယ်။ ဘယ်စားပွဲမှာ ဘာတွေမှာထားပြီး၊ စုစုပေါင်းဘယ်လောက်ကျတယ်၊ ပြီးတော့ အခွန်ငွေအစရှိတဲ့ ဒေတာတွေကို သိိမ်းထားနိုင်ရပါမယ်။

[Sale](model/sale.entity.ts)
```typescript
@Entity()
export class Sale implements IdEnable {
    @PrimaryGeneratedColumn()
    id:number
    @ManyToOne(type => Tables, {
        eager: true,
        nullable: false
    })
    tables:Tables
    @Column()
    @Transform(d => moment(d).format())
    saleDate:Date
    @Column()
    subTotal:number
    @Column()
    tax:number
    @OneToMany(type => SaleDetails, detail => detail.sale, {
        onDelete: "CASCADE"
    })
    details:SaleDetails[]
    @Column()
    paid:boolean = false
}
```
ဘောင်ချာတစ်စောင်မှာ အော်ဒါတွေ တစ်ခုထက်အများပါဝင်နိုင်တဲ့ အတွက် Sale Entity ထဲကနေ SaleDetails Entity ကို OneToMany Relationship နဲ့ Reference လုပ်ထားပါတယ်။ တဖန် Sale ကို Delete လုပ်ရင် Sale ထဲမှာရှိတဲ့ SaleDetails တွေကိုပါဖျက်နိုင်အောင် onDelete မှာ CASCADE လို့ သတ်မှတ်ထားပါတယ်။

> ကျွန်တော်တို့ မြန်မာနိုင်ငံမှာ လဘ္ဘက်ရည်ဆိုင်တွေမှာ စားပွဲတစ်လုံးမှာ Customer တစ်ယောက်ထက်မက ထိုင်နိုင်တဲ့အချက်ကို အစဉ်ပြေအောင် ဆောင်ရွက်ပေးနိုင်ဖို့ စဉ်းစားထားဖို့လိုအပ်ပါတယ်

### SaleDetails Entity

SaleDetails Entity ဟာ Sale ထဲမှာပါတဲ့ အော်ဒါတစ်ခုချင်းစီကို ကိုယ်စားပြုပါတယ်။ ဘယ်ဟင်းကို ဘယ်နှစ်ပွဲမှာထားသလဲဆိုတဲ့ ဒေတာတွေကို သိမ်းပေးထားဖို့လိုအပ်ပါတယ်။ တဖန် ဒီအော်ဒါဟာ ဘယ်ဘောင်ချာရဲ့ အော်ဒါလဲဲဲဆိုတာကို သိနေဖို့လိုအပ်ပါတယ်။

[SaleDetails](model/saledetails.entity.ts)
```typescrypt
@Entity()
export class SaleDetails implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number

    @ManyToOne(type => Sale, sale => sale.details, {
        eager: true,
        nullable : false
    })
    @Exclude()
    sale:Sale
    @ManyToOne(type => Product, {
        eager: true,
        nullable: false
    })
    product:Product
    @Column()
    unitPrice:number
    @Column()
    quantity:number
}
```

Product နဲ့ SaleDetails တို့ရဲ့ ပတ်သက်မှုကို ကြည့်မယ်ဆိုရင် ဟင်းပွဲတစ်ခုကို အော်ဒါအများကြီးမှာ အသုံးပြုနိင်မှာဖြစ်တဲ့ အတွက် SaleDetails ကနေ  Product ကို ManyToOne Relationship နဲ့ Reference လုပ်ထားပါတယ်။

ဒီလိုပဲ ဘောင်ချာတစ်စောင်မှာ အော်ဒါတွေအများကြီးရှိနေနိုင်တာဖြစ်တဲ့ အတွက် SaleDetails ထဲကနေ Sale ကို ManyToOne နဲ့ Reference လုပ်ထားပါတယ်။ 

### Bi Directional Relationship and Serializing Problem

ဒီနေရာမှာ Sale ဘက်ကလဲ SaleDetails ကို OneToMany အနေနဲ့ Reference လုပ်နေပြီး၊ SaleDetails ဘက်ကလဲ Sale ကို ManyToOne နဲ့ Reference လုပ်ထားတာဖြစ်တဲ့အတွက် Bi Directional Reference ဖြစ်နေပါတယ်။ ဒီလို Case မျိုးတွေကို Response ထဲဲကို Serialize လုပ်တဲ့ အခါမျိုးတွေမှာ ပြဿနာတက်တတ်ပါတယ်။

Sale Entity ကို Serialize လုပ်တဲ့အခါမှာ အထဲမှာပါတဲ့ SaleDetails တွေကို Serialize လုပ်ဖို့ကြိုးစားပါမယ်။ တဖန် SaleDetails ကို Serialize လုပ်တဲ့နေရာမှာလဲ အထဲမှာ Sale ကို ManyToOne နဲ့ Reference လုပ်ထားတဲ့ အတွက် Sale ကို Serialize လုပ်ဖို့ ကြိုးစားပါမယ်။ ဒီလိုနဲ့ အဆုံးမရှိ Serialize လုပ်မိနေပြီး Stack Overflow Error ကို ဖြစ်ပေါ်စေမှာ ဖြစ်ပါတယ်။

ဒါကြောင့် SaleDetails ထဲမှာ Sale ကို Exclude လုပ်တားတာဖြစ်ပါတယ်။
