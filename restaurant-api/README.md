# Restaurant API

Restaurant Management System ရဲ့ Backend အပိုင်းမှာ နေရာကျပါတယ်။ TypeScript Language နဲ့ Nest JS Framework ကို အသုံးပြုထားပါတယ်။ 

ကောင်တာအသုံးပြုသူတွေကော၊ စာပွဲထိုးလေးတွေကော၊ ဆိုင်ပိုင်ရှင်ပါ မတူညီတဲ့ Platform တွေကနေ ဒီ System ကို အသုံးပြုကြပါမယ်။ ဘယ်လို Platform ကပဲဖြစ်ဖြစ် ဘယ်လို Language ပဲဖြစ်ဖြစ် တူညီတဲ့ Resource တွေကို Support လုပ်ပေးဖို့အတွက် ဒီ Project မှာ တာဝန်ရှိပါတယ်။ 

## Architecture

![Architecture](/images/arch.png)

### Persistance Layer

Entity တွေဟာ Relational Database ထဲက  Table တွေနဲ့ Map လုပ်ထားတဲ့ Object တွေဖြစ်ပါတယ်။ တဖန် Nest JS Type Orm ကို အသုံးပြုမယ်ဆိုရင် Database Operation တွေကို အလွယ်တကူအသုံးပြုနိုင်အောင် Repository Object တွေကို လိုအပ်သလို Inject လုပ်ပြီး အသုံးပြုနိုင်မှာ ဖြစ်ပါတယ်။ 

Entity တွေဟာ Table တွေကို ကိုယ်စားပြုတဲ့ Object တွေဖြစ်ကြပြီး Repository တွေက Database Operation တွေကို ဆောင်ရွက်ပေးနိုင်တဲ့ Object တွေ ဖြစ်ကြပါတယ်။

### Business Logic Layer

အထက်ပါ Diagram ထဲမှာပါဝင်တဲ့ Services တွေဟာ Business Logic Layer မှာ တာဝန်ကျပါတယ်။ ပြန်ပြီး အသုံးပြုနိုင်တဲ့ Business Logic တွေကို Wrap လုပ်ပေးမယ်၊ ပြီးတော့ Repository တွေကို အသုံးပြုပြီး Business Logic အလိုက် Prensation Layer အတွက် Data တွေကို Support လုပ်ပေးမယ်။ Presentation Layer ကနေ ရလာတဲ့ User Input Data တွေကိုနဲ့ Repository ကို အသုံးပြုပြီး Persistance Data တွေကို Update လုပ်ပေးပါမယ်။

Business Logic ကို ရေးသားကြတဲ့နေရာမှာ တူညီတဲ့ အပိုင်းတွေရှိနိုင်ပါတယ်။ Resource တွေကို ID နဲ့ရှာတာတို့။ Resource တွေအားလုံးကို ရှာတာတို့၊ Resource တွေကို Insert, Update, Delete လုပ်တာတို့ တူညီတဲ့ လုပ်ဆောင်မှုတွေရှိနေနိုင်ပါတယ်။ အဲ့ဒီအတွက် Generic Base Class လေးတွေကို ရေးသားထားပါတယ်။ Service Class တွေက သက်ဆိုင်ရာ Base Class ကို Extend လုပ်ထားပြီး Specific Logic တွေကိုပဲ မိမိအထဲမှာ ရေးသားသွားမှာ ဖြစ်ပါတယ်။

[IdEnable](src/common/id.enable.ts)
```.typescript
export interface IdEnable extends ObjectLiteral{
    id:number
}
```

[BaseService](src/common/base.controller.ts)
```typescript
export class BaseService<T extends IdEnable> {

    constructor(protected readonly repo:Repository<T>) {}

    findAll() {
        return this.repo.find()
    }

    findById(id:number) {
        return this.repo.findOne(id)
    }
}
```
[BaseServiceMutable](src/common/base.controller.mutable.ts)
```typescript
export class BaseServiceMutable<T extends IdEnable> extends BaseService<T> {

    constructor(repo:Repository<T>) {
        super(repo)
    }

    save(t:T) {
        return this.repo.save(t)
    }
}
```

### Presentation Layer 

Request တွေကို Handle လုပ်ပေးနေတဲ့ အပိုင်းဖြစ်ပြီး၊ Nest JS Framework မှာတော့ Controller တွေက လုပ်ဆောင်ပေးရပါတယ်။ လာသမျှ Request တွေအားလုံးဟာ Nest Container ရဲ့ Routing Mechanism က လက်ခံယူပြီးတော့ Request URL နဲ့ Request Method တွေနဲ့ Map လုပ်ထားတဲ့ Controller Method တွေကို Invoke လုပ်ပြီး ရလာတဲ့ Result တွေကနေနဲ့ Client ဆီကို Response ပြန်ပေးမှာ ဖြစ်ပါတယ်။
![Request Mapping](/images/type2mvc.png)

Controller တွေမှာလဲ တူညီတဲ့ လုပ်ဆောင်ချက်တွေရှိတဲ့ အတွက် Generics ကို အသုံးပြုပြီး Base Controller လေးကို ရေးသားထားပါတယ်။

[Base Controller](src/common/base.controller.ts)
```typescript
export class BaseController<T extends IdEnable> {

    constructor(protected readonly service:BaseService<T>) {}

    @Get()
    @UseInterceptors(ClassSerializerInterceptor)
    index() {
        return this.service.findAll()
    }

    @Get(':id')
    @UseInterceptors(ClassSerializerInterceptor)
    findById(@Param('id') id:number) {
        return this.service.findById(id)
    }

}
```

| Mapping Path | Request Method | Argument | Description |
|  ---  | --- | --- | --- |
| /root-path | GET | | Resource တွေအားလုံးကို ပြန်ပေးနိုင်မှာ ဖြစ်တယ် |
| /root-path/:id | GET | id from path | Resource Object ကို ID နဲ့ ရှာပေးနိုင်မှာ ဖြစ်တယ် |

>Root Path နေရာမှာ Extends လုပ်လာတဲ့ Controller Class ရဲ့ Path က အစားထိုးဝင်ရောက်မှာ ဖြစ်တယ်

[Base Mutable Controller](src/common/base.controller.mutable.ts)
```typescript
export class BaseControllerMutable<T extends IdEnable> extends BaseController<T> {

    constructor(private readonly mutableService:BaseServiceMutable<T>, 
        private readonly path:string) {
            super(mutableService)
    }

    @Post()
    async create(@Body() t:T, @Res() res:any) {
        let savedResult = await this.mutableService.save(t)
        res.redirect(`${this.path}/${savedResult.id}`)
    }

    @Put()
    async update(@Body() t:T, @Res() res:any) {
        let savedResult = await this.mutableService.save(t)
        res.redirect(`${this.path}/${savedResult.id}`)
    }
}
```
BaseControllerMutable ဟာ BaseController ကို Extends လုပ်ထားတာဖြစ်တဲ့ အတွက် BaseController မှာ သုံးလို့ရတဲ့ Method တွေကိုလဲ BaseControllerMutable မှာ သုံးလို့ရမှာဖြစ်တယ်။

| Mapping Path | Request Method | Argument | Description |
|  ---  | --- | --- | --- |
| /root-path | GET | | Resource တွေအားလုံးကို ပြန်ပေးနိုင်မှာ ဖြစ်တယ် |
| /root-path/:id | GET | id from path | Resource Object ကို ID နဲ့ ရှာပေးနိုင်မှာ ဖြစ်တယ် |
| /root-path | POST | Resource From Body | Resource တစ်ခုကို Create လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |
| /root-path | PUT | Resource From Body | Resource တစ်ခုကို Update လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |

>Root Path နေရာမှာ Extends လုပ်လာတဲ့ Controller Class ရဲ့ Path က အစားထိုးဝင်ရောက်မှာ ဖြစ်တယ်

## Data Structure

Database အနေနဲ့ကတော့ Open Source Database ဖြစ်တဲ့ MariaDB ကို အသုံးပြုသွားပါမယ်။ Nest JS Framework မှာ Relational Database ကို အသုံးပြုဖို့အတွက် TypeORM Framework ကို အသုံးပြုသွားမှာဖြစ်ပါတယ်။

TypeORM ဆိုတာ Object Relation Mapping (ORM) ကို အခြေခံထားတဲ့ Framework တစ်ခုဖြစ်ပါတယ်။ Nest JS မှာ TypeORM ကို အသုံးပြုနိုင်အောင် ပြင်ဆင်ပေးထားပါတယ်။

ဒီီ System မှာ အသုံးပြုမည့် Database Structure (ERD) ကတော့ အောက်ပါအတိုင်းဖြစ်ပါတယ်။ 

![ERD](/images/RestaurantERD.png)

## Modules

Nest JS ဟာ Type Script ကို အသုံးပြုထားတဲ့ အတွက် ရည်ရွယ်ချက်အပေါ်မူတည်ပြီး Module တွေကို ခွဲခြားရေးသားနိုင်ပါတယ်။
လက်ရှိ System မှာတော့ အောက်ပါအတိုင်း Module တွေကိို ခွဲခြားရေးသားထားပါတယ်။

1. [Employee Module](src/employee)
>Restaurant Application ကို အသုံးပြုမည့် ဝန်ထမ်းတွေကို Manage လုပ်တာတွေကို ဆောင်ရွက်ပေးမည့် Module ဖြစ်ပါတယ်။

2. [Master Module](src/master)
>Restaurant Application အတွက် မဖြစ်မနေပါဝင်ရမည့် Master Data တွေကို Manage လုပ်ပေးနိုင်တဲ့ Module ဖြစ်ပါတယ်။ Tables (စားပွဲခုံ) တွေ၊ Category (ဟင်းပွဲအမျိုးအစား) တွေနဲ့ Menu (ဟင်းပွဲ) တွေအတွက် Entity, Service, Controller တွေပါဝင်ပါမယ်။ 

3. [Sale Module](src/sales)
>Restaurant Application အတွက် Business Transaction ကို Manage လုပ်ပေးနိုင်တဲ့ Module ဖြစ်ပါတယ်။ Customer တွေရဲ့ မှာထားတဲ့ ဘောင်ချာတစ်ခုကို ကိုယ်စားပြုတဲ့ Sale နဲ့ အော်ဒါတွေကို ကိုယ်စားပြုတဲ့ Sale Details တွေအတွက် Entity, Service, Controller တွေပါဝင်ပါမယ်။ 

