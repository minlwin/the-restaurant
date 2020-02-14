# 3. Sale Module

Restaurant ရဲ့ အရောင်းစာရင်းကို Manage လုပ်ပေးမည့် Module ဖြစ်ပါတယ်။ ပါဝင်မည့် Entity တွေကတော့ Sale နဲ့ SaleDetails တို့ပဲဖြစ်ပါတယ်။


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
