# 1. Employee Module

Restaurant Application ကို အသုံးပြုမည့် ဝန်ထမ်းတွေကို Manage လုပ်တာတွေကို ဆောင်ရွက်ပေးမည့် Module ဖြစ်ပါတယ်။

```typescript
@Module({
    imports: [
        TypeOrmModule.forFeature([Employee])
    ],
    controllers: [
        EmployeeController, ],
    providers: [
        EmployeeService, ],
})
export class EmployeeModule {}
```
EmployeeModule ထဲမှာတော့ TypeOrmModule ကနေ Employee အတွက် Repository ကို Inject လုပ်ဖို့အတွက် TypeOrmModule.forFeature([Employee]) ဆိုပြီး Import လုပ်ထားပါတယ်။ တဖန် Controller အနေနဲ့ EmployeeController နဲ့ Inject လုပ်မည့် Provider Class အနေနဲ့ EmployeeService ကို အသုံးပြုမယ်လို့ ဖေါ်ပြတားပါတယ်။

## Entity

EmployeeModule ထဲမှာအသုံးပြုမည့် Entity ကတော့ ဝန်ထမ်းတွေကို ကိုယ်စားပြုမည့် Entity Class တစ်ခုရှိပါတယ်။

```typescript
@Entity()
export class Employee implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number
    @Column()
    name:string
    @Column()
    role:string
    @Column()
    email:string
    @Column()
    password:string
    @Column()
    phone:string
    @CreateDateColumn()
    @Transform(d => moment(d).format('YYYY-MM-DD HH:mm:ss'))
    creation:Date
    @UpdateDateColumn()
    @Transform(d => moment(d).format('YYYY-MM-DD HH:mm:ss'))
    modification:Date
}
```