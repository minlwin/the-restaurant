# Restaurant API

Restaurant Management System ရဲ့ Backend အပိုင်းမှာ နေရာကျပါတယ်။ TypeScript Language နဲ့ Nest JS Framework ကို အသုံးပြုထားပါတယ်။ 

ကောင်တာအသုံးပြုသူတွေကော၊ စာပွဲထိုးလေးတွေကော၊ ဆိုင်ပိုင်ရှင်ပါ မတူညီတဲ့ Platform တွေကနေ ဒီ System ကို အသုံးပြုကြပါမယ်။ ဘယ်လို Platform ကပဲဖြစ်ဖြစ် ဘယ်လို Language ပဲဖြစ်ဖြစ် တူညီတဲ့ Resource တွေကို Support လုပ်ပေးဖို့အတွက် ဒီ Project မှာ တာဝန်ရှိပါတယ်။ 

## Modular Approach

Nest JS ဟာ Type Script ကို အသုံးပြုထားတဲ့ အတွက် ရည်ရွယ်ချက်အပေါ်မူတည်ပြီး Module တွေကို ခွဲခြားရေးသားနိုင်ပါတယ်။
လက်ရှိ System မှာတော့ အောက်ပါအတိုင်း Module တွေကိို ခွဲခြားရေးသားထားပါတယ်။

1. [Employee Module](1.Employee.md)
2. [Master Module](2.Master.md)
3. [Sale Module](3.Sale.md)


## Data Structure
Database အနေနဲ့ကတော့ Open Source Database ဖြစ်တဲ့ MariaDB ကို အသုံးပြုသွားပါမယ်။ Nest JS Framework မှာ Relational Database ကို အသုံးပြုဖို့အတွက် TypeORM Framework ကို အသုံးပြုသွားမှာဖြစ်ပါတယ်။

TypeORM ဆိုတာ Object Relation Mapping (ORM) ကို အခြေခံထားတဲ့ Framework တစ်ခုဖြစ်ပါတယ်။ Nest JS မှာ TypeORM ကို အသုံးပြုနိုင်အောင် ပြင်ဆင်ပေးထားပါတယ်။

ဒီီ System မှာ အသုံးပြုမည့် Database Structure (ERD) ကတော့ အောက်ပါအတိုင်းဖြစ်ပါတယ်။ 

![ERD](/images/RestaurantERD.png)

## Architecture
