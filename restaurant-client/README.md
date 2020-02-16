# Restaurant Client

Restaurant API ကိုအသုံးပြုမည့် Client Project ဖြစ်ပါတယ်။ Java Language ကိုအသုံးပြုထားပြီး Library အနေနဲ့ Retrofit 2 ကိုအသုံးပြုထားပါတယ်။ Restaurant GUI နဲ့ Restaurant Android Application တွေကနေ Restaurant Client ကိုအသုံးပြုပြီး API ရဲ့ Resource တွေကို အသုံးပြုမှာ ဖြစ်ပါတယ်။

![Restaurant Client Architecture](/images/client-arch.png)

အခြေခံအားဖြင့် Restaurant Client Application မှာ အစိတ်ပိုင်း ၃ မျိုုးပါဝင်ပါတယ်။ 
1. Data Transfer Object
2. Client API Interfaces
3. Client Factory Class

## Data Transfer Object

API ရဲ့ Resource တွေကို ကိုယ်စားပြုတဲ့ POJO Class တွေဖြစ်ကြတယ်။ ပါဝင်တဲ့ Class တွေကတော့ အောက်ပါအတိုင်းဖြစ်ပါတယ်။

![DTO Class Diagram](/images/dto-class-relation.png)
