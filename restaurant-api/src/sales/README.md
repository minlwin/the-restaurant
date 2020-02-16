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
        eager: true,
        cascade: true
    })
    details:SaleDetails[]
    @Column()
    paid:boolean = false
}
```
ဘောင်ချာတစ်စောင်မှာ အော်ဒါတွေ တစ်ခုထက်အများပါဝင်နိုင်တဲ့ အတွက် Sale Entity ထဲကနေ SaleDetails Entity ကို OneToMany Relationship နဲ့ Reference လုပ်ထားပါတယ်။ Sale ကို Insert, Update, Delete လုပ်ရင် SaleDetails ကိုပါ လုပ်နိုင်အောင် cascade ကို true လို့သတ်မှတ်ထားပါတယ်။

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

## Services

Service တွေဟာ Resouces တွေကို Handle လုပ်ပေးနိုင်တဲ့ Component တွေဖြစ်ကြပြီး Business Logic တွေကို Wrap လုပ်ပေးနိုင်ပါတယ်။ SaleModule ထဲမှာတော့ SaleService နဲ့ SaleDetailsService တို့ပါဝင်ကြပါတယ်။

### Sale Service

Sale Resource အတွက် အခြေခံ CRUD Operation တွေကို လုပ်ဆောင်ပေးနိုင်ပါတယ်။ [BaseService](/src/common/base.service.ts) Class ကို Extends လုပ်ထားခြင်းအားဖြင့် Super Class ထဲက Method  တွေကို Inheritance လုပ်ပြီး အသုံးပြုပါတယ်။ 

[SaleService](model/sale.service.ts)
```typescript
@Injectable()
export class SaleService extends BaseService<Sale> {

    constructor(
        @InjectRepository(Sale)
        repo:Repository<Sale>,
        @InjectRepository(SaleDetails)
        private readonly orders:Repository<SaleDetails>
    ) { super(repo) }

    save(sale:Sale) {
        sale.details.forEach(d => {
            d.sale = sale
        })
        return this.repo.save(sale)
    }
}
```
ဒီနေရာမှာ BaseServiceMutable ကို မဟုတ်ပဲ BaseService ကို Inheritance လုပ်နေတာကတော့ SaleService ဟာ save method ကို ကိုယ်ပိုင်ရေးသားဖို့လိုတဲ့ အတွက်ဖြစ်ပါတယ်။

Sale နဲ့ SaleDetails ဟာ အပြန်အလှန် Reference လုပ်နေတဲ့ အတွက် Endless Serialization ကို ရှောင်ဖို့အတွက် SaleDetails ထဲကနေ Sale ကို Exclude လုပ်ထားခဲ့ပါတယ်။ ဒါ့ကြောင့် Save မလုပ်ခင် SaleDetails မှာ Sale ကို ပြန်ပြီး သတ်မှတ်ဖို့လိုအပ်တဲ့ အတွက် save() method ကို ကိုယ်ပိုင်ရေးသားဖို့လိုအပ်တာဖြစ်ပါတယ်။

### Sale Details Service

Sale Details Resource အတွက် အခြေခံ CRUD Operation တွေကို လုပ်ဆောင်ပေးနိုင်ပါတယ်။ [BaseService](/src/common/base.service.ts) Class ကို Extends လုပ်ထားခြင်းအားဖြင့် Super Class ထဲက Method  တွေကို Inheritance လုပ်ပြီး အသုံးပြုပါတယ်။ 

[SaleDetailsService](model/saledetails.service.ts)
```typescript
@Injectable()
export class SaleDetailsService extends BaseService<SaleDetails> {

    constructor(
        private readonly saleService:SaleService,
        @InjectRepository(SaleDetails)
        repo:Repository<SaleDetails>
    ) { super(repo) }
    
    findBySale(sale:Sale) {
        return this.repo.find({
            sale : { id : sale.id }
        })
    }

