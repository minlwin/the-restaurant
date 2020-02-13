# The Restaurant

Nest JS ကိုလေ့လာရင်း ဒီပရိုဂျက်ကို ရေးသားထားတာဖြစ်ပါတယ်။ Backend REST API နေရာမှာ Nest JS ကို အသုံးပြုထားပါတယ်။ 

Restaurant Management System တစ်ခုဖြစ်ပြီဆိုကထဲက အသုံးပြုမည့်သူတွေလဲ တူကြမှာ မဟုတ်ပါဘူး။ 
စာပွဲထိိုးလေးတွေလဲ သုံးပါမယ်။ ကောင်တာကလဲ သုံးပါမယ်။ ဆိုင်ရှင်ဖြစ်သူကလဲ သုံးပါမယ်။ Franchise ဆိုင်ဆိုလို့ကတော့ သုံးမည့်သူအမျိိုးအစားတွေ ထပ်ပြီးတိုးလာပါမယ်။ အသုံးပြုတဲ့သူအပေါ်မူတည်ပြီး သုံးလိုတဲ့ ရည်ရွယ်ချက်တွေ မတူနိုင်ပါဘူး။ ဒါ့ကြောင့် သုံးလိုတဲ့ ရည်ရွယ်ချက်နဲ့ ကိိုက်ညီအောင် Platform တွေကို ပြောင်းပြီးစဉ်းစားဖို့ လိုအပ်လာပါတယ်။

Platform တွေမတူတဲ့ အခါ Platform အလိုက်အားသာတဲ့ Language တွေ ပြီးတော့ Framework တွေရှိလာပါတယ်။ အားလုံးကို One Language, One Framework နဲ့ စဉ်းစားမည့်အစား အားသာတာတွေကိို အတူတူသုံးးသွားကြရအောင်ဆိုတဲ့ Approach နဲ့ ဒီ Project ကို ရေးသားသွားမှာ ဖြစ်ပါတယ်။

ဒီ System မှာ အောက်ပါ အစိတ်အပိုင်းတွေပါဝင်မှာ ဖြစ်ပါတယ်။

1. Restaurant REST API
2. Restaurant Client
3. Restaurant GUI
4. Restaurant Android
5. Restaurant Angular

ဒီပရိုဂျက်နဲ့ မတူညီတဲ့ Platform မတူညီတဲ့ Language တွေကို အသုံးပြုထားရင်လဲ REST အာကီတက်ချာကို အသုံးပြုပြီး System တစ်ခုကို ရေးသားနိုင်တယ်ဆိုတာကို လေ့လာသွားချင်ပါတယ်။

## [Restaurant REST API](https://github.com/minlwin/the-restaurant/blob/master/restaurant-api/README.md)

Backend အပိုင်းမှာ နေရာကျပါတယ်။ TypeScript Language နဲ့ Nest JS Framework ကို အသုံးပြုထားပါတယ်။ 
Backend အပိုင်းဖြစ်တဲ့ အတွက်ဒီနေရာမှာ Resource တွေကို ဘယ်လို Manage လုပ်မလဲ ဆိုတာကို လေ့လာသွားကြမှာဖြစ်ပါတယ်။ NestJS ရဲ့ Controller တွေ၊ Security တွေ၊ Database အသုံးပြုပုံတွေနဲ့ Business Logic တွေကိုဘယ်လို ထားမလဲ ဆိုတာကို ဒီပရိုဂျက်နဲ့ လေ့လာသွားမှာ ဖြစ်ပါတယ်။

## [Restaurant Client](https://github.com/minlwin/the-restaurant/tree/master/restaurant-client)

ဒါကတေော့ API ကိုအသုံးပြုဖို့အတွက် Client Library Project တစ်ခုဖြစ်ပါတယ်။ GUI Application မှာက Java ကို အသုံးပြုပြီး Android Application မှာတော့ Kotlin ကို အသုံးပြုပါမယ်။ Kotlin လို့ပြောပေမဲ့လဲ JVM ပေါ်မှာ အလုပ်လုပ်တဲ့ Kotlin ကို အသုံးပြုသွားမှာဖြစ်တဲ့ အတွက် Java နဲ့ ရေးသားထားတဲ့ Library ကို အသုံးပြုနိုင်မှာ ဖြစ်ပါတယ်။

ဒါကြောင့် ဒီပရိုဂျက်ကို Maven Base Library Project အနေနဲ့ ရေးသားသွားပြီး GUI Application မှာကော Android Application မှာပါ Dependency အနေနဲ့ အသုံးပြုသွားမှာ ဖြစ်ပါတယ်။

ဒီ ပရိုဂျက်မှာ အသုံးပြုသွားမှာကတော့ Retrofit 2 ပဲ ဖြစ်ပါတယ်။ Library Project ဖြစ်တဲ့ အတွက် သူ့ရဲ့ Public API တွေကို မှန်ကန်မှုရှိအောင် JUnit 5 ကို အသုံးပြုပြီး Unit Test ကို သေသေချာချာလုပ်ထားဖို့ လိုအပ်ပါတယ်။

## Restaurant GUI

ဒီပရိုဂျက်ကတော့ ကောင်တာမှာနေတဲ့ သူတွေ အသုံးပြုမည့် Desktop Application တစ်ခုဖြစ်ပါတယ်။ 
Java FX ကို အသုံးပြုပြီး ရေးသားသွားမှာ ဖြစ်ပါတယ်။ စာပွဲထိုးလေးတွေက Android ကို အသုံးပြုပြီး လုပ်ဆောင်နေတာတွေကိုလဲဲ လုပ်ဆောင်နိုင်ဖို့ လိုအပ်သလို၊ ပိုက်ဆံရှင်းတာတို့၊ ပြီးတော့ အချက်အလက်တွေကိို လိုအပ်လာရင်ပြင်ပေးနိုင်ဖို့ လိုအပ်ပါတယ်။
