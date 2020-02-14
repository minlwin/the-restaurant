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