    async saveBySale(saleId:number, details:SaleDetails) {

        let sale = await this.saleService.findById(saleId)
        details.sale = sale
        if(details.id) {
            for(let index in sale.details) {
                let  data = sale.details[index]
                if(data.id == details.id) {
                    sale.details[index] = details
                }
            }            
        } else {
            sale.details.push(details)
        }
        sale.subTotal = sale.details.map(s => s.quantity * s.unitPrice).reduce((a, b) => a + b)
        sale.tax = details.sale.subTotal / 100 * 5
        await this.saleService.save(details.sale)

        return details
    }
}
```

အထက်မှာ ဖေါ်ပြထားတဲ့ အတိုင်း SaleDetails ထဲမှာ Sale ကို Exclude လုပ်ထားတဲ့ အတွက် Sale ID နဲ့ SaleDetails Object ကို အသုံးပြုပြီး Save တဲ့ Method နဲ့ Sale ID ကိုပေးပြီး SaleDetails တွေကို ရှာမည့် Method တို့ကို ဖြည့်စွက်ရေးသားထားပါတယ်။


## Controllers

Sale Resources တွေအတွက် API တွေဖြစ်ကြပါတယ်။ MasterModule ထဲမှာတော့ SaleController နဲ့ SaleDetailsController တို့ပါဝင်ကြပါတယ်။

### Sale Controller

Sale Resource တွေကို အသုံးပြုနိုင်တဲ့ End Point API ဖြစ်ပါတယ်။

[SaleController](controller/sale.controller.ts)
```typescript
@Controller("sales")
@UseInterceptors(new ExcludeInterceptor())
export class SaleController extends BaseControllerMutable<Sale> {

    constructor(
        service:SaleService
    ) { super(service, '/sales') }
}
```

SaleController ဟာ BaseControllerMutable ကို Extends လုပ်ထားတဲ့ အတွက် BaseControllerMutable မှာရှိတဲ့ Operation တွေကို အမွေဆက်ခံရရှိပါတယ်။ 

| Mapping Path | Request Method | Argument | Description |
|  ---  | --- | --- | --- |
| /sales | GET | | Sale တွေအားလုံးကို ပြန်ပေးနိုင်မှာ ဖြစ်တယ် |
| /sales/:id | GET | id from path | Sale Object ကို ID နဲ့ ရှာပေးနိုင်မှာ ဖြစ်တယ် |
| /sales | POST | Sale From Body | Sale တစ်ခုကို Create လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |
| /sales | PUT | Sale From Body | Sale တစ်ခုကို Update လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |


### Sale Details Controller

Sale Details Resource တွေကို အသုံးပြုနိုင်တဲ့ End Point API ဖြစ်ပါတယ်။

[SaleDetailsController](controller/saledetails.controller.ts)
```typescript
@Controller("orders")
export class SaleDetailsController extends BaseController<SaleDetails> {
 
    constructor(
        private readonly detailsService:SaleDetailsService
    ) {
        super(detailsService)
    }

    @Post('sale/:id')
    async createBySale(@Param('id') saleId:number, @Body() t:SaleDetails, @Res() res:any) {
        let savedResult = await this.detailsService.saveBySale(saleId, t)
        res.redirect(`/orders/${savedResult.id}`)
    }

    @Put('sale/:id')
    async updateBySale(@Param('id') saleId:number, @Body() t:SaleDetails, @Res() res:any) {
        let savedResult = await this.detailsService.saveBySale(saleId, t)
        res.redirect(`/orders/${savedResult.id}`)
    }     
}
```

SaleController ဟာ BaseController ကို Extends လုပ်ထားတဲ့ အတွက် BaseController မှာရှိတဲ့ Operation တွေကို အမွေဆက်ခံရရှိပါတယ်။ 

တဖန် Sale ID ကို ယူပြီး Insert နဲ့ Update လုပ်နိုင်ဖို့အတွက် Post နဲ့ Put Method နှစ်ခုကို ကိုယ်ပိုင် Method တွေအနေနဲ့ ရေးသားထားပါတယ်။

| Mapping Path | Request Method | Argument | Description |
|  ---  | --- | --- | --- |
| /orders | GET | | SaleDetails တွေအားလုံးကို ပြန်ပေးနိုင်မှာ ဖြစ်တယ် |
| /orders/:id | GET | id from path | SaleDetails Object ကို ID နဲ့ ရှာပေးနိုင်မှာ ဖြစ်တယ် |
| /orders/sale/:id | POST | Sale Id from path and SaleDetails From Body | SaleDetails တစ်ခုကို Create လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |
| /orders/sale/:id | PUT | Sale Id from path and SaleDetails From Body | SaleDetails တစ်ခုကို Update လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |