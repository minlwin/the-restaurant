# Restaurant API
Backend အပိုင်းမှာ နေရာကျပါတယ်။ TypeScript Language နဲ့ Nest JS Framework ကို အသုံးပြုထားပါတယ်။ 

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
